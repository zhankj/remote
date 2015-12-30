package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.List;

public class SmDataRightExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmDataRightExample() {
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

        public Criteria andMenuIdIsNull() {
            addCriterion("MENU_ID is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("MENU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Long value) {
            addCriterion("MENU_ID =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Long value) {
            addCriterion("MENU_ID <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Long value) {
            addCriterion("MENU_ID >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MENU_ID >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Long value) {
            addCriterion("MENU_ID <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Long value) {
            addCriterion("MENU_ID <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Long> values) {
            addCriterion("MENU_ID in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Long> values) {
            addCriterion("MENU_ID not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Long value1, Long value2) {
            addCriterion("MENU_ID between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Long value1, Long value2) {
            addCriterion("MENU_ID not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andRightTypeIsNull() {
            addCriterion("RIGHT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRightTypeIsNotNull() {
            addCriterion("RIGHT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRightTypeEqualTo(Short value) {
            addCriterion("RIGHT_TYPE =", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeNotEqualTo(Short value) {
            addCriterion("RIGHT_TYPE <>", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeGreaterThan(Short value) {
            addCriterion("RIGHT_TYPE >", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("RIGHT_TYPE >=", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeLessThan(Short value) {
            addCriterion("RIGHT_TYPE <", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeLessThanOrEqualTo(Short value) {
            addCriterion("RIGHT_TYPE <=", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeIn(List<Short> values) {
            addCriterion("RIGHT_TYPE in", values, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeNotIn(List<Short> values) {
            addCriterion("RIGHT_TYPE not in", values, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeBetween(Short value1, Short value2) {
            addCriterion("RIGHT_TYPE between", value1, value2, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeNotBetween(Short value1, Short value2) {
            addCriterion("RIGHT_TYPE not between", value1, value2, "rightType");
            return (Criteria) this;
        }

        public Criteria andConfigAimIsNull() {
            addCriterion("CONFIG_AIM is null");
            return (Criteria) this;
        }

        public Criteria andConfigAimIsNotNull() {
            addCriterion("CONFIG_AIM is not null");
            return (Criteria) this;
        }

        public Criteria andConfigAimEqualTo(Short value) {
            addCriterion("CONFIG_AIM =", value, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimNotEqualTo(Short value) {
            addCriterion("CONFIG_AIM <>", value, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimGreaterThan(Short value) {
            addCriterion("CONFIG_AIM >", value, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimGreaterThanOrEqualTo(Short value) {
            addCriterion("CONFIG_AIM >=", value, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimLessThan(Short value) {
            addCriterion("CONFIG_AIM <", value, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimLessThanOrEqualTo(Short value) {
            addCriterion("CONFIG_AIM <=", value, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimIn(List<Short> values) {
            addCriterion("CONFIG_AIM in", values, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimNotIn(List<Short> values) {
            addCriterion("CONFIG_AIM not in", values, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimBetween(Short value1, Short value2) {
            addCriterion("CONFIG_AIM between", value1, value2, "configAim");
            return (Criteria) this;
        }

        public Criteria andConfigAimNotBetween(Short value1, Short value2) {
            addCriterion("CONFIG_AIM not between", value1, value2, "configAim");
            return (Criteria) this;
        }

        public Criteria andRightLevelIsNull() {
            addCriterion("RIGHT_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andRightLevelIsNotNull() {
            addCriterion("RIGHT_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andRightLevelEqualTo(String value) {
            addCriterion("RIGHT_LEVEL =", value, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelNotEqualTo(String value) {
            addCriterion("RIGHT_LEVEL <>", value, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelGreaterThan(String value) {
            addCriterion("RIGHT_LEVEL >", value, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelGreaterThanOrEqualTo(String value) {
            addCriterion("RIGHT_LEVEL >=", value, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelLessThan(String value) {
            addCriterion("RIGHT_LEVEL <", value, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelLessThanOrEqualTo(String value) {
            addCriterion("RIGHT_LEVEL <=", value, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelLike(String value) {
            addCriterion("RIGHT_LEVEL like", value, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelNotLike(String value) {
            addCriterion("RIGHT_LEVEL not like", value, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelIn(List<String> values) {
            addCriterion("RIGHT_LEVEL in", values, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelNotIn(List<String> values) {
            addCriterion("RIGHT_LEVEL not in", values, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelBetween(String value1, String value2) {
            addCriterion("RIGHT_LEVEL between", value1, value2, "rightLevel");
            return (Criteria) this;
        }

        public Criteria andRightLevelNotBetween(String value1, String value2) {
            addCriterion("RIGHT_LEVEL not between", value1, value2, "rightLevel");
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