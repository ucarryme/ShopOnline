package com.cuit.portal.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhoubin 
 * @since 1.0.0
 */
public class Users implements Serializable {
    /**
     * 表id
     */
    private Integer userId;

    /**
     * 邮件
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 0 保密 1 男 2 女
     */
    private Byte sex;

    /**
     * 生日
     */
    private Integer birthday;

    /**
     * 用户金额
     */
    private BigDecimal userMoney;

    /**
     * 冻结金额
     */
    private BigDecimal frozenMoney;

    /**
     * 累积分佣金额
     */
    private BigDecimal distributMoney;

    /**
     * 消费积分
     */
    private Integer payPoints;

    /**
     * 默认收货地址
     */
    private Integer addressId;

    /**
     * 注册时间
     */
    private Integer regTime;

    /**
     * 最后登录时间
     */
    private Integer lastLogin;

    /**
     * 最后登录ip
     */
    private String lastIp;

    /**
     * QQ
     */
    private String qq;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 是否验证手机
     */
    private Byte mobileValidated;

    /**
     * 第三方来源 wx weibo alipay
     */
    private String oauth;

    /**
     * 第三方唯一标示
     */
    private String openid;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 省份
     */
    private Integer province;

    /**
     * 市区
     */
    private Integer city;

    /**
     * 县
     */
    private Integer district;

    /**
     * 是否验证电子邮箱
     */
    private Byte emailValidated;

    /**
     * 第三方返回昵称
     */
    private String nickname;

    /**
     * 会员等级
     */
    private Byte level;

    /**
     * 会员折扣，默认1不享受
     */
    private BigDecimal discount;

    /**
     * 消费累计额度
     */
    private BigDecimal totalAmount;

    /**
     * 是否被锁定冻结
     */
    private Byte isLock;

    /**
     * 是否为分销商 0 否 1 是
     */
    private Byte isDistribut;

    /**
     * 第一个上级
     */
    private Integer firstLeader;

    /**
     * 第二个上级
     */
    private Integer secondLeader;

    /**
     * 第三个上级
     */
    private Integer thirdLeader;

    /**
     * 用于app 授权类似于session_id
     */
    private String token;

    /**
     * t_users
     */
    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public BigDecimal getDistributMoney() {
        return distributMoney;
    }

    public void setDistributMoney(BigDecimal distributMoney) {
        this.distributMoney = distributMoney;
    }

    public Integer getPayPoints() {
        return payPoints;
    }

    public void setPayPoints(Integer payPoints) {
        this.payPoints = payPoints;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getRegTime() {
        return regTime;
    }

    public void setRegTime(Integer regTime) {
        this.regTime = regTime;
    }

    public Integer getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Integer lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Byte getMobileValidated() {
        return mobileValidated;
    }

    public void setMobileValidated(Byte mobileValidated) {
        this.mobileValidated = mobileValidated;
    }

    public String getOauth() {
        return oauth;
    }

    public void setOauth(String oauth) {
        this.oauth = oauth == null ? null : oauth.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Byte getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(Byte emailValidated) {
        this.emailValidated = emailValidated;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Byte getIsLock() {
        return isLock;
    }

    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
    }

    public Byte getIsDistribut() {
        return isDistribut;
    }

    public void setIsDistribut(Byte isDistribut) {
        this.isDistribut = isDistribut;
    }

    public Integer getFirstLeader() {
        return firstLeader;
    }

    public void setFirstLeader(Integer firstLeader) {
        this.firstLeader = firstLeader;
    }

    public Integer getSecondLeader() {
        return secondLeader;
    }

    public void setSecondLeader(Integer secondLeader) {
        this.secondLeader = secondLeader;
    }

    public Integer getThirdLeader() {
        return thirdLeader;
    }

    public void setThirdLeader(Integer thirdLeader) {
        this.thirdLeader = thirdLeader;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", userMoney=").append(userMoney);
        sb.append(", frozenMoney=").append(frozenMoney);
        sb.append(", distributMoney=").append(distributMoney);
        sb.append(", payPoints=").append(payPoints);
        sb.append(", addressId=").append(addressId);
        sb.append(", regTime=").append(regTime);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append(", lastIp=").append(lastIp);
        sb.append(", qq=").append(qq);
        sb.append(", mobile=").append(mobile);
        sb.append(", mobileValidated=").append(mobileValidated);
        sb.append(", oauth=").append(oauth);
        sb.append(", openid=").append(openid);
        sb.append(", headPic=").append(headPic);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", district=").append(district);
        sb.append(", emailValidated=").append(emailValidated);
        sb.append(", nickname=").append(nickname);
        sb.append(", level=").append(level);
        sb.append(", discount=").append(discount);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", isLock=").append(isLock);
        sb.append(", isDistribut=").append(isDistribut);
        sb.append(", firstLeader=").append(firstLeader);
        sb.append(", secondLeader=").append(secondLeader);
        sb.append(", thirdLeader=").append(thirdLeader);
        sb.append(", token=").append(token);
        sb.append("]");
        return sb.toString();
    }
}