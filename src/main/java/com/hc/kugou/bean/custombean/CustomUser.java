package com.hc.kugou.bean.custombean;

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
    private String userPassword2;
    private String verifyCode;

    @Override
    public String toString() {
        System.out.println(super.toString());
        return "CustomUser{" +
                "userPassword2='" + userPassword2 + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
