package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.adapter.IViewHolderClick;
import com.datalabor.soporte.mexar.adapter.ProductAdapter;
import com.datalabor.soporte.mexar.custom.SimpleDividerItemDecoration;
import com.datalabor.soporte.mexar.models.Product;
import com.datalabor.soporte.mexar.models.SubCategory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link productos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link productos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class productos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static final String TAG = "productos";

    private OnFragmentInteractionListener mListener;

    private View _view;

    private int curSubCategory = 0;

    private FragmentActivity myContext;
    private RecyclerView _recyclerview;
    private ProductAdapter _adapter;
    private LinearLayoutManager _linearLayoutManager;

    ArrayList<Product> _products;

    public productos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment productos.
     */
    // TODO: Rename and change types and number of parameters
    public static productos newInstance(int curSubCategory) {
        productos fragment = new productos();
        Bundle args = new Bundle();

        args.putInt("SubCategory",curSubCategory);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        curSubCategory = (int) getArguments().getInt("SubCategory");
        _products = new ArrayList<Product>();


        String jsonProducts = Common.loadJSONFromAsset(myContext,"productos.json");
        JSONObject obj_products;
        JSONObject producto;

        ///////////////
        try {

            obj_products = new JSONObject(jsonProducts);
            JSONArray res = obj_products.getJSONArray("productos");

            for (int i = 0; i < res.length(); i++) {
                producto = res.getJSONObject(i).getJSONObject("producto");
                String name = producto.getString("name");
                String resname = producto.getString("resname");
                String desc = producto.getString("desc");
                String desc_completa = producto.getString("desc_completa");

                int id = producto.getInt("id");
                int subcategoryid = producto.getInt("idsubcategoria");

                Product newProduct = new Product();
                newProduct.setName(name);
                newProduct.setId(id);
                newProduct.setDescription(desc);
                newProduct.set_desc_complete(desc_completa);

                int resid = myContext.getResources().getIdentifier(resname, "drawable", myContext.getPackageName());
                Log.d(TAG,newProduct.getName());
                newProduct.setResId(resid);
                if (subcategoryid == curSubCategory) {  // Solo Añadir las categorias seleccionadas
                    _products.add(newProduct);
                }
            }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate( R.layout.fragment_productos, container, false );
        _recyclerview = (RecyclerView) _view.findViewById(R.id.recyclerProducts);


        _adapter = new ProductAdapter(getActivity(), _products, new IViewHolderClick() {
            @Override
            public void onClick(int position) {

                Product curProduct = _products.get(position);

                Log.d(TAG, String.valueOf(curProduct.getId()));

                productDetail productdetail = productDetail.newInstance(curProduct.getId());

                Common.SetPage(3);
                myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, productdetail, "Product Detail" ).commit();


            }
        });

        _linearLayoutManager = new LinearLayoutManager( getActivity() );

        _recyclerview.setHasFixedSize( true );
        _recyclerview.setAdapter( _adapter );
        _recyclerview.setLayoutManager( _linearLayoutManager );
        //_recyclerview.addItemDecoration( new SimpleDividerItemDecoration( getActivity() ) );



        // return inflater.inflate(R.layout.fragment_product_detail, container, false);
        return _view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        _recyclerview.setAdapter(null );
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
