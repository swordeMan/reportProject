package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.web.model.ReworkView;
import com.eliteams.quick4j.web.model.ScrapView;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public interface PDFReportService {
	public PdfPCell createCell(String value,com.lowagie.text.Font font,int align);
	public PdfPCell createCell(String value,com.lowagie.text.Font font);
	public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan);
	public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan,boolean boderFlag);
	public PdfPTable createTable(int colNumber);
	public PdfPTable createTable(float[] widths);
	public PdfPTable createBlankTable();
	//形成返修pdf
   
	public void generateReworkPDF(List<ReworkView> reworkView);
	//形成报废pdf
	public void generateScrapPDF(List<ScrapView> reworkView,int iNum);
	
}
