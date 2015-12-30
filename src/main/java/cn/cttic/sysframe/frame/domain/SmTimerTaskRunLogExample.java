package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmTimerTaskRunLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmTimerTaskRunLogExample() {
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

        public Criteria andLogIdIsNull() {
            addCriterion("LOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("LOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLogIdEqualTo(Long value) {
            addCriterion("LOG_ID =", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotEqualTo(Long value) {
            addCriterion("LOG_ID <>", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThan(Long value) {
            addCriterion("LOG_ID >", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("LOG_ID >=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThan(Long value) {
            addCriterion("LOG_ID <", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThanOrEqualTo(Long value) {
            addCriterion("LOG_ID <=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdIn(List<Long> values) {
            addCriterion("LOG_ID in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotIn(List<Long> values) {
            addCriterion("LOG_ID not in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdBetween(Long value1, Long value2) {
            addCriterion("LOG_ID between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotBetween(Long value1, Long value2) {
            addCriterion("LOG_ID not between", value1, value2, "logId");
            return (Criteria) this;
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

        public Criteria andRunTimeIsNull() {
            addCriterion("RUN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNotNull() {
            addCriterion("RUN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRunTimeEqualTo(Date value) {
            addCriterion("RUN_TIME =", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotEqualTo(Date value) {
            addCriterion("RUN_TIME <>", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThan(Date value) {
            addCriterion("RUN_TIME >", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("RUN_TIME >=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThan(Date value) {
            addCriterion("RUN_TIME <", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThanOrEqualTo(Date value) {
            addCriterion("RUN_TIME <=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeIn(List<Date> values) {
            addCriterion("RUN_TIME in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotIn(List<Date> values) {
            addCriterion("RUN_TIME not in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeBetween(Date value1, Date value2) {
            addCriterion("RUN_TIME between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotBetween(Date value1, Date value2) {
            addCriterion("RUN_TIME not between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andResultCodeIsNull() {
            addCriterion("RESULT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andResultCodeIsNotNull() {
            addCriterion("RESULT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andResultCodeEqualTo(Short value) {
            addCriterion("RESULT_CODE =", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotEqualTo(Short value) {
            addCriterion("RESULT_CODE <>", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeGreaterThan(Short value) {
            addCriterion("RESULT_CODE >", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeGreaterThanOrEqualTo(Short value) {
            addCriterion("RESULT_CODE >=", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeLessThan(Short value) {
            addCriterion("RESULT_CODE <", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeLessThanOrEqualTo(Short value) {
            addCriterion("RESULT_CODE <=", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeIn(List<Short> values) {
            addCriterion("RESULT_CODE in", values, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotIn(List<Short> values) {
            addCriterion("RESULT_CODE not in", values, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeBetween(Short value1, Short value2) {
            addCriterion("RESULT_CODE between", value1, value2, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotBetween(Short value1, Short value2) {
            addCriterion("RESULT_CODE not between", value1, value2, "resultCode");
            return (Criteria) this;
        }

        public Criteria andRunResultIsNull() {
            addCriterion("RUN_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andRunResultIsNotNull() {
            addCriterion("RUN_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andRunResultEqualTo(String value) {
            addCriterion("RUN_RESULT =", value, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultNotEqualTo(String value) {
            addCriterion("RUN_RESULT <>", value, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultGreaterThan(String value) {
            addCriterion("RUN_RESULT >", value, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultGreaterThanOrEqualTo(String value) {
            addCriterion("RUN_RESULT >=", value, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultLessThan(String value) {
            addCriterion("RUN_RESULT <", value, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultLessThanOrEqualTo(String value) {
            addCriterion("RUN_RESULT <=", value, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultLike(String value) {
            addCriterion("RUN_RESULT like", value, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultNotLike(String value) {
            addCriterion("RUN_RESULT not like", value, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultIn(List<String> values) {
            addCriterion("RUN_RESULT in", values, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultNotIn(List<String> values) {
            addCriterion("RUN_RESULT not in", values, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultBetween(String value1, String value2) {
            addCriterion("RUN_RESULT between", value1, value2, "runResult");
            return (Criteria) this;
        }

        public Criteria andRunResultNotBetween(String value1, String value2) {
            addCriterion("RUN_RESULT not between", value1, value2, "runResult");
            return (Criteria) this;
        }

        public Criteria andInstanceIdIsNull() {
            addCriterion("INSTANCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstanceIdIsNotNull() {
            addCriterion("INSTANCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstanceIdEqualTo(Long value) {
            addCriterion("INSTANCE_ID =", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotEqualTo(Long value) {
            addCriterion("INSTANCE_ID <>", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdGreaterThan(Long value) {
            addCriterion("INSTANCE_ID >", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INSTANCE_ID >=", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdLessThan(Long value) {
            addCriterion("INSTANCE_ID <", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdLessThanOrEqualTo(Long value) {
            addCriterion("INSTANCE_ID <=", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdIn(List<Long> values) {
            addCriterion("INSTANCE_ID in", values, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotIn(List<Long> values) {
            addCriterion("INSTANCE_ID not in", values, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdBetween(Long value1, Long value2) {
            addCriterion("INSTANCE_ID between", value1, value2, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotBetween(Long value1, Long value2) {
            addCriterion("INSTANCE_ID not between", value1, value2, "instanceId");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
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