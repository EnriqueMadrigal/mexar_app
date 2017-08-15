package com.datalabor.soporte.mexar.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.models.Product;

import java.io.File;
import java.util.ArrayList;

import static android.R.attr.format;

/**
 * Created by Enrique on 14/08/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>
{
    private Context _context;
    private ArrayList<Product> _items;
    private IViewHolderClick _listener;
    private Bitmap _placeHolder;

    public ProductsAdapter( Context context, ArrayList<Product> items, IViewHolderClick listener )
    {
        _context = context;
        _items = items;
        _listener = listener;
        _placeHolder = BitmapFactory.decodeResource( context.getResources(), R.drawable.placeholder );
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

        holder.getLabelModel().setText( "0" );
        holder.getLabelPrice().setText( "0" );
        holder.setIndex( position );

        boolean foundCampaign = false;
        String image = "";

            holder.getIconView().setImageBitmap( _placeHolder );

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView _iconView;
        private ImageView _iconBrand;
        private TextView _labelModel;
        private TextView _labelPrice;
        private TextView _labelCampaign;
        private int _index;
        private IViewHolderClick _listener;

        public ViewHolder( View view, IViewHolderClick listener )
        {
            super( view );

            view.setOnClickListener( this );
            _iconView = (ImageView) view.findViewById( R.id.imgIcon );
            _iconBrand = (ImageView) view.findViewById( R.id.imgBrand );
            _labelModel = (TextView) view.findViewById( R.id.lblModel );
            _labelPrice = (TextView) view.findViewById( R.id.lblPrice );
            _labelCampaign = (TextView) view.findViewById( R.id.lblCampaign );
            _listener = listener;
        }

        public ImageView getIconView()
        {
            return _iconView;
        }

        public ImageView getIconBrand()
        {
            return _iconBrand;
        }

        public TextView getLabelModel()
        {
            return _labelModel;
        }

        public TextView getLabelPrice()
        {
            return _labelPrice;
        }

        public TextView getLabelCampaign()
        {
            return _labelCampaign;
        }

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
