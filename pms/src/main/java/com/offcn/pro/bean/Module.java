package com.offcn.pro.bean;

public class Module {
    private Integer id;

    private String proname;

    private Project project;

    private Integer analysisFk;

    private Analysis analysis;

    private String modname;

    private String level;

    private String simpledis;

    private String detaileddis;

    private String remark;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public Integer getAnalysisFk() {
        return analysisFk;
    }

    public void setAnalysisFk(Integer analysisFk) {
        this.analysisFk = analysisFk;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname == null ? null : modname.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getSimpledis() {
        return simpledis;
    }

    public void setSimpledis(String simpledis) {
        this.simpledis = simpledis == null ? null : simpledis.trim();
    }

    public String getDetaileddis() {
        return detaileddis;
    }

    public void setDetaileddis(String detaileddis) {
        this.detaileddis = detaileddis == null ? null : detaileddis.trim();
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", proname='" + proname + '\'' +
                ", project=" + project +
                ", analysisFk=" + analysisFk +
                ", analysis=" + analysis +
                ", modname='" + modname + '\'' +
                ", level='" + level + '\'' +
                ", simpledis='" + simpledis + '\'' +
                ", detaileddis='" + detaileddis + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public void setRemark(String remark) {

        this.remark = remark == null ? null : remark.trim();
    }
}