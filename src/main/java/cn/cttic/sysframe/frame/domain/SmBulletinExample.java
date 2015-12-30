package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmBulletinExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmBulletinExample() {
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

        public Criteria andBulletinIdIsNull() {
            addCriterion("BULLETIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andBulletinIdIsNotNull() {
            addCriterion("BULLETIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBulletinIdEqualTo(Integer value) {
            addCriterion("BULLETIN_ID =", value, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdNotEqualTo(Integer value) {
            addCriterion("BULLETIN_ID <>", value, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdGreaterThan(Integer value) {
            addCriterion("BULLETIN_ID >", value, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BULLETIN_ID >=", value, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdLessThan(Integer value) {
            addCriterion("BULLETIN_ID <", value, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdLessThanOrEqualTo(Integer value) {
            addCriterion("BULLETIN_ID <=", value, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdIn(List<Integer> values) {
            addCriterion("BULLETIN_ID in", values, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdNotIn(List<Integer> values) {
            addCriterion("BULLETIN_ID not in", values, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdBetween(Integer value1, Integer value2) {
            addCriterion("BULLETIN_ID between", value1, value2, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BULLETIN_ID not between", value1, value2, "bulletinId");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleIsNull() {
            addCriterion("BULLETIN_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleIsNotNull() {
            addCriterion("BULLETIN_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleEqualTo(String value) {
            addCriterion("BULLETIN_TITLE =", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleNotEqualTo(String value) {
            addCriterion("BULLETIN_TITLE <>", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleGreaterThan(String value) {
            addCriterion("BULLETIN_TITLE >", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleGreaterThanOrEqualTo(String value) {
            addCriterion("BULLETIN_TITLE >=", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleLessThan(String value) {
            addCriterion("BULLETIN_TITLE <", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleLessThanOrEqualTo(String value) {
            addCriterion("BULLETIN_TITLE <=", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleLike(String value) {
            addCriterion("BULLETIN_TITLE like", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleNotLike(String value) {
            addCriterion("BULLETIN_TITLE not like", value, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleIn(List<String> values) {
            addCriterion("BULLETIN_TITLE in", values, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleNotIn(List<String> values) {
            addCriterion("BULLETIN_TITLE not in", values, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleBetween(String value1, String value2) {
            addCriterion("BULLETIN_TITLE between", value1, value2, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinTitleNotBetween(String value1, String value2) {
            addCriterion("BULLETIN_TITLE not between", value1, value2, "bulletinTitle");
            return (Criteria) this;
        }

        public Criteria andBulletinContentIsNull() {
            addCriterion("BULLETIN_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andBulletinContentIsNotNull() {
            addCriterion("BULLETIN_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andBulletinContentEqualTo(String value) {
            addCriterion("BULLETIN_CONTENT =", value, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentNotEqualTo(String value) {
            addCriterion("BULLETIN_CONTENT <>", value, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentGreaterThan(String value) {
            addCriterion("BULLETIN_CONTENT >", value, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentGreaterThanOrEqualTo(String value) {
            addCriterion("BULLETIN_CONTENT >=", value, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentLessThan(String value) {
            addCriterion("BULLETIN_CONTENT <", value, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentLessThanOrEqualTo(String value) {
            addCriterion("BULLETIN_CONTENT <=", value, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentLike(String value) {
            addCriterion("BULLETIN_CONTENT like", value, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentNotLike(String value) {
            addCriterion("BULLETIN_CONTENT not like", value, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentIn(List<String> values) {
            addCriterion("BULLETIN_CONTENT in", values, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentNotIn(List<String> values) {
            addCriterion("BULLETIN_CONTENT not in", values, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentBetween(String value1, String value2) {
            addCriterion("BULLETIN_CONTENT between", value1, value2, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinContentNotBetween(String value1, String value2) {
            addCriterion("BULLETIN_CONTENT not between", value1, value2, "bulletinContent");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherIsNull() {
            addCriterion("BULLETIN_PUBLISHER is null");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherIsNotNull() {
            addCriterion("BULLETIN_PUBLISHER is not null");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherEqualTo(String value) {
            addCriterion("BULLETIN_PUBLISHER =", value, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherNotEqualTo(String value) {
            addCriterion("BULLETIN_PUBLISHER <>", value, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherGreaterThan(String value) {
            addCriterion("BULLETIN_PUBLISHER >", value, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherGreaterThanOrEqualTo(String value) {
            addCriterion("BULLETIN_PUBLISHER >=", value, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherLessThan(String value) {
            addCriterion("BULLETIN_PUBLISHER <", value, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherLessThanOrEqualTo(String value) {
            addCriterion("BULLETIN_PUBLISHER <=", value, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherLike(String value) {
            addCriterion("BULLETIN_PUBLISHER like", value, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherNotLike(String value) {
            addCriterion("BULLETIN_PUBLISHER not like", value, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherIn(List<String> values) {
            addCriterion("BULLETIN_PUBLISHER in", values, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherNotIn(List<String> values) {
            addCriterion("BULLETIN_PUBLISHER not in", values, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherBetween(String value1, String value2) {
            addCriterion("BULLETIN_PUBLISHER between", value1, value2, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinPublisherNotBetween(String value1, String value2) {
            addCriterion("BULLETIN_PUBLISHER not between", value1, value2, "bulletinPublisher");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeIsNull() {
            addCriterion("BULLETIN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeIsNotNull() {
            addCriterion("BULLETIN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeEqualTo(String value) {
            addCriterion("BULLETIN_TYPE =", value, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeNotEqualTo(String value) {
            addCriterion("BULLETIN_TYPE <>", value, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeGreaterThan(String value) {
            addCriterion("BULLETIN_TYPE >", value, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BULLETIN_TYPE >=", value, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeLessThan(String value) {
            addCriterion("BULLETIN_TYPE <", value, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeLessThanOrEqualTo(String value) {
            addCriterion("BULLETIN_TYPE <=", value, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeLike(String value) {
            addCriterion("BULLETIN_TYPE like", value, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeNotLike(String value) {
            addCriterion("BULLETIN_TYPE not like", value, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeIn(List<String> values) {
            addCriterion("BULLETIN_TYPE in", values, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeNotIn(List<String> values) {
            addCriterion("BULLETIN_TYPE not in", values, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeBetween(String value1, String value2) {
            addCriterion("BULLETIN_TYPE between", value1, value2, "bulletinType");
            return (Criteria) this;
        }

        public Criteria andBulletinTypeNotBetween(String value1, String value2) {
            addCriterion("BULLETIN_TYPE not between", value1, value2, "bulletinType");
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

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
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

        public Criteria andFileNamesIsNull() {
            addCriterion("FILE_NAMES is null");
            return (Criteria) this;
        }

        public Criteria andFileNamesIsNotNull() {
            addCriterion("FILE_NAMES is not null");
            return (Criteria) this;
        }

        public Criteria andFileNamesEqualTo(String value) {
            addCriterion("FILE_NAMES =", value, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesNotEqualTo(String value) {
            addCriterion("FILE_NAMES <>", value, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesGreaterThan(String value) {
            addCriterion("FILE_NAMES >", value, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAMES >=", value, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesLessThan(String value) {
            addCriterion("FILE_NAMES <", value, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAMES <=", value, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesLike(String value) {
            addCriterion("FILE_NAMES like", value, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesNotLike(String value) {
            addCriterion("FILE_NAMES not like", value, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesIn(List<String> values) {
            addCriterion("FILE_NAMES in", values, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesNotIn(List<String> values) {
            addCriterion("FILE_NAMES not in", values, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesBetween(String value1, String value2) {
            addCriterion("FILE_NAMES between", value1, value2, "fileNames");
            return (Criteria) this;
        }

        public Criteria andFileNamesNotBetween(String value1, String value2) {
            addCriterion("FILE_NAMES not between", value1, value2, "fileNames");
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