package Base_de_Donnee;



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
	
	public Evenement_Queries(Context context){
		
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
		values.put(COL_Nom_Exercice, e.getTitre());
		values.put(COL_Type_Exercice, e.getDate());
		values.put(COL_Seance_Exercice, e.getType());
		return bdd.insert(TABLE_Exercice, null, values);
	}
	
	
	public static int updateExercice(int id, Exercice e){

		ContentValues values = new ContentValues();
		values.put(COL_Nom_Exercice,  e.getTitre());
		values.put(COL_Type_Exercice, e.getDate());
		values.put(COL_Type_Exercice, e.getType());
		return bdd.update(TABLE_Exercice, values, COL_ID_evenement + " = " +id, null);
	}
	
	
	 
		public int removeEvenementWithID(int id){
			return bdd.delete(TABLE_Exercice, COL_ID_evenement + " = " +id, null);
		}
	 
		public static Cursor getEvenementby_Date(String Date){
			String selectQuery = "SELECT *"  + 
					 " FROM "+ TABLE_Exercice	+" WHERE "+COL_Type_Exercice				
					 + " Like "+"'"+Date+"'";

		 Cursor c = bdd.rawQuery(selectQuery, null); 
		 
		return c;
		}
		
		public static Cursor getEvenementbyMonth_Year(int Month,int Year){ 
			
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
		
				
		
		public Evenement cursorToEvenement(Cursor c,int position){
		
			if (c.getCount() == 0)
				return null;
	 
			c.moveToPosition(position);
		
			Evenement e = new Evenement();
	
			e.setId(c.getInt(NUM_COL_ID_evenement));
			e.setTitre(c.getString(NUM_COL_Titre_evenement));
			e.setDate(c.getString(NUM_COL_Date_evenement));
			e.setType(c.getString(NUM_COL_Type_evenement));
			
			return e;
		}
		
		
		

}
