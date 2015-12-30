package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.List;

public class SmRegisteredServiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmRegisteredServiceExample() {
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

        public Criteria andSIdIsNull() {
            addCriterion("S_ID is null");
            return (Criteria) this;
        }

        public Criteria andSIdIsNotNull() {
            addCriterion("S_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSIdEqualTo(Long value) {
            addCriterion("S_ID =", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotEqualTo(Long value) {
            addCriterion("S_ID <>", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdGreaterThan(Long value) {
            addCriterion("S_ID >", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdGreaterThanOrEqualTo(Long value) {
            addCriterion("S_ID >=", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLessThan(Long value) {
            addCriterion("S_ID <", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLessThanOrEqualTo(Long value) {
            addCriterion("S_ID <=", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdIn(List<Long> values) {
            addCriterion("S_ID in", values, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotIn(List<Long> values) {
            addCriterion("S_ID not in", values, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdBetween(Long value1, Long value2) {
            addCriterion("S_ID between", value1, value2, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotBetween(Long value1, Long value2) {
            addCriterion("S_ID not between", value1, value2, "sId");
            return (Criteria) this;
        }

        public Criteria andSTypeIsNull() {
            addCriterion("S_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSTypeIsNotNull() {
            addCriterion("S_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSTypeEqualTo(String value) {
            addCriterion("S_TYPE =", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotEqualTo(String value) {
            addCriterion("S_TYPE <>", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeGreaterThan(String value) {
            addCriterion("S_TYPE >", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeGreaterThanOrEqualTo(String value) {
            addCriterion("S_TYPE >=", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLessThan(String value) {
            addCriterion("S_TYPE <", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLessThanOrEqualTo(String value) {
            addCriterion("S_TYPE <=", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLike(String value) {
            addCriterion("S_TYPE like", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotLike(String value) {
            addCriterion("S_TYPE not like", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeIn(List<String> values) {
            addCriterion("S_TYPE in", values, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotIn(List<String> values) {
            addCriterion("S_TYPE not in", values, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeBetween(String value1, String value2) {
            addCriterion("S_TYPE between", value1, value2, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotBetween(String value1, String value2) {
            addCriterion("S_TYPE not between", value1, value2, "sType");
            return (Criteria) this;
        }

        public Criteria andSUrlIsNull() {
            addCriterion("S_URL is null");
            return (Criteria) this;
        }

        public Criteria andSUrlIsNotNull() {
            addCriterion("S_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSUrlEqualTo(String value) {
            addCriterion("S_URL =", value, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlNotEqualTo(String value) {
            addCriterion("S_URL <>", value, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlGreaterThan(String value) {
            addCriterion("S_URL >", value, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlGreaterThanOrEqualTo(String value) {
            addCriterion("S_URL >=", value, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlLessThan(String value) {
            addCriterion("S_URL <", value, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlLessThanOrEqualTo(String value) {
            addCriterion("S_URL <=", value, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlLike(String value) {
            addCriterion("S_URL like", value, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlNotLike(String value) {
            addCriterion("S_URL not like", value, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlIn(List<String> values) {
            addCriterion("S_URL in", values, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlNotIn(List<String> values) {
            addCriterion("S_URL not in", values, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlBetween(String value1, String value2) {
            addCriterion("S_URL between", value1, value2, "sUrl");
            return (Criteria) this;
        }

        public Criteria andSUrlNotBetween(String value1, String value2) {
            addCriterion("S_URL not between", value1, value2, "sUrl");
            return (Criteria) this;
        }

        public Criteria andPSIdIsNull() {
            addCriterion("P_S_ID is null");
            return (Criteria) this;
        }

        public Criteria andPSIdIsNotNull() {
            addCriterion("P_S_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPSIdEqualTo(Long value) {
            addCriterion("P_S_ID =", value, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdNotEqualTo(Long value) {
            addCriterion("P_S_ID <>", value, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdGreaterThan(Long value) {
            addCriterion("P_S_ID >", value, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdGreaterThanOrEqualTo(Long value) {
            addCriterion("P_S_ID >=", value, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdLessThan(Long value) {
            addCriterion("P_S_ID <", value, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdLessThanOrEqualTo(Long value) {
            addCriterion("P_S_ID <=", value, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdIn(List<Long> values) {
            addCriterion("P_S_ID in", values, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdNotIn(List<Long> values) {
            addCriterion("P_S_ID not in", values, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdBetween(Long value1, Long value2) {
            addCriterion("P_S_ID between", value1, value2, "pSId");
            return (Criteria) this;
        }

        public Criteria andPSIdNotBetween(Long value1, Long value2) {
            addCriterion("P_S_ID not between", value1, value2, "pSId");
            return (Criteria) this;
        }

        public Criteria andSNameIsNull() {
            addCriterion("S_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSNameIsNotNull() {
            addCriterion("S_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSNameEqualTo(String value) {
            addCriterion("S_NAME =", value, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameNotEqualTo(String value) {
            addCriterion("S_NAME <>", value, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameGreaterThan(String value) {
            addCriterion("S_NAME >", value, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_NAME >=", value, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameLessThan(String value) {
            addCriterion("S_NAME <", value, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameLessThanOrEqualTo(String value) {
            addCriterion("S_NAME <=", value, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameLike(String value) {
            addCriterion("S_NAME like", value, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameNotLike(String value) {
            addCriterion("S_NAME not like", value, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameIn(List<String> values) {
            addCriterion("S_NAME in", values, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameNotIn(List<String> values) {
            addCriterion("S_NAME not in", values, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameBetween(String value1, String value2) {
            addCriterion("S_NAME between", value1, value2, "sName");
            return (Criteria) this;
        }

        public Criteria andSNameNotBetween(String value1, String value2) {
            addCriterion("S_NAME not between", value1, value2, "sName");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
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

        public Criteria andNetTypeIsNull() {
            addCriterion("NET_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNetTypeIsNotNull() {
            addCriterion("NET_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNetTypeEqualTo(String value) {
            addCriterion("NET_TYPE =", value, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeNotEqualTo(String value) {
            addCriterion("NET_TYPE <>", value, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeGreaterThan(String value) {
            addCriterion("NET_TYPE >", value, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("NET_TYPE >=", value, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeLessThan(String value) {
            addCriterion("NET_TYPE <", value, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeLessThanOrEqualTo(String value) {
            addCriterion("NET_TYPE <=", value, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeLike(String value) {
            addCriterion("NET_TYPE like", value, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeNotLike(String value) {
            addCriterion("NET_TYPE not like", value, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeIn(List<String> values) {
            addCriterion("NET_TYPE in", values, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeNotIn(List<String> values) {
            addCriterion("NET_TYPE not in", values, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeBetween(String value1, String value2) {
            addCriterion("NET_TYPE between", value1, value2, "netType");
            return (Criteria) this;
        }

        public Criteria andNetTypeNotBetween(String value1, String value2) {
            addCriterion("NET_TYPE not between", value1, value2, "netType");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsIsNull() {
            addCriterion("INST_IDENTIFY_STS is null");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsIsNotNull() {
            addCriterion("INST_IDENTIFY_STS is not null");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsEqualTo(String value) {
            addCriterion("INST_IDENTIFY_STS =", value, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsNotEqualTo(String value) {
            addCriterion("INST_IDENTIFY_STS <>", value, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsGreaterThan(String value) {
            addCriterion("INST_IDENTIFY_STS >", value, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsGreaterThanOrEqualTo(String value) {
            addCriterion("INST_IDENTIFY_STS >=", value, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsLessThan(String value) {
            addCriterion("INST_IDENTIFY_STS <", value, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsLessThanOrEqualTo(String value) {
            addCriterion("INST_IDENTIFY_STS <=", value, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsLike(String value) {
            addCriterion("INST_IDENTIFY_STS like", value, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsNotLike(String value) {
            addCriterion("INST_IDENTIFY_STS not like", value, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsIn(List<String> values) {
            addCriterion("INST_IDENTIFY_STS in", values, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsNotIn(List<String> values) {
            addCriterion("INST_IDENTIFY_STS not in", values, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsBetween(String value1, String value2) {
            addCriterion("INST_IDENTIFY_STS between", value1, value2, "instIdentifySts");
            return (Criteria) this;
        }

        public Criteria andInstIdentifyStsNotBetween(String value1, String value2) {
            addCriterion("INST_IDENTIFY_STS not between", value1, value2, "instIdentifySts");
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