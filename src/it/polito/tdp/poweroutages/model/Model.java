package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	private PowerOutageDAO podao;
	private NercIdMap nercIdMap;
	private List<Nerc> nercList;
	private List<PowerOutage> eventiList; 
	
	
	public Model() {
		podao = new PowerOutageDAO();
		nercIdMap= new NercIdMap();
		nercList=podao.getNercList(nercIdMap);
		eventiList=podao.getPowerOutageList(nercIdMap);
		
	}
	
	public List<PowerOutage> getEventiList(){
		return this.eventiList;
	}
	public List<Nerc> getNercList() {
		return this.nercList;
	}
	public List<Integer> getAnniList() {
		Set<Integer> anniSet = new HashSet<Integer>();
		for (PowerOutage e : eventiList) {
			anniSet.add(e.getAnno());
		}
		List<Integer> anniList = new ArrayList<Integer>(anniSet);
		anniList.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
			
		});
		return anniList;
}
}
