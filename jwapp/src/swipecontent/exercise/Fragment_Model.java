package swipecontent.exercise;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.data.Category;
import com.example.data.DatabaseHelper;
import com.example.data.Exercise;
import com.example.jwapp.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class Fragment_Model extends Fragment {

	private DatabaseHelper databaseHelper = null;

	ListView mListExercise;
	TextView Indication;

	List<Exercise> listExercise = null;
	long idkey_category = -1;
	String categoryName;

	ModelContainer modelContainer;

	public interface ModelContainer {
		public void onSelectExercise(Exercise exercise);
	}

	public void setModelContainer(ModelContainer modelContainer) {
		this.modelContainer = modelContainer;
	}

	public void setCategory(long idkey_category, String categoryName) {
		this.idkey_category = idkey_category;
		this.categoryName = categoryName;
		refreshlist();
		updatecontent();
	}
	
	public void setCategoryNoViewsUpdate(long idkey_category, String categoryName) {
		this.idkey_category = idkey_category;
		this.categoryName = categoryName;
		refreshlist();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_model_layout, container,
				false);

		mListExercise = (ListView) view
				.findViewById(R.id.exercise_create_list_model);

		Indication = (TextView) view
				.findViewById(R.id.exercise_create_model_indication);

		if (savedInstanceState != null) {
			idkey_category = savedInstanceState.getLong("idkey_category");
			categoryName = savedInstanceState.getString("categoryName");
		}

		refreshlist();
		updatecontent();
		return view;
	}

	/*
	 * Effectue les appels vers la BDD
	 */
	public void refreshlist() {
		RuntimeExceptionDao<Exercise, Long> exerciseDao = getHelper()
				.getExerciseRuntimeDao();
		RuntimeExceptionDao<Category, Long> categoryDao = getHelper()
				.getCategoryRuntimeDao();

		QueryBuilder<Exercise, Long> queryBuilder = exerciseDao.queryBuilder();
		try {
			queryBuilder.where().eq("category_id", idkey_category);
			PreparedQuery<Exercise> preparedQuery = queryBuilder.prepare();
			listExercise = exerciseDao.query(preparedQuery);
			for (int i = 0; i < listExercise.size(); i++) {
				categoryDao.refresh(listExercise.get(i).getCategory());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Met a jour le contenu des Views
	 */
	public void updatecontent() {

		if (listExercise.size() > 0) {
			mListExercise.setAdapter(new ModelAdapater(getActivity(),
					listExercise));

			mListExercise.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					modelContainer.onSelectExercise(listExercise.get(position));
				}
			});

		}

		if (categoryName != null) {
			Indication
					.setText("Vous pouvez sélectionner un modèle parmi les exercices déjà définis dans la catégorie "
							+ categoryName + " :");
		}
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putLong("idkey_category", idkey_category);
		outState.putString("categoryName", categoryName);

	}

}

class ModelAdapater extends BaseAdapter {
	List<Exercise> list = null;
	Context context;
	LayoutInflater inflater;

	public ModelAdapater(Context context, List<Exercise> list) {
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Exercise getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return list.get(position).getId();
	}

	class ViewHolder {
		TextView tvName;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.exercise_model_list_row,
					null);

			holder = new ViewHolder();
			holder.tvName = (TextView) convertView
					.findViewById(R.id.exercise_model_list_row_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvName.setText(list.get(position).getNom());
		return convertView;
	}

}