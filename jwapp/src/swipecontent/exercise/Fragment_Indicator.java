package swipecontent.exercise;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jwapp.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class Fragment_Indicator extends Fragment {

	private int currentState = 0;

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

	public interface IndicatorContainer{
		public void onSelectIndicator(int id);
	}
	
	IndicatorContainer indicatorContainer;
	
	public void setIndicatorContainer(IndicatorContainer indicatorContainer) {
		this.indicatorContainer = indicatorContainer;
	}

	TextView mCategory;
	TextView mModel;
	TextView mDetails;

	public Fragment_Indicator() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_indicator_layout, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mCategory = (TextView) getActivity().findViewById(
				R.id.exercise_create_indicator_category);
		mModel = (TextView) getActivity().findViewById(
				R.id.exercise_create_indicator_model);
		mDetails = (TextView) getActivity().findViewById(
				R.id.exercise_create_indicator_details);

		if (currentState == 0) {
			SelectStep(1);
		}

		mCategory.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SelectStep(1);
			}
		});

		mModel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SelectStep(2);
			}
		});

		mDetails.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SelectStep(3);
			}
		});

	}

	public void SelectStep(int selected_step) {
		if ((selected_step == 1) && (currentState != 1)) {
			mCategory.setTextColor(Color.RED);
			mModel.setTextColor(Color.WHITE);
			mDetails.setTextColor(Color.WHITE);
			currentState = 1;
			indicatorContainer.onSelectIndicator(1);
		}
		if ((selected_step == 2) && (currentState != 2)) {
			mCategory.setTextColor(Color.WHITE);
			mModel.setTextColor(Color.RED);
			mDetails.setTextColor(Color.WHITE);
			currentState = 2;
			indicatorContainer.onSelectIndicator(2);
		}
		if ((selected_step == 3) && (currentState != 3)) {
			mCategory.setTextColor(Color.WHITE);
			mModel.setTextColor(Color.WHITE);
			mDetails.setTextColor(Color.RED);
			currentState = 3;
			indicatorContainer.onSelectIndicator(3);
		}
	}

}
