package swipecontent.exercise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomPagerAdapter extends FragmentPagerAdapter {

	Fragment fragment;
	Fragment_Category mFragmentCategory;
	Fragment_Model mFragmentModel;
	Fragment_Details mFragmentDetails;

	public CustomPagerAdapter(FragmentManager fm,
			Fragment_Category mFragmentCategory, Fragment_Model mFragmentModel,
			Fragment_Details mFragmentDetails) {
		super(fm);
		this.mFragmentCategory = mFragmentCategory;
		this.mFragmentModel = mFragmentModel;
		this.mFragmentDetails = mFragmentDetails;
	}

	@Override
	public Fragment getItem(int arg0) {
		if (arg0 == 0) {
			return mFragmentCategory;
		}
		if (arg0 == 1) {
			return mFragmentModel;
		}
		if (arg0 == 2) {
			return mFragmentDetails;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 3;
	}

}