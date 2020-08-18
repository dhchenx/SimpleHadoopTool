package cn.edu.bjtu.cdh.bigdata.models;

public  class NewAppInfo {
		private String masterHost;
		private String masterPassword;
		private String masterPort;
		private String masterUser;
		private String hadoopBin;
		private String appRoot;
		private String userId;
		private String userPwd;
		private String appId;

		public String getMasterHost() {
			return masterHost;
		}

		public void setMasterHost(String masterHost) {
			this.masterHost = masterHost;
		}

		public String getMasterPassword() {
			return masterPassword;
		}

		public void setMasterPassword(String masterPassword) {
			this.masterPassword = masterPassword;
		}

		public String getMasterPort() {
			return masterPort;
		}

		public void setMasterPort(String masterPort) {
			this.masterPort = masterPort;
		}

		public String getMasterUser() {
			return masterUser;
		}

		public void setMasterUser(String masterUser) {
			this.masterUser = masterUser;
		}

		public String getHadoopBin() {
			return hadoopBin;
		}

		public void setHadoopBin(String hadoopBin) {
			this.hadoopBin = hadoopBin;
		}

		public String getAppRoot() {
			return appRoot;
		}

		public void setAppRoot(String appRoot) {
			this.appRoot = appRoot;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserPwd() {
			return userPwd;
		}

		public void setUserPwd(String userPwd) {
			this.userPwd = userPwd;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}
	}