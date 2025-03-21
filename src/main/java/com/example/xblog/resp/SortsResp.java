package com.example.xblog.resp;

public class SortsResp {
    private Long sortId;

    private String sortName;

    private String sortAlias;

    private Long parentSortId;

    private String sortDescription;

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortAlias() {
        return sortAlias;
    }

    public void setSortAlias(String sortAlias) {
        this.sortAlias = sortAlias;
    }

    public Long getParentSortId() {
        return parentSortId;
    }

    public void setParentSortId(Long parentSortId) {
        this.parentSortId = parentSortId;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sortId=").append(sortId);
        sb.append(", sortName=").append(sortName);
        sb.append(", sortAlias=").append(sortAlias);
        sb.append(", parentSortId=").append(parentSortId);
        sb.append(", sortDescription=").append(sortDescription);
        sb.append("]");
        return sb.toString();
    }
}