package com.eliteams.quick4j.web.model;

public class ReworkReasonRelation {
    private Long id;

    private String reworkId;

    private Long reworkReasonId;

    private String illustration;

    private Integer reworkNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getReworkId() {
		return reworkId;
	}

	public void setReworkId(String reworkId) {
		this.reworkId = reworkId == null ? null : reworkId.trim();
	}

	public Long getReworkReasonId() {
		return reworkReasonId;
	}

	public void setReworkReasonId(Long reworkReasonId) {
		this.reworkReasonId = reworkReasonId;
	}
	
    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration == null ? null : illustration.trim();
    }

    public Integer getReworkNum() {
        return reworkNum;
    }

    public void setReworkNum(Integer reworkNum) {
        this.reworkNum = reworkNum;
    }

	

	

	
}