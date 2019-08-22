package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    //用户名
    private String username;
    //密码值
    private String password;
    //组织机构代码
    private String orgCode;
    //验证码
    private String verifyCode;
    //图片验证码
    private String imgCode;
    //用户类型
    private Integer type;
    //用户状态
    private Integer status;



    /**
     * userInfo信息
     */
    //真实姓名
    private String realName;
    //身份证号码
    private String idCardNo;
    //身份证正面图片id
    private String cardImg;
    //身份证背面图片id
    private String cardBackImg;
    //手持身份证图片id
    private String cardByHandImg;
    //头像id
    private String headImg;

    /**
     * vehicle信息
     */
    //车牌
    private String vehicleNo;
    //车辆颜色
    private String vehicleColor;
    //车辆类型
    private Integer vehicleType;
    //车辆长度
    private Integer vehicleLengthType;
    //车辆载重
    private Integer vehicleLoadCapacity;
    //满载重量
    private Integer vehicleLoadMax;
    //从业资格证
    private String qualificationLicense;
    //道路运输证
    private String transportLicense;
    //行驶证图片
    private String vehicleLicenceImg;
    //驾驶证图片
    private String drivingLicenceImg;
    //车辆照片
    private String vehicleImg;
    //司机手机号
    private String driverMobile;

    /**
     * company信息
     */
    private String companyName;

    private String locationCode;

    private String locationStr;

    private String locationDetail;

    private Integer licenseHave;

    private String socialCreditCode;

    private String socialCreditImg;

    private String companyRegisterTime;

    private String transportLicenseImg;

    private String signboardImg;

    private String businesscardImg;

    private String logoImg;

    private String remarks;


    //注册步骤
    private Integer step;


    //绑定角色
    //原始角色
    private List<Long> originalIds;
    //修改后的角色
    private List<Long> changedIds;
    //待新增的角色ID
    private List<Long> toAddIds;
    //待删除的角色ID
    private List<Long> toDelIds;

    private Date createTime;
    private Long createUserId;
    private Integer isDeleted;
}
