package org.example.swagger.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class XlService {

    public int min(MultipartFile file, int n) {
        List<Integer> list = readFile(file);
        if (n < 1 || n > list.size()) {
            throw new IllegalArgumentException("n вне диапазона");
        }
        return sort(list, 0, list.size() - 1, n - 1);
    }

    private List<Integer> readFile(MultipartFile file) {
        List<Integer> list = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    list.add((int) cell.getNumericCellValue());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла", e);
        }
        return list;
    }


    private int division(List<Integer> list, int left, int right) {
        int pivot = list.get(right);
        int i = left;
        for (int j = left; j < right; j++) {
            if (list.get(j) < pivot) {
                Collections.swap(list, i, j);
                i++;
            }
        }
        Collections.swap(list, i, right);
        return i;
    }

    private int sort(List<Integer> list, int left, int right, int n) {
        if (left == right) {
            return list.get(left);
        }
        int pivotIndex = division(list, left, right);
        if (n == pivotIndex) {
            return list.get(n);
        } else if (n < pivotIndex) {
            return sort(list, left, pivotIndex - 1, n);
        } else {
            return sort(list, pivotIndex + 1, right, n);
        }
    }
}
