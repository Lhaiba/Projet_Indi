package com.example.data;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	
	private static final Class<?>[] classes = new Class[] {
	    Exercise.class,CoordonneeGPS.class,Programme.class,Seance.class,
	    Utilisateur.class,ExerciseSeance.class,Category.class,Seance_Programme.class
	    ,Seance_Utilisateur.class
	  };
	
	public static void main(String[] args) throws Exception {
		writeConfigFile("ormlite_config.txt",classes);
	}
}
