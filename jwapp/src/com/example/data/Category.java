package com.example.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Category {
	@DatabaseField(generatedId = true)
	private long id;

	@DatabaseField
	private String nom;

	public Category() {
	}

	public Category(String nom) {
		this.nom = nom;
	}

	public long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String name) {
		this.nom = name;
	}
}
