package swipecontent.exercise;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.jwapp.R;

public class DialogFragment_SelectImage extends DialogFragment {

	ArrayList<Integer> images24 = null;

	public interface DialogSelectImageListener {
		public void onImageSelectedFromDialog (int img);
	}
	
	DialogSelectImageListener dialogSelectImageListener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		dialogSelectImageListener = (DialogSelectImageListener) activity;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		images24 = new ArrayList<Integer>();

		images24.add(R.drawable.baseball_icon_24);
		images24.add(R.drawable.bike_icon_24);
		images24.add(R.drawable.cardio_icon_24);
		images24.add(R.drawable.flexion_icon_24);
		images24.add(R.drawable.gym_icon_24);
		images24.add(R.drawable.jump_icon_24);
		images24.add(R.drawable.martialart_icon_24);
		images24.add(R.drawable.muscu_icon_24);
		images24.add(R.drawable.push_icon_24);
		images24.add(R.drawable.soccer_icon_24);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();

		View view = inflater.inflate(
				R.layout.exercise_create_select_image_dialog, null);

		GridView gridView = (GridView) view
				.findViewById(R.id.exercise_create_select_image_gridview);
		gridView.setAdapter(new ImageAdapter(getActivity(), images24));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				dialogSelectImageListener.onImageSelectedFromDialog(images24.get(position));
				dismiss();
			}
		});

		builder.setTitle(R.string.select_icon_header);
		builder.setView(view);
		builder.setNegativeButton(R.string.select_icon_back, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		Dialog dialog = builder.create();
		return dialog;
	}

}

class ImageAdapter extends BaseAdapter {
	ArrayList<Integer> images = null;
	Context context;
	LayoutInflater inflater;

	public ImageAdapter(Context context, ArrayList<Integer> images) {
		this.images = images;
		this.context = context;
		this.inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public Object getItem(int position) {
		return images.get(position);
	}

	@Override
	public long getItemId(int position) {
		return images.get(position);
	}

	class ViewHolder {
		ImageView IvIcon;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.simple_item_icon, null);
			holder = new ViewHolder();

			holder.IvIcon = (ImageView) convertView
					.findViewById(R.id.simple_list_item_icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.IvIcon.setImageResource(images.get(position));
		return convertView;
	}

}
