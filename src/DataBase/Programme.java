package DataBase;

import java.util.Date;

public class Programme {

	private static int id;
	private static String Nom;
	private static String Description_Programme;
	private static String dateDebut;
	private static String dateFin;

	public Programme() {
		Programme.id = 0;
		Programme.Nom = null;
		Programme.Description_Programme = null;
		Programme.dateDebut = null;
		Programme.dateFin = null;

	}

	public Programme(String Nom,String Description_Programme, String dateDebut, String dateFin) {

		Programme.Nom = Nom;
		Programme.Description_Programme = Description_Programme;
		Programme.dateDebut = dateDebut;
		Programme.dateFin = dateFin;
	}

	
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Programme.id = id;
	}

	public static String getNom() {
		return Nom;
	}

	public static void setNom(String nom) {
		Nom = nom;
	}

	public static String getDescription_Programme() {
		return Description_Programme;
	}

	public static void setDescription_Programme(String description_Programme) {
		Description_Programme = description_Programme;
	}

	public static String getDateDebut() {
		return dateDebut;
	}

	public static void setDateDebut(String dateDebut) {
		Programme.dateDebut = dateDebut;
	}

	public static String getDateFin() {
		return dateFin;
	}

	public static void setDateFin(String dateFin) {
		Programme.dateFin = dateFin;
	}

	
	

	

}
