package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmDataSourceCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmDataSourceCriteria() {
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

        public Criteria andDataCodeIsNull() {
            addCriterion("DATA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDataCodeIsNotNull() {
            addCriterion("DATA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDataCodeEqualTo(String value) {
            addCriterion("DATA_CODE =", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotEqualTo(String value) {
            addCriterion("DATA_CODE <>", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeGreaterThan(String value) {
            addCriterion("DATA_CODE >", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_CODE >=", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeLessThan(String value) {
            addCriterion("DATA_CODE <", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeLessThanOrEqualTo(String value) {
            addCriterion("DATA_CODE <=", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeLike(String value) {
            addCriterion("DATA_CODE like", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotLike(String value) {
            addCriterion("DATA_CODE not like", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeIn(List<String> values) {
            addCriterion("DATA_CODE in", values, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotIn(List<String> values) {
            addCriterion("DATA_CODE not in", values, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeBetween(String value1, String value2) {
            addCriterion("DATA_CODE between", value1, value2, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotBetween(String value1, String value2) {
            addCriterion("DATA_CODE not between", value1, value2, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataNameIsNull() {
            addCriterion("DATA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDataNameIsNotNull() {
            addCriterion("DATA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDataNameEqualTo(String value) {
            addCriterion("DATA_NAME =", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotEqualTo(String value) {
            addCriterion("DATA_NAME <>", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameGreaterThan(String value) {
            addCriterion("DATA_NAME >", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_NAME >=", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLessThan(String value) {
            addCriterion("DATA_NAME <", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLessThanOrEqualTo(String value) {
            addCriterion("DATA_NAME <=", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLike(String value) {
            addCriterion("DATA_NAME like", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotLike(String value) {
            addCriterion("DATA_NAME not like", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameIn(List<String> values) {
            addCriterion("DATA_NAME in", values, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotIn(List<String> values) {
            addCriterion("DATA_NAME not in", values, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameBetween(String value1, String value2) {
            addCriterion("DATA_NAME between", value1, value2, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotBetween(String value1, String value2) {
            addCriterion("DATA_NAME not between", value1, value2, "dataName");
            return (Criteria) this;
        }

        public Criteria andSqlSelectIsNull() {
            addCriterion("SQL_SELECT is null");
            return (Criteria) this;
        }

        public Criteria andSqlSelectIsNotNull() {
            addCriterion("SQL_SELECT is not null");
            return (Criteria) this;
        }

        public Criteria andSqlSelectEqualTo(String value) {
            addCriterion("SQL_SELECT =", value, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectNotEqualTo(String value) {
            addCriterion("SQL_SELECT <>", value, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectGreaterThan(String value) {
            addCriterion("SQL_SELECT >", value, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectGreaterThanOrEqualTo(String value) {
            addCriterion("SQL_SELECT >=", value, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectLessThan(String value) {
            addCriterion("SQL_SELECT <", value, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectLessThanOrEqualTo(String value) {
            addCriterion("SQL_SELECT <=", value, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectLike(String value) {
            addCriterion("SQL_SELECT like", value, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectNotLike(String value) {
            addCriterion("SQL_SELECT not like", value, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectIn(List<String> values) {
            addCriterion("SQL_SELECT in", values, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectNotIn(List<String> values) {
            addCriterion("SQL_SELECT not in", values, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectBetween(String value1, String value2) {
            addCriterion("SQL_SELECT between", value1, value2, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlSelectNotBetween(String value1, String value2) {
            addCriterion("SQL_SELECT not between", value1, value2, "sqlSelect");
            return (Criteria) this;
        }

        public Criteria andSqlFromIsNull() {
            addCriterion("SQL_FROM is null");
            return (Criteria) this;
        }

        public Criteria andSqlFromIsNotNull() {
            addCriterion("SQL_FROM is not null");
            return (Criteria) this;
        }

        public Criteria andSqlFromEqualTo(String value) {
            addCriterion("SQL_FROM =", value, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromNotEqualTo(String value) {
            addCriterion("SQL_FROM <>", value, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromGreaterThan(String value) {
            addCriterion("SQL_FROM >", value, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromGreaterThanOrEqualTo(String value) {
            addCriterion("SQL_FROM >=", value, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromLessThan(String value) {
            addCriterion("SQL_FROM <", value, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromLessThanOrEqualTo(String value) {
            addCriterion("SQL_FROM <=", value, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromLike(String value) {
            addCriterion("SQL_FROM like", value, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromNotLike(String value) {
            addCriterion("SQL_FROM not like", value, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromIn(List<String> values) {
            addCriterion("SQL_FROM in", values, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromNotIn(List<String> values) {
            addCriterion("SQL_FROM not in", values, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromBetween(String value1, String value2) {
            addCriterion("SQL_FROM between", value1, value2, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlFromNotBetween(String value1, String value2) {
            addCriterion("SQL_FROM not between", value1, value2, "sqlFrom");
            return (Criteria) this;
        }

        public Criteria andSqlWhereIsNull() {
            addCriterion("SQL_WHERE is null");
            return (Criteria) this;
        }

        public Criteria andSqlWhereIsNotNull() {
            addCriterion("SQL_WHERE is not null");
            return (Criteria) this;
        }

        public Criteria andSqlWhereEqualTo(String value) {
            addCriterion("SQL_WHERE =", value, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereNotEqualTo(String value) {
            addCriterion("SQL_WHERE <>", value, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereGreaterThan(String value) {
            addCriterion("SQL_WHERE >", value, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereGreaterThanOrEqualTo(String value) {
            addCriterion("SQL_WHERE >=", value, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereLessThan(String value) {
            addCriterion("SQL_WHERE <", value, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereLessThanOrEqualTo(String value) {
            addCriterion("SQL_WHERE <=", value, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereLike(String value) {
            addCriterion("SQL_WHERE like", value, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereNotLike(String value) {
            addCriterion("SQL_WHERE not like", value, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereIn(List<String> values) {
            addCriterion("SQL_WHERE in", values, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereNotIn(List<String> values) {
            addCriterion("SQL_WHERE not in", values, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereBetween(String value1, String value2) {
            addCriterion("SQL_WHERE between", value1, value2, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSqlWhereNotBetween(String value1, String value2) {
            addCriterion("SQL_WHERE not between", value1, value2, "sqlWhere");
            return (Criteria) this;
        }

        public Criteria andSourceStsIsNull() {
            addCriterion("SOURCE_STS is null");
            return (Criteria) this;
        }

        public Criteria andSourceStsIsNotNull() {
            addCriterion("SOURCE_STS is not null");
            return (Criteria) this;
        }

        public Criteria andSourceStsEqualTo(Short value) {
            addCriterion("SOURCE_STS =", value, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsNotEqualTo(Short value) {
            addCriterion("SOURCE_STS <>", value, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsGreaterThan(Short value) {
            addCriterion("SOURCE_STS >", value, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsGreaterThanOrEqualTo(Short value) {
            addCriterion("SOURCE_STS >=", value, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsLessThan(Short value) {
            addCriterion("SOURCE_STS <", value, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsLessThanOrEqualTo(Short value) {
            addCriterion("SOURCE_STS <=", value, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsIn(List<Short> values) {
            addCriterion("SOURCE_STS in", values, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsNotIn(List<Short> values) {
            addCriterion("SOURCE_STS not in", values, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsBetween(Short value1, Short value2) {
            addCriterion("SOURCE_STS between", value1, value2, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andSourceStsNotBetween(Short value1, Short value2) {
            addCriterion("SOURCE_STS not between", value1, value2, "sourceSts");
            return (Criteria) this;
        }

        public Criteria andStsDateIsNull() {
            addCriterion("STS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStsDateIsNotNull() {
            addCriterion("STS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStsDateEqualTo(Date value) {
            addCriterion("STS_DATE =", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotEqualTo(Date value) {
            addCriterion("STS_DATE <>", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateGreaterThan(Date value) {
            addCriterion("STS_DATE >", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateGreaterThanOrEqualTo(Date value) {
            addCriterion("STS_DATE >=", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateLessThan(Date value) {
            addCriterion("STS_DATE <", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateLessThanOrEqualTo(Date value) {
            addCriterion("STS_DATE <=", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateIn(List<Date> values) {
            addCriterion("STS_DATE in", values, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotIn(List<Date> values) {
            addCriterion("STS_DATE not in", values, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateBetween(Date value1, Date value2) {
            addCriterion("STS_DATE between", value1, value2, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotBetween(Date value1, Date value2) {
            addCriterion("STS_DATE not between", value1, value2, "stsDate");
            return (Criteria) this;
        }

        public Criteria andSqlOrderIsNull() {
            addCriterion("SQL_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andSqlOrderIsNotNull() {
            addCriterion("SQL_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andSqlOrderEqualTo(String value) {
            addCriterion("SQL_ORDER =", value, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderNotEqualTo(String value) {
            addCriterion("SQL_ORDER <>", value, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderGreaterThan(String value) {
            addCriterion("SQL_ORDER >", value, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderGreaterThanOrEqualTo(String value) {
            addCriterion("SQL_ORDER >=", value, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderLessThan(String value) {
            addCriterion("SQL_ORDER <", value, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderLessThanOrEqualTo(String value) {
            addCriterion("SQL_ORDER <=", value, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderLike(String value) {
            addCriterion("SQL_ORDER like", value, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderNotLike(String value) {
            addCriterion("SQL_ORDER not like", value, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderIn(List<String> values) {
            addCriterion("SQL_ORDER in", values, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderNotIn(List<String> values) {
            addCriterion("SQL_ORDER not in", values, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderBetween(String value1, String value2) {
            addCriterion("SQL_ORDER between", value1, value2, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andSqlOrderNotBetween(String value1, String value2) {
            addCriterion("SQL_ORDER not between", value1, value2, "sqlOrder");
            return (Criteria) this;
        }

        public Criteria andIsDistinctIsNull() {
            addCriterion("IS_DISTINCT is null");
            return (Criteria) this;
        }

        public Criteria andIsDistinctIsNotNull() {
            addCriterion("IS_DISTINCT is not null");
            return (Criteria) this;
        }

        public Criteria andIsDistinctEqualTo(Short value) {
            addCriterion("IS_DISTINCT =", value, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctNotEqualTo(Short value) {
            addCriterion("IS_DISTINCT <>", value, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctGreaterThan(Short value) {
            addCriterion("IS_DISTINCT >", value, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_DISTINCT >=", value, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctLessThan(Short value) {
            addCriterion("IS_DISTINCT <", value, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctLessThanOrEqualTo(Short value) {
            addCriterion("IS_DISTINCT <=", value, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctIn(List<Short> values) {
            addCriterion("IS_DISTINCT in", values, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctNotIn(List<Short> values) {
            addCriterion("IS_DISTINCT not in", values, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctBetween(Short value1, Short value2) {
            addCriterion("IS_DISTINCT between", value1, value2, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andIsDistinctNotBetween(Short value1, Short value2) {
            addCriterion("IS_DISTINCT not between", value1, value2, "isDistinct");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNull() {
            addCriterion("SOURCE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNotNull() {
            addCriterion("SOURCE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualTo(Short value) {
            addCriterion("SOURCE_TYPE =", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualTo(Short value) {
            addCriterion("SOURCE_TYPE <>", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThan(Short value) {
            addCriterion("SOURCE_TYPE >", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("SOURCE_TYPE >=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThan(Short value) {
            addCriterion("SOURCE_TYPE <", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualTo(Short value) {
            addCriterion("SOURCE_TYPE <=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIn(List<Short> values) {
            addCriterion("SOURCE_TYPE in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotIn(List<Short> values) {
            addCriterion("SOURCE_TYPE not in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeBetween(Short value1, Short value2) {
            addCriterion("SOURCE_TYPE between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotBetween(Short value1, Short value2) {
            addCriterion("SOURCE_TYPE not between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andImplClassIsNull() {
            addCriterion("IMPL_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andImplClassIsNotNull() {
            addCriterion("IMPL_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andImplClassEqualTo(String value) {
            addCriterion("IMPL_CLASS =", value, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassNotEqualTo(String value) {
            addCriterion("IMPL_CLASS <>", value, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassGreaterThan(String value) {
            addCriterion("IMPL_CLASS >", value, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassGreaterThanOrEqualTo(String value) {
            addCriterion("IMPL_CLASS >=", value, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassLessThan(String value) {
            addCriterion("IMPL_CLASS <", value, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassLessThanOrEqualTo(String value) {
            addCriterion("IMPL_CLASS <=", value, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassLike(String value) {
            addCriterion("IMPL_CLASS like", value, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassNotLike(String value) {
            addCriterion("IMPL_CLASS not like", value, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassIn(List<String> values) {
            addCriterion("IMPL_CLASS in", values, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassNotIn(List<String> values) {
            addCriterion("IMPL_CLASS not in", values, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassBetween(String value1, String value2) {
            addCriterion("IMPL_CLASS between", value1, value2, "implClass");
            return (Criteria) this;
        }

        public Criteria andImplClassNotBetween(String value1, String value2) {
            addCriterion("IMPL_CLASS not between", value1, value2, "implClass");
            return (Criteria) this;
        }

        public Criteria andSqlTransIsNull() {
            addCriterion("SQL_TRANS is null");
            return (Criteria) this;
        }

        public Criteria andSqlTransIsNotNull() {
            addCriterion("SQL_TRANS is not null");
            return (Criteria) this;
        }

        public Criteria andSqlTransEqualTo(String value) {
            addCriterion("SQL_TRANS =", value, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransNotEqualTo(String value) {
            addCriterion("SQL_TRANS <>", value, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransGreaterThan(String value) {
            addCriterion("SQL_TRANS >", value, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransGreaterThanOrEqualTo(String value) {
            addCriterion("SQL_TRANS >=", value, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransLessThan(String value) {
            addCriterion("SQL_TRANS <", value, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransLessThanOrEqualTo(String value) {
            addCriterion("SQL_TRANS <=", value, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransLike(String value) {
            addCriterion("SQL_TRANS like", value, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransNotLike(String value) {
            addCriterion("SQL_TRANS not like", value, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransIn(List<String> values) {
            addCriterion("SQL_TRANS in", values, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransNotIn(List<String> values) {
            addCriterion("SQL_TRANS not in", values, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransBetween(String value1, String value2) {
            addCriterion("SQL_TRANS between", value1, value2, "sqlTrans");
            return (Criteria) this;
        }

        public Criteria andSqlTransNotBetween(String value1, String value2) {
            addCriterion("SQL_TRANS not between", value1, value2, "sqlTrans");
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