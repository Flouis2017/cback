package com.flouis.counter.entity;

public class Stock {
    private Long id;

    private String code;

    private String name;

    private String abbrname;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAbbrname() {
        return abbrname;
    }

    public void setAbbrname(String abbrname) {
        this.abbrname = abbrname == null ? null : abbrname.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}