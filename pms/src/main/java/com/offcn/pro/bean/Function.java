package com.offcn.pro.bean;

public class Function {
    private Integer id;

    private String proname;

    private Project project;

    private String analysisname;

    private Analysis analysis;

    private Integer modeleFk;

    private Module module1;

    private String functionname;

    private String level;

    private String simpledis;

    private String detaileddis;

    private String remark;

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", proname='" + proname + '\'' +
                ", project=" + project +
                ", analysisname='" + analysisname + '\'' +
                ", analysis=" + analysis +
                ", modeleFk=" + modeleFk +
                ", module1=" + module1 +
                ", functionname='" + functionname + '\'' +
                ", level='" + level + '\'' +
                ", simpledis='" + simpledis + '\'' +
                ", detaileddis='" + detaileddis + '\'' +
                ", remark='" + remark + '\'' +
                '}';
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
        this.proname = proname;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getAnalysisname() {
        return analysisname;
    }

    public void setAnalysisname(String analysisname) {
        this.analysisname = analysisname;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public Integer getModeleFk() {
        return modeleFk;
    }

    public void setModeleFk(Integer modeleFk) {
        this.modeleFk = modeleFk;
    }

    public Module getModule1() {
        return module1;
    }

    public void setModule1(Module module1) {
        this.module1 = module1;
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSimpledis() {
        return simpledis;
    }

    public void setSimpledis(String simpledis) {
        this.simpledis = simpledis;
    }

    public String getDetaileddis() {
        return detaileddis;
    }

    public void setDetaileddis(String detaileddis) {
        this.detaileddis = detaileddis;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}