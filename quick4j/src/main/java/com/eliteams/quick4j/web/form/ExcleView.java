package com.eliteams.quick4j.web.form;

import com.eliteams.quick4j.web.enums.FieldNameEnum;
import com.eliteams.quick4j.web.model.SysSerialNumber;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcleView<T> extends AbstractXlsView {

	private String title;

	public ExcleView(String title) {
		this.title = title;
	}
	public ExcleView() {}

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<T> dataList = (List) model.get("list");
		//设置导出名称

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String fileName = URLEncoder.encode(title,"UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName+s.format(new Date())+".xls");
		
		Class modelClass = dataList.get(0).getClass();
		
//		Field[] fields = dataList.get(0).getClass().getDeclaredFields();
		
		String[] fields = (String[]) model.get("fieldNames");
		String[] fieldNames = getFieldNames(fields);

		Sheet sheet = workbook.createSheet();

		// 产生合并的表格标题行
		Cell header = sheet.createRow(0).createCell(0);// 第0行0列
		CellStyle columnTopStyle = getColumnTopStyle(workbook);// 获取列头样式对象
		CellStyle style = getStyle(workbook);// 单元格样式对象
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (fieldNames.length - 1)));
		header.setCellStyle(columnTopStyle);
		header.setCellValue(title);

		// 定义所需列数
		int columnNum = fieldNames.length;
		Row rowRowName = sheet.createRow(2); // 在索引2的位置创建行(最顶端的行开始的第二行)
		sheet.createFreezePane(0,3);//锁定列名行
		// 将列头设置到sheet的单元格中
		for (int n = 0; n < columnNum; n++) {
			Cell cellRowName = rowRowName.createCell(n); // 创建列头对应个数的单元格
			cellRowName.setCellType(Cell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
			RichTextString text = new HSSFRichTextString(fieldNames[n]);
			cellRowName.setCellValue(text); // 设置列头单元格的值
			cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
		}
		
		for (int i = 0; i < dataList.size(); i++) {
			Row dataRow = sheet.createRow(i+3);//从第4行开始显示数据
			for (int n = 0; n < columnNum; n++) {
				Cell cell = dataRow.createCell(n); // 创建列头对应个数的单元格
				Method m = findGetMethod(modelClass,fields[n]);
				Object value = m.invoke(dataList.get(i));
				//RichTextString text = new HSSFRichTextString(value.toString());
				//cell.setCellValue(value); // 设置列头单元格的值
				setCellValue(cell,value);
				cell.setCellStyle(style); // 设置列头单元格样式
			}
		}
		autoSizeColumn(sheet);


	}
	
	
	
	
	

	private static CellStyle getColumnTopStyle(Workbook workbook) {

		// 设置字体
		Font font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 11);
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		CellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;

	}

	/*
	 * 列数据信息单元格样式
	 */
	private static CellStyle getStyle(Workbook workbook) {
		// 设置字体
		Font font = workbook.createFont();
		// 设置字体大小
		// font.setFontHeightInPoints((short)10);
		// 字体加粗
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		CellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;

	}
	
	private String[] getFieldNames(String[] fields){
		String [] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = FieldNameEnum.valueOf(fields[i]).getTitle();
		}
		return fieldNames;
	}
	
	private static Method findGetMethod(Class<?> clazz,String fieldName){
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(fieldName, "fieldName must not be null");
		StringBuffer sb = new StringBuffer();     
	    sb.append("get");     
	    sb.append(fieldName.substring(0, 1).toUpperCase());     
	    sb.append(fieldName.substring(1));
	    try {     
	        return clazz.getMethod(sb.toString());     
	    } catch (Exception e) {
	    	
	    }
		return null;
	}

	private static void autoSizeColumn(Sheet sheet){
		int columnCount = sheet.getLastRowNum();
		for (int i = 0;i<columnCount;i++){
			sheet.autoSizeColumn(i);
		}
		int rowCount = sheet.getLastRowNum();
		for (int columnIndex = 0;columnIndex<columnCount;columnIndex++){
			//从第4行开始,前两行是title，第3行是列名
			for (int rowIndex = 3;rowIndex<rowCount;rowIndex++){
				Row row = sheet.getRow(rowIndex);
				if(row!=null){
					Cell cell = row.getCell(columnIndex);
					if(cell!=null&&cell.getCellType()==Cell.CELL_TYPE_STRING){
						String cellValue = cell.getStringCellValue();
						if(isContainChinese(cellValue)){
							int width = sheet.getColumnWidth(columnIndex);
							sheet.setColumnWidth(columnIndex,width*2);
							break;
						}
					}
					if(cell!=null&&cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
							int width = sheet.getColumnWidth(columnIndex);
							sheet.setColumnWidth(columnIndex,width*2);
							break;
					}

				}
			}
		}
//		for(Row row:sheet){
//			for (Cell cell:row){
//				if(cell.getCellType()==Cell.CELL_TYPE_STRING){
//					String cellValue = cell.getStringCellValue();
//					if(isContainChinese(cellValue)){
//						int columnIndex = cell.getColumnIndex();
//						//double this
//					}
//				}
//			}
//		}
	}

	private static boolean isContainChinese(String str){
		return str.getBytes().length!=str.length();
	}

	//单元格设置值
	public static void setCellValue(Cell cell,Object value){
		if (value instanceof Double){
			cell.setCellValue((Double)value);
		}else if (value instanceof Long){
			cell.setCellValue((Long)value);
		}else if (value instanceof Integer){
			cell.setCellValue((Integer)value);
		}else if (value instanceof Date){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cell.setCellValue(simpleDateFormat.format((Date)value));
		}else{
			RichTextString text = new HSSFRichTextString(value+"");
			cell.setCellValue(text);
		}
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
