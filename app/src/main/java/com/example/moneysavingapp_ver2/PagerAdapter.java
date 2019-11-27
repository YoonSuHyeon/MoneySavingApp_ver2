package com.example.moneysavingapp_ver2;






import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {

    private Context context = null;
    private ArrayList<Integer> imageList;



    public PagerAdapter(Context context,ArrayList<Integer> imageList){
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.page,container,false);

        ImageView imageView = view.findViewById(R.id.iv_background);
        imageView.setImageResource(imageList.get(position));

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View)object);
    }
}