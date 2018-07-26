package cn.songlin.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.songlin.entity.TableColumn;

public class MapUtil {

	public static Map<String, List<TableColumn>> dealTableColumn(List<TableColumn> tableColumns) {

		Map<String, List<TableColumn>> map = new HashMap<>();
		for (TableColumn tableColumn : tableColumns) {
			// 查看map中是否存在该键
			if (map.keySet().contains(tableColumn.getTableName())) {// 存在
				List<TableColumn> columns = map.get(tableColumn.getTableName());
				columns.add(tableColumn);
			} else {
				List<TableColumn> columns = new ArrayList<>();
				columns.add(tableColumn);
				map.put(tableColumn.getTableName(), columns);
			}
		}
		return map;
	}

}
