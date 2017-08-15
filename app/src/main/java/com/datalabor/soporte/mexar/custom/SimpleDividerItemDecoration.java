package com.datalabor.soporte.mexar.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.datalabor.soporte.mexar.R;

/**
 * Created by Enrique on 15/08/2017.
 */

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration
{
    private Drawable mDivider;

    public SimpleDividerItemDecoration( Context context )
    {
        mDivider = ContextCompat.getDrawable( context, R.drawable.line_divider );
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state )
    {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for( int i = 0; i < childCount; i++ )
        {
            View child = parent.getChildAt( i );

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds( left + 16, top, right - 16, bottom );
            mDivider.draw( c );
        }
    }
}

