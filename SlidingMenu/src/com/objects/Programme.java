package com.objects;

import java.util.Date;

import com.orm.SugarRecord;

public class Programme extends SugarRecord<Programme>{


	private  String Nom;
	private  String Description_Programme;
	private  String dateDebut;
	private  String dateFin;

	public Programme() {

	}

	public Programme(String Nom,String Description_Programme, String dateDebut, String dateFin) {

		this.Nom = Nom;
		this.Description_Programme = Description_Programme;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

}