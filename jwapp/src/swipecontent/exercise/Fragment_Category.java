package swipecontent.exercise;

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
import com.example.jwapp.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class Fragment_Category extends Fragment {

	private DatabaseHelper databaseHelper = null;

	ListView mListCategory;
	List<Category> listCategory;
	
	CategoryContainer categoryContainer;
	
	public interface CategoryContainer {
		public void onSelectCategory(long id, String name);
	}
	
	public void setCategoryContainer(CategoryContainer categoryContainer) {
		this.categoryContainer = categoryContainer;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_category_layout,
				container, false);
		
		mListCategory = (ListView) view
				.findViewById(R.id.exercise_create_list_category);

		//Chargement des données depuis la BDD
		RuntimeExceptionDao<Category, Long> categoryDao = getHelper()
				.getCategoryRuntimeDao();
		listCategory = categoryDao.queryForAll();

		mListCategory.setAdapter(new CategoryAdapater(getActivity(),
				listCategory));
		
		mListCategory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				categoryContainer.onSelectCategory(listCategory.get(position).getId(),listCategory.get(position).getNom());

			}
		});

		return view;
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
		
	}
	
}

class CategoryAdapater extends BaseAdapter {
	List<Category> list = null;
	Context context;
	LayoutInflater inflater;

	public CategoryAdapater(Context context, List<Category> list) {
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Category getItem(int position) {
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
			convertView = inflater.inflate(R.layout.exercise_category_list_row,
					null);

			holder = new ViewHolder();
			holder.tvName = (TextView) convertView
					.findViewById(R.id.exercise_category_list_row_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvName.setText(list.get(position).getNom());
		return convertView;
	}

}
