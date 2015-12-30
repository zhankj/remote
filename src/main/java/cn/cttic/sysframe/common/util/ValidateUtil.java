package cn.cttic.sysframe.common.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.cttic.sysframe.common.model.ValidateItemModel;
import cn.cttic.sysframe.common.model.ValidateModel;

/**
 * Title: S4BOSS<br>
 * Description:后台校验信息处理类 <br>
 */
public class ValidateUtil {

    /**
     * 处理所有校验明细信息，并转化成校验结果对象
     * 
     * @param validateItemList
     * @return
     */
    public static ValidateModel process(List<ValidateItemModel> validateItemList) {
        ValidateModel validateVo = new ValidateModel();
        if (!CollectionUtil.isEmpty(validateItemList)) {
            for (ValidateItemModel validateItemVo : validateItemList) {
                if (ValidateItemModel.VALID_LEVEL_PASS == validateItemVo.getValidLevel()) {
                    validateVo.getListPass().add(validateItemVo);
                } else if (ValidateItemModel.VALID_LEVEL_WARN == validateItemVo.getValidLevel()) {
                    validateVo.getListWarn().add(validateItemVo);
                } else if (ValidateItemModel.VALID_LEVEL_CONFIRM == validateItemVo.getValidLevel()) {
                    validateVo.getListConfirm().add(validateItemVo);
                } else if (ValidateItemModel.VALID_LEVEL_ERROR == validateItemVo.getValidLevel()) {
                    validateVo.getListError().add(validateItemVo);
                }
            }
        }
        return validateVo;
    }

    /**
     * 直接将校验信息转化成JSON对象
     * 
     * @param validateItemList
     * @return
     */
    public static JSONObject transferToJSON(List<ValidateItemModel> validateItemList) {
        ValidateModel validateVo = process(validateItemList);
        JSONObject jsonObject = transfer(validateVo);
        return jsonObject;
    }

    /**
     * 将采集到的校验信息转化成客户端js校验框架所需要的JSON数据<br>
     * 按优先级，只处理最高级别的信息<br>
     * 
     * @param validateVo
     * @return
     */
    public static JSONObject transfer(ValidateModel validateVo) {
        JSONObject jsonObject = new JSONObject();
        /* 1.首先处理最高级别的消息，错误信息 */
        List<ValidateItemModel> listError = validateVo.getListError();
        if (!CollectionUtil.isEmpty(listError)) {
            JSONArray jsonArray = transfer(listError);
            jsonObject.put("VALID_LEVEL", ValidateItemModel.VALID_LEVEL_ERROR);
            jsonObject.put("VALID_INFO", jsonArray);
            return jsonObject;
        }
        /* 2.如果错误信息不存在，则处理确认信息 */
        List<ValidateItemModel> listConfirm = validateVo.getListConfirm();
        if (!CollectionUtil.isEmpty(listConfirm)) {
            JSONArray jsonArray = transfer(listConfirm);
            jsonObject.put("VALID_LEVEL", ValidateItemModel.VALID_LEVEL_CONFIRM);
            jsonObject.put("VALID_INFO", jsonArray);
            return jsonObject;
        }
        /* 3.如果需要确认的信息不存在，则处理警告的信息 */
        List<ValidateItemModel> listWarn = validateVo.getListWarn();
        if (!CollectionUtil.isEmpty(listWarn)) {
            JSONArray jsonArray = transfer(listWarn);
            jsonObject.put("VALID_LEVEL", ValidateItemModel.VALID_LEVEL_WARN);
            jsonObject.put("VALID_INFO", jsonArray);
            return jsonObject;
        }
        /* 4.通过的信息不进行处理 */
        jsonObject.put("VALID_LEVEL", ValidateItemModel.VALID_LEVEL_PASS);
        return jsonObject;
    }

    /**
     * 将校验信息转化成JSONArray
     * 
     * @param validateItemList
     * @return
     */
    private static JSONArray transfer(List<ValidateItemModel> validateItemList) {
        if (CollectionUtil.isEmpty(validateItemList))
            return null;
        JSONArray jsonArray = new JSONArray();
        for (ValidateItemModel validateItemVo : validateItemList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("VALID_CODE", validateItemVo.getValidCode());
            jsonObject.put("VALID_MESSAGE", validateItemVo.getValidMessage());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
