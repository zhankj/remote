package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.List;

public class SmSysDictCriteria {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SmSysDictCriteria() {
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

		public Criteria andParamCodeIsNull() {
			addCriterion("TYPE_PARAM_CODE is null");
			return (Criteria) this;
		}

		public Criteria andParamCodeIsNotNull() {
			addCriterion("TYPE_PARAM_CODE is not null");
			return (Criteria) this;
		}

		public Criteria andParamCodeEqualTo(String value) {
			addCriterion("TYPE_PARAM_CODE =", value, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeNotEqualTo(String value) {
			addCriterion("TYPE_PARAM_CODE <>", value, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeGreaterThan(String value) {
			addCriterion("TYPE_PARAM_CODE >", value, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeGreaterThanOrEqualTo(String value) {
			addCriterion("TYPE_PARAM_CODE >=", value, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeLessThan(String value) {
			addCriterion("TYPE_PARAM_CODE <", value, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeLessThanOrEqualTo(String value) {
			addCriterion("TYPE_PARAM_CODE <=", value, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeLike(String value) {
			addCriterion("TYPE_PARAM_CODE like", value, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeNotLike(String value) {
			addCriterion("TYPE_PARAM_CODE not like", value, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeIn(List<String> values) {
			addCriterion("TYPE_PARAM_CODE in", values, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeNotIn(List<String> values) {
			addCriterion("TYPE_PARAM_CODE not in", values, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeBetween(String value1, String value2) {
			addCriterion("TYPE_PARAM_CODE between", value1, value2, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamCodeNotBetween(String value1, String value2) {
			addCriterion("TYPE_PARAM_CODE not between", value1, value2, "paramCode");
			return (Criteria) this;
		}

		public Criteria andParamDescIsNull() {
			addCriterion("PARAM_DESC is null");
			return (Criteria) this;
		}

		public Criteria andParamDescIsNotNull() {
			addCriterion("PARAM_DESC is not null");
			return (Criteria) this;
		}

		public Criteria andParamDescEqualTo(String value) {
			addCriterion("PARAM_DESC =", value, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescNotEqualTo(String value) {
			addCriterion("PARAM_DESC <>", value, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescGreaterThan(String value) {
			addCriterion("PARAM_DESC >", value, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescGreaterThanOrEqualTo(String value) {
			addCriterion("PARAM_DESC >=", value, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescLessThan(String value) {
			addCriterion("PARAM_DESC <", value, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescLessThanOrEqualTo(String value) {
			addCriterion("PARAM_DESC <=", value, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescLike(String value) {
			addCriterion("PARAM_DESC like", value, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescNotLike(String value) {
			addCriterion("PARAM_DESC not like", value, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescIn(List<String> values) {
			addCriterion("PARAM_DESC in", values, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescNotIn(List<String> values) {
			addCriterion("PARAM_DESC not in", values, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescBetween(String value1, String value2) {
			addCriterion("PARAM_DESC between", value1, value2, "paramDesc");
			return (Criteria) this;
		}

		public Criteria andParamDescNotBetween(String value1, String value2) {
			addCriterion("PARAM_DESC not between", value1, value2, "paramDesc");
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