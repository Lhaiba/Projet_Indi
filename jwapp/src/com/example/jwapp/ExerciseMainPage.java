package com.example.jwapp;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data.DatabaseHelper;
import com.example.data.Exercise;
import com.example.jwapp.DialogFragment_DeleteConfirm.DeleteConfirmListener;
import com.example.jwapp.ExerciseAdapter.onExerciseEventListener;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class ExerciseMainPage extends FragmentActivity implements
		onExerciseEventListener, DeleteConfirmListener {

	private boolean editMode = false;
	private Context context = this;

	private List<Exercise> listExercise;
	private ListView lvExercise;

	private DatabaseHelper databaseHelper = null;

	@Override
	protected void onResume() {
		super.onResume();
		setContent();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_main_page_layout);

		lvExercise = (ListView) findViewById(R.id.exercise_main_page_list);
		setContent();
	}

	void setContent() {
		updateList();

		if (listExercise.isEmpty()) {
			lvExercise.setAdapter(null);
		} else {

			ExerciseAdapter adapter = new ExerciseAdapter(context,
					listExercise, editMode);
			adapter.setExerciseEventListener(this);
			lvExercise.setAdapter(adapter);
		}

		lvExercise.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"Objet d'id " + id + " selectionné.",
						Toast.LENGTH_SHORT).show();

			}
		});
	}

	/*
	 * Liberation du helper
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	/*
	 * Obtention d'un helper unique
	 */
	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	/*
	 * Charge la liste d'exercices depuis la BDD
	 */
	private void updateList() {
		RuntimeExceptionDao<Exercise, Long> exerciseDao = getHelper()
				.getExerciseRuntimeDao();

		listExercise = exerciseDao.queryForAll();
	}

	/*
	 * Methode appelée lorsque l'on clique sur le bouton Créer un nouvel
	 * exercice
	 */
	public void onClick_ExerciseNew(View view) {
		Intent intent = new Intent(this, ExerciseCreate.class);
		startActivity(intent);
	}

	/*
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton Mode
	 * modifications
	 */
	public void onClick_ExerciseEdit(View view) {
		if (editMode) {
			editMode = false;
		} else {
			editMode = true;
		}
		if (listExercise.isEmpty()) {
			lvExercise.setAdapter(null);
		} else {

			ExerciseAdapter adapter = new ExerciseAdapter(context,
					listExercise, editMode);
			adapter.setExerciseEventListener(this);
			lvExercise.setAdapter(adapter);
		}
	}

	/*
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton Editer d'un
	 * des exercices de la liste
	 */
	@Override
	public void onEditExercise(long id) {
		Intent intent = new Intent(this, ExerciseEdit.class);
		intent.putExtra("idkey_exerciseToEdit", id);
		startActivity(intent);
	}

	/*
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton Supprimer d'un
	 * des exercices de la liste
	 */
	@Override
	public void onDeleteExercise(long id) {
		DialogFragment_DeleteConfirm dialog = new DialogFragment_DeleteConfirm();
		dialog.setId(id);
		dialog.show(getSupportFragmentManager(), "Delete Confirm Dialog");

	}

	@Override
	public void onDeleteConfirm(long id, String nom) {
		RuntimeExceptionDao<Exercise, Long> exerciseDao = getHelper().getExerciseRuntimeDao();
		exerciseDao.deleteById(id);
		
		Toast.makeText(getApplicationContext(),
				"Exercice " + nom + " supprimé", Toast.LENGTH_SHORT).show();
		
		setContent();
	}

}

class ExerciseAdapter extends BaseAdapter {

	private Context context;
	private List<Exercise> listExercise;
	private LayoutInflater inflater;
	private boolean editMode;

	private static class ViewHolder {
		ImageView ivIcon, ivEdit, ivDelete;
		TextView tvName;
	}

	public interface onExerciseEventListener {
		public void onEditExercise(long id);

		public void onDeleteExercise(long id);
	}

	private onExerciseEventListener exerciseEventListener;

	public void setExerciseEventListener(
			onExerciseEventListener exerciseEventListener) {
		this.exerciseEventListener = exerciseEventListener;
	}

	public ExerciseAdapter(Context context, List<Exercise> listExercise,
			boolean editMode) {
		this.context = context;
		this.listExercise = listExercise;
		this.editMode = editMode;
		this.inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		return listExercise.size();
	}

	@Override
	public Exercise getItem(int position) {
		return listExercise.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listExercise.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (editMode) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.exercise_list_row_edit,
						null);
				holder = new ViewHolder();
				holder.ivIcon = (ImageView) convertView
						.findViewById(R.id.exercise_row_icon);
				holder.tvName = (TextView) convertView
						.findViewById(R.id.exercise_row_name);
				holder.ivEdit = (ImageView) convertView
						.findViewById(R.id.exercise_row_edit);
				holder.ivDelete = (ImageView) convertView
						.findViewById(R.id.exercise_row_delete);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// holder.ivEdit.setTag(position);
			// holder.ivDelete.setTag(position);

			holder.ivEdit.setTag(listExercise.get(position).getId());
			holder.ivDelete.setTag(listExercise.get(position).getId());

			holder.tvName.setText(listExercise.get(position).getNom());
			holder.ivIcon.setImageResource(listExercise.get(position)
					.getImage());
			holder.ivEdit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					exerciseEventListener.onEditExercise((Long) v.getTag());
				}
			});

			holder.ivDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					exerciseEventListener.onDeleteExercise((Long) v.getTag());
				}
			});

		}

		else {
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.exercise_list_row, null);
				holder = new ViewHolder();
				holder.ivIcon = (ImageView) convertView
						.findViewById(R.id.exercise_row_icon);
				holder.tvName = (TextView) convertView
						.findViewById(R.id.exercise_row_name);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.tvName.setText(listExercise.get(position).getNom());
			holder.ivIcon.setImageResource(listExercise.get(position)
					.getImage());
		}

		return convertView;
	}

}
