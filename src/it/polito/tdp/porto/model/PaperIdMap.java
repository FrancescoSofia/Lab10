package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.Map;

public class PaperIdMap {
	
	private Map<Integer,Paper> map ;
	
	public PaperIdMap() {
		map = new HashMap<>() ;
	}
	
	public Paper get(Integer eprintId) {
		return map.get(eprintId) ;
	}
	
	public Paper put(Paper paper) {
		Paper old = map.get(paper.getEprintid()) ; 
		if(old==null) {
			map.put(paper.getEprintid(), paper) ;
			return paper ;
		} else {
			return old ;
		}
	}
	
}
