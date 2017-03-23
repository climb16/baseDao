package cn.com.role.model;

import java.util.Date;

public class Role {
    private int rId;
    private String rName;
    private Date updateTime;
    private int state;
    private String depict;

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public String getRname() {
        return rName;
    }

    public void setRName(String rName) {
        this.rName = rName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }
}
