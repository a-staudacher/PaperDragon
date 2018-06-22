package at.fh.swenga.report;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.itextpdf.text.pdf.AcroFields.Item;

import at.fh.swenga.model.Character;
import at.fh.swenga.model.ItemBaseModel;
import at.fh.swenga.model.ItemModel;


public class ExcelCharacterReportView extends AbstractXlsxView {

	//needs to have exactly this signature, workbook = baseclass of excel sheets
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
 
 
		// change the file name, xls old, xlsx new
        response.setHeader("Content-Disposition", "attachment; filename=\"character.xlsx\"");
 
 
		Character character = (Character)model.get("character") ;
		Set<ItemModel> items = character.getItems();
 
		// ------------------------------------------------------
		// recommended go through examples and copy
		// APACHE POI Documenations and examples:
		// https://poi.apache.org/spreadsheet/index.html
		// ------------------------------------------------------
 
		// create a worksheet
		Sheet sheet = workbook.createSheet("Character " + character.getName());
 
		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		CellStyle equipstyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColorPredefined.BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		equipstyle.setFillForegroundColor(HSSFColorPredefined.GREEN.getIndex());
		equipstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setBold(true);
		font.setColor(HSSFColorPredefined.WHITE.getIndex());
		style.setFont(font);
		equipstyle.setFont(font);
 
		// create a new row in the worksheet
		Row headerRow = sheet.createRow(0);
		Row headerRow2 = sheet.createRow(3);
 
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
		
		// ------------------------------------------------------
		
		Cell cella0 = headerRow2.createCell(0);
		cella0.setCellValue("Item Name");
		cella0.setCellStyle(style);
		
		Cell cella1 = headerRow2.createCell(1);
		cella1.setCellValue("Description");
		cella1.setCellStyle(style);
		
		Cell cella2 = headerRow2.createCell(2);
		cella2.setCellValue("Strength");
		cella2.setCellStyle(style);
		
		Cell cella3 = headerRow2.createCell(3);
		cella3.setCellValue("Dexterity");
		cella3.setCellStyle(style);
		
		Cell cella4 = headerRow2.createCell(4);
		cella4.setCellValue("Intelligence");
		cella4.setCellStyle(style);
		
		Cell cella5 = headerRow2.createCell(5);
		cella5.setCellValue("Constitution");
		cella5.setCellStyle(style);
		
		Cell cella6 = headerRow2.createCell(6);
		cella6.setCellValue("Vitality");
		cella6.setCellStyle(style);
		
		Cell cella7 = headerRow2.createCell(7);
		cella7.setCellValue("Wisdom");
		cella7.setCellStyle(style);
		
		Cell cella8 = headerRow2.createCell(8);
		cella8.setCellValue("Charisma");
		cella8.setCellStyle(style);
		
		Cell cella9 = headerRow2.createCell(9);
		cella9.setCellValue("Price");
		cella9.setCellStyle(style);
		
		
		
		
		
		 
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
		
		int i = 4;
		for(ItemModel item : items )
		{
			
			Row row5 = sheet.createRow(i);
			Cell cell_0 = row5.createCell(0);
			cell_0.setCellValue(item.getItemBase().getName());
			
			Cell cell_1 = row5.createCell(1);
			cell_1.setCellValue(item.getItemBase().getText());
			
			Cell cell_2 = row5.createCell(2);
			cell_2.setCellValue(item.getItemBase().getStrength());
			
			Cell cell_3 = row5.createCell(3);
			cell_3.setCellValue(item.getItemBase().getDexterity());
			
			Cell cell_4 = row5.createCell(4);
			cell_4.setCellValue(item.getItemBase().getInteligenz());
			
			Cell cell_5 = row5.createCell(5);
			cell_5.setCellValue(item.getItemBase().getConstitution());
			
			Cell cell_6 = row5.createCell(6);
			cell_6.setCellValue(item.getItemBase().getVitality());
			
			Cell cell_7 = row5.createCell(7);
			cell_7.setCellValue(item.getItemBase().getWisdom());
			
			Cell cell_8 = row5.createCell(8);
			cell_8.setCellValue(item.getItemBase().getCharisma());
			
			Cell cell_9 = row5.createCell(9);
			cell_9.setCellValue(item.getItemBase().getPrice());
			
			if(item.isEquipped())
			{
				cell_0.setCellStyle(equipstyle);
				cell_1.setCellStyle(equipstyle);
				cell_2.setCellStyle(equipstyle);
				cell_3.setCellStyle(equipstyle);
				cell_4.setCellStyle(equipstyle);
				cell_5.setCellStyle(equipstyle);
				cell_6.setCellStyle(equipstyle);
				cell_7.setCellStyle(equipstyle);
				cell_8.setCellStyle(equipstyle);
				cell_9.setCellStyle(equipstyle);

			}
			
			i++;
		}
 
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
