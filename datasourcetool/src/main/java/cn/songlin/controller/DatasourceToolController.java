package cn.songlin.controller;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.dto.TableColumnDto;
import cn.songlin.entity.TableColumn;
import cn.songlin.service.ExcelService;
import cn.songlin.service.TableColumnService;
import cn.songlin.utils.MapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("exportExcel")
@Api("数据库导出Excel")
@CrossOrigin
public class DatasourceToolController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private TableColumnService tableColumnService;
	
	@Autowired
	private ExcelService excelService;
	
	

	@PostMapping("printAll")
	@ApiOperation(value = "所有表结构打印测试")
	public ResponseEntity<List<TableColumn>> printAll(@RequestBody TableColumnDto tableColumnDto) {
		List<TableColumn> tableColumns = tableColumnService.getAllTableColumn(tableColumnDto.getDataSourceName());
		Map<String, List<TableColumn>> map = MapUtil.dealTableColumn(tableColumns);
		excelService.createTableColumnExcel(map, tableColumnDto);
		return new ResponseEntity<List<TableColumn>>(tableColumns, HttpStatus.OK);
	}
	
	@PostMapping("printSingle")
	@ApiOperation(value = "单表结构打印测试")
	public ResponseEntity<List<TableColumn>> printSingle(@RequestBody TableColumnDto tableColumnDto) {
		List<TableColumn> tableColumns = tableColumnService.getTableColumns(tableColumnDto.getDataSourceName(),
				tableColumnDto.getTableName());
		excelService.createSingleTableColumnExcel(tableColumns,tableColumnDto);
		
		return new ResponseEntity<List<TableColumn>>(tableColumns, HttpStatus.OK);
	}
}
