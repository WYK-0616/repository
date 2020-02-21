package com.offcn.emp.bean;

public class EmpRole {
    private Integer eid;
    private Integer rid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        return "EmpRole{" +
                "eid=" + eid +
                ", rid=" + rid +
                '}';
    }
}
