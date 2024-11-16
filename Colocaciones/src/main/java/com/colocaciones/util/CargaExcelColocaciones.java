package com.colocaciones.util;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.colocaciones.model.Colocacion;

public class CargaExcelColocaciones {
	

	
	public void cargarArchivoColocaciones(InputStream is, List<Colocacion> colocaciones) throws IOException{
		 
		Workbook workbook = new HSSFWorkbook(is);
		
		 String nombreHoja = workbook.getSheetName(0);
		 
		 if(!nombreHoja.equalsIgnoreCase("ACCESS")) {
			 System.out.println("ERROR EN NOMBRE DE HOJA");
		 }
		 
		 this.getDataColocaciones(workbook, 1, colocaciones);
	}


	private void getDataColocaciones(Workbook workbook, int inicioDatos, List<Colocacion> colocaciones) throws IOException{
		Sheet sheet = workbook.getSheetAt(0);
        
        Iterator<Row> rows = sheet.iterator();
        int rowNumber = 0;
        
        while (rows.hasNext()) {
        	 Row currentRow = rows.next();
             if (rowNumber < inicioDatos) {
                 rowNumber++;
                 continue;
             }	
  
             Colocacion colocacion = this.obtenerDatoCOlocacion(currentRow);
		
             colocaciones.add(colocacion);
             
             rowNumber++;
        }
        
        workbook.close();
        System.out.println("TERMINA CARGA EN MEMORIA");
	}
	

	private Colocacion obtenerDatoCOlocacion(Row  currentRow) {
		Iterator<Cell> cellsInRow = currentRow.iterator();

		Colocacion colocacion = new Colocacion();

		int cellIdx = 0;

		while (cellsInRow.hasNext()) {
			Cell currentCell = cellsInRow.next();
			switch (cellIdx) {
			case 0:
				colocacion.setId((int)currentCell.getNumericCellValue());
				break;
			case 1:
				colocacion.setFechaCorte(this.convertDateToLocalDate(currentCell.getDateCellValue()));
				break;
			case 2:
				colocacion.setDptId(currentCell.getStringCellValue());
				break;
			case 3:
				colocacion.setDptNombre(currentCell.getStringCellValue());
				break;	
			case 4:
				colocacion.setMpoId(currentCell.getStringCellValue());
				break;	
			case 5:
				colocacion.setMpoNombre(currentCell.getStringCellValue());
				break;	
			case 6:
				colocacion.setCiiuCod(currentCell.getStringCellValue());
				break;
			case 7:
				colocacion.setCiiuNombre(currentCell.getStringCellValue());
				break;
			case 8:
				colocacion.setDenId(currentCell.getStringCellValue());
				break;	
			case 9:
				colocacion.setIndNombre(currentCell.getStringCellValue());
				break;	
			case 10:
				colocacion.setOcuId(currentCell.getStringCellValue());
				break;	
			case 11:
				colocacion.setOcupacion(currentCell.getStringCellValue());
				break;
			case 12:
				colocacion.setGenero(currentCell.getStringCellValue());
				break;
			/*	
			case 13:
				break;	
			case 14:
				break;	
			case 15:
				break;	
			case 16:
				break;
			case 17:
				break;
			case 18:
				break;	
			case 19:
				break;	
			case 20:
				break;	
			case 21:
				break;
			case 22:
				break;
			case 23:
				break;	
			case 24:
				break;	
				*/
			case 25:
				colocacion.setEdad((int)currentCell.getNumericCellValue());
				break;	
			case 26:
				colocacion.setSueldoMes(currentCell.getNumericCellValue());
				break;
			case 27:
				colocacion.setTotalColoca(currentCell.getNumericCellValue());
				break;
			case 28:
				colocacion.setFechaCargue(this.convertDateToLocalDate(currentCell.getDateCellValue()));
				break;	
			case 29:
				colocacion.setSsmaTimeStamp(this.convertDateToLocalDate(currentCell.getDateCellValue()));
				break;	
			default:
				break;
			}
		}
		
		return colocacion;
	}
	
	
    private LocalDateTime convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

	
}



