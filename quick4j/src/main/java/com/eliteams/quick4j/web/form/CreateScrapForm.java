package com.eliteams.quick4j.web.form;

import com.eliteams.quick4j.web.model.Scrap;
import com.eliteams.quick4j.web.model.ScrapReasonRelation;

public class CreateScrapForm {
	
	private Scrap scrap;
	
	private ScrapReasonRelation[] scrapReasonRelation;
	
	
	public Scrap getScrap() {
		return scrap;
	}
	public void setScrap(Scrap scrap) {
		this.scrap = scrap;
	}
	public ScrapReasonRelation[] getScrapReasonRelation() {
		return scrapReasonRelation;
	}
	public void setScrapReasonRelation(ScrapReasonRelation[] scrapReasonRelation) {
		this.scrapReasonRelation = scrapReasonRelation;
	}
	
	

}
