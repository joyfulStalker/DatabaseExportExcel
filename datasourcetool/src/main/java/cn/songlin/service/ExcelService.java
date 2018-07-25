package cn.songlin.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import cn.songlin.dto.SingleTableColumnDto;
import cn.songlin.entity.TableColumn;
import cn.songlin.utils.ExcelUtil;

@Service
public class ExcelService {

	/**
	 * 创建常规单表结构
	 * 
	 * @author liusonglin
	 * @date 2018年7月25日
	 * @param tableColumns
	 * @param tableColumnDto
	 */

	public void createSingleTableColumnExcel(List<TableColumn> tableColumns, SingleTableColumnDto tableColumnDto) {
		
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
		try {
			OutputStream os = new FileOutputStream(tableColumnDto.getExcelFileName());
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
