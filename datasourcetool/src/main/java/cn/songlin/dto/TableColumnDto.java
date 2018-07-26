package cn.songlin.dto;

/**
 * 表结构查询dto
 * 
 * @author liusonglin
 * @date 2018年7月25日
 */

public class TableColumnDto {

	private String dataSourceName;

	private String tableName;

	private String excelFileName;

	private String sheetName;

	private String[] excelTitle = {"列名", "数据类型", "字段类型", "长度", "是否为空", "默认值", "备注"};

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String[] getExcelTitle() {
		return excelTitle;
	}

}
