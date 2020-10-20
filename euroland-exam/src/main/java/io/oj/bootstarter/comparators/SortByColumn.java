package io.oj.bootstarter.comparators;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
@Component
public class SortByColumn implements Comparator<Map<String, String>> {

	private String columnName;
	private String order;
	private String specialCase;

	public SortByColumn() {
	}

	public SortByColumn(String columnName, String order, String specialCase) {
		this.columnName = columnName;
		this.order = order;
		this.specialCase = specialCase;
	}

	@Override
	public int compare(Map<String, String> m1, Map<String, String> m2) {
		int compare = 0;
		Set<String> headers = m1.keySet();
		
		for (String header : headers) {
			if (columnName.equalsIgnoreCase(header))
				columnName = header;
		}
		
		if (specialCase.equalsIgnoreCase(SpecialCase.LASTLETTER.toString()))
			compare = m1.get(columnName).substring(m1.get(columnName).length() - 1)
					.compareToIgnoreCase(m2.get(columnName).substring(m2.get(columnName).length() - 1));
		else
			compare = m1.get(columnName).compareToIgnoreCase(m2.get(columnName));

		if (order.equalsIgnoreCase(Order.ASC.toString()))
			return compare;
		else if (order.equalsIgnoreCase(Order.DESC.toString()))
			return compare * (-1);
		return 0;
	}

}
