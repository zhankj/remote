package cn.cttic.sysframe.common.model;


public class CacheModel {
	private String cacheName;
	private int keyAmount;
	private boolean reloadability;
	
    public String getCacheName() {
    	return cacheName;
    }
	
    public void setCacheName(String cacheName) {
    	this.cacheName = cacheName;
    }
	
    public int getKeyAmount() {
    	return keyAmount;
    }
	
    public void setKeyAmount(int keyAmount) {
    	this.keyAmount = keyAmount;
    }

	
    public boolean isReloadability() {
    	return reloadability;
    }
	
    public void setReloadability(boolean reloadability) {
    	this.reloadability = reloadability;
    }
	
}
