package com.example.data;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;


public class Utilisateur {
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String email_Utilisateur;
	@DatabaseField
    private String password_Utilisateur;
	@DatabaseField
    private String nom_Utilisateur;
	@DatabaseField
    private String prenom_Utilisateur;
	@DatabaseField
    private Date ddn_Utilisateur;
	@DatabaseField
    private Double taille_Utilisateur;
	@DatabaseField
    private Double poids_Utilisateur;

	public Utilisateur() {
		
	}

	public Utilisateur(String email_Utilisateur, String password_Utilisateur, String nom_Utilisateur,
			String prenom_Utilisateur,Date ddn_Utilisateur,Double taille_Utilisateur,Double poids_Utilisateur) {

		this.email_Utilisateur = email_Utilisateur;
		this.password_Utilisateur = password_Utilisateur;
		this.nom_Utilisateur = nom_Utilisateur;
		this.prenom_Utilisateur = prenom_Utilisateur;
		this.ddn_Utilisateur = ddn_Utilisateur;
		this.taille_Utilisateur = taille_Utilisateur;
		this.poids_Utilisateur = poids_Utilisateur;
		
	}

	
	

	
	

}
