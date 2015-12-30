package cn.cttic.sysframe.frame.domain;

import java.util.Date;

public class SmOper2Role extends SmOper2RoleKey {
    private Date effDate;

    private Date expDate;

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
}