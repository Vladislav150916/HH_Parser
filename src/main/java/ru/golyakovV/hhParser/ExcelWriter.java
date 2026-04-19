package ru.golyakovV.hhParser;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.golyakovV.hhParser.model.Vacancy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ExcelWriter {
    private String vacancyName;
    private String area;
    private String filePath;
    private LocalDate date = LocalDate.now();

    public ExcelWriter(Map<String, String> parameters){
        vacancyName = parameters.get("vacancyName");
        area = parameters.get("area");
        filePath = "src/main/java/" + vacancyName + "-" + area + "-" + date + ".xlsx";
        System.out.println(filePath);
    }

    public void write(List<Vacancy> vacancies){

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Вакансии");



        Row headerRow = sheet.createRow(0);
        headerRow.createCell(1).setCellValue("Название вакансии");
        headerRow.createCell(2).setCellValue("Работодатель");
        headerRow.createCell(3).setCellValue("Зарплата от");
        headerRow.createCell(4).setCellValue("Зарплата до");

        for (int i = 0; i < vacancies.size(); i++){
            Vacancy v = vacancies.get(i);
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(i + 1);
            dataRow.createCell(1).setCellValue(v.getName());
            dataRow.createCell(2).setCellValue(v.getEmployer());
            if (v.getSalaryFrom() == 0) {
                dataRow.createCell(3).setCellValue("-");
            } else {
                dataRow.createCell(3).setCellValue(v.getSalaryFrom());
            }
            if (v.getSalaryTo() == 0) {
                dataRow.createCell(4).setCellValue("-");
            } else {
                dataRow.createCell(4).setCellValue(v.getSalaryTo());
            }
        }

        CellStyle style = wb.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellStyle(style);
            }
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        try (FileOutputStream fos = new FileOutputStream(filePath)){
            wb.write(fos);
            wb.close();
            System.out.println("Файл сохранен");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Ошибка IOException");
        }
    }
}
