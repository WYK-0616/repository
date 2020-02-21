package com.offcn.system.bean;

public class RoleSources {
    private Integer sid;
    private Integer rid;

    public Integer getRid() {
        return rid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "RoleSources{" +
                "sid=" + sid +
                ", rid=" + rid +
                '}';
    }
}
