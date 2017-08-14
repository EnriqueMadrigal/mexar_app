package com.datalabor.soporte.mexar.custom;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.datalabor.soporte.mexar.R;

/**
 * Created by soporte on 14/08/2017.
 */

public class banner_image_class extends Fragment {


    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int ResId = getArguments().getInt(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.banner_image, container, false);

        ImageView curImage = (ImageView) v.findViewById(R.id.curImage);
        curImage.setImageResource(ResId);
       // ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.banner_image, container, false);

        return v;
    }


    public static final banner_image_class newInstance(int ResId)

    {

        banner_image_class f = new banner_image_class();

        Bundle bdl = new Bundle(1);
        bdl.putInt(EXTRA_MESSAGE, ResId);

        f.setArguments(bdl);

        return f;

    }

}
