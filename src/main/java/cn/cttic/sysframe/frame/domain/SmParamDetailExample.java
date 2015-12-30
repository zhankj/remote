package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmParamDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmParamDetailExample() {
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

        public Criteria andTypeCodeIsNull() {
            addCriterion("TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIsNotNull() {
            addCriterion("TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeEqualTo(String value) {
            addCriterion("TYPE_CODE =", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotEqualTo(String value) {
            addCriterion("TYPE_CODE <>", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThan(String value) {
            addCriterion("TYPE_CODE >", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_CODE >=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThan(String value) {
            addCriterion("TYPE_CODE <", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("TYPE_CODE <=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLike(String value) {
            addCriterion("TYPE_CODE like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotLike(String value) {
            addCriterion("TYPE_CODE not like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIn(List<String> values) {
            addCriterion("TYPE_CODE in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotIn(List<String> values) {
            addCriterion("TYPE_CODE not in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeBetween(String value1, String value2) {
            addCriterion("TYPE_CODE between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotBetween(String value1, String value2) {
            addCriterion("TYPE_CODE not between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeIsNull() {
            addCriterion("TYPE_PARAM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeIsNotNull() {
            addCriterion("TYPE_PARAM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeEqualTo(String value) {
            addCriterion("TYPE_PARAM_CODE =", value, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeNotEqualTo(String value) {
            addCriterion("TYPE_PARAM_CODE <>", value, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeGreaterThan(String value) {
            addCriterion("TYPE_PARAM_CODE >", value, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_PARAM_CODE >=", value, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeLessThan(String value) {
            addCriterion("TYPE_PARAM_CODE <", value, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeLessThanOrEqualTo(String value) {
            addCriterion("TYPE_PARAM_CODE <=", value, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeLike(String value) {
            addCriterion("TYPE_PARAM_CODE like", value, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeNotLike(String value) {
            addCriterion("TYPE_PARAM_CODE not like", value, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeIn(List<String> values) {
            addCriterion("TYPE_PARAM_CODE in", values, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeNotIn(List<String> values) {
            addCriterion("TYPE_PARAM_CODE not in", values, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeBetween(String value1, String value2) {
            addCriterion("TYPE_PARAM_CODE between", value1, value2, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andTypeParamCodeNotBetween(String value1, String value2) {
            addCriterion("TYPE_PARAM_CODE not between", value1, value2, "typeParamCode");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeIsNull() {
            addCriterion("LANGUAGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeIsNotNull() {
            addCriterion("LANGUAGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeEqualTo(String value) {
            addCriterion("LANGUAGE_TYPE =", value, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeNotEqualTo(String value) {
            addCriterion("LANGUAGE_TYPE <>", value, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeGreaterThan(String value) {
            addCriterion("LANGUAGE_TYPE >", value, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LANGUAGE_TYPE >=", value, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeLessThan(String value) {
            addCriterion("LANGUAGE_TYPE <", value, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeLessThanOrEqualTo(String value) {
            addCriterion("LANGUAGE_TYPE <=", value, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeLike(String value) {
            addCriterion("LANGUAGE_TYPE like", value, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeNotLike(String value) {
            addCriterion("LANGUAGE_TYPE not like", value, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeIn(List<String> values) {
            addCriterion("LANGUAGE_TYPE in", values, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeNotIn(List<String> values) {
            addCriterion("LANGUAGE_TYPE not in", values, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeBetween(String value1, String value2) {
            addCriterion("LANGUAGE_TYPE between", value1, value2, "languageType");
            return (Criteria) this;
        }

        public Criteria andLanguageTypeNotBetween(String value1, String value2) {
            addCriterion("LANGUAGE_TYPE not between", value1, value2, "languageType");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescIsNull() {
            addCriterion("TYPE_PARAM_DESC is null");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescIsNotNull() {
            addCriterion("TYPE_PARAM_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescEqualTo(String value) {
            addCriterion("TYPE_PARAM_DESC =", value, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescNotEqualTo(String value) {
            addCriterion("TYPE_PARAM_DESC <>", value, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescGreaterThan(String value) {
            addCriterion("TYPE_PARAM_DESC >", value, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_PARAM_DESC >=", value, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescLessThan(String value) {
            addCriterion("TYPE_PARAM_DESC <", value, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescLessThanOrEqualTo(String value) {
            addCriterion("TYPE_PARAM_DESC <=", value, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescLike(String value) {
            addCriterion("TYPE_PARAM_DESC like", value, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescNotLike(String value) {
            addCriterion("TYPE_PARAM_DESC not like", value, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescIn(List<String> values) {
            addCriterion("TYPE_PARAM_DESC in", values, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescNotIn(List<String> values) {
            addCriterion("TYPE_PARAM_DESC not in", values, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescBetween(String value1, String value2) {
            addCriterion("TYPE_PARAM_DESC between", value1, value2, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andTypeParamDescNotBetween(String value1, String value2) {
            addCriterion("TYPE_PARAM_DESC not between", value1, value2, "typeParamDesc");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeIsNull() {
            addCriterion("PARENT_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeIsNotNull() {
            addCriterion("PARENT_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeEqualTo(String value) {
            addCriterion("PARENT_TYPE_CODE =", value, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeNotEqualTo(String value) {
            addCriterion("PARENT_TYPE_CODE <>", value, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeGreaterThan(String value) {
            addCriterion("PARENT_TYPE_CODE >", value, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_TYPE_CODE >=", value, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeLessThan(String value) {
            addCriterion("PARENT_TYPE_CODE <", value, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("PARENT_TYPE_CODE <=", value, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeLike(String value) {
            addCriterion("PARENT_TYPE_CODE like", value, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeNotLike(String value) {
            addCriterion("PARENT_TYPE_CODE not like", value, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeIn(List<String> values) {
            addCriterion("PARENT_TYPE_CODE in", values, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeNotIn(List<String> values) {
            addCriterion("PARENT_TYPE_CODE not in", values, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeBetween(String value1, String value2) {
            addCriterion("PARENT_TYPE_CODE between", value1, value2, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentTypeCodeNotBetween(String value1, String value2) {
            addCriterion("PARENT_TYPE_CODE not between", value1, value2, "parentTypeCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeIsNull() {
            addCriterion("PARENT_PARAM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeIsNotNull() {
            addCriterion("PARENT_PARAM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeEqualTo(String value) {
            addCriterion("PARENT_PARAM_CODE =", value, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeNotEqualTo(String value) {
            addCriterion("PARENT_PARAM_CODE <>", value, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeGreaterThan(String value) {
            addCriterion("PARENT_PARAM_CODE >", value, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_PARAM_CODE >=", value, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeLessThan(String value) {
            addCriterion("PARENT_PARAM_CODE <", value, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeLessThanOrEqualTo(String value) {
            addCriterion("PARENT_PARAM_CODE <=", value, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeLike(String value) {
            addCriterion("PARENT_PARAM_CODE like", value, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeNotLike(String value) {
            addCriterion("PARENT_PARAM_CODE not like", value, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeIn(List<String> values) {
            addCriterion("PARENT_PARAM_CODE in", values, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeNotIn(List<String> values) {
            addCriterion("PARENT_PARAM_CODE not in", values, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeBetween(String value1, String value2) {
            addCriterion("PARENT_PARAM_CODE between", value1, value2, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andParentParamCodeNotBetween(String value1, String value2) {
            addCriterion("PARENT_PARAM_CODE not between", value1, value2, "parentParamCode");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
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

        public Criteria andParamOrderIsNull() {
            addCriterion("PARAM_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andParamOrderIsNotNull() {
            addCriterion("PARAM_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andParamOrderEqualTo(Short value) {
            addCriterion("PARAM_ORDER =", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotEqualTo(Short value) {
            addCriterion("PARAM_ORDER <>", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderGreaterThan(Short value) {
            addCriterion("PARAM_ORDER >", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderGreaterThanOrEqualTo(Short value) {
            addCriterion("PARAM_ORDER >=", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderLessThan(Short value) {
            addCriterion("PARAM_ORDER <", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderLessThanOrEqualTo(Short value) {
            addCriterion("PARAM_ORDER <=", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderIn(List<Short> values) {
            addCriterion("PARAM_ORDER in", values, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotIn(List<Short> values) {
            addCriterion("PARAM_ORDER not in", values, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderBetween(Short value1, Short value2) {
            addCriterion("PARAM_ORDER between", value1, value2, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotBetween(Short value1, Short value2) {
            addCriterion("PARAM_ORDER not between", value1, value2, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andOperIdIsNull() {
            addCriterion("OPER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperIdIsNotNull() {
            addCriterion("OPER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperIdEqualTo(String value) {
            addCriterion("OPER_ID =", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotEqualTo(String value) {
            addCriterion("OPER_ID <>", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThan(String value) {
            addCriterion("OPER_ID >", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_ID >=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThan(String value) {
            addCriterion("OPER_ID <", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_ID <=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLike(String value) {
            addCriterion("OPER_ID like", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotLike(String value) {
            addCriterion("OPER_ID not like", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdIn(List<String> values) {
            addCriterion("OPER_ID in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotIn(List<String> values) {
            addCriterion("OPER_ID not in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdBetween(String value1, String value2) {
            addCriterion("OPER_ID between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotBetween(String value1, String value2) {
            addCriterion("OPER_ID not between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNull() {
            addCriterion("MODIFY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNotNull() {
            addCriterion("MODIFY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andModifyDateEqualTo(Date value) {
            addCriterion("MODIFY_DATE =", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotEqualTo(Date value) {
            addCriterion("MODIFY_DATE <>", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThan(Date value) {
            addCriterion("MODIFY_DATE >", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFY_DATE >=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThan(Date value) {
            addCriterion("MODIFY_DATE <", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            addCriterion("MODIFY_DATE <=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIn(List<Date> values) {
            addCriterion("MODIFY_DATE in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotIn(List<Date> values) {
            addCriterion("MODIFY_DATE not in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateBetween(Date value1, Date value2) {
            addCriterion("MODIFY_DATE between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            addCriterion("MODIFY_DATE not between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andSourceCodeIsNull() {
            addCriterion("SOURCE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSourceCodeIsNotNull() {
            addCriterion("SOURCE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceCodeEqualTo(String value) {
            addCriterion("SOURCE_CODE =", value, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeNotEqualTo(String value) {
            addCriterion("SOURCE_CODE <>", value, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeGreaterThan(String value) {
            addCriterion("SOURCE_CODE >", value, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_CODE >=", value, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeLessThan(String value) {
            addCriterion("SOURCE_CODE <", value, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_CODE <=", value, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeLike(String value) {
            addCriterion("SOURCE_CODE like", value, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeNotLike(String value) {
            addCriterion("SOURCE_CODE not like", value, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeIn(List<String> values) {
            addCriterion("SOURCE_CODE in", values, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeNotIn(List<String> values) {
            addCriterion("SOURCE_CODE not in", values, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeBetween(String value1, String value2) {
            addCriterion("SOURCE_CODE between", value1, value2, "sourceCode");
            return (Criteria) this;
        }

        public Criteria andSourceCodeNotBetween(String value1, String value2) {
            addCriterion("SOURCE_CODE not between", value1, value2, "sourceCode");
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