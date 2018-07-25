package cn.songlin.dto;

/**
 * 单表结构查询dto
 * 
 * @author liusonglin
 * @date 2018年7月25日
 */

public class SingleTableColumnDto {

	private String dataSourceName;

	private String tableName;

	private String excelFileName;

	private String sheetName;

	private String[] excelTitle;

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

	public void setExcelTitle(String[] excelTitle) {
		this.excelTitle = excelTitle;
	}

}
