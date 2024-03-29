package com.magic.daoyuan.business.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * 读取excel
 * @author lzh
 * @create 2017/8/4 15:00
 */
public class ExcelReader {

    private static POIFSFileSystem fs;
    private static HSSFWorkbook wb;
    private static HSSFSheet sheet;
    private static HSSFRow row;

    private static XSSFWorkbook xssfWorkbook;

    private static Workbook workbook;

    private static Sheet sheet_;

    private static Row row_;

    /**
     * 导入银行流水读取Excel数据内容
     * @param is
     * @param  startRow 起始行
     * @return Map 包含单元格数据内容的Map对象
     */
    public static Map<Integer, List<String>> readExcelContent(InputStream is,Integer startRow,String fileSuffix,Integer flag)
        {
        Map<Integer, List<String>> content = new TreeMap<Integer, List<String>>();
        String str = "";
        try {

            if("xlsx".equals(fileSuffix)){
                workbook = new XSSFWorkbook(is);
            }
            else if("xls".equals(fileSuffix)){
                workbook = new HSSFWorkbook(is);
            }
//            workbook = WorkbookFactory.create(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
            sheet_ = workbook.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet_.getLastRowNum();

        if (startRow >= 2) {
            row_ = sheet_.getRow(startRow - 1);
        }else{
            row_ = sheet_.getRow(0);
        }
        int colNum = row_.getPhysicalNumberOfCells();
        // 正文内容应该从第三行开始,第一行为表头的标题
        for (int i = startRow; i <= rowNum; i++) {
            row_ = sheet_.getRow(i);
            int j = 0;
            List<String> list = new ArrayList<String>();
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                list.add(getCellFormatValue(row_.getCell((short) j)).trim());
                j++;
            }
            content.put(i, list);
        }
        return content;
    }
  /**
     * 读取Excel数据内容
     * @param is
     * @param  startRow 起始行
     * @return Map 包含单元格数据内容的Map对象
     */
    public static Map<Integer, List<String>> readExcelContent(InputStream is,Integer startRow) {
        Map<Integer, List<String>> content = new TreeMap<Integer, List<String>>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();

        if (startRow >= 2) {
            row = sheet.getRow(startRow - 1);
        }else{
            row = sheet.getRow(0);
        }
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第三行开始,第一行为表头的标题
        for (int i = startRow; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            List<String> list = new ArrayList<String>();
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                list.add(getCellFormatValue(row.getCell((short) j)).trim());
                j++;
            }
            content.put(i, list);
        }
        return content;
    }


    /**
     * 读取用户Excel数据内容
     * @param is
     * @param  startRow 起始行
     * @return Map 包含单元格数据内容的Map对象
     */
    public static Map<Integer, List<String>> readExcelContent(InputStream is,Integer startRow,Integer flag) {
        Map<Integer, List<String>> content = new TreeMap<Integer, List<String>>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();

        row = sheet.getRow(startRow);

        int colNum = row.getPhysicalNumberOfCells() + 11;
        // 正文内容应该从第三行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            if(null == row){
                break;
            }
            int j = 0;
            List<String> list = new ArrayList<String>();
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                list.add(getCellFormatValue(row.getCell((short) j)).trim());
                j++;
            }
            content.put(i, list);
        }
        return content;
    }

    /**
     * 读取用户Excel数据内容
     * @param is
     * @param  startRow 起始行
     * @return Map 包含单元格数据内容的Map对象
     */
    public static Map<Integer, List<String>> readExcelContent(InputStream is,Integer startRow,String flag) {
        Map<Integer, List<String>> content = new TreeMap<Integer, List<String>>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();

        row = sheet.getRow(startRow);

        int colNum = row.getPhysicalNumberOfCells() + 4;
        // 正文内容应该从第三行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            List<String> list = new ArrayList<String>();
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                list.add(getCellFormatValue(row.getCell((short) j)).trim());
                j++;
            }
            content.put(i, list);
        }
        return content;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    private static String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_NUMERIC:
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 不进行任何格式化，当作字符串处理
//                        cellvalue = cell.getRichStringCellValue().getString();
                        // 取得当前Cell的数值
                        DecimalFormat df = new DecimalFormat("#0.00");
                        cellvalue = String.valueOf(df.format(cell.getNumericCellValue()));
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case Cell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        DecimalFormat df = new DecimalFormat("#0.00");
                        cellvalue = String.valueOf(df.format(cell.getNumericCellValue()));
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }


}
