package layout;

import android.content.Context;
import android.content.Intent;
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
import com.datalabor.soporte.mexar.models.Product_Adhiere;
import com.datalabor.soporte.mexar.models.Product_Aplication;
import com.datalabor.soporte.mexar.models.Product_Characteristic;
import com.datalabor.soporte.mexar.models.Product_Color;
import com.datalabor.soporte.mexar.models.Product_Especification;
import com.datalabor.soporte.mexar.models.Product_Image;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
    private TextView presentation;
    private TextView aplication;
    private TextView aplications;
    private TextView especification;
    private TextView especifications;
    private TextView caracteristica;
    private TextView caracteristicas;
    private TextView adhiere;
    private TextView adhieres;
    private TextView color;
    private TextView colors;
    private TextView nota;


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
        JSONArray aplicaciones;
        JSONArray especificaciones;
        JSONArray caracteristicas;
        JSONArray adhieres;
        JSONArray colors;


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
                String ficha_tecnica = producto.getString("ficha_tecnica");
                String nota = producto.getString("nota");

                int id = producto.getInt("id");

                if (id == _curProdcutId) {
                    //int subcategoryid = producto.getInt("idsubcategoria");

                    Product newProduct = new Product();
                    newProduct.setName(name);
                    newProduct.setId(id);
                    newProduct.setDescription(desc);
                    newProduct.set_desc_complete(desc_completa);
                    newProduct.set_ficha_tecnica(ficha_tecnica);
                    newProduct.set_nota(nota);

                    int resid = myContext.getResources().getIdentifier(resname, "drawable", myContext.getPackageName());

                    newProduct.setResId(resid);
                   // Solo Añadir las categorias seleccionadas

                    presentaciones = producto.getJSONArray("presentaciones");
                    images = producto.getJSONArray("images");
                    aplicaciones = producto.getJSONArray("aplicaciones");
                    especificaciones = producto.getJSONArray("especificaciones");
                    caracteristicas = producto.getJSONArray("caracteristicas");
                    adhieres = producto.getJSONArray("adhieres");
                    colors = producto.getJSONArray("colors");


                    ArrayList<Presentation> presentations = new ArrayList<>();
                    ArrayList<Product_Image> product_images = new ArrayList<>();
                    ArrayList<Product_Aplication> product_aplications = new ArrayList<>();
                    ArrayList<Product_Especification> product_especifications = new ArrayList<>();
                    ArrayList<Product_Characteristic> product_characteristics = new ArrayList<>();
                    ArrayList<Product_Adhiere> product_adhieres = new ArrayList<>();
                    ArrayList<Product_Color> product_colors = new ArrayList<>();

                    for (int j = 0; j < colors.length(); j++)
                    {
                        String dato = colors.getString(j);
                        Product_Color newChar = new Product_Color();
                        newChar.set_color(dato);
                        product_colors.add(newChar);
                    }


                    for (int j = 0; j < adhieres.length(); j++)
                    {
                        String dato = adhieres.getString(j);
                        Product_Adhiere newChar = new Product_Adhiere();
                        newChar.set_adhiere(dato);
                        product_adhieres.add(newChar);
                    }

                    for (int j = 0; j < caracteristicas.length(); j++)
                    {
                        String dato = caracteristicas.getString(j);
                        Product_Characteristic newChar = new Product_Characteristic();
                        newChar.set_characteristic(dato);
                        product_characteristics.add(newChar);

                    }


                    for (int j = 0; j < presentaciones.length(); j++)
                    {
                    String presentacion = presentaciones.getString(j);
                        Presentation curPresentation = new Presentation();
                        curPresentation.setPresentation(presentacion);
                        presentations.add(curPresentation);

                    }

                    for (int j=0 ; j < images.length(); j++)
                    {
                        String curImage = images.getString(j);
                        Product_Image curProduct_Image = new Product_Image();
                        curProduct_Image.set_image(curImage);
                        product_images.add(curProduct_Image);

                    }

                    for (int j=0 ; j < aplicaciones.length(); j++)
                    {
                        String curAplication = aplicaciones.getString(j);
                        Product_Aplication product_aplication = new Product_Aplication();
                        product_aplication.set_aplication(curAplication);
                        product_aplications.add(product_aplication);
                    }

                    for (int j=0 ; j < especificaciones.length(); j++)
                    {
                        String curEspecificacion = especificaciones.getString(j);
                        Product_Especification product_especification = new Product_Especification();
                        product_especification.set_especification(curEspecificacion);
                        product_especifications.add(product_especification);
                    }


                    newProduct.set_presentations(presentations);
                    newProduct.setProduct_images(product_images);
                    newProduct.setProduct_aplications(product_aplications);
                    newProduct.setProduct_especifications(product_especifications);
                    newProduct.setProduct_characteristics(product_characteristics);
                    newProduct.setProduct_adhieres(product_adhieres);
                    newProduct.setProduct_colors(product_colors);

                    curProduct = newProduct;

                }
            }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file products");
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
        presentation = (TextView) _view.findViewById(R.id.ProductDetailPresentation);

        aplications = (TextView) _view.findViewById(R.id.ProductDetailAplications);
        aplication = (TextView) _view.findViewById(R.id.ProductDetailAplication);

        especifications = (TextView) _view.findViewById(R.id.ProductDetailEspecifications);
        especification = (TextView) _view.findViewById(R.id.ProductDetailEspecification);

        adhieres = (TextView) _view.findViewById(R.id.ProductDetailAdhieres);
        adhiere = (TextView) _view.findViewById(R.id.ProductDetailAdhiere);

        caracteristicas = (TextView) _view.findViewById(R.id.ProductDetailCarecteristicas);
        caracteristica = (TextView) _view.findViewById(R.id.ProductDetailCarecteristica);

        colors = (TextView) _view.findViewById(R.id.ProductDetailColors);
        color = (TextView) _view.findViewById(R.id.ProductDetailColor);

        ficha = (Button) _view.findViewById(R.id.ProductDetailFicha);
        mPager = (ViewPager) _view.findViewById(R.id.productDetailViewPager);

        nota = (TextView) _view.findViewById(R.id.ProductDetailnota);

        if (this.curProduct == null) return null;

        title.setText(this.curProduct.getName());
        if (this.curProduct.get_desc_complete().length()>1) {
            desc.setText(this.curProduct.get_desc_complete());
        }

        // Nota
        String curNote = this.curProduct.get_nota();

        if (curNote.length()>1)
        {
            nota.setText("Nota: " + curNote);

        }

        else {
            nota.setVisibility(View.GONE);
        }

        // Presentaciones

        StringBuilder presentaciones= new StringBuilder();

        for(Presentation presentation: curProduct.get_presentations())
        {
            presentaciones.append(presentation.getPresentation());
            presentaciones.append(System.getProperty("line.separator"));

        }

        presentations.setText(presentaciones.toString());

        int numPresentations = presentaciones.toString().length();
        if (numPresentations <= 1)
        {
            presentation.setVisibility(View.GONE);
            presentations.setVisibility(View.GONE);
        }


        // Aplicaciones

        StringBuilder aplicaciones = new StringBuilder();

        for (Product_Aplication curAplication: curProduct.getProduct_aplications())
        {
            aplicaciones.append(curAplication.get_aplication());
            aplicaciones.append(System.getProperty("line.separator"));
        }

        aplications.setText(aplicaciones.toString());

        int numAplications = aplicaciones.toString().length();
        if (numAplications <=1 )
        {
            aplication.setVisibility(View.GONE);
            aplications.setVisibility(View.GONE);
        }

        // Especificaciones

        StringBuilder especificaciones = new StringBuilder();

        for (Product_Especification curEspecification: curProduct.getProduct_especifications())
        {
            especificaciones.append(curEspecification.get_especification());
            especificaciones.append(System.getProperty("line.separator"));
        }

        especifications.setText(especificaciones.toString());

        int numEspecifications = especificaciones.toString().length();
        if (numEspecifications <=1 )
        {
            especification.setVisibility(View.GONE);
            especifications.setVisibility(View.GONE);
        }

        // Caracteristicas y beneficios

        StringBuilder beneficios = new StringBuilder();

        for (Product_Characteristic curEspecification: curProduct.getProduct_characteristics())
        {
            beneficios.append(curEspecification.get_characteristic());
            beneficios.append(System.getProperty("line.separator"));
        }

        caracteristicas.setText(beneficios.toString());

        int numCaracteristicas = beneficios.toString().length();
        if (numCaracteristicas <=1 )
        {
            caracteristica.setVisibility(View.GONE);
            caracteristicas.setVisibility(View.GONE);
        }

        // Adhieres a


        StringBuilder adhiers = new StringBuilder();

        for (Product_Adhiere curEspecification: curProduct.getProduct_adhieres())
        {
            adhiers.append(curEspecification.get_adhiere());
            adhiers.append(System.getProperty("line.separator"));
        }

        adhieres.setText(adhiers.toString());

        int numAdhieres = adhiers.toString().length();
        if (numAdhieres <1 )
        {
            adhiere.setVisibility(View.GONE);
            adhieres.setVisibility(View.GONE);
        }

        /// Colores

        StringBuilder colores = new StringBuilder();

        for (Product_Color curColor: curProduct.getProduct_colors())
        {
            colores.append(curColor.get_color());
            colores.append(System.getProperty("line.separator"));
        }

        colors.setText(colores.toString());

        int numColors = colores.toString().length();
        if (numColors <1 )
        {
            colors.setVisibility(View.GONE);
            color.setVisibility(View.GONE);
        }



        ficha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"clicked");
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(curProduct.get_ficha_tecnica()));
                startActivity(browserIntent);

            }
        });

        if (curProduct.get_ficha_tecnica().length() == 0)
        {
            ficha.setVisibility(View.GONE);
        }

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
