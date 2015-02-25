package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MaBaseSQLite extends SQLiteOpenHelper {

	/*
	 * Table Exercice
	 */

	private static final String TABLE_Exercice = "table_exercice";

	public static final String COL_ID_Exercice = "_id";
	public static final String COL_Nom_Exercice = "Nom";
	public static final String COL_Type_Exercice = "Type";
	public static final String COL_Seance_Exercice = "Seance";

	private static final String CREATE_Table_Exercice = "CREATE TABLE "
			+ TABLE_Exercice + " (" + COL_ID_Exercice
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_Nom_Exercice + ","
			+ COL_Type_Exercice + "," + COL_Seance_Exercice + ");";

	/*--------------------------------------------------------------------------*/

	/*
	 * Table Seance
	 */

	private static final String TABLE_Seance = "table_seance";

	public static final String COL_ID_Seance = "_id";
	public static final String COL_Date_Seance = "Nom";
	public static final String COL_heureDebut_Seance = "Type";
	public static final String COL_heureFin_Seance = "Seance";

	private static final String CREATE_Table_Seance = "CREATE TABLE "
			+ TABLE_Seance + " (" + COL_ID_Seance
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_Date_Seance + ","
			+ COL_heureDebut_Seance + "," + COL_heureFin_Seance + ");";

	/*--------------------------------------------------------------------------*/

	/*
	 * Table Programme
	 */

	private static final String TABLE_Programme = "table_programme";

	public static final String COL_ID_Programme = "_id";
	public static final String COL_Nom_Programme = "Nom";
	public static final String COL_Description_Programme = "Description";
	public static final String COL_dateDebut_Programme = "dateDebut";
	public static final String COL_dateFin_Programme = "dateFin";

	private static final String CREATE_Table_Programme = "CREATE TABLE "
			+ TABLE_Programme + " (" + COL_ID_Programme
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_Nom_Programme + ","
			+ COL_Description_Programme + "," + COL_dateDebut_Programme + ","
			+ COL_dateFin_Programme + ");";

	/*--------------------------------------------------------------------------*/

	public MaBaseSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// on créé la table à partir de la requête écrite dans la variable
		// CREATE_BDD

		db.execSQL(CREATE_Table_Exercice);
		db.execSQL(CREATE_Table_Seance);
		db.execSQL(CREATE_Table_Programme);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE " + TABLE_Exercice + ";");
		db.execSQL("DROP TABLE " + TABLE_Seance + ";");
		db.execSQL("DROP TABLE " + TABLE_Programme + ";");
		db.execSQL(CREATE_Table_Exercice);
		db.execSQL(CREATE_Table_Seance);
		db.execSQL(CREATE_Table_Programme);

		onCreate(db);
	}

}