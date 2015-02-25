package DataBase;

import java.util.Date;

public class Seance {

	private static int id;
	private static String Nom;
	private static String Date;
	private static String heureDebut;
	private static String heureFin;

	public Seance() {
		Seance.id = 0;
		Seance.Nom = null;
		Seance.Date = null;
		Seance.heureDebut = null;
		Seance.heureFin = null;

	}

	public Seance(String Nom,String Date, String heureDebut, String heureFin) {

		Seance.Nom = Nom;
		Seance.Date = Date;
		Seance.heureDebut = heureDebut;
		Seance.heureFin = heureFin;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Seance.id = id;
	}
	
	public static int getNom() {
		return id;
	}

	public static void setNom(String Nom) {
		Seance.Nom = Nom;
	}

	public static String getDate() {
		return Date;
	}

	public static void setDate(String date) {
		Date = date;
	}

	public static String getHeureDebut() {
		return heureDebut;
	}

	public static void setHeureDebut(String heureDebut) {
		Seance.heureDebut = heureDebut;
	}

	public static String getHeureFin() {
		return heureFin;
	}

	public static void setHeureFin(String heureFin) {
		Seance.heureFin = heureFin;
	}


	

}
