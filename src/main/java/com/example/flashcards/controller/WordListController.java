package com.example.flashcards.controller;

import com.example.flashcards.model.WordList;
import com.example.flashcards.service.ExcelDataService;
import com.example.flashcards.service.WordListService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WordListController {

    private ExcelDataService excelDataService;
    private WordListService wordListService;

    Logger logger = LoggerFactory.getLogger(WordListController.class);
    @Autowired
    public WordListController(ExcelDataService excelDataService, WordListService wordListService) {
        this.excelDataService = excelDataService;
        this.wordListService = wordListService;
    }

    @PostMapping("/api/import-excel")
    public ResponseEntity<?> importExcelFile(@RequestParam("file") MultipartFile files){
        excelDataService.saveExcelDataInDB(files);
        logger.info("Save date from task: importExcelFile");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
