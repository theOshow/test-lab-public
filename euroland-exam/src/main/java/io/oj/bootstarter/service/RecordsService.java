package io.oj.bootstarter.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.oj.bootstarter.components.HtmltoRecordList;
import io.oj.bootstarter.components.RecordGroup;

@Service
public class RecordsService {

//	@Autowired
//	private RecordList recordList;
	
	@Autowired
	HtmltoRecordList htmltoRecordList;
	
	List<LinkedHashMap<String, String>>  recordList; 
	
	@PostConstruct
	public void init(){
		recordList =  htmltoRecordList.getRecordList();		
	}
	
	public String getTotals() {
	//	init();
		StringBuilder totals = new StringBuilder("ROWS(" + getTotalRows(recordList) + ") ");
		totals.append("COLUMNS(" + getTotalColumns(recordList) + ")");
		return totals.toString();
	}
	
	public List<Map<String, String>> getSortedRecordsListAsc(String column) {
	
		String headerColumn = this.getColumn(column);		
		return	recordList.stream()
		.sorted((Comparator.comparing((Map<String, String> m) -> m.get(headerColumn))))
		.collect(Collectors.toList());
	}
	
	public List<Map<String, String>> getSortedRecordsListDesc(String column) {
		String headerColumn = this.getColumn(column);
		return	recordList.stream()
				.sorted((Comparator.comparing((Map<String, String> m) -> m.get(headerColumn)))
						.reversed())
				.collect(Collectors.toList());
	}
	
	public List<Map<String, String>> getSortedRecordsListLastLetterDesc(String column) {
		String headerColumn = this.getColumn(column);
		return	recordList.stream()
				.sorted((Comparator.comparing(
						(Map<String, String> m) -> 
						m.get(headerColumn).substring(m.get(headerColumn).length() - 1)))
						.reversed())
				.collect(Collectors.toList());
	}
	
	public List<Map<String, String>> getSortedRecordsListLastLetterAsc(String column) {
		String headerColumn = this.getColumn(column);
		return	recordList.stream()
				.sorted((Comparator.comparing((Map<String, String> m) -> m.get(headerColumn).substring(m.get(headerColumn).length() - 1))))
				.collect(Collectors.toList());
	}	

	public Map<String, RecordGroup> getRecordGroupMapDesc(String column) {
		String headerColumn = this.getColumn(column);
		Map<String, List<Map<String, String>>> recordMap  = new LinkedHashMap<String, List<Map<String, String>>>();
		recordMap = recordList.stream()
				.collect(Collectors.groupingBy(m -> m.get(headerColumn), Collectors.mapping(map -> {
					Map<String, String> temp = new HashMap<>(map);
					temp.remove(headerColumn);
					return temp;
				}, Collectors.toList()// toList()
				)));
		TreeMap<String,RecordGroup> tmap = new TreeMap<>();
		recordMap.forEach((k, v) -> tmap.put(k, new RecordGroup(v)));
		return tmap.descendingMap();
	}
	
	
	public List<Map<String, String>> searchRecords(List<Map<String, String>> searchParameter) {
		String[] headerArray = recordList.get(0).keySet()
				.toArray(new String[recordList.get(0).keySet().size()]);
		List<Map<String, String>> searchResult = 
				recordList.stream()
				.filter(
				map -> 
				searchParameter.stream().anyMatch( sp ->
				map.get(headerArray[0]).equalsIgnoreCase(sp.get("col_1"))
				&& map.get(headerArray[1]).equalsIgnoreCase(sp.get("col_2"))
				)).collect(Collectors.toList());
		return searchResult;
	}	
	
	public List<LinkedHashMap<String, String>>  getRecordsList() {
		return recordList;
	}

	
	public int getTotalRows(List<LinkedHashMap<String, String>>  records) {
		return records.size();
	}

	public int getTotalColumns(List<LinkedHashMap<String, String>>  records) {
		return records.get(0).size();
	}
	
	public String getColumn(String column) {

		return recordList
				.get(0).keySet().stream()
				.filter(e -> e.equalsIgnoreCase(column))
				.findFirst()
				.orElse(null);
	}
	
	
	


		
		

	
}