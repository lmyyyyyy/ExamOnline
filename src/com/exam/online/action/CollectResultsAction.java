package com.exam.online.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Paper;
import com.exam.online.domain.Results;
import com.exam.online.service.PaperService;
import com.exam.online.service.ResultsService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CollectResultsAction extends ActionSupport{

	private static final long serialVersionUID = -5907018416751820493L;

	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String execute(){
		
		return SUCCESS;
	}
	
	private String ename;
	
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	@Resource
	private PaperService paperService;
	@Resource
	private ResultsService resultsService;
	
	public InputStream getIs() {
		try {
		/*	HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();*/
			
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("学生成绩表");
			
			Paper paper = paperService.getPaper(sid);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.ename = (sf.format(new Date()).toString())+ ".xls";
			//this.ename = paper.getName();
			List<Results> list = resultsService.getCurrentResults(paper.getNumber());
			
				
				HSSFCellStyle style = wb.createCellStyle();
				style.setWrapText(true);
				
				//第一行表头数据
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell1 = row.createCell(0);
				HSSFCell cell2 = row.createCell(1);
				HSSFCell cell3 = row.createCell(2);
				HSSFCell cell4 = row.createCell(3);
				//定义单元格为字符串类型
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
				//单元格中的内容
				cell1.setCellValue("学号");
				cell2.setCellValue("姓名");
				cell3.setCellValue("班级");
				cell4.setCellValue("总分");
				
				//统计excel行数，填充数据
				for (int i=0; i<list.size(); i++) {
					row = sheet.createRow(i+1);
					Results r = list.get(i);
					if (r.getTotalScore() == null) {
						r.setTotalScore(0);
					}
					//创建单元格，并设置值
					row.createCell(0).setCellValue(r.getSnumber());
					row.createCell(1).setCellValue(r.getSname());
					row.createCell(2).setCellValue(r.getSclas());
					row.createCell(3).setCellValue(r.getTotalScore());
				}
				ByteArrayOutputStream boas = new ByteArrayOutputStream();
				wb.write(boas);
				ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(boas.toByteArray());
				return byteArrayIS;
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
