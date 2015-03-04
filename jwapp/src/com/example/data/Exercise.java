package com.example.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Exercise {

	/*
	 * General
	 */

	@DatabaseField(generatedId = true)
	private long id;

	@DatabaseField(canBeNull = false)
	private String nom;

	@DatabaseField
	private int image;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
	private Category category;

	public Exercise() {
	}

	public Exercise(String nom, int image, Category category) {
		super();
		this.nom = nom;
		this.image = image;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setAttributesfromExercise (Exercise e) {
		// General
		this.nom = e.getNom();
		this.image = e.getImage();
		this.category = e.getCategory();
		
		// Musculation
		this.nbRepetitions = e.getNbRepetitions();
		this.tpsRepos = e.getTpsRepos();
		
		// Cardio
		this.parcours = e.getParcours();
		this.temps = e.getTemps();
	}
	
	/*
	 * Musculation
	 */

	@DatabaseField
	private int nbRepetitions;

	@DatabaseField
	private String tpsRepos;

	public void setMuscleSpec(int nbRepetitions, String tpsRepos) {
		this.nbRepetitions = nbRepetitions;
		this.tpsRepos = tpsRepos;
	}

	public int getNbRepetitions() {
		return nbRepetitions;
	}

	public void setNbRepetitions(int nbRepetitions) {
		this.nbRepetitions = nbRepetitions;
	}

	public String getTpsRepos() {
		return tpsRepos;
	}

	public void setTpsRepos(String tpsRepos) {
		this.tpsRepos = tpsRepos;
	}

	/*
	 * Cardio
	 */

	@DatabaseField
	private String parcours;

	@DatabaseField
	private long temps; // in milliseconds

	public void setCardioSpec(String parcours, long temps) {
		this.parcours = parcours;
		this.temps = temps;
	}

	public String getParcours() {
		return parcours;
	}

	public void setParcours(String parcours) {
		this.parcours = parcours;
	}

	public long getTemps() {
		return temps;
	}

	public void setTemps(long temps) {
		this.temps = temps;
	}

}
