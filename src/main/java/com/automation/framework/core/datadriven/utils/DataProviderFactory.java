package com.automation.framework.core.datadriven.utils;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.automation.framework.core.datadriven.objects.DataMapping;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Reader;


public class DataProviderFactory {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(DataProviderFactory.class);

    @DataProvider(name = "excelData")
    public Object[][] dataProviderMethod(Method method) {
        Object[][] excelData = null;
        String testCaseId = null;
        try {
            LOGGER.info("Parsing the test jsonData...");
            String testCaseName = method.getName();
            String[] tcParts = testCaseName.split("_");
            testCaseId = tcParts[2] + "_" + tcParts[3] + "_" + tcParts[4] + "_" + tcParts[5];
            JSONObject jsonData = (JSONObject) getTestData().get(testCaseId);
            if (jsonData.isEmpty()) {
                throw new IllegalArgumentException("The test jsonData of the testcase Id " + testCaseId + " is empty");
            }
            String xlFilePath = jsonData.get("xlFilePath").toString();
            String sheetName = jsonData.get("sheetName").toString();
            String specificRows = jsonData.get("specificRows").toString();
            if (specificRows.isEmpty()) {
                excelData = getTestData(xlFilePath, sheetName);
            } else {
                if (specificRows.contains("-")) {
                    excelData = getTestDataBySpecificRange(xlFilePath, sheetName, specificRows);
                } else {
                    excelData = getTestDataBySpecificRows(xlFilePath, sheetName, specificRows);
                }
            }
        } catch (NullPointerException e) {
            LOGGER.info("Not found the testcase Id {} in the testdata" + testCaseId);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.info("The format of the testcase is not correct. Please help to check.");
        } catch (Exception e) {
            //ExceptionHelper.rethrow(e);
        }
        return excelData;
    }


    private Object[][] getTestData(String xlFilePath, String sheetName) throws IOException {
        ExcelHelper excelHelper = new ExcelHelper(xlFilePath);

        int rows = excelHelper.getRowCount(sheetName);
        int columns = excelHelper.getColumnCount(sheetName);
        Object[][] excelData = new Object[rows - 1][1];
        List<String> columnNames = excelHelper.getColumnNames(sheetName, columns);

        for (int i = 1; i < rows; i++) {
            Map<String, String> rowDataMap = new HashMap<>();
            for (String columnName : columnNames) {
                String cellData = excelHelper.getCellData(sheetName, columnName, i);
                rowDataMap.put(columnName, cellData);
            }
            rowDataMap.put("xlFilePath", xlFilePath);
            rowDataMap.put("sheetName", sheetName);
            excelData[i - 1][0] = rowDataMap;
        }
        return excelData;

    }

    private Object[][] getTestDataBySpecificRows(String xlFilePath, String sheetName, String specificRows) throws IOException {

        Object[][] excelData = this.getTestData(xlFilePath, sheetName);

        List<String> listSpecificRows = Arrays.asList(specificRows.split("\\s*,\\s*"));

        Object[][] newExcelData = new Object[listSpecificRows.size()][1];

        for (int i = 0; i < listSpecificRows.size(); i++) {
            int specificRow = Integer.valueOf(listSpecificRows.get(i)) - 1;
            newExcelData[i][0] = excelData[specificRow][0];
        }
        return newExcelData;
    }

    private Object[][] getTestDataBySpecificRange(String xlFilePath, String sheetName, String range) throws IOException {

        Object[][] excelData = this.getTestData(xlFilePath, sheetName);

        List<String> listRange = Arrays.asList(range.split("\\s*-\\s*"));

        int fromRow = Integer.parseInt(listRange.get(0));
        int toRow = Integer.parseInt(listRange.get(1));


        Object[][] newExcelData = new Object[toRow - fromRow + 1][1];

        for (int i = fromRow; i <= toRow; i++) {
            newExcelData[i - fromRow][0] = excelData[i - 1][0];
        }
        LOGGER.info(String.format("Test case is run from jsonData row %s to row %s", fromRow, toRow));
        return newExcelData;
    }

    protected JSONObject getTestData() throws IOException,ParseException{
        String filePath = DataMapping.DEFAULT_TESTDATA_PATH;
        Reader reader = new FileReader(filePath);
        JSONParser parser = new JSONParser();
        JSONObject result = (JSONObject) parser.parse(reader);
        if (result == null) {
            LOGGER.error("testData is null!!!");
        }
        return result;
    }

}

