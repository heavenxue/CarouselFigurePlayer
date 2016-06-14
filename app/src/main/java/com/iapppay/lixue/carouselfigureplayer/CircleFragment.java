package com.iapppay.lixue.carouselfigureplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iapppay.lixue.carouselfigureplayerlib.PictureViewPlayer;

/**
 * Created by Administrator on 2016/6/14.
 */
public class CircleFragment extends Fragment implements FragmentListPagerAdapter.TitleFragment {
    private PictureViewPlayer pictureViewPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_player, null);
        pictureViewPlayer = (PictureViewPlayer) view.findViewById(R.id.viewPager);
        pictureViewPlayer.getViewPlayer().setAdapter(new PicturePagerAdapter(getChildFragmentManager(), Constants.IMAGES));
        return view;
    }

    @Override
    public CharSequence pageTitle() {
        return "转圈";
    }

    @Override
    public void onPause() {
        super.onPause();
        pictureViewPlayer.getViewPlayer().stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        pictureViewPlayer.getViewPlayer().start();
    }
}