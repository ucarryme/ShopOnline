package com.cuit.managerment.service.implet;

import com.cuit.common.result.FileResult;
import com.cuit.managerment.service.UploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.InputStream;


@Service
public class UploadServiceImple implements UploadService {
    @Override
    public FileResult upload(InputStream inputStream, String fileName) {
        FileResult fileResult = new FileResult();
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "z3SsvlYyolMz1e67lcv5PFRoT7QtEfO1LpkbjrEj";
        String secretKey = "3pqTW5qk3mRRIPnEyGQi4EhEAfaNiUy0OgKxq08i";
        String bucket = "shoponline-pjp";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;

        try {

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                if(response.statusCode == 200){
                    fileResult.setSuccess("success");
                    fileResult.setMessage("上传图片成功");
                    fileResult.setFileUrl("http://app2.merborn.fun/"+fileName);
                    return fileResult;
                }else {
                    fileResult.setError("error");
                    fileResult.setMessage("上传图片失败");
                }
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                fileResult.setError("error");
                fileResult.setMessage("上传图片失败");
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return fileResult;
    }
}
