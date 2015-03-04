package com.example.jwapp;

import swipecontent.exercise.CustomPagerAdapter;
import swipecontent.exercise.DialogFragment_SelectImage.DialogSelectImageListener;
import swipecontent.exercise.Fragment_Category;
import swipecontent.exercise.Fragment_Category.CategoryContainer;
import swipecontent.exercise.Fragment_Details;
import swipecontent.exercise.Fragment_Details.DetailsContainer;
import swipecontent.exercise.Fragment_Indicator;
import swipecontent.exercise.Fragment_Indicator.IndicatorContainer;
import swipecontent.exercise.Fragment_Model;
import swipecontent.exercise.Fragment_Model.ModelContainer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.example.data.Category;
import com.example.data.DatabaseHelper;
import com.example.data.Exercise;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class ExerciseEdit extends FragmentActivity implements
		IndicatorContainer, CategoryContainer, ModelContainer,
		DetailsContainer, DialogSelectImageListener {

	ViewPager mViewPager = null;
	Fragment_Indicator mFragmentIndicator;

	Fragment_Category mFragmentCategory;
	String mFragmentCategoryTag;

	Fragment_Model mFragmentModel;
	String mFragmentModelTag;

	Fragment_Details mFragmentDetails;
	String mFragmentDetailsTag;

	// R�f�rences vers l'exercice � modifier
	long idkey_exerciseToEdit = -1;
	Exercise exerciseToEdit = null;

	long idkey_category = -1;
	String categoryName;
	long idkey_model_exercise = -1;

	boolean editFirstTime = true;

	DatabaseHelper databaseHelper = null;

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	/*
	 * Memorise le Tag des fragments associ�s � cette vue pour permettre une
	 * r�utilisation apr�s destruction
	 */
	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);

		if (fragment instanceof Fragment_Category) {
			mFragmentCategoryTag = fragment.getTag();
		}

		if (fragment instanceof Fragment_Model) {
			mFragmentModelTag = fragment.getTag();
		}

		if (fragment instanceof Fragment_Details) {
			mFragmentDetailsTag = fragment.getTag();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// r�initialisation des valeurs
		if (savedInstanceState != null) {
			idkey_exerciseToEdit = savedInstanceState.getLong(
					"idkey_exerciseToEdit", -1);
			editFirstTime = savedInstanceState.getBoolean("editFirstTime");

			idkey_category = savedInstanceState.getLong("idkey_category");
			categoryName = savedInstanceState.getString("categoryName");
			idkey_model_exercise = savedInstanceState
					.getLong("idkey_model_exercise");

			mFragmentCategoryTag = savedInstanceState
					.getString("mFragmentCategoryTag");
			mFragmentModelTag = savedInstanceState
					.getString("mFragmentModelTag");
			mFragmentDetailsTag = savedInstanceState
					.getString("mFragmentDetailsTag");
		}

		// si l'exercice a editer n'est pas encore d�fini
		if (idkey_exerciseToEdit == -1) {
			// on l'obtient de l'intent
			idkey_exerciseToEdit = getIntent().getLongExtra(
					"idkey_exerciseToEdit", -1);

		}
		if (idkey_exerciseToEdit == -1) {
			// si idkey_exerciseToEdit vaut toujours 1 � ce stade, c'est que la
			// cl� n'a pas �t� pass�e correctement par l'intent. Surement une
			// erreur, on ferme l'activit�
			finish();
		}

		// cr�ation de exerciseToEdit depuis la BDD
		RuntimeExceptionDao<Exercise, Long> exerciseDao = getHelper()
				.getExerciseRuntimeDao();
		RuntimeExceptionDao<Category, Long> categoryDao = getHelper()
				.getCategoryRuntimeDao();

		exerciseToEdit = exerciseDao.queryForId(idkey_exerciseToEdit);

		categoryDao.refresh(exerciseToEdit.getCategory());

		Log.i("INSPECT", "exercise name " + exerciseToEdit.getNom());
		Log.i("INSPECT", "exercise category "
				+ exerciseToEdit.getCategory().getNom());

		setContentView(R.layout.exercise_create_layout);

		FragmentManager fragmentManager = getSupportFragmentManager();

		mFragmentIndicator = (Fragment_Indicator) fragmentManager
				.findFragmentById(R.id.exercise_create_indicator);
		mFragmentIndicator.setIndicatorContainer(this);

		mViewPager = (ViewPager) findViewById(R.id.exercise_create_pager);

		mFragmentCategory = (Fragment_Category) fragmentManager
				.findFragmentByTag(mFragmentCategoryTag);
		if (mFragmentCategory == null) {
			mFragmentCategory = new Fragment_Category();
		}
		mFragmentCategory.setCategoryContainer(this);

		mFragmentModel = (Fragment_Model) fragmentManager
				.findFragmentByTag(mFragmentModelTag);
		if (mFragmentModel == null) {
			mFragmentModel = new Fragment_Model();
		}
		mFragmentModel.setModelContainer(this);

		mFragmentDetails = (Fragment_Details) fragmentManager
				.findFragmentByTag(mFragmentDetailsTag);
		if (mFragmentDetails == null) {
			mFragmentDetails = new Fragment_Details();
		}
		mFragmentDetails.setDetailsContainer(this);

		CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(
				fragmentManager, mFragmentCategory, mFragmentModel,
				mFragmentDetails);

		mViewPager.setAdapter(pagerAdapter);

		mViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						mFragmentIndicator.SelectStep(arg0 + 1);

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});

		if (editFirstTime) {
			editFirstTime = false;
			this.idkey_category = exerciseToEdit.getCategory().getId();
			this.categoryName = exerciseToEdit.getCategory().getNom();

			mFragmentModel.setCategoryNoViewsUpdate(idkey_category, categoryName);
			mFragmentDetails.setMode(2);
			mFragmentDetails.setExerciseModelNoViewsUpdate(exerciseToEdit);
			
		}
		
	}

	/*
	 * Methode appel�e lorsqu'un bouton est appuy� sur le fragment indicator
	 */
	@Override
	public void onSelectIndicator(int id) {
		mViewPager.setCurrentItem(id - 1);
	}

	/*
	 * Methode appel�e lorsqu'une cat�gorie est choisie depuis le fragment
	 * Category
	 */
	@Override
	public void onSelectCategory(long idkey, String categoryName) {
		this.idkey_category = idkey;
		this.categoryName = categoryName;
		mViewPager.setCurrentItem(1);
		mFragmentModel.setCategory(idkey, categoryName);
		mFragmentDetails.setIdkey_category(idkey);
	}

	/*
	 * Methode appel�e lorsqu'un exercise est choisi depuis le fragment Model
	 */
	@Override
	public void onSelectExercise(Exercise e) {
		mFragmentDetails.setExerciseModel(e);
		mViewPager.setCurrentItem(2);
	}

	/*
	 * Methode appel�e lorsqu'un exercice est d�fini enti�rement et l'enregistre
	 * dans la BDD
	 */
	@Override
	public void onValidateExercise(Exercise e) {
		RuntimeExceptionDao<Exercise, Long> exerciseDao = getHelper()
				.getExerciseRuntimeDao();

		exerciseToEdit.setAttributesfromExercise(e);
		exerciseDao.update(exerciseToEdit);
		Toast.makeText(getApplicationContext(), "Modifications enregistr�es.",
				Toast.LENGTH_SHORT).show();
		finish();
	}

	/*
	 * Methode appel�e lorsqu'un utilisateur choisit une image depuis un
	 * DialogFragment
	 */
	@Override
	public void onImageSelectedFromDialog(int img) {
		mFragmentDetails.setImage(img);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putLong("idkey_category", idkey_category);
		outState.putString("categoryName", categoryName);
		outState.putLong("idkey_model_exercise", idkey_model_exercise);
		outState.putString("mFragmentCategoryTag", mFragmentCategoryTag);
		outState.putString("mFragmentModelTag", mFragmentModelTag);
		outState.putString("mFragmentDetailsTag", mFragmentDetailsTag);
		outState.putBoolean("editFirstTime", editFirstTime);
	}

}