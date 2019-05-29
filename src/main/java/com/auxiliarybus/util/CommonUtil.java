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
* �����ƣ�CommonUtil  
* ��������http����ͽ���
* @version       
*/
public class CommonUtil<T>{
	// ��־�ļ�
	protected final static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	public static void main(String[] args) {
		String url = "http://www.jkxabus.com/mgt/order/orderMaster/list?orderStatus=1&isAsc=desc";
		String res = httpRequest(url,"POST","");
		System.out.println(res);
	}


	/**
	 * ����https���󲢻�ȡ���
	 *  
	 * @param requestUrl �����ַ
	 * @param requestMethod ����ʽ��GET��POST��
	 * @param outputStr �ύ������
	 * @return JSONObject (ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	 */  
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
	    String jsonObject = null;
	   
	    try {  
	        //����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��(֤�����)
	        TrustManager[] tm = { new MyX509TrustManager() }; 
	        //ȡ��SSL��SSLContextʵ��  
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	        //��ʼ��SSLContext
	        sslContext.init(null, tm, new java.security.SecureRandom());  
	        //������SSLContext�����еõ�SSLSocketFactory����  
	        SSLSocketFactory ssf = sslContext.getSocketFactory(); 
	        
	        URL url = new URL(requestUrl);  
	        HttpsURLConnection httpUrlConn=(HttpsURLConnection) url.openConnection();
	        httpUrlConn.setSSLSocketFactory(ssf);   
	       
	        httpUrlConn.setDoOutput(true);  
	        httpUrlConn.setDoInput(true);  
	        httpUrlConn.setUseCaches(false);  
	        //��������ʽ��GET/POST��  
	        httpUrlConn.setRequestMethod(requestMethod);
	        
	        /*if ("GET".equalsIgnoreCase(requestMethod))  
	            httpUrlConn.connect();   */
	        //����������Ҫ�ύʱ(��outputStr��Ϊnullʱ���������д����)
	        if (null != outputStr) {  
	            OutputStream outputStream = httpUrlConn.getOutputStream();  
	            // ע������ʽ����ֹ��������  
	            outputStream.write(outputStr.getBytes("UTF-8"));  
	            outputStream.close();  
	        }   
	        
	        // �����ص�������ת�����ַ���  
	        InputStream inputStream = httpUrlConn.getInputStream();  
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	        String str = null; 
	        StringBuffer buffer=new StringBuffer();
	        while ((str = bufferedReader.readLine()) != null) {  
	            buffer.append(str);  
	        } 
	        
	        //�ͷ���Դ  
	        bufferedReader.close();  
	        inputStreamReader.close();  
	        inputStream.close();  
	        inputStream = null;  
	        httpUrlConn.disconnect();
			jsonObject = buffer.toString();
	    } catch (ConnectException ce) {  
	        log.error("���ӳ�ʱ: {}",ce);  
	    } catch (Exception e) {  
	        log.error("https�����쳣: {}", e);  
	    }  
	    return jsonObject;
	} 

	
	/**
	 * URL����(utf-8)
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
	 * ���������ж��ļ���չ��
	 * 
	 * @param contentType ��������
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
