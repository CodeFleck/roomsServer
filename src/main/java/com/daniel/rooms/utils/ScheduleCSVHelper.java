package com.daniel.rooms.utils;

import com.daniel.rooms.model.Room;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleCSVHelper {

    public static String exportSchedule(List<List<Room>> allRooms) {
        final String PATH = "download/";
        XSSFWorkbook planilha = new XSSFWorkbook();
        String fileName = "schedule";
        String header = "Segunda, Terça, Quarta, Quinta, Sexta, Sábado";

        StringBuilder fileNameBuilder = getFileNameWithTimeStamp(new StringBuilder(), fileName);

        try {
            File filePath = new File(PATH);
            if (!filePath.exists()) {
                System.out.println(filePath.mkdirs());
            }
            File file = new File(filePath, fileNameBuilder.toString());
            FileOutputStream fileOut = new FileOutputStream(String.valueOf(file.toPath()));

            XSSFSheet abaPrimaria = planilha.createSheet();
            abaPrimaria.setColumnWidth(0, 5000);
            abaPrimaria.setColumnWidth(1, 5000);
            abaPrimaria.setColumnWidth(2, 5000);
            abaPrimaria.setColumnWidth(3, 5000);
            abaPrimaria.setColumnWidth(4, 5000);
            abaPrimaria.setColumnWidth(5, 5000);
            int rowIndex = 0;
            XSSFRow linha = abaPrimaria.createRow(rowIndex);

            String[] headerChar = header.split(",");
            int cellIndex = 0;
            for (String word : headerChar) {
                XSSFCell celula = linha.createCell(cellIndex);
                celula.setCellValue(word);
                cellIndex++;
            }
            rowIndex++;

            List<Room> mondayRooms = allRooms.get(0);
            List<Room> tuesdayRooms = allRooms.get(1);
            List<Room> wednesdayRooms = allRooms.get(2);
            List<Room> thursdayRooms = allRooms.get(3);
            List<Room> fridayRooms = allRooms.get(4);
            List<Room> saturdayRooms = allRooms.get(5);

            CellStyle style = planilha.createCellStyle();
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());

            for (int i = 0; i < allRooms.get(0).size(); i++) {
                cellIndex = 0;
                XSSFRow novaLinha = abaPrimaria.createRow(rowIndex);
                XSSFCell monCell = novaLinha.createCell(cellIndex);
                Room mondayRoom = mondayRooms.get(i);
                if (mondayRoom.isSpecialtyRoom()) {
                    monCell.setCellStyle(style);
                }
                if (null != mondayRoom.getProfessional()) {
                    monCell.setCellValue(mondayRoom.getRoomName() + " - " + mondayRoom.getProfessional().getName());
                } else {
                    monCell.setCellValue(mondayRoom.getRoomName() + " - ");
                }
                cellIndex++;

                Room tuesdayRoom = tuesdayRooms.get(i);
                XSSFCell tueCell = novaLinha.createCell(cellIndex);
                if (tuesdayRoom.isSpecialtyRoom()) {
                    tueCell.setCellStyle(style);
                }
                if (null != tuesdayRoom.getProfessional()) {
                    tueCell.setCellValue(tuesdayRoom.getRoomName() + " - " + tuesdayRoom.getProfessional().getName());
                } else {
                    tueCell.setCellValue(tuesdayRoom.getRoomName() + " - ");
                }
                cellIndex++;

                XSSFCell wedCell = novaLinha.createCell(cellIndex);
                Room wednesdayRoom = wednesdayRooms.get(i);
                if (wednesdayRoom.isSpecialtyRoom()) {
                    wedCell.setCellStyle(style);
                }
                if (null != wednesdayRoom.getProfessional()) {
                    wedCell.setCellValue(wednesdayRoom.getRoomName() + " - " + wednesdayRoom.getProfessional().getName());
                } else {
                    wedCell.setCellValue(wednesdayRoom.getRoomName() + " - ");
                }
                cellIndex++;

                Room thrusdayRoom = thursdayRooms.get(i);
                XSSFCell thuCell = novaLinha.createCell(cellIndex);
                if (thrusdayRoom.isSpecialtyRoom()) {
                    thuCell.setCellStyle(style);
                }
                if (null != thrusdayRoom.getProfessional()) {
                    thuCell.setCellValue(thrusdayRoom.getRoomName() + " - " + thrusdayRoom.getProfessional().getName());
                } else {
                    thuCell.setCellValue(thrusdayRoom.getRoomName() + " - ");
                }
                cellIndex++;

                Room fridayRoom = fridayRooms.get(i);
                XSSFCell friCell = novaLinha.createCell(cellIndex);
                if (fridayRoom.isSpecialtyRoom()) {
                    friCell.setCellStyle(style);
                }
                if (null != fridayRoom.getProfessional()) {
                    friCell.setCellValue(fridayRoom.getRoomName() + " - " + fridayRoom.getProfessional().getName());
                } else {
                    friCell.setCellValue(fridayRoom.getRoomName() + " - ");
                }
                cellIndex++;

                Room saturdayRoom = saturdayRooms.get(i);
                XSSFCell satCell = novaLinha.createCell(cellIndex);
                if (saturdayRoom.isSpecialtyRoom()) {
                    satCell.setCellStyle(style);
                }
                if (null != saturdayRoom.getProfessional()) {
                    satCell.setCellValue(saturdayRoom.getRoomName() + " - " + saturdayRoom.getProfessional().getName());
                } else {
                    satCell.setCellValue(saturdayRoom.getRoomName() + " - ");
                }
                cellIndex++;

                rowIndex++;
            }
            planilha.write(fileOut);
            planilha.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNameBuilder.toString();
    }

    private static StringBuilder getFileNameWithTimeStamp(StringBuilder fileNameBuilder, String fileName) {
        fileNameBuilder.append(fileName);
        fileNameBuilder.append(LocalDateTime.now().toString());
        fileNameBuilder.append(".xlsx");
        return fileNameBuilder;
    }
}
