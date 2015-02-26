package com.objects;

import com.orm.*;

public class Exercice extends SugarRecord<Exercice> {

	private String Nom;
	private String Seance;
	private String Type;

	public Exercice() {

	}

	public Exercice(String Nom, String Seance, String Type) {

		this.Nom = Nom;
		this.Seance = Seance;
		this.Type = Type;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getSeance() {
		return Seance;
	}

	public void setSeance(String seance) {
		Seance = seance;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

}
