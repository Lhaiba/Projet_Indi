package Base_de_Donnee;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
 
public class MaBaseSQLite extends SQLiteOpenHelper {
 
	
	/*
	 * Table Événement
	 */
	
	private static final String TABLE_Evenement = "table_evenement";
	private static final String Ev_COL_ID = "_id";
	private static final String Ev_COL_Titre = "Titre";
	private static final String Ev_COL_Date = "Date";
	private static final String Ev_COL_Type = "Type";
	
	
	
	private static final String CREATE_BDD_Evenement = "CREATE TABLE " + TABLE_Evenement + " ("
			+ Ev_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Ev_COL_Titre + ","+
			Ev_COL_Date +","+ Ev_COL_Type +");";
	
	
	
	/*--------------------------------------------------------------------------*/
	
	public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		//on créé la table à partir de la requête écrite dans la variable CREATE_BDD
		
		db.execSQL(CREATE_BDD_Evenement);
		
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE " + TABLE_Evenement + ";");
		
		onCreate(db);
	}
 
}