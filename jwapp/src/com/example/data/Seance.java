package com.example.data;

import com.j256.ormlite.field.DatabaseField;

public class Seance  {
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String Nom;
	@DatabaseField
	private String Date;
	@DatabaseField
	private String heureDebut;
	@DatabaseField
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
