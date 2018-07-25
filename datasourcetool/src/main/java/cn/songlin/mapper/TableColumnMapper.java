package cn.songlin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.songlin.entity.TableColumn;
import tk.mybatis.mapper.common.Mapper;

public interface TableColumnMapper extends Mapper<TableColumn> {

	List<TableColumn> getTableColumn(@Param("dataSourceName")String dataSourceName,@Param("tableName")String tableName);
}