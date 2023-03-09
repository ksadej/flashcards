package com.example.flashcards.service;

import com.example.flashcards.model.WordList;
import com.example.flashcards.repository.WordListRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatFlagsException;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelDataService {

    private WordListRepository wordListRepository;

    @Autowired
    public ExcelDataService(WordListRepository wordListRepository) {
        this.wordListRepository = wordListRepository;
    }


    public void saveExcelDataInDB(MultipartFile files){

        XSSFWorkbook workbook = null;

        try {
            workbook = new XSSFWorkbook(files.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(workbook.getWorkbookType().equals("XLSX")){
            throw new IllegalFormatFlagsException("Wrong format file");
        }

        XSSFSheet worksheet = workbook.getSheetAt(0);
        int numberOfRows = worksheet.getPhysicalNumberOfRows();


        for(int i=0; i< numberOfRows; i++){
            WordList wordList = new WordList();
            XSSFRow row = worksheet.getRow(i);
            if (row != null) {
                wordList.setWord(row.getCell(0).getStringCellValue());
                wordListRepository.save(wordList);
            }
        }
    }
}
