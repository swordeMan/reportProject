package com.eliteams.quick4j.web.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.web.model.ReworkView;
import com.eliteams.quick4j.web.model.ScrapView;
import com.eliteams.quick4j.web.service.PDFReportService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PDFReportServiceImpl implements PDFReportService{
	 
	 // 建立一个Document对象        
	    private static Font headfont ;// 设置字体大小  
	    private static Font keyfont;// 设置字体大小  
	    private static Font textfont;// 设置字体大小   
	    static{  
	        BaseFont bfChinese;  
	        try {  
	          //  bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
	        bfChinese = BaseFont.createFont("C:/Windows/Fonts/SIMFANG.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED); 
	        	//bfChinese = BaseFont.createFont(); 
	        	headfont = new Font(bfChinese, 16, Font.UNDERLINE);// 设置字体大小  
	            keyfont = new Font(bfChinese, 10, Font.BOLD);// 设置标题字体大小  
	            textfont = new Font(bfChinese, 12, Font.NORMAL);// 设置字体大小  
	        } catch (Exception e) {           
	            e.printStackTrace();  
	        }   
	    }
	    //构造方法
	/*    public PDFReportServiceImpl(File file){
	    	document.setPageSize(PageSize.A4);// 设置页面大小  
	         try {  
	            PdfWriter.getInstance(document,new FileOutputStream(file));  
	            document.open();   
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }   
	    };*/
	 int maxWidth = 600;
	
	 public PdfPCell createCell(String value,com.lowagie.text.Font font,int align){  
         PdfPCell cell = new PdfPCell();  
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);          
         cell.setHorizontalAlignment(align);      
         cell.setPhrase(new Phrase(value,font));  
        return cell;  
    }  
      //
     public PdfPCell createCell(String value,com.lowagie.text.Font font){  
         PdfPCell cell = new PdfPCell();  
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);   
         cell.setPhrase(new Phrase(value,font));  
        return cell;  
    }  
  
     public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan){  
         PdfPCell cell = new PdfPCell();  
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
         cell.setHorizontalAlignment(align);      
         cell.setColspan(colspan);  
         cell.setPhrase(new Phrase(value,font)); 
         cell.setPaddingTop(4.0f);  
         cell.setPaddingBottom(5.0f); 
        return cell;  
    }  
     
    public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan,boolean boderFlag){  
         PdfPCell cell = new PdfPCell();  
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  
         cell.setHorizontalAlignment(align);      
         cell.setColspan(colspan);  
         cell.setPhrase(new Phrase(value,font));  
         cell.setPadding(3.0f);  
         if(!boderFlag){  
             cell.setBorder(0);  
             cell.setPaddingTop(3.0f);  
             cell.setPaddingBottom(3.0f);  
         }  
        return cell;  
    }  
    @Override
     public PdfPTable createTable(int colNumber){  
        PdfPTable table = new PdfPTable(colNumber);  
        try{  
            table.setTotalWidth(maxWidth);  
            table.setLockedWidth(true);  
            table.setHorizontalAlignment(Element.ALIGN_CENTER);       
            table.getDefaultCell().setBorder(1);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return table;  
    }  
     @Override
     public PdfPTable createTable(float[] widths){  
            PdfPTable table = new PdfPTable(widths);  
            try{  
                table.setTotalWidth(maxWidth);  
                table.setLockedWidth(true);  
                table.setHorizontalAlignment(Element.ALIGN_CENTER);       
                table.getDefaultCell().setBorder(1);  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
            return table;  
        }  
     @Override
     public PdfPTable createBlankTable(){  
         PdfPTable table = new PdfPTable(1);  
         table.getDefaultCell().setBorder(0);  
         table.addCell(createCell("", keyfont));           
         table.setSpacingAfter(20.0f);  
         table.setSpacingBefore(20.0f);  
         return table;  
     }
	
	
	/**
	 * 产生返修单pdf
	 */
	/*public void generateReworkPDF(List<ReworkView> reworkView)  {
		
		Document document = new Document(PageSize.A4);
	
	//	Document document = new Document(PageSize.A4.rotate());  
		      try{
	            PdfPTable table = createTable(9);  
	            //产生标题
	            File file = new File("D:\\rework\\text.pdf");  
	            file.createNewFile(); 
	            PdfWriter.getInstance(document,new FileOutputStream(file));
	            document.open(); 
	            table.addCell("返修单");
	            document.add(table);
	            PdfPCell cell = createCell("返   修   单", headfont,Element.ALIGN_CENTER,9,false);
	            
	            table.addCell(cell);
	          //空格
	          //  table.addCell(createCell("", keyfont,Element.ALIGN_CENTER,3,false));
	            table.addCell(createCell("返修单编号:", textfont, Element.ALIGN_LEFT,1)); 
	            table.addCell(createCell(reworkView.get(0).getReworkId(), textfont, Element.ALIGN_CENTER,5)); 
	            table.addCell(createCell("产品名称:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(reworkView.get(0).getMaterialDescribe(), textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("规格:", textfont, Element.ALIGN_CENTER,1));
	            table.addCell(createCell(reworkView.get(0).getMaterialId(), textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("生产车间/工序:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(reworkView.get(0).getProductionProcedure(), textfont, Element.ALIGN_CENTER,2));
	            SimpleDateFormat fs = new SimpleDateFormat("yyyy-MM-dd");
	            String reworkTime = fs.format(reworkView.get(0).getReworkTime());
	            table.addCell(createCell("返修日期:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(reworkTime, textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("返修班次:", textfont, Element.ALIGN_CENTER,1));
	            table.addCell(createCell(reworkView.get(0).getClasses(), textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("返修数量:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(reworkView.get(0).getWasteTotal().toString(), textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("", textfont, Element.ALIGN_CENTER,3)); 
	            table.addCell(createCell("原因明细", keyfont,Element.ALIGN_LEFT,9,false));
	            table.addCell(createCell("返修原因", textfont, Element.ALIGN_CENTER,3));
	            table.addCell(createCell("返修数量", textfont, Element.ALIGN_CENTER,1));
	            table.addCell(createCell("备注", textfont, Element.ALIGN_CENTER,5));
	            for(int i=0;i<6;i++){
	            	if(i<reworkView.size()){
	                table.addCell(createCell(reworkView.get(i).getReason(), textfont,Element.ALIGN_CENTER,3));  
	                table.addCell(createCell(reworkView.get(i).getReworkNum().toString(), textfont,Element.ALIGN_CENTER,1));  
	                table.addCell(createCell(reworkView.get(i).getIllustration(), textfont,Element.ALIGN_CENTER,5));  
	                }
	            	else{
	            	table.addCell(createCell("", textfont,Element.ALIGN_CENTER,3));  
	 	            table.addCell(createCell("", textfont,Element.ALIGN_CENTER,1));  
	 	            table.addCell(createCell("", textfont,Element.ALIGN_CENTER,5));  
	            	}
	            	}
	            table.addCell(createCell("审核人:", textfont, Element.ALIGN_LEFT,3,false)); 
	            table.addCell(createCell("审批:", textfont, Element.ALIGN_LEFT,3,false)); 
	            table.addCell(createCell("日期:", textfont, Element.ALIGN_LEFT,3,false)); 
	            document.add(table); 
	            document.close();  
		      }
		     catch (Exception e) {           
		            e.printStackTrace();}
		      finally {
	        		 document.close();
				} 
				
			} */ 
	/**
	 * 产生报废单pdf
	 */
	@Override
	public void generateScrapPDF(List<ScrapView> scrapView,int iNum)  {
		//Document document = new Document(PageSize.A4);
		Document document = new Document(new RectangleReadOnly(682,396));
		      try{
	            PdfPTable table = createTable(9);  
	            //产生标题
	            String fileName=scrapView.get(0).getScrapId()+String.valueOf(iNum);
	            File file = new File("D:\\scrap\\"+fileName+".pdf");  
	            file.createNewFile(); 
	            PdfWriter.getInstance(document,new FileOutputStream(file));
	            document.open(); 
	           /* table.addCell("返修单");
	            document.add(table);*/
	           table.addCell(createCell("浙江金固股份有限公司", headfont,Element.ALIGN_CENTER,9,false));
	           table.addCell(createCell("报    废    单", headfont,Element.ALIGN_CENTER,9,false));
	            table.addCell(createCell("编号：F-Q-092-A/0", textfont, Element.ALIGN_LEFT,5,false)); 
	          
	            table.addCell(createCell("报废单编号:",textfont, Element.ALIGN_RIGHT,2,false)); 
	            table.addCell(createCell(scrapView.get(0).getScrapId(),textfont, Element.ALIGN_CENTER,2,false));
	            table.addCell(createCell("客户:",textfont, Element.ALIGN_CENTER,1));  
	 	        table.addCell(createCell(scrapView.get(0).getUserSimpleName(),textfont, Element.ALIGN_CENTER,2));
	 	        //销售订单和销售订单行相加
	 	        String saleOrder=addRow(scrapView.get(0).getSaleOrderId(),scrapView.get(0).getSaleOrderRow());
	 	        		
	 	        table.addCell(createCell("销售订单:",textfont, Element.ALIGN_CENTER,1));  
	 	        table.addCell(createCell(saleOrder,textfont, Element.ALIGN_CENTER,2));
	 	        //截取生产订单，去除四个0
	 	        String productOrder=getProductOrder(scrapView.get(0).getProductOrderId());
	 	        table.addCell(createCell("生产订单:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(productOrder, textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("产品名称:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(scrapView.get(0).getMaterialDescribe(), textfont, Element.ALIGN_CENTER,2));
	           //从物料描述中获取规格
	            String guiGe=getGui(scrapView.get(0).getMaterialDescribe());
	            table.addCell(createCell("规格:", textfont, Element.ALIGN_CENTER,1));
	            table.addCell(createCell(guiGe, textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("物料号:", textfont, Element.ALIGN_CENTER,1));
	            table.addCell(createCell(scrapView.get(0).getMaterialId(), textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("生产车间/工序:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(scrapView.get(0).getProductionProcess(), textfont, Element.ALIGN_CENTER,2));
	            SimpleDateFormat fs = new SimpleDateFormat("yyyy-MM-dd");
	            String scrapTime = fs.format(scrapView.get(0).getScrapTime());
	            table.addCell(createCell("报废日期:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(scrapTime, textfont, Element.ALIGN_CENTER,2));
	        //  table.addCell(createCell("报废班次:", textfont, Element.ALIGN_CENTER,1));
	       //   table.addCell(createCell(scrapView.get(0).getClasses(), textfont, Element.ALIGN_CENTER,2));
	            table.addCell(createCell("报废数量:", textfont, Element.ALIGN_CENTER,1)); 
	            table.addCell(createCell(scrapView.get(0).getWasteTotal().toString(), textfont, Element.ALIGN_CENTER,2));
	          //报废原因明细
	            table.addCell(createCell("报废原因", textfont, Element.ALIGN_CENTER,3));
	            table.addCell(createCell("明细数量", textfont, Element.ALIGN_CENTER,1));
	            table.addCell(createCell("备注", textfont, Element.ALIGN_CENTER,5));
	            for(int i=0;i<6;i++){
	            	if(i<scrapView.size()){
	                table.addCell(createCell(scrapView.get(i).getsReason(), textfont,Element.ALIGN_CENTER,3));  
	                table.addCell(createCell(scrapView.get(i).getScrapNum().toString(), textfont,Element.ALIGN_CENTER,1));  
	                table.addCell(createCell(scrapView.get(i).getIllustration(), textfont,Element.ALIGN_CENTER,5));  
	            } 
            	else{
            	table.addCell(createCell("\b", textfont,Element.ALIGN_CENTER,3));  
 	            table.addCell(createCell("\b", textfont,Element.ALIGN_CENTER,1));  
 	            table.addCell(createCell("\b", textfont,Element.ALIGN_CENTER,5));  
            	}
	            }
	          //  table.addCell(createCell("",textfont ,Element.ALIGN_CENTER,9,false));
	            table.addCell(createCell("检验员:"+"\b"+scrapView.get(0).getInspector(), textfont, Element.ALIGN_LEFT,3,false)); 	            
	            table.addCell(createCell("审批:", textfont, Element.ALIGN_LEFT,3,false)); 
	            table.addCell(createCell("日期:", textfont, Element.ALIGN_LEFT,3,false)); 
	            document.add(table); 
	            }
		     catch (Exception e) {           
		            e.printStackTrace();}
	        	 finally {
	        		 document.close();
				}
			}  
	         
	        /**
	         * 从物料描述中截取规格
	         */
	        public String getGui(String materialDescribe){
	        	//获取第2次出现"/"的位置
	        	int d2=getCharacterPosition(materialDescribe);
	        	String guiGe = materialDescribe.substring(materialDescribe.indexOf("/")+1,d2);
				return guiGe;	
	        }
	        /**
	         * 截取生产订单
	         */
	        public String getProductOrder(String productOrder){
	        	String productOrderId=productOrder.substring(4);
	        	
				return productOrderId;
	        }

	        /**
	         * 获取字符串中第n个符合的位置
	         */
	        public int getCharacterPosition(String materialDescribe){
	    	    //这里是获取"/"符号的位置
	    	    Matcher slashMatcher = Pattern.compile("/").matcher(materialDescribe);
	    	    int mIdx = 0;
	    	    while(slashMatcher.find()) {
	    	       mIdx++;
	    	       //当"/"符号第二次出现的位置
	    	       if(mIdx == 2){
	    	          break;
	    	       }
	    	    }
	    	    return slashMatcher.start();
	    	}
	        /**
	         * 销售订单加销售订单行
	         */
	        public String addRow(String string1,String string2){
	        	if(string2!=null&&!string2.equals("")){
	        		String str = string2;// 测试用字符串
		        	int len = str.length();//取得字符串的长度
		        	int index = 0;//预定义第一个非零字符串的位置

		        	char strs[] = str.toCharArray();// 将字符串转化成字符数组
		        	for(int i=0; i<len; i++){
		        	if('0'!=strs[i]){
		        	index=i;// 找到非零字符串并跳出
		        	break;
		        	}
		        	}
					String strLast = str.substring(index);
					String string3=string1+"/"+strLast;
		        	return string3;
	        	}else{String string4="信息缺失";
        		return string4;
	        	
	        }
	}
}

