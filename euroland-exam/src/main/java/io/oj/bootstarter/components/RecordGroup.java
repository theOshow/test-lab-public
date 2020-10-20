package io.oj.bootstarter.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RecordGroup {

	private List<Map<String, String>> members;
	private int count;



	public RecordGroup(List<Map<String, String>> members) {
		if (members == null)
			members = new ArrayList<Map<String, String>>();
		this.members =members;
		count=members.size();
		//members.add(map);
	}

	
	
	public RecordGroup() {
	}

	public void addMember(Map<String, String> map) {
		members.add(map);
		count++;
		;
	}

	public List<Map<String, String>> getMembers() {
		return members;
	}

	public void setMembers(List<Map<String, String>> members) {
		this.members = members;
	}

	public int getCount() {

		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
