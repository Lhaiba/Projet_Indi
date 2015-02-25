package DataBase;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Programme_Queries {
 
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "training_db.db";

	//****************************Programme**************************************//
	protected static final String TABLE_Programme = "table_programme";
	public static final String COL_ID_Programme = "_id";
	public static final int NUM_COL_ID_Programme = 0;
	public static final String COL_Nom_Programme = "Nom";
	public static final int NUM_COL_Nom_Programme = 1;
	public static final String COL_Description_Programme = "Description";
	public static final int NUM_COL_Description_Programme = 2;
	public static final String COL_dateDebut_Programme = "dateDebut";
	public static final int NUM_COL_dateDebut_Programme = 3;
	public static final String COL_dateFin_Programme = "dateFin";
	public static final int NUM_COL_dateFin_Programme = 4;

	//******************************************************************//
	public static SQLiteDatabase bdd;
	private static MaBaseSQLite maBaseSQLite;
	//******************************************************************//
	
	public Programme_Queries(Context context){
		
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

	public static long insertProgramme(Programme p){
		ContentValues values = new ContentValues();
		values.put(COL_Nom_Programme, p.getNom());
		values.put(COL_Description_Programme, p.getDescription_Programme());
		values.put(COL_dateDebut_Programme,p.getDateDebut());
		values.put(COL_dateFin_Programme,p.getDateFin());
		return bdd.insert(TABLE_Programme, null, values);
	}
	
	
	public static int updateProgramme(int id,Programme p){
		
		ContentValues values = new ContentValues();
		values.put(COL_Nom_Programme, p.getNom());
		values.put(COL_Description_Programme, p.getDescription_Programme());
		values.put(COL_dateDebut_Programme,p.getDateDebut());
		values.put(COL_dateFin_Programme,p.getDateFin());
		return bdd.update(TABLE_Programme, values, COL_ID_Programme + " = " +id, null);
	}
	
	
	 
		public int removeProgrammeWithID(int id){
			return bdd.delete(TABLE_Programme, COL_ID_Programme + " = " +id, null);
		}
	 
		public static Cursor getProgrammeby_DateD(String DateD){
			String selectQuery = "SELECT *"  + 
					 " FROM "+ TABLE_Programme	+" WHERE "+COL_dateDebut_Programme				
					 + " Like "+"'"+DateD+"'";

		 Cursor c = bdd.rawQuery(selectQuery, null); 
		 
		return c;
		}
		
		
				
		
		public Programme cursorToProgramme(Cursor c,int position){
		
			if (c.getCount() == 0)
				return null;
	 
			c.moveToPosition(position);
		
			Programme p = new Programme();
	
			p.setId(c.getInt(NUM_COL_ID_Programme));
			p.setNom(c.getString(NUM_COL_Nom_Programme));
			p.setDescription_Programme(c.getString(NUM_COL_Description_Programme));
			p.setDateDebut(c.getString(NUM_COL_dateDebut_Programme));
			p.setDateFin(c.getString(NUM_COL_dateFin_Programme));
			
			return p;
		}
		
		
		

}
