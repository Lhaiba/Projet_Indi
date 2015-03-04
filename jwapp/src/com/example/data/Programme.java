package com.example.data;

import com.j256.ormlite.field.DatabaseField;

public class Programme {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String Nom;
	@DatabaseField
	private String Description_Programme;
	@DatabaseField
	private String dateDebut;
	@DatabaseField
	private String dateFin;

	public Programme() {

	}

	public Programme(String Nom, String Description_Programme,
			String dateDebut, String dateFin) {

		this.Nom = Nom;
		this.Description_Programme = Description_Programme;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

}