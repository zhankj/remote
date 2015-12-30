package cn.cttic.sysframe.common.model;

/**
 * Title: S4BOSS<br>
 * Description:校验信息明细 <br>
 */
public class ValidateItemModel {

    // 不提示，通过
    public final static int VALID_LEVEL_PASS = 0;

    // 警告，通过，不中断用户请求
    public final static int VALID_LEVEL_WARN = 1;

    // 确认，由用户确认是否通过
    public final static int VALID_LEVEL_CONFIRM = 2;

    // 错误，中断用户请求
    public final static int VALID_LEVEL_ERROR = 3;

    // 校验明细级别
    private int validLevel;

    // 校验明细编码
    private String validCode;

    // 校验明细信息
    private String validMessage;

    public ValidateItemModel() {

    }

    public ValidateItemModel(int validLevel, String validMessage) {
        this.validLevel = validLevel;
        this.validMessage = validMessage;
    }

    public ValidateItemModel(int validLevel, String validCode, String validMessage) {
        this.validLevel = validLevel;
        this.validCode = validCode;
        this.validMessage = validMessage;
    }

    public int getValidLevel() {
        return validLevel;
    }

    public void setValidLevel(int validLevel) {
        this.validLevel = validLevel;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getValidMessage() {
        return validMessage;
    }

    public void setValidMessage(String validMessage) {
        this.validMessage = validMessage;
    }
}

