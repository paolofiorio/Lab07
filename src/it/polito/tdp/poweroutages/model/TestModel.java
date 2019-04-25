package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		List<Nerc> nercList = model.getNercList();
		System.out.println("Nerc List size: " + nercList.size());
		
		Nerc selectedNerc = nercList.get(3);
		List<PowerOutage> peggiore = model.casoPeggiore(3,10, selectedNerc);
		
		System.out.println("Tot people affected: " + model.sommaAffectedPeople(peggiore));
		System.out.println("Tot hours of outage: " + model.sommaOutageOre(peggiore));
		
		for (PowerOutage event : peggiore) {
			System.out.println(String.format("%d %s %s %d %d", event.getAnno(), event.getOutageInizio(),
					event.getOutageFine(), event.getOutageDurata(), event.getAffectedPeople()));
}
}}
