package com.auxiliarybus.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**  
*
* 类名称：CommonUtil  
* 类描述：http请求和接收
* @version       
*/
public class CommonUtil<T>{
	// 日志文件
	protected final static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	public static void main(String[] args) {
		String url = "http://www.jkxabus.com/mgt/order/orderMaster/list?orderStatus=1&isAsc=desc";
		String res = httpRequest(url,"POST","");
		System.out.println(res);
	}


	/**
	 * 发起https请求并获取结果
	 *  
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject (通过JSONObject.get(key)的方式获取json对象的属性值)
	 */  
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
	    String jsonObject = null;
	   
	    try {  
	        //创建SSLContext对象，并使用我们指定的信任管理器初始化(证书过滤)
	        TrustManager[] tm = { new MyX509TrustManager() }; 
	        //取得SSL的SSLContext实例  
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	        //初始化SSLContext
	        sslContext.init(null, tm, new java.security.SecureRandom());  
	        //从上述SSLContext对象中得到SSLSocketFactory对象  
	        SSLSocketFactory ssf = sslContext.getSocketFactory(); 
	        
	        URL url = new URL(requestUrl);  
	        HttpsURLConnection httpUrlConn=(HttpsURLConnection) url.openConnection();
	        httpUrlConn.setSSLSocketFactory(ssf);   
	       
	        httpUrlConn.setDoOutput(true);  
	        httpUrlConn.setDoInput(true);  
	        httpUrlConn.setUseCaches(false);  
	        //设置请求方式（GET/POST）  
	        httpUrlConn.setRequestMethod(requestMethod);
	        
	        /*if ("GET".equalsIgnoreCase(requestMethod))  
	            httpUrlConn.connect();   */
	        //当有数据需要提交时(当outputStr不为null时，向输出流写数据)
	        if (null != outputStr) {  
	            OutputStream outputStream = httpUrlConn.getOutputStream();  
	            // 注意编码格式，防止中文乱码  
	            outputStream.write(outputStr.getBytes("UTF-8"));  
	            outputStream.close();  
	        }   
	        
	        // 将返回的输入流转换成字符串  
	        InputStream inputStream = httpUrlConn.getInputStream();  
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	        String str = null; 
	        StringBuffer buffer=new StringBuffer();
	        while ((str = bufferedReader.readLine()) != null) {  
	            buffer.append(str);  
	        } 
	        
	        //释放资源  
	        bufferedReader.close();  
	        inputStreamReader.close();  
	        inputStream.close();  
	        inputStream = null;  
	        httpUrlConn.disconnect();
			jsonObject = buffer.toString();
	    } catch (ConnectException ce) {  
	        log.error("连接超时: {}",ce);  
	    } catch (Exception e) {  
	        log.error("https请求异常: {}", e);  
	    }  
	    return jsonObject;
	} 

	
	/**
	 * URL编码(utf-8)
	 * 
	 * @param source
	 * @return String
	 */
	public static String urlEncodeUTF8(String source) {
		String result=source;
		try {
			result=URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return String
	 */
	public static String getFileExt(String contentType) {
		String fileExt="";
		if ("image/jpeg".equals(contentType)) {
			fileExt=".jpg";
		}else if ("audio/mpeg".equals(contentType)) {
			fileExt=".mp3";
		}else if ("audio/amr".equals(contentType)) {
			fileExt=".amr";
		}else if ("video/mp4".equals(contentType)) {
			fileExt=".mp4";
		}else if ("video/mpeg4".equals(contentType)) {
			fileExt=".mp4";
		}
		return fileExt;
	}

}
