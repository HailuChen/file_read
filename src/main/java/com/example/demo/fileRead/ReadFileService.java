package com.example.demo.fileRead;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ReadFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadFileService.class);

    public  String validateFileContentType(String fileName) {
        LOGGER.info(fileName);
        if (fileName.contains(".")) {
            String fileType = fileName.split("\\.")[1];

            if (fileType != null && fileType.toLowerCase().matches("pdf|xls|txt")) {
                return fileType;
            }
        }

        return null;
    }

    public String readFile(InputStream inputStream, String suffix) throws Exception{
        String content;
        if ("txt".equals(suffix)) {
            content = this.readTxtFileContent(inputStream);
        } else if ("pdf".equals(suffix)) {
            content = this.readPdf(inputStream);
        } /*else if ("word".equals(suffix)) {
            content = this.readTxtFileContent(inputStream);
        } */else {
            content = this.readExcel(inputStream);
        }
        return content;
    }

    private String readTxtFileContent(InputStream inputStream) throws IOException{
        StringBuffer sb = new StringBuffer("");
        int len = 0;
        byte[] buf = new byte[1024];
        while((len = inputStream.read(buf)) != -1){
            sb.append(new String(buf,0,len));
        }
        inputStream.close();
        return sb.toString();
    }

    private String readPdf(InputStream inputStream) throws IOException{
        String content = "";
        PDDocument doc = PDDocument.load(inputStream);
        PDFTextStripper stripper = null;
        stripper = new PDFTextStripper();
        content = stripper.getText(doc);
        doc.close();
        return content;
    }

    /*public static String readDoc(FileInputStream inputStream) {

        WordExtractor ex = new WordExtractor(is);
        String text2003 = ex.getText();
        System.out.println(text2003);

    }*/

    private String readExcel(InputStream inputStream) throws  Exception{
        StringBuffer content = new StringBuffer("");
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            HSSFRow row = (HSSFRow) sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                if (j < 0) {
                    continue;//增加下标判断
                }
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                content.append(cell.getStringCellValue());
            }
        }
        inputStream.close();
        return content.toString();
    }


}
