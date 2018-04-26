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
import com.datalabor.soporte.mexar.models.Product;

import java.util.ArrayList;

/**
 * Created by soporte on 15/08/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolderProduct>
{

    private Context _context;
    private ArrayList<Product> _items;
    private IViewHolderClick _listener;
    private Bitmap _placeHolder;


    public ProductAdapter(Context context, ArrayList<Product> items, IViewHolderClick listener )
    {
        _context = context;
        _items = items;
        _listener = listener;
//        _placeHolder = BitmapFactory.decodeResource( context.getResources(), R.drawable.placeholder );
    }

    @Override
    public int getItemCount()
    {
        return _items.size();
    }

    @Override
    public ViewHolderProduct onCreateViewHolder(ViewGroup parent, int viewType )
    {
        View view = LayoutInflater.from( _context ).inflate( R.layout.product_layout, parent, false );
        ViewHolderProduct viewHolder = new ViewHolderProduct( view, new IViewHolderClick()
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
    public void onBindViewHolder( ViewHolderProduct holder, int position )
    {
        Product curProduct = _items.get(position);
        holder.get_labelProduct().setText(curProduct.getName());
        holder.get_labelDescription().setText(curProduct.getDescription());
        holder.setIndex( position );


        //holder.getIconView().setImageBitmap( _placeHolder );
        holder.getIconView().setImageResource(curProduct.getResId());

    }



///////////-------------

    public static class ViewHolderProduct extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView _iconView;

        private TextView _labelProduct;
        private TextView _labelDescription;
        private int _index;
        private IViewHolderClick _listener;

        public ViewHolderProduct( View view, IViewHolderClick listener )
        {
            super( view );

            view.setOnClickListener( this );
            _iconView = (ImageView) view.findViewById( R.id.imgProduct );
            _labelProduct = (TextView) view.findViewById( R.id.ProductTitle );
            _labelDescription = (TextView) view.findViewById(R.id.ProductDescription);
            _listener = listener;
        }

        public ImageView getIconView()
        {
            return _iconView;
        }



        public TextView get_labelProduct()
        {
            return _labelProduct;
        }

        public TextView get_labelDescription()
        {
            return _labelDescription;
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


/////////----------------



}


