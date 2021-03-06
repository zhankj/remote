package cn.cttic.icar.domain;

import java.util.ArrayList;
import java.util.List;

public class CfgPartTypeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public CfgPartTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
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

        public Criteria andPartTypeIsNull() {
            addCriterion("part_type is null");
            return (Criteria) this;
        }

        public Criteria andPartTypeIsNotNull() {
            addCriterion("part_type is not null");
            return (Criteria) this;
        }

        public Criteria andPartTypeEqualTo(String value) {
            addCriterion("part_type =", value, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeNotEqualTo(String value) {
            addCriterion("part_type <>", value, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeGreaterThan(String value) {
            addCriterion("part_type >", value, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeGreaterThanOrEqualTo(String value) {
            addCriterion("part_type >=", value, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeLessThan(String value) {
            addCriterion("part_type <", value, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeLessThanOrEqualTo(String value) {
            addCriterion("part_type <=", value, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeLike(String value) {
            addCriterion("part_type like", value, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeNotLike(String value) {
            addCriterion("part_type not like", value, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeIn(List<String> values) {
            addCriterion("part_type in", values, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeNotIn(List<String> values) {
            addCriterion("part_type not in", values, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeBetween(String value1, String value2) {
            addCriterion("part_type between", value1, value2, "partType");
            return (Criteria) this;
        }

        public Criteria andPartTypeNotBetween(String value1, String value2) {
            addCriterion("part_type not between", value1, value2, "partType");
            return (Criteria) this;
        }

        public Criteria andSysTypeIsNull() {
            addCriterion("sys_type is null");
            return (Criteria) this;
        }

        public Criteria andSysTypeIsNotNull() {
            addCriterion("sys_type is not null");
            return (Criteria) this;
        }

        public Criteria andSysTypeEqualTo(String value) {
            addCriterion("sys_type =", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotEqualTo(String value) {
            addCriterion("sys_type <>", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeGreaterThan(String value) {
            addCriterion("sys_type >", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_type >=", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLessThan(String value) {
            addCriterion("sys_type <", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLessThanOrEqualTo(String value) {
            addCriterion("sys_type <=", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLike(String value) {
            addCriterion("sys_type like", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotLike(String value) {
            addCriterion("sys_type not like", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeIn(List<String> values) {
            addCriterion("sys_type in", values, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotIn(List<String> values) {
            addCriterion("sys_type not in", values, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeBetween(String value1, String value2) {
            addCriterion("sys_type between", value1, value2, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotBetween(String value1, String value2) {
            addCriterion("sys_type not between", value1, value2, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeIsNull() {
            addCriterion("sys_sub_type is null");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeIsNotNull() {
            addCriterion("sys_sub_type is not null");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeEqualTo(String value) {
            addCriterion("sys_sub_type =", value, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeNotEqualTo(String value) {
            addCriterion("sys_sub_type <>", value, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeGreaterThan(String value) {
            addCriterion("sys_sub_type >", value, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_sub_type >=", value, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeLessThan(String value) {
            addCriterion("sys_sub_type <", value, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeLessThanOrEqualTo(String value) {
            addCriterion("sys_sub_type <=", value, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeLike(String value) {
            addCriterion("sys_sub_type like", value, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeNotLike(String value) {
            addCriterion("sys_sub_type not like", value, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeIn(List<String> values) {
            addCriterion("sys_sub_type in", values, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeNotIn(List<String> values) {
            addCriterion("sys_sub_type not in", values, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeBetween(String value1, String value2) {
            addCriterion("sys_sub_type between", value1, value2, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysSubTypeNotBetween(String value1, String value2) {
            addCriterion("sys_sub_type not between", value1, value2, "sysSubType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeIsNull() {
            addCriterion("sys_part_type is null");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeIsNotNull() {
            addCriterion("sys_part_type is not null");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeEqualTo(String value) {
            addCriterion("sys_part_type =", value, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeNotEqualTo(String value) {
            addCriterion("sys_part_type <>", value, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeGreaterThan(String value) {
            addCriterion("sys_part_type >", value, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_part_type >=", value, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeLessThan(String value) {
            addCriterion("sys_part_type <", value, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeLessThanOrEqualTo(String value) {
            addCriterion("sys_part_type <=", value, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeLike(String value) {
            addCriterion("sys_part_type like", value, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeNotLike(String value) {
            addCriterion("sys_part_type not like", value, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeIn(List<String> values) {
            addCriterion("sys_part_type in", values, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeNotIn(List<String> values) {
            addCriterion("sys_part_type not in", values, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeBetween(String value1, String value2) {
            addCriterion("sys_part_type between", value1, value2, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andSysPartTypeNotBetween(String value1, String value2) {
            addCriterion("sys_part_type not between", value1, value2, "sysPartType");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cfg_part_type
     *
     * @mbggenerated do_not_delete_during_merge Tue Dec 29 15:48:11 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cfg_part_type
     *
     * @mbggenerated Tue Dec 29 15:48:11 CST 2015
     */
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