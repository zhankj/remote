package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmSendmailSuccessLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmSendmailSuccessLogExample() {
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

        public Criteria andLogMailIdIsNull() {
            addCriterion("LOG_MAIL_ID is null");
            return (Criteria) this;
        }

        public Criteria andLogMailIdIsNotNull() {
            addCriterion("LOG_MAIL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLogMailIdEqualTo(Long value) {
            addCriterion("LOG_MAIL_ID =", value, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdNotEqualTo(Long value) {
            addCriterion("LOG_MAIL_ID <>", value, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdGreaterThan(Long value) {
            addCriterion("LOG_MAIL_ID >", value, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("LOG_MAIL_ID >=", value, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdLessThan(Long value) {
            addCriterion("LOG_MAIL_ID <", value, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdLessThanOrEqualTo(Long value) {
            addCriterion("LOG_MAIL_ID <=", value, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdIn(List<Long> values) {
            addCriterion("LOG_MAIL_ID in", values, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdNotIn(List<Long> values) {
            addCriterion("LOG_MAIL_ID not in", values, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdBetween(Long value1, Long value2) {
            addCriterion("LOG_MAIL_ID between", value1, value2, "logMailId");
            return (Criteria) this;
        }

        public Criteria andLogMailIdNotBetween(Long value1, Long value2) {
            addCriterion("LOG_MAIL_ID not between", value1, value2, "logMailId");
            return (Criteria) this;
        }

        public Criteria andMailSubjectIsNull() {
            addCriterion("MAIL_SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andMailSubjectIsNotNull() {
            addCriterion("MAIL_SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andMailSubjectEqualTo(String value) {
            addCriterion("MAIL_SUBJECT =", value, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectNotEqualTo(String value) {
            addCriterion("MAIL_SUBJECT <>", value, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectGreaterThan(String value) {
            addCriterion("MAIL_SUBJECT >", value, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_SUBJECT >=", value, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectLessThan(String value) {
            addCriterion("MAIL_SUBJECT <", value, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectLessThanOrEqualTo(String value) {
            addCriterion("MAIL_SUBJECT <=", value, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectLike(String value) {
            addCriterion("MAIL_SUBJECT like", value, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectNotLike(String value) {
            addCriterion("MAIL_SUBJECT not like", value, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectIn(List<String> values) {
            addCriterion("MAIL_SUBJECT in", values, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectNotIn(List<String> values) {
            addCriterion("MAIL_SUBJECT not in", values, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectBetween(String value1, String value2) {
            addCriterion("MAIL_SUBJECT between", value1, value2, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andMailSubjectNotBetween(String value1, String value2) {
            addCriterion("MAIL_SUBJECT not between", value1, value2, "mailSubject");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIsNull() {
            addCriterion("BUSI_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIsNotNull() {
            addCriterion("BUSI_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBusiTypeEqualTo(String value) {
            addCriterion("BUSI_TYPE =", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotEqualTo(String value) {
            addCriterion("BUSI_TYPE <>", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeGreaterThan(String value) {
            addCriterion("BUSI_TYPE >", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BUSI_TYPE >=", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLessThan(String value) {
            addCriterion("BUSI_TYPE <", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLessThanOrEqualTo(String value) {
            addCriterion("BUSI_TYPE <=", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLike(String value) {
            addCriterion("BUSI_TYPE like", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotLike(String value) {
            addCriterion("BUSI_TYPE not like", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIn(List<String> values) {
            addCriterion("BUSI_TYPE in", values, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotIn(List<String> values) {
            addCriterion("BUSI_TYPE not in", values, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeBetween(String value1, String value2) {
            addCriterion("BUSI_TYPE between", value1, value2, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotBetween(String value1, String value2) {
            addCriterion("BUSI_TYPE not between", value1, value2, "busiType");
            return (Criteria) this;
        }

        public Criteria andMailContentIsNull() {
            addCriterion("MAIL_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andMailContentIsNotNull() {
            addCriterion("MAIL_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andMailContentEqualTo(String value) {
            addCriterion("MAIL_CONTENT =", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentNotEqualTo(String value) {
            addCriterion("MAIL_CONTENT <>", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentGreaterThan(String value) {
            addCriterion("MAIL_CONTENT >", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_CONTENT >=", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentLessThan(String value) {
            addCriterion("MAIL_CONTENT <", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentLessThanOrEqualTo(String value) {
            addCriterion("MAIL_CONTENT <=", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentLike(String value) {
            addCriterion("MAIL_CONTENT like", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentNotLike(String value) {
            addCriterion("MAIL_CONTENT not like", value, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentIn(List<String> values) {
            addCriterion("MAIL_CONTENT in", values, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentNotIn(List<String> values) {
            addCriterion("MAIL_CONTENT not in", values, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentBetween(String value1, String value2) {
            addCriterion("MAIL_CONTENT between", value1, value2, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailContentNotBetween(String value1, String value2) {
            addCriterion("MAIL_CONTENT not between", value1, value2, "mailContent");
            return (Criteria) this;
        }

        public Criteria andMailFilesIsNull() {
            addCriterion("MAIL_FILES is null");
            return (Criteria) this;
        }

        public Criteria andMailFilesIsNotNull() {
            addCriterion("MAIL_FILES is not null");
            return (Criteria) this;
        }

        public Criteria andMailFilesEqualTo(String value) {
            addCriterion("MAIL_FILES =", value, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesNotEqualTo(String value) {
            addCriterion("MAIL_FILES <>", value, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesGreaterThan(String value) {
            addCriterion("MAIL_FILES >", value, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_FILES >=", value, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesLessThan(String value) {
            addCriterion("MAIL_FILES <", value, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesLessThanOrEqualTo(String value) {
            addCriterion("MAIL_FILES <=", value, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesLike(String value) {
            addCriterion("MAIL_FILES like", value, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesNotLike(String value) {
            addCriterion("MAIL_FILES not like", value, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesIn(List<String> values) {
            addCriterion("MAIL_FILES in", values, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesNotIn(List<String> values) {
            addCriterion("MAIL_FILES not in", values, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesBetween(String value1, String value2) {
            addCriterion("MAIL_FILES between", value1, value2, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andMailFilesNotBetween(String value1, String value2) {
            addCriterion("MAIL_FILES not between", value1, value2, "mailFiles");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumIsNull() {
            addCriterion("FAILURE_RETRY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumIsNotNull() {
            addCriterion("FAILURE_RETRY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumEqualTo(Short value) {
            addCriterion("FAILURE_RETRY_NUM =", value, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumNotEqualTo(Short value) {
            addCriterion("FAILURE_RETRY_NUM <>", value, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumGreaterThan(Short value) {
            addCriterion("FAILURE_RETRY_NUM >", value, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumGreaterThanOrEqualTo(Short value) {
            addCriterion("FAILURE_RETRY_NUM >=", value, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumLessThan(Short value) {
            addCriterion("FAILURE_RETRY_NUM <", value, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumLessThanOrEqualTo(Short value) {
            addCriterion("FAILURE_RETRY_NUM <=", value, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumIn(List<Short> values) {
            addCriterion("FAILURE_RETRY_NUM in", values, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumNotIn(List<Short> values) {
            addCriterion("FAILURE_RETRY_NUM not in", values, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumBetween(Short value1, Short value2) {
            addCriterion("FAILURE_RETRY_NUM between", value1, value2, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andFailureRetryNumNotBetween(Short value1, Short value2) {
            addCriterion("FAILURE_RETRY_NUM not between", value1, value2, "failureRetryNum");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNull() {
            addCriterion("SEND_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNotNull() {
            addCriterion("SEND_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSendTypeEqualTo(String value) {
            addCriterion("SEND_TYPE =", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotEqualTo(String value) {
            addCriterion("SEND_TYPE <>", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThan(String value) {
            addCriterion("SEND_TYPE >", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_TYPE >=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThan(String value) {
            addCriterion("SEND_TYPE <", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThanOrEqualTo(String value) {
            addCriterion("SEND_TYPE <=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLike(String value) {
            addCriterion("SEND_TYPE like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotLike(String value) {
            addCriterion("SEND_TYPE not like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIn(List<String> values) {
            addCriterion("SEND_TYPE in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotIn(List<String> values) {
            addCriterion("SEND_TYPE not in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeBetween(String value1, String value2) {
            addCriterion("SEND_TYPE between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotBetween(String value1, String value2) {
            addCriterion("SEND_TYPE not between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andPreSendDateIsNull() {
            addCriterion("PRE_SEND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPreSendDateIsNotNull() {
            addCriterion("PRE_SEND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPreSendDateEqualTo(Date value) {
            addCriterion("PRE_SEND_DATE =", value, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateNotEqualTo(Date value) {
            addCriterion("PRE_SEND_DATE <>", value, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateGreaterThan(Date value) {
            addCriterion("PRE_SEND_DATE >", value, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("PRE_SEND_DATE >=", value, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateLessThan(Date value) {
            addCriterion("PRE_SEND_DATE <", value, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateLessThanOrEqualTo(Date value) {
            addCriterion("PRE_SEND_DATE <=", value, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateIn(List<Date> values) {
            addCriterion("PRE_SEND_DATE in", values, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateNotIn(List<Date> values) {
            addCriterion("PRE_SEND_DATE not in", values, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateBetween(Date value1, Date value2) {
            addCriterion("PRE_SEND_DATE between", value1, value2, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andPreSendDateNotBetween(Date value1, Date value2) {
            addCriterion("PRE_SEND_DATE not between", value1, value2, "preSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateIsNull() {
            addCriterion("SUCCESS_SEND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateIsNotNull() {
            addCriterion("SUCCESS_SEND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateEqualTo(Date value) {
            addCriterion("SUCCESS_SEND_DATE =", value, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateNotEqualTo(Date value) {
            addCriterion("SUCCESS_SEND_DATE <>", value, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateGreaterThan(Date value) {
            addCriterion("SUCCESS_SEND_DATE >", value, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("SUCCESS_SEND_DATE >=", value, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateLessThan(Date value) {
            addCriterion("SUCCESS_SEND_DATE <", value, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateLessThanOrEqualTo(Date value) {
            addCriterion("SUCCESS_SEND_DATE <=", value, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateIn(List<Date> values) {
            addCriterion("SUCCESS_SEND_DATE in", values, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateNotIn(List<Date> values) {
            addCriterion("SUCCESS_SEND_DATE not in", values, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateBetween(Date value1, Date value2) {
            addCriterion("SUCCESS_SEND_DATE between", value1, value2, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andSuccessSendDateNotBetween(Date value1, Date value2) {
            addCriterion("SUCCESS_SEND_DATE not between", value1, value2, "successSendDate");
            return (Criteria) this;
        }

        public Criteria andFailureNumIsNull() {
            addCriterion("FAILURE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andFailureNumIsNotNull() {
            addCriterion("FAILURE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andFailureNumEqualTo(Short value) {
            addCriterion("FAILURE_NUM =", value, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumNotEqualTo(Short value) {
            addCriterion("FAILURE_NUM <>", value, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumGreaterThan(Short value) {
            addCriterion("FAILURE_NUM >", value, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumGreaterThanOrEqualTo(Short value) {
            addCriterion("FAILURE_NUM >=", value, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumLessThan(Short value) {
            addCriterion("FAILURE_NUM <", value, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumLessThanOrEqualTo(Short value) {
            addCriterion("FAILURE_NUM <=", value, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumIn(List<Short> values) {
            addCriterion("FAILURE_NUM in", values, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumNotIn(List<Short> values) {
            addCriterion("FAILURE_NUM not in", values, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumBetween(Short value1, Short value2) {
            addCriterion("FAILURE_NUM between", value1, value2, "failureNum");
            return (Criteria) this;
        }

        public Criteria andFailureNumNotBetween(Short value1, Short value2) {
            addCriterion("FAILURE_NUM not between", value1, value2, "failureNum");
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

        public Criteria andSendResultDescIsNull() {
            addCriterion("SEND_RESULT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andSendResultDescIsNotNull() {
            addCriterion("SEND_RESULT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andSendResultDescEqualTo(String value) {
            addCriterion("SEND_RESULT_DESC =", value, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescNotEqualTo(String value) {
            addCriterion("SEND_RESULT_DESC <>", value, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescGreaterThan(String value) {
            addCriterion("SEND_RESULT_DESC >", value, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_RESULT_DESC >=", value, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescLessThan(String value) {
            addCriterion("SEND_RESULT_DESC <", value, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescLessThanOrEqualTo(String value) {
            addCriterion("SEND_RESULT_DESC <=", value, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescLike(String value) {
            addCriterion("SEND_RESULT_DESC like", value, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescNotLike(String value) {
            addCriterion("SEND_RESULT_DESC not like", value, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescIn(List<String> values) {
            addCriterion("SEND_RESULT_DESC in", values, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescNotIn(List<String> values) {
            addCriterion("SEND_RESULT_DESC not in", values, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescBetween(String value1, String value2) {
            addCriterion("SEND_RESULT_DESC between", value1, value2, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andSendResultDescNotBetween(String value1, String value2) {
            addCriterion("SEND_RESULT_DESC not between", value1, value2, "sendResultDesc");
            return (Criteria) this;
        }

        public Criteria andMailFromIsNull() {
            addCriterion("MAIL_FROM is null");
            return (Criteria) this;
        }

        public Criteria andMailFromIsNotNull() {
            addCriterion("MAIL_FROM is not null");
            return (Criteria) this;
        }

        public Criteria andMailFromEqualTo(String value) {
            addCriterion("MAIL_FROM =", value, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromNotEqualTo(String value) {
            addCriterion("MAIL_FROM <>", value, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromGreaterThan(String value) {
            addCriterion("MAIL_FROM >", value, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_FROM >=", value, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromLessThan(String value) {
            addCriterion("MAIL_FROM <", value, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromLessThanOrEqualTo(String value) {
            addCriterion("MAIL_FROM <=", value, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromLike(String value) {
            addCriterion("MAIL_FROM like", value, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromNotLike(String value) {
            addCriterion("MAIL_FROM not like", value, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromIn(List<String> values) {
            addCriterion("MAIL_FROM in", values, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromNotIn(List<String> values) {
            addCriterion("MAIL_FROM not in", values, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromBetween(String value1, String value2) {
            addCriterion("MAIL_FROM between", value1, value2, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailFromNotBetween(String value1, String value2) {
            addCriterion("MAIL_FROM not between", value1, value2, "mailFrom");
            return (Criteria) this;
        }

        public Criteria andMailToIsNull() {
            addCriterion("MAIL_TO is null");
            return (Criteria) this;
        }

        public Criteria andMailToIsNotNull() {
            addCriterion("MAIL_TO is not null");
            return (Criteria) this;
        }

        public Criteria andMailToEqualTo(String value) {
            addCriterion("MAIL_TO =", value, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToNotEqualTo(String value) {
            addCriterion("MAIL_TO <>", value, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToGreaterThan(String value) {
            addCriterion("MAIL_TO >", value, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_TO >=", value, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToLessThan(String value) {
            addCriterion("MAIL_TO <", value, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToLessThanOrEqualTo(String value) {
            addCriterion("MAIL_TO <=", value, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToLike(String value) {
            addCriterion("MAIL_TO like", value, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToNotLike(String value) {
            addCriterion("MAIL_TO not like", value, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToIn(List<String> values) {
            addCriterion("MAIL_TO in", values, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToNotIn(List<String> values) {
            addCriterion("MAIL_TO not in", values, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToBetween(String value1, String value2) {
            addCriterion("MAIL_TO between", value1, value2, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailToNotBetween(String value1, String value2) {
            addCriterion("MAIL_TO not between", value1, value2, "mailTo");
            return (Criteria) this;
        }

        public Criteria andMailCcIsNull() {
            addCriterion("MAIL_CC is null");
            return (Criteria) this;
        }

        public Criteria andMailCcIsNotNull() {
            addCriterion("MAIL_CC is not null");
            return (Criteria) this;
        }

        public Criteria andMailCcEqualTo(String value) {
            addCriterion("MAIL_CC =", value, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcNotEqualTo(String value) {
            addCriterion("MAIL_CC <>", value, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcGreaterThan(String value) {
            addCriterion("MAIL_CC >", value, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_CC >=", value, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcLessThan(String value) {
            addCriterion("MAIL_CC <", value, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcLessThanOrEqualTo(String value) {
            addCriterion("MAIL_CC <=", value, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcLike(String value) {
            addCriterion("MAIL_CC like", value, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcNotLike(String value) {
            addCriterion("MAIL_CC not like", value, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcIn(List<String> values) {
            addCriterion("MAIL_CC in", values, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcNotIn(List<String> values) {
            addCriterion("MAIL_CC not in", values, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcBetween(String value1, String value2) {
            addCriterion("MAIL_CC between", value1, value2, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailCcNotBetween(String value1, String value2) {
            addCriterion("MAIL_CC not between", value1, value2, "mailCc");
            return (Criteria) this;
        }

        public Criteria andMailBccIsNull() {
            addCriterion("MAIL_BCC is null");
            return (Criteria) this;
        }

        public Criteria andMailBccIsNotNull() {
            addCriterion("MAIL_BCC is not null");
            return (Criteria) this;
        }

        public Criteria andMailBccEqualTo(String value) {
            addCriterion("MAIL_BCC =", value, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccNotEqualTo(String value) {
            addCriterion("MAIL_BCC <>", value, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccGreaterThan(String value) {
            addCriterion("MAIL_BCC >", value, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_BCC >=", value, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccLessThan(String value) {
            addCriterion("MAIL_BCC <", value, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccLessThanOrEqualTo(String value) {
            addCriterion("MAIL_BCC <=", value, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccLike(String value) {
            addCriterion("MAIL_BCC like", value, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccNotLike(String value) {
            addCriterion("MAIL_BCC not like", value, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccIn(List<String> values) {
            addCriterion("MAIL_BCC in", values, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccNotIn(List<String> values) {
            addCriterion("MAIL_BCC not in", values, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccBetween(String value1, String value2) {
            addCriterion("MAIL_BCC between", value1, value2, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andMailBccNotBetween(String value1, String value2) {
            addCriterion("MAIL_BCC not between", value1, value2, "mailBcc");
            return (Criteria) this;
        }

        public Criteria andBusiIdIsNull() {
            addCriterion("BUSI_ID is null");
            return (Criteria) this;
        }

        public Criteria andBusiIdIsNotNull() {
            addCriterion("BUSI_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBusiIdEqualTo(Long value) {
            addCriterion("BUSI_ID =", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdNotEqualTo(Long value) {
            addCriterion("BUSI_ID <>", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdGreaterThan(Long value) {
            addCriterion("BUSI_ID >", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BUSI_ID >=", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdLessThan(Long value) {
            addCriterion("BUSI_ID <", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdLessThanOrEqualTo(Long value) {
            addCriterion("BUSI_ID <=", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdIn(List<Long> values) {
            addCriterion("BUSI_ID in", values, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdNotIn(List<Long> values) {
            addCriterion("BUSI_ID not in", values, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdBetween(Long value1, Long value2) {
            addCriterion("BUSI_ID between", value1, value2, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdNotBetween(Long value1, Long value2) {
            addCriterion("BUSI_ID not between", value1, value2, "busiId");
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