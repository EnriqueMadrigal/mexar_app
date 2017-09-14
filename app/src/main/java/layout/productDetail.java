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
import android.widget.Button;
import android.widget.TextView;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.adapter.IViewHolderClick;
import com.datalabor.soporte.mexar.adapter.MyPageAdapter;
import com.datalabor.soporte.mexar.adapter.ProductAdapter;
import com.datalabor.soporte.mexar.custom.SimpleDividerItemDecoration;
import com.datalabor.soporte.mexar.custom.banner_image_class;
import com.datalabor.soporte.mexar.custom.product_image_class;
import com.datalabor.soporte.mexar.models.Presentation;
import com.datalabor.soporte.mexar.models.Product;
import com.datalabor.soporte.mexar.models.Product_Image;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link productDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link productDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class productDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    private View _view;

    private FragmentActivity myContext;
   // private RecyclerView _recyclerview;
   // private ProductAdapter _adapter;
    private LinearLayoutManager _linearLayoutManager;

    private TextView title;
    private TextView desc;
    private TextView presentations;
    private MyPageAdapter myPageAdapter;
    private ViewPager mPager;
    private Button ficha;

    private List<Fragment> fList;

    Product curProduct;
    int _curProdcutId=0;

    public static final String TAG = "productDetail";

    public productDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment productDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static productDetail newInstance(int productId) {
        productDetail fragment = new productDetail();
        Bundle args = new Bundle();
        args.putInt("curProductID", productId);
        //args.putSerializable("curProduct", curProduct);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //curProduct = (Product) getArguments().getSerializable("curProduct");
        _curProdcutId = (int) getArguments().getInt("curProductID");

        //Obtener el productoID

////// Obtener los datos del producto


        String jsonProducts = Common.loadJSONFromAsset(myContext,"productos.json");
        JSONObject obj_products;
        JSONObject producto;
        JSONArray presentaciones;
        JSONArray images;



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

                if (id == _curProdcutId) {
                    //int subcategoryid = producto.getInt("idsubcategoria");

                    Product newProduct = new Product();
                    newProduct.setName(name);
                    newProduct.setId(id);
                    newProduct.setDescription(desc);
                    newProduct.set_desc_complete(desc_completa);

                    int resid = myContext.getResources().getIdentifier(resname, "drawable", myContext.getPackageName());

                    newProduct.setResId(resid);
                   // Solo Añadir las categorias seleccionadas

                    presentaciones = producto.getJSONArray("presentaciones");
                    images = producto.getJSONArray("images");

                    ArrayList<Presentation> presentations = new ArrayList<>();
                    ArrayList<Product_Image> product_images = new ArrayList<>();


                    for (int j = 0; j < presentaciones.length(); j++)
                    {
                    String presentacion = presentaciones.getString(j);
                        Presentation curPresentation = new Presentation();
                        curPresentation.setPresentation(presentacion.toString());
                        presentations.add(curPresentation);

                    }

                    for (int j=0 ; j < images.length(); j++)
                    {
                        String curImage = images.getString(j);
                        Product_Image curProduct_Image = new Product_Image();
                        curProduct_Image.set_image(curImage);
                        product_images.add(curProduct_Image);

                    }



                    newProduct.set_presentations(presentations);
                    newProduct.setProduct_images(product_images);
                    curProduct = newProduct;

                }
            }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }



        ////////////


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate( R.layout.product_detail_layout, container, false );
       // _recyclerview = (RecyclerView) _view.findViewById(R.id.recycler2);
        title = (TextView) _view.findViewById(R.id.ProductDetailTitle);
        desc = (TextView) _view.findViewById(R.id.ProductDetailDescription);
        presentations = (TextView) _view.findViewById(R.id.ProductDetailPresentations);
        ficha = (Button) _view.findViewById(R.id.ProductDetailFicha);
        mPager = (ViewPager) _view.findViewById(R.id.productDetailViewPager);

        if (this.curProduct == null) return null;

        title.setText(this.curProduct.getName());
        desc.setText(this.curProduct.get_desc_complete());


        StringBuilder presentaciones= new StringBuilder();

        for(Presentation presentation: curProduct.get_presentations())
        {
            presentaciones.append(presentation.getPresentation());
            presentaciones.append(System.getProperty("line.separator"));

        }

        presentations.setText(presentaciones.toString());

        ficha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"clicked");
            }
        });

        _linearLayoutManager = new LinearLayoutManager( getActivity() );

        //Images

        ////////// Banners

        fList = new ArrayList<Fragment>();

        for (Product_Image curImage: curProduct.getProduct_images())
        {

            int resid = myContext.getResources().getIdentifier(curImage.get_image(), "drawable", myContext.getPackageName());
            fList.add(product_image_class.newInstance(resid));


        }

        //fList.add(banner_image_class.newInstance(R.drawable.banner1));
        //fList.add(banner_image_class.newInstance(R.drawable.banner2));
        //fList.add(banner_image_class.newInstance(R.drawable.banner3));

        myPageAdapter = new MyPageAdapter(myContext.getSupportFragmentManager(), fList);


        mPager.setAdapter(myPageAdapter);
        mPager.setCurrentItem(0);
        myPageAdapter.notifyDataSetChanged();



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
