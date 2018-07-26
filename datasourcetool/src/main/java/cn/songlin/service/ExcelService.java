package cn.songlin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.songlin.dto.TableColumnDto;
import cn.songlin.entity.TableColumn;
import cn.songlin.utils.ExcelUtil;

@Service
public class ExcelService {

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 创建多表结构
	 * 
	 * @author liusonglin
	 * @date 2018年7月25日
	 * @param tableColumns
	 * @param tableColumnDto
	 */

	public void createTableColumnExcel(Map<String, List<TableColumn>> map, TableColumnDto tableColumnDto)
			throws Exception {
		if(StringUtils.isEmpty(tableColumnDto.getExcelFileName())) {
			throw new Exception("未输入导出文件名");
		}else {
			File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
			tableColumnDto.setExcelFileName(desktopDir+"/"+tableColumnDto.getExcelFileName()+".xls");
		}

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(tableColumnDto.getSheetName());
		sheet.setDefaultColumnWidth(12);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		// 声明列对象
		HSSFCell cell = null;

		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 450);// 设置行高
		cell = row.createCell(4);
		cell.setCellValue(tableColumnDto.getDataSourceName() + "数据库所有表的表结构");
		cell.setCellStyle(style);

		// POI导出EXCEL设置跨行跨列（在所有数据行和列创建完成后再执行）
		CellRangeAddress range = new CellRangeAddress(0, 0, 4, 6);
		sheet.addMergedRegion(range);

		// 数据处理
		// logger.info(sheet.getLastRowNum());
		// row = sheet.createRow(sheet.getLastRowNum()+3);//向下偏移3行
		String[][] content = null;
		Set<String> keySet = map.keySet();
		for (String key : keySet) {

			row = sheet.createRow(sheet.getLastRowNum() + 2);// 向下偏移2行
			row.createCell(2).setCellValue("表名：" + key);
			row = sheet.createRow(sheet.getLastRowNum() + 1);// 向下偏移1行
			// 创建标题
			for (int i = 0; i < tableColumnDto.getExcelTitle().length; i++) {
				cell = row.createCell(i + 2);// 单元格从第二列开始
				cell.setCellValue(tableColumnDto.getExcelTitle()[i]);
				cell.setCellStyle(style);
			}
			List<TableColumn> tableColumns = map.get(key);
			content = new String[tableColumns.size()][tableColumnDto.getExcelTitle().length];

			// 创建内容
			for (int i = 0; i < tableColumns.size(); i++) {
				TableColumn tableColumn = tableColumns.get(i);
				content[i][0] = tableColumn.getColumnName();
				content[i][1] = tableColumn.getColumnType();
				content[i][2] = tableColumn.getDataType();
				content[i][3] = tableColumn.getCharacterMaximumLength();
				content[i][4] = tableColumn.getIsNullable();
				content[i][5] = tableColumn.getColumnDefault();
				content[i][6] = tableColumn.getColumnComment();
			}
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 0; i < content.length; i++) {
				row = sheet.createRow(i + 1 + lastRowNum);
				for (int j = 0; j < content[i].length; j++) {
					// 将内容按顺序赋给对应的列对象
					row.createCell(j + 2).setCellValue(content[i][j]);
				}
			}
		}

		// 导入指定地址的Excel
		OutputStream os = new FileOutputStream(tableColumnDto.getExcelFileName());
		wb.write(os);
		os.close();
	}

	/**
	 * 创建常规单表结构
	 * 
	 * @author liusonglin
	 * @date 2018年7月25日
	 * @param tableColumns
	 * @param tableColumnDto
	 */

	public void createSingleTableColumnExcel(List<TableColumn> tableColumns, TableColumnDto tableColumnDto)
			throws Exception {
		if(StringUtils.isEmpty(tableColumnDto.getExcelFileName())) {
			throw new Exception("未输入导出文件名");
		}else {
			File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
			tableColumnDto.setExcelFileName(desktopDir+"/"+tableColumnDto.getExcelFileName()+".xls");
		}

		String[][] content = new String[tableColumns.size()][tableColumnDto.getExcelTitle().length];

		for (int i = 0; i < tableColumns.size(); i++) {
			TableColumn tableColumn = tableColumns.get(i);
			content[i][0] = tableColumn.getColumnName();
			content[i][1] = tableColumn.getColumnType();
			content[i][2] = tableColumn.getDataType();
			content[i][3] = tableColumn.getCharacterMaximumLength();
			content[i][4] = tableColumn.getIsNullable();
			content[i][5] = tableColumn.getColumnDefault();
			content[i][6] = tableColumn.getColumnComment();
		}

		// 创建HSSFWorkbook
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(tableColumnDto.getSheetName(), tableColumnDto.getExcelTitle(),
				content, null);

		// 响应到客户端
		OutputStream os = new FileOutputStream(tableColumnDto.getExcelFileName());
		wb.write(os);
		os.close();

	}
}
