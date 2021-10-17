package com.cuit.managerment.service;



import com.cuit.common.result.FileResult;

import java.io.InputStream;

public interface UploadService {
    /**
     * 上传服务
     * @param inputStream
     * @param fileName
     * @return
     */
    FileResult upload(InputStream inputStream, String fileName);
}
