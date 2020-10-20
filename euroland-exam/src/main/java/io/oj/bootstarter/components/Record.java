package io.oj.bootstarter.components;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Record {

	private LinkedHashMap<String, String> row;

	public Record() {
	}

	public Record(Map<Integer, String> headers, List<Table> data) {
		row = new LinkedHashMap<String, String>();
		for (Table datum : data) {
			row.put(headers.get(datum.getCol()), datum.getValue());
		}
	}

	public LinkedHashMap<String, String> getRow() {
		return row;
	}

	public void setRow(LinkedHashMap<String, String> row) {
		this.row = row;
	}

}
