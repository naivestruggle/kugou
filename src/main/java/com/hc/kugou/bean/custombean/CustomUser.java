package com.hc.kugou.bean.custombean;

import com.hc.commons.StringUtils;
import com.hc.kugou.bean.User;
import lombok.Data;
import sun.security.util.Password;

import java.io.Serializable;

/**
 * @Author:
 * @Date:2019/4/7
 * @Description:com.hc.kugou.bean.custombean    用户扩展类
 * @Version:1.0
 */
@Data
public class CustomUser extends User implements Serializable {
    /**
     * 新密码
     */
    private String userPassword1;
    /**
     * 确认密码
     */
    private String userPassword2;
    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 密文手机号
     */
    private String tel;
    public String getTel(){
        String str = this.getUserTel();
        if(str == null){
            return null;
        }
        return str.substring(0,3) + "****" + str.substring(7);
    }

    /**
     * 省
     */
    private String province;
    public String getProvince(){
        String str = this.getUserAddr();
        if(str != null && !"".equals(str)){
            return this.getUserAddr().split("-")[0];
        }
        return null;
    }

    /**
     * 市
     */
    private String city;
    public String getCity(){
        String str = this.getUserAddr();
        if(str != null && !"".equals(str)){
            return this.getUserAddr().split("-")[1];        }
        return null;
    }

    /**
     * 县
     */
    private String districts;
    public String getDistricts(){
        String str = this.getUserAddr();
        if(str != null && !"".equals(str)){
            return this.getUserAddr().split("-")[2];        }
        return null;
    }


}
