package com.hc.commons;

/**
 * @Author:杨鑫虎
 * @Date:2019/4/26
 * @Description:   验证工具类
 * @Version:1.0
 */

public class Regx {
    private Regx(){}
    // 密码
    private static final String PWD_REGX = "[A-Za-z\\d.!@#$%^&*]{6,18}";
    // 姓名
    public static final String NAME_REGX = "^(([\\u4e00-\\u9fa5]{2,8}))$";
    // 身份证号
    public static final String ID_CARD_REGX = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    // 邮箱
    private static final String EMAIL_REGX = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
    // 手机号码
    private static final String TEL_NUM_REGX = "[1][34578]\\d{9}";
    public static final String VERIFY_TEL_REGX = "[0-9]{6}";
    public static final String VERIFY_EMAIL_REGX = "[a-zA-Z0-9]{6}";
    public static final int REGX_TYPE_RESTER = 1;
    public static final int REGX_TYPE_ALTER = 2;
    public static final int REGX_TYPE_ALTERINOF = 3;

    /**
     * 验证手机号格式
     * @param telphoneNumber	手机号
     * @return	正确返回true / 错误返回false
     */
    public static final boolean regxTelphone(final String telphoneNumber){
        System.out.println("手机号："+telphoneNumber);
        if(telphoneNumber == null){
            return false;
        }
        return telphoneNumber.matches(TEL_NUM_REGX);
    }

    /**
     * 验证密码格式
     * @param password	密码
     * @return	正确返回true / 错误返回false
     */
    public static final boolean regxPassword(final String password){
        if(password == null){
            return false;
        }
        return password.matches(PWD_REGX);
    }

    /**
     * 验证两个验证码是否正确
     * @param verifyCode1	验证码1
     * @param verifyCode2	 验证码2
     * @return	正确返回true / 错误返回false
     */
    public static final boolean regxVerifCode(final String verifyCode1,final String verifyCode2){
        if(verifyCode1 == null){
            return false;
        }
        if(verifyCode2 == null){
            return false;
        }
        if(!verifyCode1.equals(verifyCode2)){
            return false;
        }
        return true;
    }

    /**
     * 验证邮箱格式
     * @param email
     * @return
     */
    public static final boolean regxEmail(String email) {
        if(email == null){
            return false;
        }
        return email.matches(EMAIL_REGX);
    }


    /**
     * 验证身份证
     * @param idCard 身份证
     * @return
     */
    public static final boolean regxIdCard(String idCard) {
        if(idCard == null){
            return false;
        }
        return idCard.matches(ID_CARD_REGX);
    }
}
