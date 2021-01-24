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

/**
 * 
 *This class is used to manage an history of scores 
 *obtained after a game of solitary tic-tac-toe.
 *
 *It takes the path of an xls file as a parameter of its constructor.
 * 
 **/

public class HistoryManager {
	
	private final File historyFile; 
	private DateFormat DATE = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT);
	
	//Constructor
	public HistoryManager(String historyFilePath) {
		this.historyFile = new File(historyFilePath); 
	}
	
	/**
	 * This method is used to store informations after an game
	 * informations are stored in the class parametre "historyfile"
	 * 
	 * @param name : A String which correspond to the name of the player or the computeur
	 * @param score : An int which correspond to the score obtained by the player
	 * @param gameVersion : A String which correspond to the version of the "solitary tic-tac-to (5D or 5T)
	 * 
	 * @throws IOExeption
	 * 
	 * @return : void
	 * 
	 **/
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
	
	/**
	 * This method is used to insert an record in a row of xls file
	 * 
	 * @param row : A XXSFRow object which represent the row where the record have to be store
	 * @param name : A String which correspond to the name of the player or the computeur
	 * @param score : An int which correspond to the score obtained by the player
	 * @param gameVersion : A String which correspond to the version of the "solitary tic-tac-to (5D or 5T)
	 * @param cellStyleDate : A XSSFCellStyle object which represente the date style use to store the current date
	 * 
	 * @return : void
	 * 
	 **/
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
	

}