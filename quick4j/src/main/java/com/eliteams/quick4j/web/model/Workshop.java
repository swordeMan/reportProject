package com.eliteams.quick4j.web.model;

public class Workshop {
    private Integer id;

    private String workshop;

    private String factory;

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

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }
}