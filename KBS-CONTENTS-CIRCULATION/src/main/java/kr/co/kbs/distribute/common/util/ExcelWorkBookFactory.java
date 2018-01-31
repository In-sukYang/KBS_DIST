package kr.co.kbs.distribute.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Created by medialog on 2017-03-09.
 */
public class ExcelWorkBookFactory {

	private Workbook workbook = null;

	private ExcelWorkBookFactory(int rowOverToDisk) {
		workbook = new SXSSFWorkbook(rowOverToDisk);
	}

	public static ExcelWorkBookFactory create(int rowOverToDisk) {
		return new ExcelWorkBookFactory(rowOverToDisk);
	}

	public ExcelSheetFactory sheet(String sheetName) {
		ExcelSheetFactory factory = new ExcelSheetFactory(this, workbook.createSheet(sheetName));
		return factory;
	}

	public byte[] make() throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			workbook.write(out);
			return out.toByteArray();
		} catch (IOException e) {
			throw new Exception();
		} finally {
			try {
				out.close();
			} catch (IOException ignore) {}
		}
	}

	public final class ExcelSheetFactory {
		private ExcelWorkBookFactory parent = null;
		private Sheet sheet = null;
		private int rowNum = 0;

		public ExcelSheetFactory(ExcelWorkBookFactory parent, Sheet sheet) {
			this.parent = parent;
			this.sheet = sheet;
		}

		public ExcelSheetFactory headers(int startingPoint, String... headers) {
			Row row = sheet.createRow(++rowNum);
			for (int cellnum = 0; cellnum < headers.length; cellnum++) {
				Cell cell = row.createCell(startingPoint + cellnum, Cell.CELL_TYPE_STRING);
				cell.setCellValue(headers[cellnum]);
			}
			return this;
		}

		public ExcelSheetFactory headers(String... headers) {
			return headers(0, headers);
		}

		public ExcelSheetFactory rowCellValues(List<?> list, CellValueRef ref) {
			for (int i = 0; i < list.size(); i++) {
				Row row = sheet.createRow(++rowNum);
				ref.setRowData(row, list.get(i));
			}
			return this;
		}

		public ExcelWorkBookFactory end() {
			return parent;
		}
	}

	public interface CellValueRef<T> {
		void setRowData(Row row, T t);
	}
}