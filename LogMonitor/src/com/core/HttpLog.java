package com.core;

import java.text.SimpleDateFormat;
import java.util.Date;

// Represent log entry
public class HttpLog {
	private String client;
	private String userIdentifier;
	private String userId;
	private Date ts;
	private String clientRequest;
	private String httpStatusCode;
	private String resposeSize;
	private static SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyy:HH:mm:ss ZZZZ");

	public static HttpLog parseHttpLog(String str) {
		HttpLog hl = null;
		if (str != null) {
			String[] sr = str.split("\\s+");
			try
			{
				hl = new HttpLog();
				hl.client = sr[0];
				hl.userIdentifier = sr[1];
				hl.userId = sr[2];
				hl.clientRequest = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
				hl.ts = sd.parse(str.substring(str.indexOf("[") + 1, str.indexOf("]")));
				hl.httpStatusCode = sr[sr.length - 2];
				hl.resposeSize = sr[sr.length - 1];
			} catch (Exception exp) {
				//exp.printStackTrace();
			}
		}
		return hl;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getClientRequest() {
		return clientRequest;
	}

	public void setClientRequest(String clientRequest) {
		this.clientRequest = clientRequest;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getResposeSize() {
		return resposeSize;
	}

	public void setResposeSize(String resposeSize) {
		this.resposeSize = resposeSize;
	}	
}
