package cn.cttic.sysframe.frame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmFtpPathExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmFtpPathExample() {
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

        public Criteria andFtpPathCodeIsNull() {
            addCriterion("FTP_PATH_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeIsNotNull() {
            addCriterion("FTP_PATH_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeEqualTo(String value) {
            addCriterion("FTP_PATH_CODE =", value, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeNotEqualTo(String value) {
            addCriterion("FTP_PATH_CODE <>", value, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeGreaterThan(String value) {
            addCriterion("FTP_PATH_CODE >", value, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FTP_PATH_CODE >=", value, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeLessThan(String value) {
            addCriterion("FTP_PATH_CODE <", value, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeLessThanOrEqualTo(String value) {
            addCriterion("FTP_PATH_CODE <=", value, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeLike(String value) {
            addCriterion("FTP_PATH_CODE like", value, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeNotLike(String value) {
            addCriterion("FTP_PATH_CODE not like", value, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeIn(List<String> values) {
            addCriterion("FTP_PATH_CODE in", values, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeNotIn(List<String> values) {
            addCriterion("FTP_PATH_CODE not in", values, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeBetween(String value1, String value2) {
            addCriterion("FTP_PATH_CODE between", value1, value2, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpPathCodeNotBetween(String value1, String value2) {
            addCriterion("FTP_PATH_CODE not between", value1, value2, "ftpPathCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeIsNull() {
            addCriterion("FTP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFtpCodeIsNotNull() {
            addCriterion("FTP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFtpCodeEqualTo(String value) {
            addCriterion("FTP_CODE =", value, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeNotEqualTo(String value) {
            addCriterion("FTP_CODE <>", value, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeGreaterThan(String value) {
            addCriterion("FTP_CODE >", value, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FTP_CODE >=", value, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeLessThan(String value) {
            addCriterion("FTP_CODE <", value, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeLessThanOrEqualTo(String value) {
            addCriterion("FTP_CODE <=", value, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeLike(String value) {
            addCriterion("FTP_CODE like", value, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeNotLike(String value) {
            addCriterion("FTP_CODE not like", value, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeIn(List<String> values) {
            addCriterion("FTP_CODE in", values, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeNotIn(List<String> values) {
            addCriterion("FTP_CODE not in", values, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeBetween(String value1, String value2) {
            addCriterion("FTP_CODE between", value1, value2, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andFtpCodeNotBetween(String value1, String value2) {
            addCriterion("FTP_CODE not between", value1, value2, "ftpCode");
            return (Criteria) this;
        }

        public Criteria andRemotePathIsNull() {
            addCriterion("REMOTE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andRemotePathIsNotNull() {
            addCriterion("REMOTE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andRemotePathEqualTo(String value) {
            addCriterion("REMOTE_PATH =", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathNotEqualTo(String value) {
            addCriterion("REMOTE_PATH <>", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathGreaterThan(String value) {
            addCriterion("REMOTE_PATH >", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathGreaterThanOrEqualTo(String value) {
            addCriterion("REMOTE_PATH >=", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathLessThan(String value) {
            addCriterion("REMOTE_PATH <", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathLessThanOrEqualTo(String value) {
            addCriterion("REMOTE_PATH <=", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathLike(String value) {
            addCriterion("REMOTE_PATH like", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathNotLike(String value) {
            addCriterion("REMOTE_PATH not like", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathIn(List<String> values) {
            addCriterion("REMOTE_PATH in", values, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathNotIn(List<String> values) {
            addCriterion("REMOTE_PATH not in", values, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathBetween(String value1, String value2) {
            addCriterion("REMOTE_PATH between", value1, value2, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathNotBetween(String value1, String value2) {
            addCriterion("REMOTE_PATH not between", value1, value2, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisIsNull() {
            addCriterion("REMOTE_PATH_HIS is null");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisIsNotNull() {
            addCriterion("REMOTE_PATH_HIS is not null");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisEqualTo(String value) {
            addCriterion("REMOTE_PATH_HIS =", value, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisNotEqualTo(String value) {
            addCriterion("REMOTE_PATH_HIS <>", value, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisGreaterThan(String value) {
            addCriterion("REMOTE_PATH_HIS >", value, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisGreaterThanOrEqualTo(String value) {
            addCriterion("REMOTE_PATH_HIS >=", value, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisLessThan(String value) {
            addCriterion("REMOTE_PATH_HIS <", value, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisLessThanOrEqualTo(String value) {
            addCriterion("REMOTE_PATH_HIS <=", value, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisLike(String value) {
            addCriterion("REMOTE_PATH_HIS like", value, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisNotLike(String value) {
            addCriterion("REMOTE_PATH_HIS not like", value, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisIn(List<String> values) {
            addCriterion("REMOTE_PATH_HIS in", values, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisNotIn(List<String> values) {
            addCriterion("REMOTE_PATH_HIS not in", values, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisBetween(String value1, String value2) {
            addCriterion("REMOTE_PATH_HIS between", value1, value2, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathHisNotBetween(String value1, String value2) {
            addCriterion("REMOTE_PATH_HIS not between", value1, value2, "remotePathHis");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempIsNull() {
            addCriterion("REMOTE_PATH_TEMP is null");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempIsNotNull() {
            addCriterion("REMOTE_PATH_TEMP is not null");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempEqualTo(String value) {
            addCriterion("REMOTE_PATH_TEMP =", value, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempNotEqualTo(String value) {
            addCriterion("REMOTE_PATH_TEMP <>", value, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempGreaterThan(String value) {
            addCriterion("REMOTE_PATH_TEMP >", value, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempGreaterThanOrEqualTo(String value) {
            addCriterion("REMOTE_PATH_TEMP >=", value, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempLessThan(String value) {
            addCriterion("REMOTE_PATH_TEMP <", value, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempLessThanOrEqualTo(String value) {
            addCriterion("REMOTE_PATH_TEMP <=", value, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempLike(String value) {
            addCriterion("REMOTE_PATH_TEMP like", value, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempNotLike(String value) {
            addCriterion("REMOTE_PATH_TEMP not like", value, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempIn(List<String> values) {
            addCriterion("REMOTE_PATH_TEMP in", values, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempNotIn(List<String> values) {
            addCriterion("REMOTE_PATH_TEMP not in", values, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempBetween(String value1, String value2) {
            addCriterion("REMOTE_PATH_TEMP between", value1, value2, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andRemotePathTempNotBetween(String value1, String value2) {
            addCriterion("REMOTE_PATH_TEMP not between", value1, value2, "remotePathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathIsNull() {
            addCriterion("LOCAL_PATH is null");
            return (Criteria) this;
        }

        public Criteria andLocalPathIsNotNull() {
            addCriterion("LOCAL_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andLocalPathEqualTo(String value) {
            addCriterion("LOCAL_PATH =", value, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathNotEqualTo(String value) {
            addCriterion("LOCAL_PATH <>", value, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathGreaterThan(String value) {
            addCriterion("LOCAL_PATH >", value, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_PATH >=", value, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathLessThan(String value) {
            addCriterion("LOCAL_PATH <", value, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_PATH <=", value, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathLike(String value) {
            addCriterion("LOCAL_PATH like", value, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathNotLike(String value) {
            addCriterion("LOCAL_PATH not like", value, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathIn(List<String> values) {
            addCriterion("LOCAL_PATH in", values, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathNotIn(List<String> values) {
            addCriterion("LOCAL_PATH not in", values, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathBetween(String value1, String value2) {
            addCriterion("LOCAL_PATH between", value1, value2, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathNotBetween(String value1, String value2) {
            addCriterion("LOCAL_PATH not between", value1, value2, "localPath");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisIsNull() {
            addCriterion("LOCAL_PATH_HIS is null");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisIsNotNull() {
            addCriterion("LOCAL_PATH_HIS is not null");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisEqualTo(String value) {
            addCriterion("LOCAL_PATH_HIS =", value, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisNotEqualTo(String value) {
            addCriterion("LOCAL_PATH_HIS <>", value, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisGreaterThan(String value) {
            addCriterion("LOCAL_PATH_HIS >", value, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_PATH_HIS >=", value, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisLessThan(String value) {
            addCriterion("LOCAL_PATH_HIS <", value, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_PATH_HIS <=", value, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisLike(String value) {
            addCriterion("LOCAL_PATH_HIS like", value, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisNotLike(String value) {
            addCriterion("LOCAL_PATH_HIS not like", value, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisIn(List<String> values) {
            addCriterion("LOCAL_PATH_HIS in", values, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisNotIn(List<String> values) {
            addCriterion("LOCAL_PATH_HIS not in", values, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisBetween(String value1, String value2) {
            addCriterion("LOCAL_PATH_HIS between", value1, value2, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathHisNotBetween(String value1, String value2) {
            addCriterion("LOCAL_PATH_HIS not between", value1, value2, "localPathHis");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempIsNull() {
            addCriterion("LOCAL_PATH_TEMP is null");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempIsNotNull() {
            addCriterion("LOCAL_PATH_TEMP is not null");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempEqualTo(String value) {
            addCriterion("LOCAL_PATH_TEMP =", value, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempNotEqualTo(String value) {
            addCriterion("LOCAL_PATH_TEMP <>", value, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempGreaterThan(String value) {
            addCriterion("LOCAL_PATH_TEMP >", value, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_PATH_TEMP >=", value, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempLessThan(String value) {
            addCriterion("LOCAL_PATH_TEMP <", value, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_PATH_TEMP <=", value, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempLike(String value) {
            addCriterion("LOCAL_PATH_TEMP like", value, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempNotLike(String value) {
            addCriterion("LOCAL_PATH_TEMP not like", value, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempIn(List<String> values) {
            addCriterion("LOCAL_PATH_TEMP in", values, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempNotIn(List<String> values) {
            addCriterion("LOCAL_PATH_TEMP not in", values, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempBetween(String value1, String value2) {
            addCriterion("LOCAL_PATH_TEMP between", value1, value2, "localPathTemp");
            return (Criteria) this;
        }

        public Criteria andLocalPathTempNotBetween(String value1, String value2) {
            addCriterion("LOCAL_PATH_TEMP not between", value1, value2, "localPathTemp");
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