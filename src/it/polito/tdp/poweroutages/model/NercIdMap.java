package it.polito.tdp.poweroutages.model;

import java.util.HashMap;
import java.util.Map;

public class NercIdMap {

	private Map<Integer, Nerc> map;
	
	public NercIdMap() {
		
		map = new HashMap<>();
	}
	
	public Nerc get(int id) {
		return map.get(id);
	}
	
	public Nerc get(Nerc nerc) {
		Nerc v= map.get(nerc.getId());
		if (v == null) {
			map.put(nerc.getId(), nerc);
			return nerc;
		} else {
			return v;
		}
	}
	
	public void put(int id, Nerc n) {
		map.put(id, n);
	}
}