package at.fh.swenga.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import at.fh.swenga.model.Character;


public class ExcelCharacterReportView extends AbstractXlsxView {

	//needs to have exactly this signature, workbook = baseclass of excel sheets
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
 
 
		// change the file name, xls old, xlsx new
        response.setHeader("Content-Disposition", "attachment; filename=\"character.xlsx\"");
 
 
		Character character = (Character)model.get("character") ;
 
		// ------------------------------------------------------
		// recommended go through examples and copy
		// APACHE POI Documenations and examples:
		// https://poi.apache.org/spreadsheet/index.html
		// ------------------------------------------------------
 
		// create a worksheet
		Sheet sheet = workbook.createSheet("Character " + character.getName());
 
		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColorPredefined.BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setBold(true);
		font.setColor(HSSFColorPredefined.WHITE.getIndex());
		style.setFont(font);
 
		// create a new row in the worksheet
		Row headerRow = sheet.createRow(0);
 
		// create a new cell in the row 
		Cell cell0 = headerRow.createCell(0);
		cell0.setCellValue("Character Name");
		cell0.setCellStyle(style);
 
		// create a new cell in the row 
		Cell cell1 = headerRow.createCell(1);
		cell1.setCellValue("Strength");
		cell1.setCellStyle(style);
 
		// create a new cell in the row 
		Cell cell2 = headerRow.createCell(2);
		cell2.setCellValue("Dexterity");
		cell2.setCellStyle(style);
		
		Cell cell3 = headerRow.createCell(3);
		cell3.setCellValue("Intelligence");
		cell3.setCellStyle(style);
		
		Cell cell4 = headerRow.createCell(4);
		cell4.setCellValue("Constitution");
		cell4.setCellStyle(style);
		
		Cell cell5 = headerRow.createCell(5);
		cell5.setCellValue("Vitality");
		cell5.setCellStyle(style);
		
		Cell cell6 = headerRow.createCell(6);
		cell6.setCellValue("Wisdom");
		cell6.setCellStyle(style);
		
		Cell cell7 = headerRow.createCell(7);
		cell7.setCellValue("Charisma");
		cell7.setCellStyle(style);
		
		Cell cell8 = headerRow.createCell(8);
		cell8.setCellValue("Gender");
		cell8.setCellStyle(style);
		
		Cell cell9 = headerRow.createCell(9);
		cell9.setCellValue("Lore");
		cell9.setCellStyle(style);
		 
		// create multiple rows with employee data
		Row row = sheet.createRow(1);
		row.createCell(0).setCellValue(character.getName());
		row.createCell(1).setCellValue(character.getStrength());
		row.createCell(2).setCellValue(character.getDexterity());
		row.createCell(3).setCellValue(character.getIntelligence());
		row.createCell(4).setCellValue(character.getConstitution());
		row.createCell(5).setCellValue(character.getVitality());
		row.createCell(6).setCellValue(character.getWisdom());
		row.createCell(7).setCellValue(character.getCharisma());
		row.createCell(8).setCellValue(character.getGender());
		row.createCell(9).setCellValue(character.getHistory());

 
		// adjust column width to fit the content
		sheet.autoSizeColumn((short) 0);
		sheet.autoSizeColumn((short) 1);
		sheet.autoSizeColumn((short) 2);
		sheet.autoSizeColumn((short) 3);
		sheet.autoSizeColumn((short) 4);
		sheet.autoSizeColumn((short) 5);
		sheet.autoSizeColumn((short) 6);
		sheet.autoSizeColumn((short) 7);
		sheet.autoSizeColumn((short) 8);
		sheet.autoSizeColumn((short) 9);
		
 
	}
		// nothing special needed at the end, spring handles everything
}
