package com.example.xblog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoAddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VideoAddressExample() {
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

        public Criteria andVideoidIsNull() {
            addCriterion("videoid is null");
            return (Criteria) this;
        }

        public Criteria andVideoidIsNotNull() {
            addCriterion("videoid is not null");
            return (Criteria) this;
        }

        public Criteria andVideoidEqualTo(Long value) {
            addCriterion("videoid =", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidNotEqualTo(Long value) {
            addCriterion("videoid <>", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidGreaterThan(Long value) {
            addCriterion("videoid >", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidGreaterThanOrEqualTo(Long value) {
            addCriterion("videoid >=", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidLessThan(Long value) {
            addCriterion("videoid <", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidLessThanOrEqualTo(Long value) {
            addCriterion("videoid <=", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidIn(List<Long> values) {
            addCriterion("videoid in", values, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidNotIn(List<Long> values) {
            addCriterion("videoid not in", values, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidBetween(Long value1, Long value2) {
            addCriterion("videoid between", value1, value2, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidNotBetween(Long value1, Long value2) {
            addCriterion("videoid not between", value1, value2, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideotagIsNull() {
            addCriterion("videotag is null");
            return (Criteria) this;
        }

        public Criteria andVideotagIsNotNull() {
            addCriterion("videotag is not null");
            return (Criteria) this;
        }

        public Criteria andVideotagEqualTo(String value) {
            addCriterion("videotag =", value, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagNotEqualTo(String value) {
            addCriterion("videotag <>", value, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagGreaterThan(String value) {
            addCriterion("videotag >", value, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagGreaterThanOrEqualTo(String value) {
            addCriterion("videotag >=", value, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagLessThan(String value) {
            addCriterion("videotag <", value, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagLessThanOrEqualTo(String value) {
            addCriterion("videotag <=", value, "videotag");
            return (Criteria) this;
        }


        public Criteria andVideoaddressLike(String value) {
            addCriterion("videoaddress like", value, "videoaddress");
            return (Criteria) this;
        }

        public Criteria andVideoaddressNotLike(String value) {
            addCriterion("videoaddress not like", value, "videoaddress");
            return (Criteria) this;
        }

        public Criteria andVideotagLike(String value) {
            addCriterion("videotag like", value, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagNotLike(String value) {
            addCriterion("videotag not like", value, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagIn(List<String> values) {
            addCriterion("videotag in", values, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagNotIn(List<String> values) {
            addCriterion("videotag not in", values, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagBetween(String value1, String value2) {
            addCriterion("videotag between", value1, value2, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideotagNotBetween(String value1, String value2) {
            addCriterion("videotag not between", value1, value2, "videotag");
            return (Criteria) this;
        }

        public Criteria andVideosourceIsNull() {
            addCriterion("videosource is null");
            return (Criteria) this;
        }

        public Criteria andVideosourceIsNotNull() {
            addCriterion("videosource is not null");
            return (Criteria) this;
        }

        public Criteria andVideosourceEqualTo(String value) {
            addCriterion("videosource =", value, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceNotEqualTo(String value) {
            addCriterion("videosource <>", value, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceGreaterThan(String value) {
            addCriterion("videosource >", value, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceGreaterThanOrEqualTo(String value) {
            addCriterion("videosource >=", value, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceLessThan(String value) {
            addCriterion("videosource <", value, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceLessThanOrEqualTo(String value) {
            addCriterion("videosource <=", value, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceLike(String value) {
            addCriterion("videosource like", value, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceNotLike(String value) {
            addCriterion("videosource not like", value, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceIn(List<String> values) {
            addCriterion("videosource in", values, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceNotIn(List<String> values) {
            addCriterion("videosource not in", values, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceBetween(String value1, String value2) {
            addCriterion("videosource between", value1, value2, "videosource");
            return (Criteria) this;
        }

        public Criteria andVideosourceNotBetween(String value1, String value2) {
            addCriterion("videosource not between", value1, value2, "videosource");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andVideotypeIsNull() {
            addCriterion("videotype is null");
            return (Criteria) this;
        }

        public Criteria andVideotypeIsNotNull() {
            addCriterion("videotype is not null");
            return (Criteria) this;
        }

        public Criteria andVideotypeEqualTo(String value) {
            addCriterion("videotype =", value, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeNotEqualTo(String value) {
            addCriterion("videotype <>", value, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeGreaterThan(String value) {
            addCriterion("videotype >", value, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeGreaterThanOrEqualTo(String value) {
            addCriterion("videotype >=", value, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeLessThan(String value) {
            addCriterion("videotype <", value, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeLessThanOrEqualTo(String value) {
            addCriterion("videotype <=", value, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeLike(String value) {
            addCriterion("videotype like", value, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeNotLike(String value) {
            addCriterion("videotype not like", value, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeIn(List<String> values) {
            addCriterion("videotype in", values, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeNotIn(List<String> values) {
            addCriterion("videotype not in", values, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeBetween(String value1, String value2) {
            addCriterion("videotype between", value1, value2, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideotypeNotBetween(String value1, String value2) {
            addCriterion("videotype not between", value1, value2, "videotype");
            return (Criteria) this;
        }

        public Criteria andVideostateIsNull() {
            addCriterion("videostate is null");
            return (Criteria) this;
        }

        public Criteria andVideostateIsNotNull() {
            addCriterion("videostate is not null");
            return (Criteria) this;
        }

        public Criteria andVideostateEqualTo(String value) {
            addCriterion("videostate =", value, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateNotEqualTo(String value) {
            addCriterion("videostate <>", value, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateGreaterThan(String value) {
            addCriterion("videostate >", value, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateGreaterThanOrEqualTo(String value) {
            addCriterion("videostate >=", value, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateLessThan(String value) {
            addCriterion("videostate <", value, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateLessThanOrEqualTo(String value) {
            addCriterion("videostate <=", value, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateLike(String value) {
            addCriterion("videostate like", value, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateNotLike(String value) {
            addCriterion("videostate not like", value, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateIn(List<String> values) {
            addCriterion("videostate in", values, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateNotIn(List<String> values) {
            addCriterion("videostate not in", values, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateBetween(String value1, String value2) {
            addCriterion("videostate between", value1, value2, "videostate");
            return (Criteria) this;
        }

        public Criteria andVideostateNotBetween(String value1, String value2) {
            addCriterion("videostate not between", value1, value2, "videostate");
            return (Criteria) this;
        }

        public Criteria andFolderIsNull() {
            addCriterion("folder is null");
            return (Criteria) this;
        }

        public Criteria andFolderIsNotNull() {
            addCriterion("folder is not null");
            return (Criteria) this;
        }

        public Criteria andFolderEqualTo(String value) {
            addCriterion("folder =", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderNotEqualTo(String value) {
            addCriterion("folder <>", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderGreaterThan(String value) {
            addCriterion("folder >", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderGreaterThanOrEqualTo(String value) {
            addCriterion("folder >=", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderLessThan(String value) {
            addCriterion("folder <", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderLessThanOrEqualTo(String value) {
            addCriterion("folder <=", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderLike(String value) {
            addCriterion("folder like", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderNotLike(String value) {
            addCriterion("folder not like", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderIn(List<String> values) {
            addCriterion("folder in", values, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderNotIn(List<String> values) {
            addCriterion("folder not in", values, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderBetween(String value1, String value2) {
            addCriterion("folder between", value1, value2, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderNotBetween(String value1, String value2) {
            addCriterion("folder not between", value1, value2, "folder");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeIsNull() {
            addCriterion("collectiontime is null");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeIsNotNull() {
            addCriterion("collectiontime is not null");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeEqualTo(String value) {
            addCriterion("collectiontime =", value, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeNotEqualTo(String value) {
            addCriterion("collectiontime <>", value, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeGreaterThan(String value) {
            addCriterion("collectiontime >", value, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeGreaterThanOrEqualTo(String value) {
            addCriterion("collectiontime >=", value, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeLessThan(String value) {
            addCriterion("collectiontime <", value, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeLessThanOrEqualTo(String value) {
            addCriterion("collectiontime <=", value, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeLike(String value) {
            addCriterion("collectiontime like", value, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeNotLike(String value) {
            addCriterion("collectiontime not like", value, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeIn(List<String> values) {
            addCriterion("collectiontime in", values, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeNotIn(List<String> values) {
            addCriterion("collectiontime not in", values, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeBetween(String value1, String value2) {
            addCriterion("collectiontime between", value1, value2, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andCollectiontimeNotBetween(String value1, String value2) {
            addCriterion("collectiontime not between", value1, value2, "collectiontime");
            return (Criteria) this;
        }

        public Criteria andFilesizeIsNull() {
            addCriterion("filesize is null");
            return (Criteria) this;
        }

        public Criteria andFilesizeIsNotNull() {
            addCriterion("filesize is not null");
            return (Criteria) this;
        }

        public Criteria andFilesizeEqualTo(String value) {
            addCriterion("filesize =", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeNotEqualTo(String value) {
            addCriterion("filesize <>", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeGreaterThan(String value) {
            addCriterion("filesize >", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeGreaterThanOrEqualTo(String value) {
            addCriterion("filesize >=", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeLessThan(String value) {
            addCriterion("filesize <", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeLessThanOrEqualTo(String value) {
            addCriterion("filesize <=", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeLike(String value) {
            addCriterion("filesize like", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeNotLike(String value) {
            addCriterion("filesize not like", value, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeIn(List<String> values) {
            addCriterion("filesize in", values, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeNotIn(List<String> values) {
            addCriterion("filesize not in", values, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeBetween(String value1, String value2) {
            addCriterion("filesize between", value1, value2, "filesize");
            return (Criteria) this;
        }

        public Criteria andFilesizeNotBetween(String value1, String value2) {
            addCriterion("filesize not between", value1, value2, "filesize");
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