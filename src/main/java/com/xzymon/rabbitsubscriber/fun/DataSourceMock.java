package com.xzymon.rabbitsubscriber.fun;

public class DataSourceMock {
	private String connectionUrl;
	private String username;
	private String password;

	public DataSourceMock(String connectionUrl, String username, String password) {
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
