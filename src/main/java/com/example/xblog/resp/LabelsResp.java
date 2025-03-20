package com.example.xblog.resp;

public class LabelsResp {
    private Long labelId;

    private String labelName;

    private String labelAlias;

    private String labelDescription;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelAlias() {
        return labelAlias;
    }

    public void setLabelAlias(String labelAlias) {
        this.labelAlias = labelAlias;
    }

    public String getLabelDescription() {
        return labelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", labelId=").append(labelId);
        sb.append(", labelName=").append(labelName);
        sb.append(", labelAlias=").append(labelAlias);
        sb.append(", labelDescription=").append(labelDescription);
        sb.append("]");
        return sb.toString();
    }
}