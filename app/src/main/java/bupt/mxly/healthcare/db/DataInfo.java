package bupt.mxly.healthcare.db;

import java.util.Date;

public class DataInfo {
    private String userId;
    private Date collectTime;
    private String healthData;
    private String dataType;
    private int excp;
    private int dataId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public String getHealthData() {
        return healthData;
    }

    public void setHealthData(String healthData) {
        this.healthData = healthData;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getExcp() {
        return excp;
    }

    public void setExcp(int excp) {
        this.excp = excp;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }


}
