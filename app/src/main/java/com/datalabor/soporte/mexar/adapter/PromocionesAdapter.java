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
import com.datalabor.soporte.mexar.models.Promocion;

import java.util.ArrayList;

/**
 * Created by soporte on 16/08/2017.
 */

public class PromocionesAdapter extends RecyclerView.Adapter<PromocionesAdapter.ViewHolderProduct>
{

    private Context _context;
    private ArrayList<Promocion> _items;
    private IViewHolderClick _listener;
    private Bitmap _placeHolder;


    public PromocionesAdapter(Context context, ArrayList<Promocion> items, IViewHolderClick listener )
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
    public PromocionesAdapter.ViewHolderProduct onCreateViewHolder(ViewGroup parent, int viewType )
    {
        View view = LayoutInflater.from( _context ).inflate( R.layout.promociones_layout, parent, false );
        PromocionesAdapter.ViewHolderProduct viewHolder = new PromocionesAdapter.ViewHolderProduct( view, new IViewHolderClick()
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
    public void onBindViewHolder(PromocionesAdapter.ViewHolderProduct holder, int position )
    {
        Promocion curPromocion = _items.get(position);
        holder.get_labelProduct().setText(curPromocion.get_name());
        holder.get_labelDescription().setText(curPromocion.get_desc());
        holder.setIndex( position );


        //holder.getIconView().setImageBitmap( _placeHolder );
        holder.getIconView().setImageResource(curPromocion.get_resId());

    }





    public static class ViewHolderProduct extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView _iconView;

        private TextView _labelPromocion;
        private TextView _labelDescription;
        private int _index;
        private IViewHolderClick _listener;

        public ViewHolderProduct( View view, IViewHolderClick listener )
        {
            super( view );

            view.setOnClickListener( this );
            _iconView = (ImageView) view.findViewById( R.id.imgPromocion );
            _labelPromocion = (TextView) view.findViewById( R.id.PromocionesTitle );
            _labelDescription = (TextView) view.findViewById(R.id.PromocionesDescription);
            _listener = listener;
        }

        public ImageView getIconView()
        {
            return _iconView;
        }



        public TextView get_labelProduct()
        {
            return _labelPromocion;
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
