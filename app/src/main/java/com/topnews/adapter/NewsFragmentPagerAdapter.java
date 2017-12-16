package com.topnews.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topnews.R;

import static com.topnews.R.id.hottopic_tv;

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<Fragment> fragments;
	private FragmentManager fm;

	public NewsFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		this.fm = fm;
	}

	public NewsFragmentPagerAdapter(Context context, FragmentManager fm,
                                    ArrayList<Fragment> fragments) {
		super(fm);
		this.context = context;
		this.fm = fm;
		this.fragments = fragments;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public void setFragments(ArrayList<Fragment> fragments) {
		if (this.fragments != null) {
			FragmentTransaction ft = fm.beginTransaction();
			for (Fragment f : this.fragments) {
				ft.remove(f);
			}
			ft.commit();
			ft = null;
			fm.executePendingTransactions();
		}
	}

    public void setNotifyFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
//		for (int i=0; i<container.getChildCount(); i++) {
//            if (i!=position){
//                container.removeView(container.getChildAt(i));
//            }
//		}
    }

    @Override
	public Object instantiateItem(ViewGroup container, final int position) {
		Object obj = super.instantiateItem(container, position);
//        View view = LayoutInflater.from(context).inflate(R.layout.fragment_hottopic,null);
//		TextView hottopic_tv = (TextView) view.findViewById(R.id.hottopic_tv);

		return obj;
//		return view;
	}

}
