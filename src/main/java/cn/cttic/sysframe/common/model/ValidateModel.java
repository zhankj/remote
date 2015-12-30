package cn.cttic.sysframe.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: S4BOSS<br>
 * Description:验证信息 <br>
 */
public class ValidateModel {

    // 存储所有校验通过的信息
    private List<ValidateItemModel> listPass = new ArrayList<ValidateItemModel>();

    // 存储所有校验不通过的信息
    private List<ValidateItemModel> listError = new ArrayList<ValidateItemModel>();

    // 存储所有需要确认是否通过的信息
    private List<ValidateItemModel> listConfirm = new ArrayList<ValidateItemModel>();

    // 存储所有警告但是通过的信息
    private List<ValidateItemModel> listWarn = new ArrayList<ValidateItemModel>();

    public List<ValidateItemModel> getListError() {
        return listError;
    }

    public List<ValidateItemModel> getListConfirm() {
        return listConfirm;
    }

    public List<ValidateItemModel> getListWarn() {
        return listWarn;
    }

    public List<ValidateItemModel> getListPass() {
        return listPass;
    }
}