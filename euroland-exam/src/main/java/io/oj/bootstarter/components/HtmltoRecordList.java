package io.oj.bootstarter.components;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HtmltoRecordList {
	
	
	@Value("${file.path}${file.name}")
	private  String pathName;

	public  List<LinkedHashMap<String, String>>   getRecordList() {
		List<Table> tables = new ArrayList<>();
		try {
			tables = htmlExtract();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return extractRecordsToList(tables);
	}

	public  List<LinkedHashMap<String, String>> extractRecordsToList(List<Table> tables) {
		List<LinkedHashMap<String, String>> recordsAsList = new ArrayList<LinkedHashMap<String, String>>();
		for (Record record : extractRecords(tables)) {
			recordsAsList.add(record.getRow());
		}		
		return recordsAsList;
	}

	public  List<Table> htmlExtract() throws IOException {
		List<Table> tables = new ArrayList<Table>();
		File input = new File(pathName);
		Document doc = Jsoup.parse(input, "UTF-8", "");
		Element table = doc.select("table").get(0);
		Elements rows = table.select("tr");
		for (int r = 0; r < rows.size(); r++) {
			Element row = rows.get(r);
			Elements cols = new Elements();
			if (r == 0)
				cols = row.select("th");
			else
				cols = row.select("td");
			for (int c = 0; c < cols.size(); c++) {
				tables.add(new Table(r, c, cols.get(c).text()));
			}
		}

		return tables;
	}

	public static List<Record> extractRecords(List<Table> tables) {
		Map<Integer, String> headers = new HashMap<Integer, String>();
		List<Record> records = new ArrayList<Record>();
		Record record = new Record();
		List<Table> row = new ArrayList<Table>();
		for (Table table : tables) {
			if (table.getRow() == 0) {
				headers.put(Integer.valueOf(table.getCol()), table.getValue());
			} else {
				row.add(table);
				if (table.getCol() == headers.size() - 1) {
					record = new Record(headers, row);
					records.add(record);
					row = new ArrayList<Table>();
					record = new Record();
				}
			}
		}
		return records;
	}	
}
