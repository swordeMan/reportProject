package com.eliteams.quick4j.web.model;

public class ScrapReasonRelation {
    private Long id;

    private String scrapId;

    private String sReasonId;

    private String illustration;

    private Integer scrapNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScrapId() {
        return scrapId;
    }

    public void setScrapId(String scrapId) {
        this.scrapId = scrapId == null ? null : scrapId.trim();
    }

    public String getsReasonId() {
        return sReasonId;
    }

    public void setsReasonId(String sReasonId) {
        this.sReasonId = sReasonId == null ? null : sReasonId.trim();
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration == null ? null : illustration.trim();
    }

    public Integer getScrapNum() {
        return scrapNum;
    }

    public void setScrapNum(Integer scrapNum) {
        this.scrapNum = scrapNum;
    }
}