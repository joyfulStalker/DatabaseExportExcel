package cn.songlin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.songlin.entity.TableColumn;
import cn.songlin.mapper.TableColumnMapper;

@Service
@Transactional
public class TableColumnService {

	@Autowired
	private TableColumnMapper columnMapper;

	
	/**
	* 查询指定的数据库表的表结构
	* @author liusonglin
	* @date 2018年7月25日
	* @return
	*/
		
	public List<TableColumn> getTableColumns(String dataSourceName,String tableName) {
		if(StringUtils.isEmpty(dataSourceName)||StringUtils.isEmpty(tableName)) {
			throw new RuntimeException("请输入数据库名和表名");
		}
		//查询数据库和表是否存在
		//待做
		List<TableColumn> tableColumn = columnMapper.getTableColumn(dataSourceName,tableName);
		return tableColumn;
	}
}
