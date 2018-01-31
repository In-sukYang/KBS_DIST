package kr.co.kbs.distribute.common.util;

import java.util.HashMap;


public class Configuration {

	private static Configuration instance = null;
	
    private Configuration() {
    	
    }

    public static synchronized Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    
    private String serverName = null;
    private String dmsUrl = null;
    private int sessionExpireMin = 10;
    private HashMap<String, String> defaultChannelUrl = new HashMap<String, String>();
 
    private boolean useTestServer = true;

	public boolean isUseTestServer() {
		return useTestServer;
	}

	public void setUseTestServer(boolean useTestServer) {
		// TODO Auto-generated method stub
		this.useTestServer = useTestServer;
	}
	
	public HashMap<String, String> getDefaultChannelUrl() {
		return defaultChannelUrl;
	}

	public void setDefaultChannelUrl(HashMap<String, String> defaultChannelUrl) {
		this.defaultChannelUrl = defaultChannelUrl;
	}

	public int getSessionExpireMin() {
		return sessionExpireMin;
	}

	public void setSessionExpireMin(int sessionExpireMin) {
		this.sessionExpireMin = sessionExpireMin;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDmsUrl() {
		return dmsUrl;
	}

	public void setDmsUrl(String dmsUrl) {
		this.dmsUrl = dmsUrl;
	}
    
}
