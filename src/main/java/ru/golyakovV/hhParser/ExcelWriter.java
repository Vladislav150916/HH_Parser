package ru.golyakovV.hhParser;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.golyakovV.hhParser.model.VacancyAbstract;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ExcelWriter {
    private String vacancyName;
    private String area;
    private String site;
    private Path filePath;
    private LocalDate date = LocalDate.now();

    public ExcelWriter(Map<String, String> parameters){
        vacancyName = parameters.get("vacancyName");
        area = parameters.get("areaToFileName");
        site = (parameters.get("site").equals("1"))? "HH" : "SJ";
        filePath = getPath();
    }

    private Path getPath() {
        Path path;
        Path resultsDirectory = Paths.get("results");
        if (!Files.exists(resultsDirectory)) {
            try {
                Files.createDirectories(resultsDirectory);
                System.out.println("Создана папка results");
            } catch (IOException e) {
                System.out.println("Ошибка создания папки results");
            }
        }
        String fileName = site + "-" + vacancyName + "-" + area + "-" + date + ".xlsx";
        path = resultsDirectory.resolve(fileName);
        return path;
    }

    public void write(List<? extends VacancyAbstract> vacancies){

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Вакансии");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(1).setCellValue("Название вакансии");
        headerRow.createCell(2).setCellValue("Работодатель");
        headerRow.createCell(3).setCellValue("Зарплата от (руб)");
        headerRow.createCell(4).setCellValue("Зарплата до (руб)");
        headerRow.createCell(5).setCellValue("Ссылка");

        for (int i = 0; i < vacancies.size(); i++){
            VacancyAbstract v = vacancies.get(i);
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
            dataRow.createCell(5).setCellValue(v.getVacancyUrl());
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
        sheet.setColumnWidth(0, 1280);
        sheet.setColumnWidth(1, 21505);
        sheet.setColumnWidth(2, 18680);
        sheet.setColumnWidth(3, 4860);
        sheet.setColumnWidth(4, 4860);
        sheet.setColumnWidth(5, 16390);

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())){
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
