package DataBase;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Seance_Queries {
 
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "training_db.db";

	//****************************Seance**************************************//
	protected static final String TABLE_Seance = "table_seance";
	public static final String COL_ID_Seance = "_id";
	public static final int NUM_COL_ID_Seance = 0;
	public static final String COL_Nom_Seance = "Nom";
	public static final int NUM_COL_Nom_Seance = 1;
	public static final String COL_Date_Seance = "Date";
	public static final int NUM_COL_Date_Seance = 2;
	public static final String COL_heureDebut_Seance = "heureDebut";
	public static final int NUM_COL_heureDebut_Seance = 3;
	public static final String COL_heureFin_Seance = "heureFin";
	public static final int NUM_COL_heureFin_Seance = 4;
	
	
	
	//******************************************************************//
	public static SQLiteDatabase bdd;
	private static MaBaseSQLite maBaseSQLite;
	//******************************************************************//
	
	public Seance_Queries(Context context){
		
		maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en ecriture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public static void close(){
		//on ferme l'acc�s � la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}

	public static long insertSeance(Seance s){
		ContentValues values = new ContentValues();
		values.put(COL_Nom_Seance, s.getNom());
		values.put(COL_Date_Seance, s.getDate());
		values.put(COL_heureDebut_Seance, s.getHeureDebut());
		values.put(COL_heureFin_Seance, s.getHeureFin());
		return bdd.insert(TABLE_Seance, null, values);
	}
	
	
	public static int updateExercice(int id, Seance s){

		ContentValues values = new ContentValues();
		values.put(COL_Nom_Seance, s.getNom());
		values.put(COL_Date_Seance, s.getDate());
		values.put(COL_heureDebut_Seance, s.getHeureDebut());
		values.put(COL_heureFin_Seance, s.getHeureFin());
		return bdd.update(TABLE_Seance, values, COL_ID_Seance + " = " +id, null);
	}
	
	
	 
		public int removeExerciceWithID(int id){
			return bdd.delete(TABLE_Seance, COL_ID_Seance + " = " +id, null);
		}
	 
		public static Cursor getExerciceby_Date(String Date){
			String selectQuery = "SELECT *"  + 
					 " FROM "+ TABLE_Seance	+" WHERE "+COL_Date_Seance				
					 + " Like "+"'"+Date+"'";

		 Cursor c = bdd.rawQuery(selectQuery, null); 
		 
		return c;
		}
				
				
		
		public Seance cursorToExercice(Cursor c,int position){
		
			if (c.getCount() == 0)
				return null;
	 
			c.moveToPosition(position);
		
			Seance s = new Seance();
	
			s.setId(c.getInt(NUM_COL_ID_Seance));
			s.setNom(c.getString(NUM_COL_Nom_Seance));
			s.setDate(c.getString(NUM_COL_Date_Seance));
			s.setHeureDebut(c.getString(NUM_COL_heureDebut_Seance));
			s.setHeureFin(c.getString(NUM_COL_heureFin_Seance));
			
			return s;
		}
		
		
		

}
