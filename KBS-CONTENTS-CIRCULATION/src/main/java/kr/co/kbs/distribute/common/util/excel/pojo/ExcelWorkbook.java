package kr.co.kbs.distribute.common.util.excel.pojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class ExcelWorkbook {

	private String fileName;
	private Collection<ExcelWorksheet> sheets = new ArrayList<ExcelWorksheet>();
	
	public void addExcelWorksheet(ExcelWorksheet sheet) {
		sheets.add(sheet);
	}
	
	public String toJson(boolean pretty) {
		ObjectMapper om = null;
		ObjectWriter  ow = null;
		String returnStr ="";
		try {
			om = new ObjectMapper();
			ow=om.writer();
			if(pretty) {
				returnStr = ow.withDefaultPrettyPrinter().writeValueAsString(this);	
			} else {
				returnStr = ow.withPrettyPrinter(null).writeValueAsString(this);	
			}
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			 try {
//				if(ow!=null)ow.notifyAll();
//				if(om!=null)om.notifyAll();
					ow = null;
					om = null;
             } catch (Exception e) {
                 e.printStackTrace();
             }
			
			return returnStr;
		}
		
	}
	
	
	// GET/SET
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Collection<ExcelWorksheet> getSheets() {
		return sheets;
	}

	public void setSheets(Collection<ExcelWorksheet> sheets) {
		this.sheets = sheets;
	}

}