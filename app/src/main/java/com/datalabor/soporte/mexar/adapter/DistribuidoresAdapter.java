package com.datalabor.soporte.mexar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.models.Distribuidor;

import java.util.ArrayList;

/**
 * Created by soporte on 15/08/2017.
 */

public class DistribuidoresAdapter extends RecyclerView.Adapter<DistribuidoresAdapter.ViewHolder>
{

    private Context _context;
    private ArrayList<Distribuidor> _items;
    private IViewHolderClick _listener;

    public DistribuidoresAdapter(Context context, ArrayList<Distribuidor> items, IViewHolderClick listener )
    {
        _context = context;
        _items = items;
        _listener = listener;

    }


    @Override
    public int getItemCount()
    {
        return _items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType )
    {
        View view = LayoutInflater.from( _context ).inflate( R.layout.distribuidor_layout, parent, false );
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
        Distribuidor curDist = _items.get(position);
        holder.get_labelNombre().setText(curDist.get_comercial_name());
        holder.get_labelEstado().setText(curDist.get_estado());
        holder.get_labelCiudad().setText(curDist.get_ciudad());
        holder.get_labelDireccion().setText(curDist.get_direccion());
        holder.get_labelTelefono().setText(curDist.get_telefono1());
        holder.get_labelTelefono2().setText(curDist.get_telefono2());

        holder.setIndex( position );

        //holder.getIconView().setImageBitmap( _placeHolder );


    }





    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {


        private TextView _labelNombre;
        private TextView _labelEstado;
        private TextView _labelCiudad;
        private TextView _labelDireccion;
        private TextView _labelTelefono;
        private TextView _labelTelefono2;

        private int _index;
        private IViewHolderClick _listener;

        public ViewHolder( View view, IViewHolderClick listener )
        {
            super( view );

            view.setOnClickListener( this );

            _labelNombre = (TextView) view.findViewById( R.id.dist_nombre );
            _labelEstado = (TextView) view.findViewById( R.id.dist_estado );
            _labelCiudad = (TextView) view.findViewById( R.id.dist_ciudad );
            _labelDireccion = (TextView) view.findViewById( R.id.dist_dir );
            _labelTelefono = (TextView) view.findViewById( R.id.dist_tel );
            _labelTelefono2 = (TextView) view.findViewById( R.id.dist_tel2 );
            _listener = listener;
        }



        public TextView get_labelNombre()
        {
            return _labelNombre;
        }
        public TextView get_labelEstado()
        {
            return _labelEstado;
        }
        public TextView get_labelCiudad()
        {
            return _labelCiudad;
        }
        public TextView get_labelDireccion()
        {
            return _labelDireccion;
        }
        public TextView get_labelTelefono()
        {
            return _labelTelefono;
        }
        public TextView get_labelTelefono2()
        {
            return _labelTelefono2;
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
