package com.fjnu.fjnu.utils;

import com.fjnu.fjnu.bean.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    /**
     * 读取Excel文件，获取信息集合
     */
    public List<User> getExcelInfo(MultipartFile mFile) {
        //获取文件名
        String fileName = mFile.getOriginalFilename();
        List<User> userList = null;
        try {
            //验证文件名是否合格
            if(!validateExcel(fileName)){
                //不合格的话直接return
                return null;
            }
            //根据文件名判断是2003版本的还是2007版本的
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003 = false;
            }
            userList= createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
    /**
     * 判断是不是2003版本的excel
     */
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    /**
     * 判断是不是2007版本的excel
     */
    public static boolean isExcel2007(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    /**
     * 判断是不是excel文件格式
     */
    public boolean validateExcel(String filePath){
        if(filePath ==null||!(isExcel2003(filePath) || isExcel2007(filePath))){
            System.out.println("文件名不是excel格式");
            return false;
        }
        return true;
    }
    /**
     * 读取excel里面的信息
     */
    public List<User> readExcelValue(Workbook wb){
        List<User> userlist=new ArrayList<>();
        //先得到一个sheet
        int TRows =0;
        int TCells = 0;
        Sheet sheet = wb.getSheetAt(0);
        //得到excel里面的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        TRows=totalRows;
        //在有行的前提下，得到excel里面的列
        //从第二行开始读取数据
        if(totalRows >1 && sheet.getRow(0)!=null){
            int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            TCells=totalCells;
        }
        for (int r = 1 ; r < totalRows; r++){
            Row row = sheet.getRow(r);
            if(row == null){
                continue;//行为空直接中断
            }
            User user = new User();
            //循环excel的列
            for(int c = 0; c<TCells ; c++){
                Cell cell = row.getCell(c);
                if(cell != null){
                    //根据excel需要导入的列数来写
                    if(c == 0){
                        if(cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC){
                            //因为id为int类型所以强行转换为int
                            int id = Integer.valueOf((int) cell.getNumericCellValue());
                            user.setID(id);
                        }
                    }else if (c == 1){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            //通过截取字符串去掉小数位获得整数
                            String uname = String.valueOf(cell.getNumericCellValue());
                            //如果length()-2（.0）长度为零，说明只有一位数，则截取0到1
                            user.setUname(uname.substring(0,uname.length()-2>0?uname.length()-2:1));
                        }else{
                            //如果不是纯数字可以直接获得名称
                            user.setUname(cell.getStringCellValue());
                        }
                    }else if( c == 2){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String upass = String.valueOf(cell.getNumericCellValue());
                            user.setUpassword(upass.substring(0, upass.length()-2>0?upass.length()-2:1));
                        }else{
                            user.setUpassword(cell.getStringCellValue());
                        }
                    }else if( c == 3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String headpic = String.valueOf(cell.getNumericCellValue());
                            user.setHeadpic(headpic.substring(0, headpic.length()-2>0?headpic.length()-2:1));
                        }else{
                            user.setHeadpic(cell.getStringCellValue());
                        }
                    }
                }
            }
            userlist.add(user);//加入userlist
        }
        return userlist;
    }
    public List<User> createExcel(InputStream is , boolean isExcel2003){
        List<User> userlist = null;
        try {
            Workbook wb = null;
            if(isExcel2003){
                //如果是2003版本的就new一个2003的wb出来
                wb = new HSSFWorkbook(is);
            }else{
                //否则就new 一个2007版的出来
                wb = new XSSFWorkbook(is);

            }
            //再让wb去解析readExcelValue(Workbook wb)方法
            userlist = readExcelValue(wb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userlist;
    }

}