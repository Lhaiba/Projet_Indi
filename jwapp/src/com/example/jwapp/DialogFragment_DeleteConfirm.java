package com.example.jwapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.data.DatabaseHelper;
import com.example.data.Exercise;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class DialogFragment_DeleteConfirm extends DialogFragment {

	private DatabaseHelper databaseHelper = null;

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	long id;
	String nom;

	public void setId(long id) {
		this.id = id;
	}

	public interface DeleteConfirmListener {
		public void onDeleteConfirm(long id, String nom);
	}

	DeleteConfirmListener deleteConfirmListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		deleteConfirmListener = (DeleteConfirmListener) activity;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putLong("id", id);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			id = savedInstanceState.getLong("id");
		}

		// VERIFICATIONS BDD
		RuntimeExceptionDao <Exercise, Long> exerciseDao = getHelper()
				.getExerciseRuntimeDao();
		Exercise e = exerciseDao.queryForId(id);
		nom = e.getNom();
		//
		// TO DO - Verifier si l'exercice appartient a des séances pour alerter
		// l'utilisateur
		//

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Confirmation");
		builder.setMessage("Êtes-vous sûr de vouloir supprimer l'exercice " + nom + " ?");
		builder.setNegativeButton("Annuler",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dismiss();
					}
				});

		builder.setPositiveButton("Supprimer",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						deleteConfirmListener.onDeleteConfirm(id, nom);
						dismiss();
					}
				});

		return builder.create();
	}

}
