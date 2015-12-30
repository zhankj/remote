package cn.cttic.sysframe.frame.domain;

public class SmMenuFavorites {
    private String operId;

    private String menuId;

    private Short menuOrder;

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public Short getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Short menuOrder) {
        this.menuOrder = menuOrder;
    }
}