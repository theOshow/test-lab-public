package io.oj.bootstarter.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.oj.bootstarter.components.RecordGroup;
import io.oj.bootstarter.service.RecordsService;

@RestController
public class RecordsController {
	
	@Autowired
	RecordsService recordsService;
    
	//1
	@RequestMapping("/records/totals")
	public String getTotals() {
		return recordsService.getTotals();
	}
   //2
	@RequestMapping("/records/")
	public  List<LinkedHashMap<String,String>> getRecords(){
		return recordsService.getRecordsList();
	}
	//3
	@RequestMapping("/records/sorted/{column}/asc") //order: ASC
	public   List<Map<String, String>> getSortedRecords(@PathVariable String column ){
		return recordsService.getSortedRecordsListAsc(column);
	}
	@RequestMapping("/records/sorted/{column}/desc") //order: DESC
	public   List<Map<String, String>> getSortedRecordsDesc(@PathVariable String column ){
		return recordsService.getSortedRecordsListDesc(column);
	}
	
	@RequestMapping("/records/sorted/{column}/desc/lastletter") //order: DESC
	public   List<Map<String, String>> getSortedRecordsDescLastLetter(@PathVariable String column ){
		return recordsService.getSortedRecordsListLastLetterDesc(column);
	}
	
	@RequestMapping("/records/sorted/{column}/asc/lastletter") //order: ASC
	public   List<Map<String, String>> getSortedRecordsListLastLetterAsc(@PathVariable String column ){
		return recordsService.getSortedRecordsListLastLetterAsc(column);
	}
	
	//4
	@RequestMapping("/records/grouped/{column}/desc")//order: /DESC
	public  Map<String, RecordGroup>  getRecordGroupMapDesc(@PathVariable String column){
	return	recordsService.getRecordGroupMapDesc(column);
	
	}
	
	//5
	@RequestMapping(method = RequestMethod.POST, value = "/records/search")
	public List<Map<String, String>>  search(@RequestBody List<Map<String, String>> searchParameter) {	
	
		
		return recordsService.searchRecords(searchParameter);
		
	}
	
	 

}
