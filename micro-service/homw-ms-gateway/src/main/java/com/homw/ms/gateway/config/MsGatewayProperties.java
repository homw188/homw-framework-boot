package com.homw.ms.gateway.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ms.gateway")
public class MsGatewayProperties {
	
	private Auth auth = new Auth();
	public Auth getAuth() {
		return auth;
	}

	public class Auth {
		private String appKey;
		private List<String> excludePaths;
		
		public String getAppKey() {
			return appKey;
		}
		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}
		public List<String> getExcludePaths() {
			return excludePaths;
		}
		public void setExcludePaths(List<String> pathExcludes) {
			this.excludePaths = pathExcludes;
		}
	}
	
}
