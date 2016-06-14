package com.iapppay.lixue.carouselfigureplayer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iapppay.lixue.carouselfigureplayerlib.PictureViewPlayer;
import com.iapppay.lixue.carouselfigureplayerlib.PlayMode;

/**
 * Created by Administrator on 2016/6/14.
 */
public class SwingFragment extends Fragment implements FragmentListPagerAdapter.TitleFragment {
    private PictureViewPlayer pictureViewPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_player, null);
        pictureViewPlayer = (PictureViewPlayer) view.findViewById(R.id.viewPager);

        pictureViewPlayer.getViewPlayer().setAdapter(new PicturePagerAdapter(getChildFragmentManager(), Constants.IMAGES));
        pictureViewPlayer.getViewPlayer().setViewPlayMode(PlayMode.SWING);
        pictureViewPlayer.getViewPlayer().setPageTransformer(true, new DepthPageTransformer());
        return view;
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

    @Override
    public CharSequence pageTitle() {
        return "摇摆";
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;
        @SuppressLint("NewApi")
        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when
                // moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
