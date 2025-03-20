package com.example.xblog.domain;

import java.util.ArrayList;
import java.util.List;

public class SortsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SortsExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andSortIdIsNull() {
            addCriterion("sort_id is null");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNotNull() {
            addCriterion("sort_id is not null");
            return (Criteria) this;
        }

        public Criteria andSortIdEqualTo(Long value) {
            addCriterion("sort_id =", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotEqualTo(Long value) {
            addCriterion("sort_id <>", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThan(Long value) {
            addCriterion("sort_id >", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sort_id >=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThan(Long value) {
            addCriterion("sort_id <", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThanOrEqualTo(Long value) {
            addCriterion("sort_id <=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdIn(List<Long> values) {
            addCriterion("sort_id in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotIn(List<Long> values) {
            addCriterion("sort_id not in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdBetween(Long value1, Long value2) {
            addCriterion("sort_id between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotBetween(Long value1, Long value2) {
            addCriterion("sort_id not between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortNameIsNull() {
            addCriterion("sort_name is null");
            return (Criteria) this;
        }

        public Criteria andSortNameIsNotNull() {
            addCriterion("sort_name is not null");
            return (Criteria) this;
        }

        public Criteria andSortNameEqualTo(String value) {
            addCriterion("sort_name =", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotEqualTo(String value) {
            addCriterion("sort_name <>", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameGreaterThan(String value) {
            addCriterion("sort_name >", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameGreaterThanOrEqualTo(String value) {
            addCriterion("sort_name >=", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLessThan(String value) {
            addCriterion("sort_name <", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLessThanOrEqualTo(String value) {
            addCriterion("sort_name <=", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLike(String value) {
            addCriterion("sort_name like", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotLike(String value) {
            addCriterion("sort_name not like", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameIn(List<String> values) {
            addCriterion("sort_name in", values, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotIn(List<String> values) {
            addCriterion("sort_name not in", values, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameBetween(String value1, String value2) {
            addCriterion("sort_name between", value1, value2, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotBetween(String value1, String value2) {
            addCriterion("sort_name not between", value1, value2, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortAliasIsNull() {
            addCriterion("sort_alias is null");
            return (Criteria) this;
        }

        public Criteria andSortAliasIsNotNull() {
            addCriterion("sort_alias is not null");
            return (Criteria) this;
        }

        public Criteria andSortAliasEqualTo(String value) {
            addCriterion("sort_alias =", value, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasNotEqualTo(String value) {
            addCriterion("sort_alias <>", value, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasGreaterThan(String value) {
            addCriterion("sort_alias >", value, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasGreaterThanOrEqualTo(String value) {
            addCriterion("sort_alias >=", value, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasLessThan(String value) {
            addCriterion("sort_alias <", value, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasLessThanOrEqualTo(String value) {
            addCriterion("sort_alias <=", value, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasLike(String value) {
            addCriterion("sort_alias like", value, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasNotLike(String value) {
            addCriterion("sort_alias not like", value, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasIn(List<String> values) {
            addCriterion("sort_alias in", values, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasNotIn(List<String> values) {
            addCriterion("sort_alias not in", values, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasBetween(String value1, String value2) {
            addCriterion("sort_alias between", value1, value2, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andSortAliasNotBetween(String value1, String value2) {
            addCriterion("sort_alias not between", value1, value2, "sortAlias");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIsNull() {
            addCriterion("parent_sort_id is null");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIsNotNull() {
            addCriterion("parent_sort_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentSortIdEqualTo(Long value) {
            addCriterion("parent_sort_id =", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotEqualTo(Long value) {
            addCriterion("parent_sort_id <>", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdGreaterThan(Long value) {
            addCriterion("parent_sort_id >", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_sort_id >=", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdLessThan(Long value) {
            addCriterion("parent_sort_id <", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_sort_id <=", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIn(List<Long> values) {
            addCriterion("parent_sort_id in", values, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotIn(List<Long> values) {
            addCriterion("parent_sort_id not in", values, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdBetween(Long value1, Long value2) {
            addCriterion("parent_sort_id between", value1, value2, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_sort_id not between", value1, value2, "parentSortId");
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