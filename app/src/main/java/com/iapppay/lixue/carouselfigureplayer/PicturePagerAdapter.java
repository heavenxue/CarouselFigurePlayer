package com.iapppay.lixue.carouselfigureplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iapppay.lixue.carouselfigureplayerlib.PlayFragmentStatePagerAdatper;
import com.lixue.aibei.universalimageloaderlib.core.DisplayImageOptions;
import com.lixue.aibei.universalimageloaderlib.core.UniversalImageLoader;
import com.lixue.aibei.universalimageloaderlib.core.display.SimpleBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class PicturePagerAdapter extends PlayFragmentStatePagerAdatper {
    private List<String> pictures;//图片列表

    public PicturePagerAdapter(FragmentManager fm,List<String> pictures) {
        super(fm);
        this.pictures = pictures;
    }

    public PicturePagerAdapter(FragmentManager fm, String... pictureUrls) {
        super(fm);
        pictures = new ArrayList<String>(pictureUrls.length);
        for(String url : pictureUrls){
            pictures.add(url);
        }
    }

    @Override
    public int getRealCount() {
        return pictures != null ? pictures.size() : 0 ;
    }

    @Override
    public Fragment getRealItem(int position) {
        PictureFragment pictureFragment = new PictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PictureFragment.PARAM_REQUIRED_STRING_PICTURE_URL, pictures.get(position));
        pictureFragment.setArguments(bundle);
        return pictureFragment;
    }

    public static class PictureFragment extends Fragment{
        public static final String PARAM_REQUIRED_STRING_PICTURE_URL = "PARAM_REQUIRED_STRING_PICTURE_URL";
        private DisplayImageOptions options;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            options = new DisplayImageOptions.Builder()
//                    .showImageOnLoading(R.drawable.ic_stub)
//                    .showImageForEmptyUri(R.drawable.ic_empty)
//                    .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .consinderExifParams(true)
//                    .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
                    .displayer(new SimpleBitmapDisplayer())
                    .build();

            ImageView imageView = new ImageView(getActivity().getBaseContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            UniversalImageLoader.getInstance().displayImage(getArguments().getString(PARAM_REQUIRED_STRING_PICTURE_URL), imageView, options, null);
            return imageView;
        }
    }
}
