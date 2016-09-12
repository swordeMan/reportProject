package com.eliteams.quick4j.web.model;

public class Workshop {
    private Integer id;

    private String workshop;

    private String print;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getworkshop() {
        return workshop;
    }

    public void setworkshop(String workshop) {
        this.workshop = workshop == null ? null : workshop.trim();
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print == null ? null : print.trim();
    }
}