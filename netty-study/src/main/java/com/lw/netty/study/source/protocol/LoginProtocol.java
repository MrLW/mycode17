package com.lw.netty.study.source.protocol;

/**
 * 
 * @author liwen
 *
 */
public class LoginProtocol {

	/**
	 * 请求登陆
	 */
	public static final int AREA_LOGINREQUEST = 0;
	/**
	 * 登陆响应
	 */
	public static final int AREA_LOGINRESPONSE = 1;
	/**
	 * 无效消息
	 */
	public static final int LOGIN_INVALIDMESSAGE = 0;
	/**
	 * 无效用户名
	 */
	public static final int LOGIN_INVALIDUSERNAME = 1;
	/**
	 * 密码错误
	 */
	public static final int LOGIN_INVALIDPASSWORD = 2;
	/**
	 * 登陆成功
	 */
	public static final int LOGIN_SUCCEED = 10;
}
