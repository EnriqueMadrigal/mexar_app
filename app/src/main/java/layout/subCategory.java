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
import com.datalabor.soporte.mexar.adapter.SubCategoryAdapter;
import com.datalabor.soporte.mexar.custom.SimpleDividerItemDecoration;
import com.datalabor.soporte.mexar.models.Product;
import com.datalabor.soporte.mexar.models.SubCategory;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link subCategory.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link subCategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class subCategory extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<SubCategory> _subcategories;
    private SubCategoryAdapter _adapter;

    private OnFragmentInteractionListener mListener;

    private View _view;
    private RecyclerView _recyclerview;
    private LinearLayoutManager _linearLayoutManager;
    private FragmentActivity myContext;




    public subCategory() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment subCategory.
     */
    // TODO: Rename and change types and number of parameters
    public static subCategory newInstance(ArrayList<SubCategory> categories) {
        subCategory fragment = new subCategory();
        Bundle args = new Bundle();
        args.putSerializable("SubCategorias", categories);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subcategories = (ArrayList<SubCategory>) getArguments().getSerializable( "SubCategorias" );


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate( R.layout.fragment_sub_category, container, false );
        _recyclerview = (RecyclerView) _view.findViewById(R.id.recyclerSubCategory);

        _adapter = new SubCategoryAdapter(getActivity(), _subcategories, new IViewHolderClick() {
            @Override
            public void onClick(int position) {

                Log.d("mainf", String.valueOf(_subcategories.get(position).getId()));
                long curiId =  _subcategories.get(position).getId();

                if (curiId == 1) {

                    ArrayList<Product> products = new ArrayList<>();

                    ////Productos
                    Product product1 = new Product();
                    product1.setId(1);
                    product1.setResId(R.drawable.contact202);
                    product1.setName("Contact 202");
                    product1.setDescription("Cemento transparente consistencia regular para uso en tubos y conexiones de PVC hasta 4\" para tubería, drenaje y ventilación C-40 y hasta 2\" para C-80. ");

                    Product product2 = new Product();
                    product2.setId(2);
                    product2.setResId(R.drawable.contact222);
                    product2.setName("Contact 222");
                    product2.setDescription("Cemento de color azul de mediana densidad para tubos y conexiones de PVC hasta 6\" de diámetro");


                    Product product3 = new Product();
                    product3.setId(3);
                    product3.setResId(R.drawable.contact227);
                    product3.setName("Contact 227");
                    product3.setDescription("Cemento amarillo de alta viscosidad para uso en tubos y conexiones de CPVC de hasta 4\" de diámetro. Para C.T.S.");


                    Product product4 = new Product();
                    product4.setId(4);
                    product4.setResId(R.drawable.contact212);
                    product4.setName("Contact 212 Primer");
                    product4.setDescription("En una mezcla de solventes orgánicos capaces de disolver hasta el 10 % de resina de PVC y CPVC");

                    Product product5 = new Product();
                    product5.setId(5);
                    product5.setResId(R.drawable.contact204);
                    product5.setName("CONTACT 204");
                    product5.setDescription("Transparente etiqueta azul claro, extra reforzado para PVC hidráulico, diámetros especiales.");


                    Product product6 = new Product();
                    product6.setId(5);
                    product6.setResId(R.drawable.contact244);
                    product6.setName("Contact 244");
                    product6.setDescription("Transparente etiqueta gris para PVC hidráulico diámetros grandes.");



                    Product product7 = new Product();
                    product7.setId(7);
                    product7.setResId(R.drawable.contact205);
                    product7.setName("Contact 205 Limpiador");
                    product7.setDescription("Una mezcla de solventes orgánicos especialmente formulados para limpiar cualquier clase de suciedad depositada en la superficie de las tuberías y accesorios de PVC y CPVC.");


                    Product product8 = new Product();
                    product8.setId(8);
                    product8.setResId(R.drawable.contact206);
                    product8.setName("CONTACT 206 REFORZADO");
                    product8.setDescription("Solución de resina de PVC con disolventes apropiados que, al secar por evaporación de solventes realiza la fusión de las superficies y su unión permanente, dejando una sola pieza como resultado.");


                    Product product9 = new Product();
                    product9.setId(9);
                    product9.setResId(R.drawable.contact216);
                    product9.setName("CONTACT 216 EXTRA REFORZADO");
                    product9.setDescription("Cemento Contact Extra-reforzado Gris de viscosidad Gruesa, Clasificación ASTM D2564 para tubos y accesorios de todas clases de PVC, para utilizar en tubería cédula 40 hasta 12” de diámetro y cédula 80 hasta 6” de diámetro para sistemas con presión de agua fría y en sistemas sin presión D.W.V. hasta 18” de diámetro");


                    Product product10 = new Product();
                    product10.setId(8);
                    product10.setResId(R.drawable.borlapvc);
                    product10.setName("BORLA PARA PVC");
                    product10.setDescription("Repuesto practico.");




                    products.add(product1);
                    products.add(product2);
                    products.add(product3);
                    products.add(product4);
                    products.add(product5);
                    products.add(product6);
                    products.add(product7);
                    products.add(product8);
                    products.add(product9);
                    products.add(product10);



                    //////// Productos
                    Common.SetPage(2);
                    productDetail _productDetail = productDetail.newInstance(products);
                    myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, _productDetail, "Sub Categoria" ).commit();




                }


            }
        });

        _linearLayoutManager = new LinearLayoutManager( getActivity() );

        _recyclerview.setHasFixedSize( true );
        _recyclerview.setAdapter( _adapter );
        _recyclerview.setLayoutManager( _linearLayoutManager );
        _recyclerview.addItemDecoration( new SimpleDividerItemDecoration( getActivity() ) );


        return _view;
        //return inflater.inflate(R.layout.fragment_sub_category, container, false);
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
