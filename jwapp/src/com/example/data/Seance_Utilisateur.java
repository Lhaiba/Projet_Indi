package com.example.data;

import com.j256.ormlite.field.DatabaseField;


public class Seance_Utilisateur {

		@DatabaseField(generatedId = true)
		private int id;
		@DatabaseField(foreign = true, columnName = "seance_id")
		Seance seance;
		@DatabaseField(foreign = true, columnName = "utilisateur_id")
		Utilisateur utilisateur;

		public Seance_Utilisateur() {

		}

		public Seance_Utilisateur(Seance seance,Utilisateur utilisateur) {
			this.seance = seance;
			this.utilisateur = utilisateur;
			}

}
