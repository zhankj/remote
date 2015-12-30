package cn.cttic.sysframe.frame.model;

import cn.cttic.sysframe.frame.domain.SmMenuFavorites;

public class SmMenuFavoritesModel extends SmMenuFavorites {

	private String menuName;
	
	private String menuUrl;
	
	private String menuPic;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuPic() {
		return menuPic;
	}

	public void setMenuPic(String menuPic) {
		this.menuPic = menuPic;
	}
}
