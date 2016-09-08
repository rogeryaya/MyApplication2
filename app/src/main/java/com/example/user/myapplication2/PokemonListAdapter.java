package com.example.user.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.myapplication2.model.OwnedPokemonInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/9/5.
 */
public class PokemonListAdapter extends ArrayAdapter<OwnedPokemonInfo> implements OnPokemonSelectedChangeListener{
    int rowViewLayoutId;
    LayoutInflater mInflater;
    public ArrayList<OwnedPokemonInfo> selectedPokemonInfos = new ArrayList<>();

    public PokemonListAdapter(Context context, int layoutId, List<OwnedPokemonInfo> objects) {
        super(context, layoutId, objects);

        rowViewLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
        ViewHolder.mAdapter = this;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        OwnedPokemonInfo data = getItem(position);
        ViewHolder viewHolder = null;

        if(rowView == null) {
            rowView = mInflater.inflate(rowViewLayoutId, null);
            viewHolder = new ViewHolder(rowView);
            rowView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)rowView.getTag();
        }
        viewHolder.setView(data);
        return rowView;
    }

    @Override
    public void onSelectedChanged(OwnedPokemonInfo data) {
        if (data.isSelected) {
            selectedPokemonInfos.add(data);
        } else {
            selectedPokemonInfos.remove(data);
        }
    }

    //ViewHolder implements OnClickListener because it's convenient.
    public static class ViewHolder implements View.OnClickListener {
        View mRowView; //mRowView is a relavtive layout
        ImageView mAppearanceImg;
        TextView mNameText;
        TextView mLevelText;
        TextView mCurrentHP;
        TextView mMaxHP;
        ProgressBar mHPBar;

        OwnedPokemonInfo mData;

        public static PokemonListAdapter mAdapter;

        public ViewHolder(View rowView) {
            mRowView = rowView;
            mAppearanceImg = (ImageView) rowView.findViewById(R.id.appearanceImg);
            mNameText = (TextView) rowView.findViewById(R.id.nameText);
            mLevelText = (TextView) rowView.findViewById(R.id.levelText);
           mCurrentHP = (TextView) rowView.findViewById(R.id.currentHP);
            mMaxHP = (TextView)rowView.findViewById(R.id.maxHP);
            mHPBar = (ProgressBar)rowView.findViewById(R.id.hpBar);

            mAppearanceImg.setOnClickListener(this);
        }
        public void  setView(OwnedPokemonInfo data) {
            mData = data;

            mNameText.setText(data.name);
            mLevelText.setText(String.valueOf(data.level));
            mCurrentHP.setText(String.valueOf(data.currentHP));
            mMaxHP.setText(String.valueOf(data.maxHP));
            int progress = (int) ((((float)data.currentHP)/data.maxHP) * 100);
            mHPBar.setProgress(progress);

            String imgUrl =
                    String.format("http://www.csie.ntu.edu.tw/~r03944003/listImg/%d.png",
                        data.pokemonId);
            ImageLoader.getInstance().displayImage(imgUrl, mAppearanceImg);

        }

        public void setSelected() {
            mData.isSelected = !mData.isSelected;
            mRowView.setActivated(mData.isSelected);
            mAdapter.onSelectedChanged(mData);
        }

        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            if (viewId == R.id.appearanceImg) {
                setSelected();
            }
        }
    }
}
