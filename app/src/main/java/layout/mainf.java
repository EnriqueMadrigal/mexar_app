package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.adapter.IViewHolderClick;
import com.datalabor.soporte.mexar.adapter.MyPageAdapter;
import com.datalabor.soporte.mexar.adapter.ProductsAdapter;
import com.datalabor.soporte.mexar.custom.SimpleDividerItemDecoration;
import com.datalabor.soporte.mexar.custom.banner_image_class;
import com.datalabor.soporte.mexar.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mainf.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mainf#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mainf extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ViewPager mPager;
    private View _view;
    private MyPageAdapter myPageAdapter;

    private FragmentActivity myContext;
    private RecyclerView _recyclerview;
    private ProductsAdapter _adapter;
    private LinearLayoutManager _linearLayoutManager;

    public mainf() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mainf.
     */
    // TODO: Rename and change types and number of parameters
    public static mainf newInstance(String param1, String param2) {
        mainf fragment = new mainf();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _view = inflater.inflate( R.layout.fragment_mainf, container, false );
        mPager = (ViewPager) _view.findViewById(R.id.ViewPager);
        _recyclerview = (RecyclerView) _view.findViewById(R.id.recycler1);

             ArrayList<Product> _products = new ArrayList<>();
            Product product1 = new Product();
            product1.setBrand("Pipes");
            product1.setName("Pipes");
            product1.setPrice(1000.00);
            product1.setFamily("familia");

        Product product2 = new Product();
        product2.setBrand("Pipes");
        product2.setName("Pipes");
        product2.setPrice(1000.00);
        product2.setFamily("familia");


        Product product3 = new Product();
        product3.setBrand("Pipes");
        product3.setName("Pipes");
        product3.setPrice(1000.00);
        product3.setFamily("familia");


            _products.add(product1);
        _products.add(product2);
        _products.add(product3);

            _adapter = new ProductsAdapter(getActivity(), _products, new IViewHolderClick() {
                @Override
                public void onClick(int position) {
                    Log.d("mainf", "clicked");
                }
            });



        _linearLayoutManager = new LinearLayoutManager( getActivity() );

        _recyclerview.setHasFixedSize( true );
        _recyclerview.setAdapter( _adapter );
        _recyclerview.setLayoutManager( _linearLayoutManager );
        _recyclerview.addItemDecoration( new SimpleDividerItemDecoration( getActivity() ) );


        ////////// Banners

            List<Fragment> fList = new ArrayList<Fragment>();
            fList.add(banner_image_class.newInstance(R.drawable.banner1));
            fList.add(banner_image_class.newInstance(R.drawable.banner2));
            fList.add(banner_image_class.newInstance(R.drawable.banner3));

        myPageAdapter = new MyPageAdapter(myContext.getSupportFragmentManager(), fList);


        mPager.setAdapter(myPageAdapter);




        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mainf, container, false);
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
