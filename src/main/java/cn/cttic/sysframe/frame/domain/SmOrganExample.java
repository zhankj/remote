package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmOrganExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmOrganExample() {
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

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgDescIsNull() {
            addCriterion("ORG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andOrgDescIsNotNull() {
            addCriterion("ORG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andOrgDescEqualTo(String value) {
            addCriterion("ORG_DESC =", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescNotEqualTo(String value) {
            addCriterion("ORG_DESC <>", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescGreaterThan(String value) {
            addCriterion("ORG_DESC >", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_DESC >=", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescLessThan(String value) {
            addCriterion("ORG_DESC <", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescLessThanOrEqualTo(String value) {
            addCriterion("ORG_DESC <=", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescLike(String value) {
            addCriterion("ORG_DESC like", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescNotLike(String value) {
            addCriterion("ORG_DESC not like", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescIn(List<String> values) {
            addCriterion("ORG_DESC in", values, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescNotIn(List<String> values) {
            addCriterion("ORG_DESC not in", values, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescBetween(String value1, String value2) {
            addCriterion("ORG_DESC between", value1, value2, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescNotBetween(String value1, String value2) {
            addCriterion("ORG_DESC not between", value1, value2, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgAliasIsNull() {
            addCriterion("ORG_ALIAS is null");
            return (Criteria) this;
        }

        public Criteria andOrgAliasIsNotNull() {
            addCriterion("ORG_ALIAS is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAliasEqualTo(String value) {
            addCriterion("ORG_ALIAS =", value, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasNotEqualTo(String value) {
            addCriterion("ORG_ALIAS <>", value, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasGreaterThan(String value) {
            addCriterion("ORG_ALIAS >", value, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ALIAS >=", value, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasLessThan(String value) {
            addCriterion("ORG_ALIAS <", value, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasLessThanOrEqualTo(String value) {
            addCriterion("ORG_ALIAS <=", value, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasLike(String value) {
            addCriterion("ORG_ALIAS like", value, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasNotLike(String value) {
            addCriterion("ORG_ALIAS not like", value, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasIn(List<String> values) {
            addCriterion("ORG_ALIAS in", values, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasNotIn(List<String> values) {
            addCriterion("ORG_ALIAS not in", values, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasBetween(String value1, String value2) {
            addCriterion("ORG_ALIAS between", value1, value2, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgAliasNotBetween(String value1, String value2) {
            addCriterion("ORG_ALIAS not between", value1, value2, "orgAlias");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNull() {
            addCriterion("ORG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNotNull() {
            addCriterion("ORG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeEqualTo(String value) {
            addCriterion("ORG_TYPE =", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotEqualTo(String value) {
            addCriterion("ORG_TYPE <>", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThan(String value) {
            addCriterion("ORG_TYPE >", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE >=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThan(String value) {
            addCriterion("ORG_TYPE <", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE <=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLike(String value) {
            addCriterion("ORG_TYPE like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotLike(String value) {
            addCriterion("ORG_TYPE not like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIn(List<String> values) {
            addCriterion("ORG_TYPE in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotIn(List<String> values) {
            addCriterion("ORG_TYPE not in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeBetween(String value1, String value2) {
            addCriterion("ORG_TYPE between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotBetween(String value1, String value2) {
            addCriterion("ORG_TYPE not between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNull() {
            addCriterion("ORG_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNotNull() {
            addCriterion("ORG_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelEqualTo(String value) {
            addCriterion("ORG_LEVEL =", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotEqualTo(String value) {
            addCriterion("ORG_LEVEL <>", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThan(String value) {
            addCriterion("ORG_LEVEL >", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL >=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThan(String value) {
            addCriterion("ORG_LEVEL <", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL <=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLike(String value) {
            addCriterion("ORG_LEVEL like", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotLike(String value) {
            addCriterion("ORG_LEVEL not like", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIn(List<String> values) {
            addCriterion("ORG_LEVEL in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotIn(List<String> values) {
            addCriterion("ORG_LEVEL not in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL not between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIsNull() {
            addCriterion("ORG_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIsNotNull() {
            addCriterion("ORG_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneEqualTo(String value) {
            addCriterion("ORG_PHONE =", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotEqualTo(String value) {
            addCriterion("ORG_PHONE <>", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneGreaterThan(String value) {
            addCriterion("ORG_PHONE >", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PHONE >=", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLessThan(String value) {
            addCriterion("ORG_PHONE <", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLessThanOrEqualTo(String value) {
            addCriterion("ORG_PHONE <=", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLike(String value) {
            addCriterion("ORG_PHONE like", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotLike(String value) {
            addCriterion("ORG_PHONE not like", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIn(List<String> values) {
            addCriterion("ORG_PHONE in", values, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotIn(List<String> values) {
            addCriterion("ORG_PHONE not in", values, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneBetween(String value1, String value2) {
            addCriterion("ORG_PHONE between", value1, value2, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotBetween(String value1, String value2) {
            addCriterion("ORG_PHONE not between", value1, value2, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgAddrIsNull() {
            addCriterion("ORG_ADDR is null");
            return (Criteria) this;
        }

        public Criteria andOrgAddrIsNotNull() {
            addCriterion("ORG_ADDR is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAddrEqualTo(String value) {
            addCriterion("ORG_ADDR =", value, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrNotEqualTo(String value) {
            addCriterion("ORG_ADDR <>", value, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrGreaterThan(String value) {
            addCriterion("ORG_ADDR >", value, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ADDR >=", value, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrLessThan(String value) {
            addCriterion("ORG_ADDR <", value, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrLessThanOrEqualTo(String value) {
            addCriterion("ORG_ADDR <=", value, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrLike(String value) {
            addCriterion("ORG_ADDR like", value, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrNotLike(String value) {
            addCriterion("ORG_ADDR not like", value, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrIn(List<String> values) {
            addCriterion("ORG_ADDR in", values, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrNotIn(List<String> values) {
            addCriterion("ORG_ADDR not in", values, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrBetween(String value1, String value2) {
            addCriterion("ORG_ADDR between", value1, value2, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgAddrNotBetween(String value1, String value2) {
            addCriterion("ORG_ADDR not between", value1, value2, "orgAddr");
            return (Criteria) this;
        }

        public Criteria andOrgPidIsNull() {
            addCriterion("ORG_PID is null");
            return (Criteria) this;
        }

        public Criteria andOrgPidIsNotNull() {
            addCriterion("ORG_PID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPidEqualTo(String value) {
            addCriterion("ORG_PID =", value, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidNotEqualTo(String value) {
            addCriterion("ORG_PID <>", value, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidGreaterThan(String value) {
            addCriterion("ORG_PID >", value, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PID >=", value, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidLessThan(String value) {
            addCriterion("ORG_PID <", value, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidLessThanOrEqualTo(String value) {
            addCriterion("ORG_PID <=", value, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidLike(String value) {
            addCriterion("ORG_PID like", value, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidNotLike(String value) {
            addCriterion("ORG_PID not like", value, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidIn(List<String> values) {
            addCriterion("ORG_PID in", values, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidNotIn(List<String> values) {
            addCriterion("ORG_PID not in", values, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidBetween(String value1, String value2) {
            addCriterion("ORG_PID between", value1, value2, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgPidNotBetween(String value1, String value2) {
            addCriterion("ORG_PID not between", value1, value2, "orgPid");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoIsNull() {
            addCriterion("ORG_ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoIsNotNull() {
            addCriterion("ORG_ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoEqualTo(Short value) {
            addCriterion("ORG_ORDER_NO =", value, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoNotEqualTo(Short value) {
            addCriterion("ORG_ORDER_NO <>", value, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoGreaterThan(Short value) {
            addCriterion("ORG_ORDER_NO >", value, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoGreaterThanOrEqualTo(Short value) {
            addCriterion("ORG_ORDER_NO >=", value, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoLessThan(Short value) {
            addCriterion("ORG_ORDER_NO <", value, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoLessThanOrEqualTo(Short value) {
            addCriterion("ORG_ORDER_NO <=", value, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoIn(List<Short> values) {
            addCriterion("ORG_ORDER_NO in", values, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoNotIn(List<Short> values) {
            addCriterion("ORG_ORDER_NO not in", values, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoBetween(Short value1, Short value2) {
            addCriterion("ORG_ORDER_NO between", value1, value2, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNoNotBetween(Short value1, Short value2) {
            addCriterion("ORG_ORDER_NO not between", value1, value2, "orgOrderNo");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNull() {
            addCriterion("PROVINCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("PROVINCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(String value) {
            addCriterion("PROVINCE_ID =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(String value) {
            addCriterion("PROVINCE_ID <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(String value) {
            addCriterion("PROVINCE_ID >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE_ID >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(String value) {
            addCriterion("PROVINCE_ID <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE_ID <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLike(String value) {
            addCriterion("PROVINCE_ID like", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotLike(String value) {
            addCriterion("PROVINCE_ID not like", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<String> values) {
            addCriterion("PROVINCE_ID in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<String> values) {
            addCriterion("PROVINCE_ID not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(String value1, String value2) {
            addCriterion("PROVINCE_ID between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(String value1, String value2) {
            addCriterion("PROVINCE_ID not between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("AREA_ID =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("AREA_ID <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("AREA_ID >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_ID >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("AREA_ID <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("AREA_ID <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("AREA_ID like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("AREA_ID not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("AREA_ID in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("AREA_ID not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("AREA_ID between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("AREA_ID not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andCountyIdIsNull() {
            addCriterion("COUNTY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCountyIdIsNotNull() {
            addCriterion("COUNTY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCountyIdEqualTo(String value) {
            addCriterion("COUNTY_ID =", value, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdNotEqualTo(String value) {
            addCriterion("COUNTY_ID <>", value, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdGreaterThan(String value) {
            addCriterion("COUNTY_ID >", value, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTY_ID >=", value, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdLessThan(String value) {
            addCriterion("COUNTY_ID <", value, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdLessThanOrEqualTo(String value) {
            addCriterion("COUNTY_ID <=", value, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdLike(String value) {
            addCriterion("COUNTY_ID like", value, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdNotLike(String value) {
            addCriterion("COUNTY_ID not like", value, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdIn(List<String> values) {
            addCriterion("COUNTY_ID in", values, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdNotIn(List<String> values) {
            addCriterion("COUNTY_ID not in", values, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdBetween(String value1, String value2) {
            addCriterion("COUNTY_ID between", value1, value2, "countyId");
            return (Criteria) this;
        }

        public Criteria andCountyIdNotBetween(String value1, String value2) {
            addCriterion("COUNTY_ID not between", value1, value2, "countyId");
            return (Criteria) this;
        }

        public Criteria andTownIdIsNull() {
            addCriterion("TOWN_ID is null");
            return (Criteria) this;
        }

        public Criteria andTownIdIsNotNull() {
            addCriterion("TOWN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTownIdEqualTo(String value) {
            addCriterion("TOWN_ID =", value, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdNotEqualTo(String value) {
            addCriterion("TOWN_ID <>", value, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdGreaterThan(String value) {
            addCriterion("TOWN_ID >", value, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdGreaterThanOrEqualTo(String value) {
            addCriterion("TOWN_ID >=", value, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdLessThan(String value) {
            addCriterion("TOWN_ID <", value, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdLessThanOrEqualTo(String value) {
            addCriterion("TOWN_ID <=", value, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdLike(String value) {
            addCriterion("TOWN_ID like", value, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdNotLike(String value) {
            addCriterion("TOWN_ID not like", value, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdIn(List<String> values) {
            addCriterion("TOWN_ID in", values, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdNotIn(List<String> values) {
            addCriterion("TOWN_ID not in", values, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdBetween(String value1, String value2) {
            addCriterion("TOWN_ID between", value1, value2, "townId");
            return (Criteria) this;
        }

        public Criteria andTownIdNotBetween(String value1, String value2) {
            addCriterion("TOWN_ID not between", value1, value2, "townId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
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

        public Criteria andOrgPtypeIsNull() {
            addCriterion("ORG_PTYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeIsNotNull() {
            addCriterion("ORG_PTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeEqualTo(String value) {
            addCriterion("ORG_PTYPE =", value, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeNotEqualTo(String value) {
            addCriterion("ORG_PTYPE <>", value, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeGreaterThan(String value) {
            addCriterion("ORG_PTYPE >", value, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PTYPE >=", value, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeLessThan(String value) {
            addCriterion("ORG_PTYPE <", value, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeLessThanOrEqualTo(String value) {
            addCriterion("ORG_PTYPE <=", value, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeLike(String value) {
            addCriterion("ORG_PTYPE like", value, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeNotLike(String value) {
            addCriterion("ORG_PTYPE not like", value, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeIn(List<String> values) {
            addCriterion("ORG_PTYPE in", values, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeNotIn(List<String> values) {
            addCriterion("ORG_PTYPE not in", values, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeBetween(String value1, String value2) {
            addCriterion("ORG_PTYPE between", value1, value2, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andOrgPtypeNotBetween(String value1, String value2) {
            addCriterion("ORG_PTYPE not between", value1, value2, "orgPtype");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdIsNull() {
            addCriterion("CREATE_OPER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdIsNotNull() {
            addCriterion("CREATE_OPER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdEqualTo(String value) {
            addCriterion("CREATE_OPER_ID =", value, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdNotEqualTo(String value) {
            addCriterion("CREATE_OPER_ID <>", value, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdGreaterThan(String value) {
            addCriterion("CREATE_OPER_ID >", value, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_OPER_ID >=", value, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdLessThan(String value) {
            addCriterion("CREATE_OPER_ID <", value, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdLessThanOrEqualTo(String value) {
            addCriterion("CREATE_OPER_ID <=", value, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdLike(String value) {
            addCriterion("CREATE_OPER_ID like", value, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdNotLike(String value) {
            addCriterion("CREATE_OPER_ID not like", value, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdIn(List<String> values) {
            addCriterion("CREATE_OPER_ID in", values, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdNotIn(List<String> values) {
            addCriterion("CREATE_OPER_ID not in", values, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdBetween(String value1, String value2) {
            addCriterion("CREATE_OPER_ID between", value1, value2, "createOperId");
            return (Criteria) this;
        }

        public Criteria andCreateOperIdNotBetween(String value1, String value2) {
            addCriterion("CREATE_OPER_ID not between", value1, value2, "createOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdIsNull() {
            addCriterion("MODIFY_OPER_ID is null");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdIsNotNull() {
            addCriterion("MODIFY_OPER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdEqualTo(String value) {
            addCriterion("MODIFY_OPER_ID =", value, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdNotEqualTo(String value) {
            addCriterion("MODIFY_OPER_ID <>", value, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdGreaterThan(String value) {
            addCriterion("MODIFY_OPER_ID >", value, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFY_OPER_ID >=", value, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdLessThan(String value) {
            addCriterion("MODIFY_OPER_ID <", value, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdLessThanOrEqualTo(String value) {
            addCriterion("MODIFY_OPER_ID <=", value, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdLike(String value) {
            addCriterion("MODIFY_OPER_ID like", value, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdNotLike(String value) {
            addCriterion("MODIFY_OPER_ID not like", value, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdIn(List<String> values) {
            addCriterion("MODIFY_OPER_ID in", values, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdNotIn(List<String> values) {
            addCriterion("MODIFY_OPER_ID not in", values, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdBetween(String value1, String value2) {
            addCriterion("MODIFY_OPER_ID between", value1, value2, "modifyOperId");
            return (Criteria) this;
        }

        public Criteria andModifyOperIdNotBetween(String value1, String value2) {
            addCriterion("MODIFY_OPER_ID not between", value1, value2, "modifyOperId");
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