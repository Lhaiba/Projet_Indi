package com.example.jwapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.data.Category;
import com.example.data.DatabaseHelper;
import com.example.data.Exercise;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class MainActivity extends Activity {

	private DatabaseHelper databaseHelper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeDb();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	public void onClick_Exercise(View view) {
		Intent intent = new Intent(this, ExerciseMainPage.class);
		startActivity(intent);
	}

	/*
	 * Initialize the database on first use
	 */
	private void initializeDb() {

		RuntimeExceptionDao<Exercise, Long> exerciseDao = getHelper()
				.getExerciseRuntimeDao();
		List<Exercise> list = exerciseDao.queryForAll();

		if (list.isEmpty()) {

			RuntimeExceptionDao<Category, Long> categoryDao = getHelper()
					.getCategoryRuntimeDao();

			Category muscle = new Category("Musculation");
			Category cardio = new Category("Cardio");

			Exercise abdominaux = new Exercise("Abdominaux 1",
					R.drawable.flexion_icon_24, muscle);
			abdominaux.setMuscleSpec(15, "1 minute");

			Exercise pompes = new Exercise("Pompes 1", R.drawable.push_icon_24,
					muscle);
			pompes.setMuscleSpec(10, "1 minute");

			Exercise velo = new Exercise("Velo 1", R.drawable.bike_icon_24,
					cardio);
			velo.setCardioSpec("parcours 1", 1200000);

			categoryDao.create(muscle);
			categoryDao.create(cardio);
			exerciseDao.create(abdominaux);
			exerciseDao.create(pompes);
			exerciseDao.create(velo);

		}

	}

}
