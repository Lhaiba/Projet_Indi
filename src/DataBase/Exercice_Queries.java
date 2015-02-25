package DataBase;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Exercice_Queries {
 
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "training_db.db";

	//****************************Exercice**************************************//
	protected static final String TABLE_Exercice = "table_exercice";
	public static final String COL_ID_Exercice = "_id";
	public static final int NUM_COL_ID_Exercice = 0;
	public static final String COL_Nom_Exercice = "Nom";
	public static final int NUM_COL_Nom_Exercice = 1;
	public static final String COL_Type_Exercice = "Type";
	public static final int NUM_COL_Type_Exercice = 2;
	public static final String COL_Seance_Exercice = "Seance";
	public static final int NUM_COL_Seance_Exercice = 3;
	
	
	
	//******************************************************************//
	public static SQLiteDatabase bdd;
	private static MaBaseSQLite maBaseSQLite;
	//******************************************************************//
	
	public Exercice_Queries(Context context){
		
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

	public static long insertExercice(Exercice e){
		ContentValues values = new ContentValues();
		values.put(COL_Nom_Exercice, e.getNom());
		values.put(COL_Type_Exercice, e.getSeance());
		values.put(COL_Seance_Exercice, e.getType());
		return bdd.insert(TABLE_Exercice, null, values);
	}
	
	
	public static int updateExercice(int id, Exercice e){

		ContentValues values = new ContentValues();
		values.put(COL_Nom_Exercice, e.getNom());
		values.put(COL_Type_Exercice, e.getSeance());
		values.put(COL_Seance_Exercice, e.getType());
		return bdd.update(TABLE_Exercice, values, COL_ID_Exercice + " = " +id, null);
	}
	
	
	 
		public int removeExerciceWithID(int id){
			return bdd.delete(TABLE_Exercice, COL_ID_Exercice + " = " +id, null);
		}
	 
		public static Cursor getExerciceby_Date(String Date){
			String selectQuery = "SELECT *"  + 
					 " FROM "+ TABLE_Exercice	+" WHERE "+COL_Type_Exercice				
					 + " Like "+"'"+Date+"'";

		 Cursor c = bdd.rawQuery(selectQuery, null); 
		 
		return c;
		}
		
		public static Cursor getExercicebyMonth_Year(int Month,int Year){ 
			
			Cursor c;
			 String selectQuery1 = "SELECT *"  + 
						 " FROM "+ TABLE_Exercice	+" WHERE "+COL_Type_Exercice				
						 + " Like "+"'"+"___"+Month+"_"+Year+"'";
			 
			 String selectQuery2 = "SELECT *"  + 
					 " FROM "+ TABLE_Exercice	+" WHERE "+COL_Type_Exercice				
					 + " Like "+"'"+"___0"+Month+"_"+Year+"'";
			 
			 if(Month<10)
			 c = bdd.rawQuery(selectQuery2, null); 
			 else
				 c = bdd.rawQuery(selectQuery1, null); 
			 
			return c;
		 
		}
		
				
		
		public Exercice cursorToExercice(Cursor c,int position){
		
			if (c.getCount() == 0)
				return null;
	 
			c.moveToPosition(position);
		
			Exercice e = new Exercice();
	
			e.setId(c.getInt(NUM_COL_ID_Exercice));
			e.setNom(c.getString(NUM_COL_Nom_Exercice));
			e.setSeance(c.getString(NUM_COL_Seance_Exercice));
			e.setType(c.getString(NUM_COL_Type_Exercice));
			
			return e;
		}
		
		
		

}
