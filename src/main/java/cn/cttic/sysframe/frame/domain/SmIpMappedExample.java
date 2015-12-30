package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.List;

public class SmIpMappedExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmIpMappedExample() {
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

        public Criteria andConnectIpIsNull() {
            addCriterion("CONNECT_IP is null");
            return (Criteria) this;
        }

        public Criteria andConnectIpIsNotNull() {
            addCriterion("CONNECT_IP is not null");
            return (Criteria) this;
        }

        public Criteria andConnectIpEqualTo(String value) {
            addCriterion("CONNECT_IP =", value, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpNotEqualTo(String value) {
            addCriterion("CONNECT_IP <>", value, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpGreaterThan(String value) {
            addCriterion("CONNECT_IP >", value, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpGreaterThanOrEqualTo(String value) {
            addCriterion("CONNECT_IP >=", value, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpLessThan(String value) {
            addCriterion("CONNECT_IP <", value, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpLessThanOrEqualTo(String value) {
            addCriterion("CONNECT_IP <=", value, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpLike(String value) {
            addCriterion("CONNECT_IP like", value, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpNotLike(String value) {
            addCriterion("CONNECT_IP not like", value, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpIn(List<String> values) {
            addCriterion("CONNECT_IP in", values, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpNotIn(List<String> values) {
            addCriterion("CONNECT_IP not in", values, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpBetween(String value1, String value2) {
            addCriterion("CONNECT_IP between", value1, value2, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectIpNotBetween(String value1, String value2) {
            addCriterion("CONNECT_IP not between", value1, value2, "connectIp");
            return (Criteria) this;
        }

        public Criteria andConnectPortIsNull() {
            addCriterion("CONNECT_PORT is null");
            return (Criteria) this;
        }

        public Criteria andConnectPortIsNotNull() {
            addCriterion("CONNECT_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andConnectPortEqualTo(String value) {
            addCriterion("CONNECT_PORT =", value, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortNotEqualTo(String value) {
            addCriterion("CONNECT_PORT <>", value, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortGreaterThan(String value) {
            addCriterion("CONNECT_PORT >", value, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortGreaterThanOrEqualTo(String value) {
            addCriterion("CONNECT_PORT >=", value, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortLessThan(String value) {
            addCriterion("CONNECT_PORT <", value, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortLessThanOrEqualTo(String value) {
            addCriterion("CONNECT_PORT <=", value, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortLike(String value) {
            addCriterion("CONNECT_PORT like", value, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortNotLike(String value) {
            addCriterion("CONNECT_PORT not like", value, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortIn(List<String> values) {
            addCriterion("CONNECT_PORT in", values, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortNotIn(List<String> values) {
            addCriterion("CONNECT_PORT not in", values, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortBetween(String value1, String value2) {
            addCriterion("CONNECT_PORT between", value1, value2, "connectPort");
            return (Criteria) this;
        }

        public Criteria andConnectPortNotBetween(String value1, String value2) {
            addCriterion("CONNECT_PORT not between", value1, value2, "connectPort");
            return (Criteria) this;
        }

        public Criteria andMappedIdIsNull() {
            addCriterion("MAPPED_ID is null");
            return (Criteria) this;
        }

        public Criteria andMappedIdIsNotNull() {
            addCriterion("MAPPED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMappedIdEqualTo(String value) {
            addCriterion("MAPPED_ID =", value, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdNotEqualTo(String value) {
            addCriterion("MAPPED_ID <>", value, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdGreaterThan(String value) {
            addCriterion("MAPPED_ID >", value, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdGreaterThanOrEqualTo(String value) {
            addCriterion("MAPPED_ID >=", value, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdLessThan(String value) {
            addCriterion("MAPPED_ID <", value, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdLessThanOrEqualTo(String value) {
            addCriterion("MAPPED_ID <=", value, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdLike(String value) {
            addCriterion("MAPPED_ID like", value, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdNotLike(String value) {
            addCriterion("MAPPED_ID not like", value, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdIn(List<String> values) {
            addCriterion("MAPPED_ID in", values, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdNotIn(List<String> values) {
            addCriterion("MAPPED_ID not in", values, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdBetween(String value1, String value2) {
            addCriterion("MAPPED_ID between", value1, value2, "mappedId");
            return (Criteria) this;
        }

        public Criteria andMappedIdNotBetween(String value1, String value2) {
            addCriterion("MAPPED_ID not between", value1, value2, "mappedId");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("NOTE is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("NOTE =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("NOTE <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("NOTE >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("NOTE <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("NOTE <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("NOTE like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("NOTE not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("NOTE in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("NOTE not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("NOTE between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("NOTE not between", value1, value2, "note");
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