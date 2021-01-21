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
	
	File Fichier; 
	DateFormat DATE = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT);
	
	//Constructor
	public HistoryManager(String chemin) {
		this.Fichier = new File(chemin); 
	}
	
	public void saveScore (String nom, int score, String game_version) throws IOException {
		// workbook creation
		FileInputStream fileStream = new FileInputStream(this.Fichier);
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//date-time style creation
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFCreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h.mm;@"));
		
		// find number of lines so the line where we want to write the record
		int last =sheet.getPhysicalNumberOfRows ();
		XSSFRow row = sheet.createRow(last);
		
		// Name Insertion 
		XSSFCell cellname = row.createCell(0);
		cellname.setCellValue(nom);
		System.out.println("nom in history: " +nom);
		// Date insertion 
		XSSFCell celldate = row.createCell(1);
		celldate.setCellValue(new Date());
		celldate.setCellStyle(cellStyle);
		
		// Score insertion 
		XSSFCell cellscore = row.createCell(2);
		cellscore.setCellValue(score);
		System.out.println("score: " +score);
		// Score insertion 
		XSSFCell cellversion = row.createCell(3);
		cellversion.setCellValue(game_version);
		System.out.println("game_version : " + game_version);
		
	    try (OutputStream fileOut = new FileOutputStream(this.Fichier)) {
	    	workbook.write(fileOut);
	        fileOut.close();
	    }
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