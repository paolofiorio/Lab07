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
	private List<PowerOutage> filtro;
	private List<PowerOutage> soluzione;
	
	private int maxPersone;
	
	
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
	public int sommaAffectedPeople(List<PowerOutage> p) {
		int somma=0;
		for(PowerOutage e:p) {
			somma+= e.getAffectedPeople();
		}
		return somma;
		
	}
	public int sommaOutageOre(List<PowerOutage> p) {
		int somma=0;
		for(PowerOutage e: p) {
			somma+=e.getOutageDurata();
		}
		return somma;
	}
	
	private boolean controlloAnni(List<PowerOutage> p, int max) {
		if(p.size()>=2) {
			int x= p.get(0).getAnno();
			int y=p.get(p.size()-1).getAnno();
			if((y-x+1)>max)
				//ho sforato
				return false;
		}
		return true;
		
	}
	
	private boolean controlloOre(List<PowerOutage> p, int max) {
		int somma=sommaOutageOre(p);
		if(somma>max) {
			//ho sforato
			return false;
			}
		return true;
	}
	public List<PowerOutage> casoPeggiore(int maxAnni, int maxOre, Nerc n){
		
		soluzione= new ArrayList<>();
		filtro= new ArrayList<>();
		maxPersone=0;
		
		for(PowerOutage e: eventiList) {
			//se contiene il nerc
			if(e.getNerc().equals(n))
				//aggiungo alla lista filtrata
				filtro.add(e);
		}
		//ordino la lista per tempi di inizio
		filtro.sort(new Comparator<PowerOutage>()
		{
			public int compare(PowerOutage p1, PowerOutage p2) {
				return p1.getOutageInizio().compareTo(p2.getOutageInizio());
			}
		});
			
		
		
		ricorsione(new ArrayList<PowerOutage>() , maxAnni,maxOre);
		

		
		return soluzione;
	}

	private void ricorsione(List<PowerOutage> p, int maxAnni, int maxOre) {
		// TODO Auto-generated method stub
		if(sommaAffectedPeople(p)>maxPersone) {
			//aggiorno il max delle persone
			maxPersone=sommaAffectedPeople(p);
			soluzione= new ArrayList<PowerOutage>(p);
		}
		
		for(PowerOutage e:filtro) {
			if(!p.contains(e)) {
				p.add(e);
				
				if(controlloAnni(p,maxAnni) && controlloOre(p,maxOre))
					ricorsione(p,maxAnni-1,maxOre-1);
			}
			p.remove(e);
		}
		
		
		
			
	}

	
}
