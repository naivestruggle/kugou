package com.hc.commons;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;


public class Code {
	/**
	 * 验证码类型
	 * 邮箱验证码
	 */
	public static final int VERIFY_CODE_TYPE_EMAIL = 1;

	/**
	 * 验证码类型
	 * 手机号验证码
	 */
	public static final int VERIFY_CODE_TYPE_TEL = 2;

	/**
	 * 验证码源
	 */
	private static String codes = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";

	/**
	 * 验证码过期时间，单位秒
	 */
	public static final int VERIFY_OUT_TIME = 600;

	/**
	 * 默认密码
	 */
	public static final String DEFAULT_PWD = "123456";

	/**
	 * 默认歌单名
	 */
	public static final String DEFAULT_MUSIC_LIST_NAME = "我喜欢的音乐";
	/**
	 * 默认歌单头像
	 */
	public static final String DEFAULT_MUSIC_HEAD_IMG = "/img/index/logo.png";

	/**
	 * 我喜欢的音乐 默认头像
	 */
	public static final String MY_FAVORITE_MUSIC = "/img/my_songsheet/default.jpg";

	/**
	 * 生成一个长度为len的验证码文本
	 * @param len 验证码长度
	 * @param type 验证码类型  邮箱验证码  手机验证码
	 * @return
	 */
	public static String createVerifyCode(int len,int type){
		StringBuilder sb = new StringBuilder();
		Random ra = new Random();
		switch(type){
		case 1:
			for(int i=0;i<len;i++){
				int index = ra.nextInt(codes.length());
				sb.append(codes.charAt(index));
			}
			return sb.toString();
		case 2:
			for(int i=0;i<len;i++){
				sb.append(ra.nextInt(10));
			}
			return sb.toString();
		}
		return null;
	}
	
	/**
	 * 生成唯一的字符串 
	 * @param strBegin	字符串前缀
	 * @param strEnd	字符串后缀
	 * @return
	 */
	public static String createUserName(String strBegin,String strEnd){
		String s = MD5.parseMD5(UUID.randomUUID().toString().replace("-", "")).substring(0, 10);
		char[] carr = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(char c : carr){
			sb.append((int)c);
		}
		return strBegin+"_"+sb.toString().substring(0,10)+strEnd;
	}

	public static String createUserAccount(){
		long time = System.currentTimeMillis();
		Random random = new Random();
		String account = (time + "").substring(6) + random.nextInt(10)+random.nextInt(10)+random.nextInt(10);
		return account;
	}

	
}
