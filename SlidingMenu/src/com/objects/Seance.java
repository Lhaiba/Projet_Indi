package com.objects;

import java.util.Date;

import com.orm.SugarRecord;

public class Seance  extends SugarRecord<Seance>{
	
	private String Nom;
	private String Date;
	private String heureDebut;
	private String heureFin;

	public Seance() {

	}

	public Seance(String Nom,String Date, String heureDebut, String heureFin) {

		this.Nom = Nom;
		this.Date = Date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}



}
