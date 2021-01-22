package fr.dauphine.ja.M1MiageApp.projet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import org.apache.poi.xssf.usermodel.XSSFCell; 
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;

public class HistoryManager {
	
	File historyFile; 
	DateFormat DATE = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT);
	
	//Constructor
	public HistoryManager(String historyFilePath) {
		this.historyFile = new File(historyFilePath); 
	}
		
	public void saveScore (String name, int score, String gameVersion) throws IOException {
		// workbook creation
		FileInputStream fileStream = new FileInputStream(this.historyFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//date-time style creation
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFCreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h.mm;@"));
		
		// find number of lines so the line where we want to write the record
		int lastRow =sheet.getPhysicalNumberOfRows ();
		XSSFRow row = sheet.createRow(lastRow);

		insertRecord ( row, name,  score,  gameVersion ,  cellStyle);
		
	    try (OutputStream fileOut = new FileOutputStream(this.historyFile)) {
	    	workbook.write(fileOut);
	        fileOut.close();
	    }  
	}
	
	
	private void insertRecord (XSSFRow row,String name, int score, String gameVersion , XSSFCellStyle cellStyleDate) {
		// Name Insertion 
		XSSFCell cellName = row.createCell(0);
		cellName.setCellValue(name);

		// Date insertion 
		XSSFCell cellDate = row.createCell(1);
		cellDate.setCellValue(new Date());
		cellDate.setCellStyle(cellStyleDate);
		
		// Score insertion 
		XSSFCell cellScore = row.createCell(2);
		cellScore.setCellValue(score);

		// Score insertion 
		XSSFCell cellVersion = row.createCell(3);
		cellVersion.setCellValue(gameVersion);
	}
	
	public HashMap getPodium() {
		HashMap bestscores = new HashMap();
		
		return bestscores;
	}
	

}

















/*
XSSFRow row = null;
XSSFCell cell = null;

for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {
   row = (XSSFRow) rowIt.next();
   for (Iterator cellIt = row.cellIterator(); cellIt.hasNext();) {
     cell = (XSSFCell) cellIt.next();

   }
   	      }
 */