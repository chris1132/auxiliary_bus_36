package com.auxiliarybus.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;


/**  
*   
* �����ƣ�MyX509TrustManager  
* �������� ֤�����ι�����������https���󣩱�д֤�������
* @version       
*/
public class MyX509TrustManager implements X509TrustManager{	
	/**
	 * �÷�����Ϊ��ʱ�������пͻ���֤��(���ͻ���֤��)
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {	
	}
	
	/**
	 * �÷�����Ϊ��ʱ�������з�����֤��?(��������֤��)
	 */
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {	
	}
	
	/**�������ε�֤��
	?*?@return???
	?*/
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
