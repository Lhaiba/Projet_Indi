package DataBase;

public class Exercice {

	private static int id;
	private static String Nom;
	private static String Seance;
	private static String Type;

	public Exercice() {
		Exercice.id = 0;
		Exercice.Nom = null;
		Exercice.Seance = null;
		Exercice.Type = null;

	}

	public Exercice(String Nom, String Seance, String Type) {

		Exercice.Nom = Nom;
		Exercice.Seance = Seance;
		Exercice.Type = Type;
	}

	public static String getNom() {
		return Nom;
	}

	public static void setNom(String nom) {
		Nom = nom;
	}

	public static String getSeance() {
		return Seance;
	}

	public static void setSeance(String seance) {
		Seance = seance;
	}

	public static String getType() {
		return Type;
	}

	public static void setType(String type) {
		Type = type;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Exercice.id = id;
	}

	
	public String toString() {
		return "ID : " + id + "\nTitre : " + Nom + "\nDate de l'Exercice : "
				+ Seance;
	}

}
