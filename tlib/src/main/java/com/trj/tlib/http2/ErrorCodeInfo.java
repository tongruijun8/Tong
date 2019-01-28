package com.trj.tlib.http2;

/**
 * 根据项目的状态码，重写此类
 */
@Deprecated
public class ErrorCodeInfo {
	
	/**
	 * 用户名不存在
	 */
	public static final int user_no = 1001; 
	public static final String user_no_msg = "此用户不存在"; 
	/** 密码错误 */
	public static final int psw_error = 1002; 
	public static final String psw_error_msg = "密码错误，请确认"; 
	
	public static final int run_error = 500; 
	public static final int request_error = 401; 
	public static final int token_error = 403; 
	
	public static final int post_error = 404; 
	public static final String post_error_msg = "请求异常，请确认请求路径是否正确"; 
}
