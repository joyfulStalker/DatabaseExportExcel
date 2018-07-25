package cn.songlin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.dto.SingleTableColumnDto;
import cn.songlin.entity.TableColumn;
import cn.songlin.service.ExcelService;
import cn.songlin.service.TableColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("tool")
@Api("用户管理")
@CrossOrigin
public class DatasourceToolController {

	@Autowired
	private TableColumnService tableColumnService;
	
	@Autowired
	private ExcelService excelService;

	@PostMapping("test")
	@ApiOperation(value = "测试")
	public ResponseEntity<List<TableColumn>> register(@RequestBody SingleTableColumnDto tableColumnDto) {

		List<TableColumn> tableColumns = tableColumnService.getTableColumns(tableColumnDto.getDataSourceName(),
				tableColumnDto.getTableName());
		excelService.createSingleTableColumnExcel(tableColumns,tableColumnDto);
		
		return new ResponseEntity<List<TableColumn>>(tableColumns, HttpStatus.OK);
	}
}
