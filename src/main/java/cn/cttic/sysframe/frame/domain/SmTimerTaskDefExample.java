package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmTimerTaskDefExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmTimerTaskDefExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("TASK_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("TASK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Long value) {
            addCriterion("TASK_ID =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Long value) {
            addCriterion("TASK_ID <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Long value) {
            addCriterion("TASK_ID >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TASK_ID >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Long value) {
            addCriterion("TASK_ID <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Long value) {
            addCriterion("TASK_ID <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Long> values) {
            addCriterion("TASK_ID in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Long> values) {
            addCriterion("TASK_ID not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Long value1, Long value2) {
            addCriterion("TASK_ID between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Long value1, Long value2) {
            addCriterion("TASK_ID not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("TASK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("TASK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("TASK_NAME like ", "%"+value+"%", "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("TASK_NAME <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("TASK_NAME >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_NAME >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("TASK_NAME <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_NAME <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("TASK_NAME like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("TASK_NAME not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("TASK_NAME in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("TASK_NAME not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("TASK_NAME between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("TASK_NAME not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskDescIsNull() {
            addCriterion("TASK_DESC is null");
            return (Criteria) this;
        }

        public Criteria andTaskDescIsNotNull() {
            addCriterion("TASK_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDescEqualTo(String value) {
            addCriterion("TASK_DESC =", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescNotEqualTo(String value) {
            addCriterion("TASK_DESC <>", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescGreaterThan(String value) {
            addCriterion("TASK_DESC >", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_DESC >=", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescLessThan(String value) {
            addCriterion("TASK_DESC <", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescLessThanOrEqualTo(String value) {
            addCriterion("TASK_DESC <=", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescLike(String value) {
            addCriterion("TASK_DESC like", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescNotLike(String value) {
            addCriterion("TASK_DESC not like", value, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescIn(List<String> values) {
            addCriterion("TASK_DESC in", values, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescNotIn(List<String> values) {
            addCriterion("TASK_DESC not in", values, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescBetween(String value1, String value2) {
            addCriterion("TASK_DESC between", value1, value2, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskDescNotBetween(String value1, String value2) {
            addCriterion("TASK_DESC not between", value1, value2, "taskDesc");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("TASK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("TASK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(Integer value) {
            addCriterion("TASK_TYPE =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(Integer value) {
            addCriterion("TASK_TYPE <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(Integer value) {
            addCriterion("TASK_TYPE >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_TYPE >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(Integer value) {
            addCriterion("TASK_TYPE <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_TYPE <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<Integer> values) {
            addCriterion("TASK_TYPE in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<Integer> values) {
            addCriterion("TASK_TYPE not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(Integer value1, Integer value2) {
            addCriterion("TASK_TYPE between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_TYPE not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNull() {
            addCriterion("PERIOD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNotNull() {
            addCriterion("PERIOD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeEqualTo(Integer value) {
            addCriterion("PERIOD_TYPE =", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotEqualTo(Integer value) {
            addCriterion("PERIOD_TYPE <>", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThan(Integer value) {
            addCriterion("PERIOD_TYPE >", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("PERIOD_TYPE >=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThan(Integer value) {
            addCriterion("PERIOD_TYPE <", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThanOrEqualTo(Integer value) {
            addCriterion("PERIOD_TYPE <=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIn(List<Integer> values) {
            addCriterion("PERIOD_TYPE in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotIn(List<Integer> values) {
            addCriterion("PERIOD_TYPE not in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeBetween(Integer value1, Integer value2) {
            addCriterion("PERIOD_TYPE between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("PERIOD_TYPE not between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatIsNull() {
            addCriterion("SYNC_TIME_FORMAT is null");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatIsNotNull() {
            addCriterion("SYNC_TIME_FORMAT is not null");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatEqualTo(String value) {
            addCriterion("SYNC_TIME_FORMAT =", value, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatNotEqualTo(String value) {
            addCriterion("SYNC_TIME_FORMAT <>", value, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatGreaterThan(String value) {
            addCriterion("SYNC_TIME_FORMAT >", value, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatGreaterThanOrEqualTo(String value) {
            addCriterion("SYNC_TIME_FORMAT >=", value, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatLessThan(String value) {
            addCriterion("SYNC_TIME_FORMAT <", value, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatLessThanOrEqualTo(String value) {
            addCriterion("SYNC_TIME_FORMAT <=", value, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatLike(String value) {
            addCriterion("SYNC_TIME_FORMAT like", value, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatNotLike(String value) {
            addCriterion("SYNC_TIME_FORMAT not like", value, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatIn(List<String> values) {
            addCriterion("SYNC_TIME_FORMAT in", values, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatNotIn(List<String> values) {
            addCriterion("SYNC_TIME_FORMAT not in", values, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatBetween(String value1, String value2) {
            addCriterion("SYNC_TIME_FORMAT between", value1, value2, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andSyncTimeFormatNotBetween(String value1, String value2) {
            addCriterion("SYNC_TIME_FORMAT not between", value1, value2, "syncTimeFormat");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalIsNull() {
            addCriterion("RUN_TIME_INTERVAL is null");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalIsNotNull() {
            addCriterion("RUN_TIME_INTERVAL is not null");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalEqualTo(Long value) {
            addCriterion("RUN_TIME_INTERVAL =", value, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalNotEqualTo(Long value) {
            addCriterion("RUN_TIME_INTERVAL <>", value, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalGreaterThan(Long value) {
            addCriterion("RUN_TIME_INTERVAL >", value, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalGreaterThanOrEqualTo(Long value) {
            addCriterion("RUN_TIME_INTERVAL >=", value, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalLessThan(Long value) {
            addCriterion("RUN_TIME_INTERVAL <", value, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalLessThanOrEqualTo(Long value) {
            addCriterion("RUN_TIME_INTERVAL <=", value, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalIn(List<Long> values) {
            addCriterion("RUN_TIME_INTERVAL in", values, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalNotIn(List<Long> values) {
            addCriterion("RUN_TIME_INTERVAL not in", values, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalBetween(Long value1, Long value2) {
            addCriterion("RUN_TIME_INTERVAL between", value1, value2, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunTimeIntervalNotBetween(Long value1, Long value2) {
            addCriterion("RUN_TIME_INTERVAL not between", value1, value2, "runTimeInterval");
            return (Criteria) this;
        }

        public Criteria andRunNumIsNull() {
            addCriterion("RUN_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRunNumIsNotNull() {
            addCriterion("RUN_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRunNumEqualTo(Integer value) {
            addCriterion("RUN_NUM =", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumNotEqualTo(Integer value) {
            addCriterion("RUN_NUM <>", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumGreaterThan(Integer value) {
            addCriterion("RUN_NUM >", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("RUN_NUM >=", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumLessThan(Integer value) {
            addCriterion("RUN_NUM <", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumLessThanOrEqualTo(Integer value) {
            addCriterion("RUN_NUM <=", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumIn(List<Integer> values) {
            addCriterion("RUN_NUM in", values, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumNotIn(List<Integer> values) {
            addCriterion("RUN_NUM not in", values, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumBetween(Integer value1, Integer value2) {
            addCriterion("RUN_NUM between", value1, value2, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumNotBetween(Integer value1, Integer value2) {
            addCriterion("RUN_NUM not between", value1, value2, "runNum");
            return (Criteria) this;
        }

        public Criteria andImplTypeIsNull() {
            addCriterion("IMPL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andImplTypeIsNotNull() {
            addCriterion("IMPL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andImplTypeEqualTo(Integer value) {
            addCriterion("IMPL_TYPE =", value, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeNotEqualTo(Integer value) {
            addCriterion("IMPL_TYPE <>", value, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeGreaterThan(Integer value) {
            addCriterion("IMPL_TYPE >", value, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("IMPL_TYPE >=", value, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeLessThan(Integer value) {
            addCriterion("IMPL_TYPE <", value, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeLessThanOrEqualTo(Integer value) {
            addCriterion("IMPL_TYPE <=", value, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeIn(List<Integer> values) {
            addCriterion("IMPL_TYPE in", values, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeNotIn(List<Integer> values) {
            addCriterion("IMPL_TYPE not in", values, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeBetween(Integer value1, Integer value2) {
            addCriterion("IMPL_TYPE between", value1, value2, "implType");
            return (Criteria) this;
        }

        public Criteria andImplTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("IMPL_TYPE not between", value1, value2, "implType");
            return (Criteria) this;
        }

        public Criteria andImplMethIsNull() {
            addCriterion("IMPL_METH is null");
            return (Criteria) this;
        }

        public Criteria andImplMethIsNotNull() {
            addCriterion("IMPL_METH is not null");
            return (Criteria) this;
        }

        public Criteria andImplMethEqualTo(String value) {
            addCriterion("IMPL_METH =", value, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethNotEqualTo(String value) {
            addCriterion("IMPL_METH <>", value, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethGreaterThan(String value) {
            addCriterion("IMPL_METH >", value, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethGreaterThanOrEqualTo(String value) {
            addCriterion("IMPL_METH >=", value, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethLessThan(String value) {
            addCriterion("IMPL_METH <", value, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethLessThanOrEqualTo(String value) {
            addCriterion("IMPL_METH <=", value, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethLike(String value) {
            addCriterion("IMPL_METH like", value, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethNotLike(String value) {
            addCriterion("IMPL_METH not like", value, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethIn(List<String> values) {
            addCriterion("IMPL_METH in", values, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethNotIn(List<String> values) {
            addCriterion("IMPL_METH not in", values, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethBetween(String value1, String value2) {
            addCriterion("IMPL_METH between", value1, value2, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplMethNotBetween(String value1, String value2) {
            addCriterion("IMPL_METH not between", value1, value2, "implMeth");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamIsNull() {
            addCriterion("IMPL_EXTRA_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamIsNotNull() {
            addCriterion("IMPL_EXTRA_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamEqualTo(String value) {
            addCriterion("IMPL_EXTRA_PARAM =", value, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamNotEqualTo(String value) {
            addCriterion("IMPL_EXTRA_PARAM <>", value, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamGreaterThan(String value) {
            addCriterion("IMPL_EXTRA_PARAM >", value, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamGreaterThanOrEqualTo(String value) {
            addCriterion("IMPL_EXTRA _PARAM >=", value, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamLessThan(String value) {
            addCriterion("IMPL_EXTRA_PARAM <", value, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamLessThanOrEqualTo(String value) {
            addCriterion("IMPL_EXTRA_PARAM <=", value, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamLike(String value) {
            addCriterion("IMPL_EXTRA_PARAM like", value, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamNotLike(String value) {
            addCriterion("IMPL_EXTRA_PARAM not like", value, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamIn(List<String> values) {
            addCriterion("IMPL_EXTRA_PARAM in", values, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamNotIn(List<String> values) {
            addCriterion("IMPL_EXTRA_PARAM not in", values, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamBetween(String value1, String value2) {
            addCriterion("IMPL_EXTRA_PARAM between", value1, value2, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andImplExtraParamNotBetween(String value1, String value2) {
            addCriterion("IMPL_EXTRA_PARAM not between", value1, value2, "implExtraParam");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorIsNull() {
            addCriterion("TASK_CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorIsNotNull() {
            addCriterion("TASK_CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorEqualTo(String value) {
            addCriterion("TASK_CREATOR =", value, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorNotEqualTo(String value) {
            addCriterion("TASK_CREATOR <>", value, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorGreaterThan(String value) {
            addCriterion("TASK_CREATOR >", value, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_CREATOR >=", value, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorLessThan(String value) {
            addCriterion("TASK_CREATOR <", value, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorLessThanOrEqualTo(String value) {
            addCriterion("TASK_CREATOR <=", value, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorLike(String value) {
            addCriterion("TASK_CREATOR like", value, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorNotLike(String value) {
            addCriterion("TASK_CREATOR not like", value, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorIn(List<String> values) {
            addCriterion("TASK_CREATOR in", values, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorNotIn(List<String> values) {
            addCriterion("TASK_CREATOR not in", values, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorBetween(String value1, String value2) {
            addCriterion("TASK_CREATOR between", value1, value2, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andTaskCreatorNotBetween(String value1, String value2) {
            addCriterion("TASK_CREATOR not between", value1, value2, "taskCreator");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNull() {
            addCriterion("SYSTEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("SYSTEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(String value) {
            addCriterion("SYSTEM_ID =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(String value) {
            addCriterion("SYSTEM_ID <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(String value) {
            addCriterion("SYSTEM_ID >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(String value) {
            addCriterion("SYSTEM_ID >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(String value) {
            addCriterion("SYSTEM_ID <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(String value) {
            addCriterion("SYSTEM_ID <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLike(String value) {
            addCriterion("SYSTEM_ID like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotLike(String value) {
            addCriterion("SYSTEM_ID not like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<String> values) {
            addCriterion("SYSTEM_ID in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<String> values) {
            addCriterion("SYSTEM_ID not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(String value1, String value2) {
            addCriterion("SYSTEM_ID between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(String value1, String value2) {
            addCriterion("SYSTEM_ID not between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNull() {
            addCriterion("TASK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("TASK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(String value) {
            addCriterion("TASK_STATUS =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(String value) {
            addCriterion("TASK_STATUS <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(String value) {
            addCriterion("TASK_STATUS >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_STATUS >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(String value) {
            addCriterion("TASK_STATUS <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(String value) {
            addCriterion("TASK_STATUS <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLike(String value) {
            addCriterion("TASK_STATUS like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotLike(String value) {
            addCriterion("TASK_STATUS not like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<String> values) {
            addCriterion("TASK_STATUS in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<String> values) {
            addCriterion("TASK_STATUS not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(String value1, String value2) {
            addCriterion("TASK_STATUS between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(String value1, String value2) {
            addCriterion("TASK_STATUS not between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeIsNull() {
            addCriterion("PRE_RUN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeIsNotNull() {
            addCriterion("PRE_RUN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeEqualTo(Date value) {
            addCriterion("PRE_RUN_TIME =", value, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeNotEqualTo(Date value) {
            addCriterion("PRE_RUN_TIME <>", value, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeGreaterThan(Date value) {
            addCriterion("PRE_RUN_TIME >", value, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PRE_RUN_TIME >=", value, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeLessThan(Date value) {
            addCriterion("PRE_RUN_TIME <", value, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeLessThanOrEqualTo(Date value) {
            addCriterion("PRE_RUN_TIME <=", value, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeIn(List<Date> values) {
            addCriterion("PRE_RUN_TIME in", values, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeNotIn(List<Date> values) {
            addCriterion("PRE_RUN_TIME not in", values, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeBetween(Date value1, Date value2) {
            addCriterion("PRE_RUN_TIME between", value1, value2, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andPreRunTimeNotBetween(Date value1, Date value2) {
            addCriterion("PRE_RUN_TIME not between", value1, value2, "preRunTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdIsNull() {
            addCriterion("UPDATE_OPER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdIsNotNull() {
            addCriterion("UPDATE_OPER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdEqualTo(String value) {
            addCriterion("UPDATE_OPER_ID =", value, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdNotEqualTo(String value) {
            addCriterion("UPDATE_OPER_ID <>", value, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdGreaterThan(String value) {
            addCriterion("UPDATE_OPER_ID >", value, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_OPER_ID >=", value, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdLessThan(String value) {
            addCriterion("UPDATE_OPER_ID <", value, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_OPER_ID <=", value, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdLike(String value) {
            addCriterion("UPDATE_OPER_ID like", value, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdNotLike(String value) {
            addCriterion("UPDATE_OPER_ID not like", value, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdIn(List<String> values) {
            addCriterion("UPDATE_OPER_ID in", values, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdNotIn(List<String> values) {
            addCriterion("UPDATE_OPER_ID not in", values, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdBetween(String value1, String value2) {
            addCriterion("UPDATE_OPER_ID between", value1, value2, "updateOperId");
            return (Criteria) this;
        }

        public Criteria andUpdateOperIdNotBetween(String value1, String value2) {
            addCriterion("UPDATE_OPER_ID not between", value1, value2, "updateOperId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}