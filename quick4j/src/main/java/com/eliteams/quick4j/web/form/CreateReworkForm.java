package com.eliteams.quick4j.web.form;
import com.eliteams.quick4j.web.model.Rework;
import com.eliteams.quick4j.web.model.ReworkReasonRelation;

public class CreateReworkForm {
  private Rework rework;
  private ReworkReasonRelation[] reworkReasonRelation;
public Rework getRework() {
	return rework;
}
public void setRework(Rework rework) {
	this.rework = rework;
}
public ReworkReasonRelation[] getReworkReasonRelation() {
	return reworkReasonRelation;
}
public void setReworkReasonRelation(ReworkReasonRelation[] reworkReasonRelation) {
	this.reworkReasonRelation = reworkReasonRelation;
}


  
}
