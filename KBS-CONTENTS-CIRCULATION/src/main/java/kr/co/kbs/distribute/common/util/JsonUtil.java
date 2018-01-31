package kr.co.kbs.distribute.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverter;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverterConfig;
import kr.co.kbs.distribute.common.util.excel.pojo.ExcelWorkbook;

public class JsonUtil {

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	*/
	public JSONObject getJsonObject(String excelDataFile) {
		JSONObject jsonObj = null;
		try {
			ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
			config.setSourceFile(excelDataFile);
			
			ExcelToJsonConverter convert = new ExcelToJsonConverter(config);
			ExcelWorkbook book = convert.convert();
			String jsonStr = book.toJson(config.isPretty());
			System.out.println(jsonStr.length());
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jsonStr);
			jsonObj = (JSONObject) obj;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonObj;
	}
	
	private static String readCsv(String filename) {
		return readCsv(filename, "EUC-KR");
	}
	
	
	
	
	public static String readCsv(String filename, String charset) {
		BufferedReader br = null;
        String line;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), charset));
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
	
	
	public static String CSVtoJSON(String output) {

	    String[] lines = output.split("\n");

	    StringBuilder builder = new StringBuilder();
	    builder.append('[');
	    String[] headers = new String[0];

	    //CSV TO JSON
	    for (int i = 0; i < lines.length; i++) {
	    	if(lines[i].indexOf("\"")>0) {
	    		
	    		String[] tempLine = lines[i].split("\"");
	    		String tempStr ="";
	    		for(int tempChar=0;tempChar<tempLine.length;tempChar++) {
	    			if(tempChar%2==1) {
	    				tempLine[tempChar] = tempLine[tempChar].replace(",", " ");
	    			}
	    			tempStr +=tempLine[tempChar];
	    		}
	    		lines[i] = tempStr;
	    	}
	    	
	    	String[] values = lines[i].replaceAll("\"", "").split(",");

	        if (i == 0) //INDEX LIST
	        {
	            headers = values;
	        } else {
	            builder.append('{');
	            for (int j = 0; j < values.length && j < headers.length; j++) {

	                String jsonvalue = "\"" + j  + "\":\"" + values[j].trim() + "\"";
	                if (j != values.length - 1) { //if not last value of values...
	                    jsonvalue += ',';
	                }
	                builder.append(jsonvalue);
	            }
	            builder.append('}');
	            if (i != lines.length - 1) {
	                builder.append(',');
	            }
	        }
	    }
	    builder.append(']');
	    output = builder.toString();

	    return output;
	}
}
