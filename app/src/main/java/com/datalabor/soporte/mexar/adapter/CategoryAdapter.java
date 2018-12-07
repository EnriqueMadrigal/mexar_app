package com.datalabor.soporte.mexar.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.models.Category;

import java.util.ArrayList;

/**
 * Created by Enrique on 14/08/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private Context _context;
    private ArrayList<Category> _items;
    private IViewHolderClick _listener;
    private Bitmap _placeHolder;

    public CategoryAdapter(Context context, ArrayList<Category> items, IViewHolderClick listener )
    {
        _context = context;
        _items = items;
        _listener = listener;
       // _placeHolder = BitmapFactory.decodeResource( context.getResources(), R.drawable.placeholder );
    }

    @Override
    public int getItemCount()
    {
        return _items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType )
    {
        View view = LayoutInflater.from( _context ).inflate( R.layout.main_items, parent, false );
        ViewHolder viewHolder = new ViewHolder( view, new IViewHolderClick()
        {
            @Override
            public void onClick( int position )
            {
                if( _listener != null )
                {
                    _listener.onClick( position );
                }
            }
        } );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position )
    {
        Category curCategory = _items.get(position);
        //holder.get_labelProduct().setText(curCategory.getName());
        holder.get_labelDesc().setText(curCategory.get_desc());

        holder.setIndex( position );


            //holder.getIconView().setImageBitmap( _placeHolder );
        holder.getIconView().setImageResource(curCategory.getResId());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView _iconView;

        private TextView _labelProduct;
        private TextView _labelDesc;

        private int _index;
        private IViewHolderClick _listener;

        public ViewHolder( View view, IViewHolderClick listener )
        {
            super( view );

            view.setOnClickListener( this );
            _iconView = (ImageView) view.findViewById( R.id.imgIcon );
            //_labelProduct = (TextView) view.findViewById( R.id.lblProductTitle );
            _labelDesc = (TextView) view.findViewById( R.id.lblProductDescription );
            _listener = listener;
        }

        public ImageView getIconView()
        {
            return _iconView;
        }


        /*
        public TextView get_labelProduct()
        {
            return _labelProduct;
        }

        */
        public TextView get_labelDesc() {return  _labelDesc;}


        public int getIndex()
        {
            return _index;
        }

        public void setIndex( int index )
        {
            _index = index;
        }

        @Override
        public void onClick( View v )
        {
            if( _listener != null )
            {
                _listener.onClick( _index );
            }
        }
    }




}
