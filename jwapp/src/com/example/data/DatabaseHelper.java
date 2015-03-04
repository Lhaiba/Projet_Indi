package com.example.data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.jwapp.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "Sports_DataBase.db";

	private static final int DATABASE_VERSION = 4;

	// the DAO object we use to access the tables

	private Dao<CoordonneeGPS, Integer> CoordonneeGPSDao = null;
	private RuntimeExceptionDao<CoordonneeGPS, Integer> CoordonneeGPSRuntimeDao = null;

	private Dao<Exercise, Integer> ExerciseDao = null;
	private RuntimeExceptionDao<Exercise, Long> ExerciseRuntimeDao = null;

	private Dao<Utilisateur, Integer> UtilisateurDao = null;
	private RuntimeExceptionDao<Utilisateur, Integer> UtilisateurRuntimeDao = null;

	private Dao<Seance, Integer> SeanceDao = null;
	private RuntimeExceptionDao<Seance, Integer> SeanceRuntimeDao = null;

	private Dao<Programme, Integer> ProgrammeDao = null;
	private RuntimeExceptionDao<Programme, Integer> ProgrammeRuntimeDao = null;

	private Dao<ExerciseSeance, Integer> ExerciseSeanceDao = null;
	private RuntimeExceptionDao<ExerciseSeance, Integer> ExerciseSeanceRuntimeDao = null;

	private Dao<Category, Long> categoryDao = null;
	private RuntimeExceptionDao<Category, Long> categoryRuntimeDao = null;

	private Dao<Seance_Programme, Long> Seance_ProgrammeDao = null;
	private RuntimeExceptionDao<Seance_Programme, Long> Seance_ProgrammeRuntimeDao = null;
	
	private Dao<Seance_Utilisateur, Long> Seance_UtilisateurDao = null;
	private RuntimeExceptionDao<Seance_Utilisateur, Long> Seance_UtilisateurRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Exercise.class);
			TableUtils.createTable(connectionSource, Seance.class);
			TableUtils.createTable(connectionSource, Programme.class);
			TableUtils.createTable(connectionSource, CoordonneeGPS.class);
			TableUtils.createTable(connectionSource, Utilisateur.class);
			TableUtils.createTable(connectionSource, ExerciseSeance.class);
			TableUtils.createTable(connectionSource, Category.class);
			TableUtils.createTable(connectionSource, Seance_Programme.class);
			TableUtils.createTable(connectionSource, Seance_Utilisateur.class);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Exercise.class, true);
			TableUtils.dropTable(connectionSource, Seance.class, true);
			TableUtils.dropTable(connectionSource, Programme.class, true);
			TableUtils.dropTable(connectionSource, CoordonneeGPS.class, true);
			TableUtils.dropTable(connectionSource, Utilisateur.class, true);
			TableUtils.dropTable(connectionSource, ExerciseSeance.class, true);
			TableUtils.dropTable(connectionSource, Category.class, true);
			TableUtils.dropTable(connectionSource, Seance_Programme.class, true);
			TableUtils.dropTable(connectionSource, Seance_Utilisateur.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ----------DaoObjets + DaoRuntime---------------
	 */
	// //////////////////////////////////////////////
	// /

	// ------Exercise----------
	public Dao<Exercise, Integer> getExerciseDao() throws SQLException {
		if (ExerciseDao == null) {
			ExerciseDao = getDao(Exercise.class);
		}
		return ExerciseDao;
	}

	public RuntimeExceptionDao<Exercise, Long> getExerciseRuntimeDao() {
		if (ExerciseRuntimeDao == null) {
			ExerciseRuntimeDao = getRuntimeExceptionDao(Exercise.class);
		}
		return ExerciseRuntimeDao;
	}

	// ------Seance----------
	public Dao<Seance, Integer> getSeanceDao() throws SQLException {
		if (SeanceDao == null) {
			SeanceDao = getDao(Seance.class);
		}
		return SeanceDao;
	}

	public RuntimeExceptionDao<Seance, Integer> getSeanceRuntimeDao() {
		if (SeanceRuntimeDao == null) {
			SeanceRuntimeDao = getRuntimeExceptionDao(Seance.class);
		}
		return SeanceRuntimeDao;
	}

	// ------Programme--------
	public Dao<Programme, Integer> getProgrammeDao() throws SQLException {
		if (ProgrammeDao == null) {
			ProgrammeDao = getDao(Programme.class);
		}
		return ProgrammeDao;
	}

	public RuntimeExceptionDao<Programme, Integer> getProgrammeRuntimeDao() {
		if (ProgrammeRuntimeDao == null) {
			ProgrammeRuntimeDao = getRuntimeExceptionDao(Programme.class);
		}
		return ProgrammeRuntimeDao;
	}

	

	// ------CoordonneeGPS----------
	public Dao<CoordonneeGPS, Integer> getCoordonneeGPSDao()
			throws SQLException {
		if (CoordonneeGPSDao == null) {
			CoordonneeGPSDao = getDao(CoordonneeGPS.class);
		}
		return CoordonneeGPSDao;
	}

	public RuntimeExceptionDao<CoordonneeGPS, Integer> getCoordonneeGPSRuntimeDao() {
		if (CoordonneeGPSRuntimeDao == null) {
			CoordonneeGPSRuntimeDao = getRuntimeExceptionDao(CoordonneeGPS.class);
		}
		return CoordonneeGPSRuntimeDao;
	}

	
	

	// ------Utilisateur----------
	public Dao<Utilisateur, Integer> getUtilisateurDao() throws SQLException {
		if (UtilisateurDao == null) {
			UtilisateurDao = getDao(Utilisateur.class);
		}
		return UtilisateurDao;
	}

	public RuntimeExceptionDao<Utilisateur, Integer> getUtilisateurRuntimeDao() {
		if (UtilisateurRuntimeDao == null) {
			UtilisateurRuntimeDao = getRuntimeExceptionDao(Utilisateur.class);
		}
		return UtilisateurRuntimeDao;
	}

	// ------ExerciseSeance----------
	public Dao<ExerciseSeance, Integer> getExerciseSeanceDao()
			throws SQLException {
		if (ExerciseSeanceDao == null) {
			ExerciseSeanceDao = getDao(ExerciseSeance.class);
		}
		return ExerciseSeanceDao;
	}

	public RuntimeExceptionDao<ExerciseSeance, Integer> getExerciseSeanceRuntimeDao() {
		if (ExerciseSeanceRuntimeDao == null) {
			ExerciseSeanceRuntimeDao = getRuntimeExceptionDao(ExerciseSeance.class);
		}
		return ExerciseSeanceRuntimeDao;
	}

	// ------Category----------
	public Dao<Category, Long> getCategoryDao() throws SQLException {
		if (categoryDao == null) {
			categoryDao = getDao(Category.class);
		}
		return categoryDao;
	}

	public RuntimeExceptionDao<Category, Long> getCategoryRuntimeDao() {
		if (categoryRuntimeDao == null) {
			categoryRuntimeDao = getRuntimeExceptionDao(Category.class);
		}
		return categoryRuntimeDao;
	}

	// ------Seance_Programme----------
	public Dao<Seance_Programme, Long> getSeance_ProgrammeDao()
			throws SQLException {
		if (Seance_ProgrammeDao == null) {
			Seance_ProgrammeDao = getDao(Seance_Programme.class);
		}
		return Seance_ProgrammeDao;
	}

	public RuntimeExceptionDao<Seance_Programme, Long> getSeance_ProgrammeRuntimeDao() {
		if (Seance_ProgrammeRuntimeDao == null) {
			Seance_ProgrammeRuntimeDao = getRuntimeExceptionDao(Seance_Programme.class);
		}
		return Seance_ProgrammeRuntimeDao;
	}
	
	// ------Seance_Utilisateur----------
		public Dao<Seance_Utilisateur, Long> getSeance_UtilisateurDao()
				throws SQLException {
			if (Seance_UtilisateurDao == null) {
				Seance_UtilisateurDao = getDao(Seance_Utilisateur.class);
			}
			return Seance_UtilisateurDao;
		}

		public RuntimeExceptionDao<Seance_Utilisateur, Long> getSeance_UtilisateurRuntimeDao() {
			if (Seance_UtilisateurRuntimeDao == null) {
				Seance_UtilisateurRuntimeDao = getRuntimeExceptionDao(Seance_Utilisateur.class);
			}
			return Seance_UtilisateurRuntimeDao;
		}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		ExerciseDao = null;
		ExerciseRuntimeDao = null;

		SeanceDao = null;
		SeanceRuntimeDao = null;

		ProgrammeDao = null;
		ProgrammeRuntimeDao = null;

		CoordonneeGPSDao = null;
		CoordonneeGPSRuntimeDao = null;

		UtilisateurDao = null;
		UtilisateurRuntimeDao = null;

		ExerciseSeanceDao = null;
		ExerciseSeanceRuntimeDao = null;

		Seance_ProgrammeDao = null;
		Seance_ProgrammeRuntimeDao = null;
		
		Seance_UtilisateurDao = null;
		Seance_UtilisateurRuntimeDao = null;
	}
}