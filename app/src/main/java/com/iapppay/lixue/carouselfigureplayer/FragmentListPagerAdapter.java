package com.iapppay.lixue.carouselfigureplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class FragmentListPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private GetPageTitleListener getPageTitleListener;

    public FragmentListPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragmentManager = fm;
        this.fragments = fragments;
    }

    public FragmentListPagerAdapter(FragmentManager fm,Fragment... fragments){
        super(fm);
        this.fragmentManager = fm;
        if (fragments != null && fragments.length > 0){
            this.fragments = new ArrayList<>();
            for (int w = 0 ;w < fragments.length;w ++){
                this.fragments.add(fragments[w]);
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(getPageTitleListener != null){
            return getPageTitleListener.onGetPageTitle(position);
        }else{
            Fragment fragment = fragments.get(position);
            if(fragment instanceof TitleFragment){
                return ((TitleFragment) fragment).pageTitle();
            }else{
                return super.getPageTitle(position);
            }
        }
    }

    public void setFragments(List<Fragment> fragmentsList){
        if (fragments != null && fragments.size() > 0) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            for (Fragment f : this.fragments) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fragmentManager.executePendingTransactions();
        }

        this.fragments = fragmentsList;
        notifyDataSetChanged();
    }

    public void setGetPageTitleListener(GetPageTitleListener getPageTitleListener) {
        this.getPageTitleListener = getPageTitleListener;
    }
    public interface GetPageTitleListener{
        public CharSequence onGetPageTitle(int position);
    }

    public interface TitleFragment{
        public CharSequence pageTitle();
    }
}
