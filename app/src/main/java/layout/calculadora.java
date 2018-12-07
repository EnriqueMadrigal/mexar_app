package layout;

import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.adapter.IViewHolderClick;
import com.datalabor.soporte.mexar.adapter.ProductAdapter;
import com.datalabor.soporte.mexar.adapter.ProductAdapter2;
import com.datalabor.soporte.mexar.models.Catalago1;
import com.datalabor.soporte.mexar.models.Product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link calculadora.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link calculadora#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calculadora extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final  String TAG = "calculadora";
    private FragmentActivity myContext;

    private Spinner Spinner_presentacion;
    private Spinner Spinner_pieza;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView calculadora_type;

    private ImageView image1;



    private ArrayList<String> presentaciones;
    private ArrayList<String> piezas;
    private ArrayList<String> piezas_totales;


    private ArrayList<String> presentaciones_img;
    private ArrayList<String> piezas_img;

    private OnFragmentInteractionListener mListener;
    private ImageButton calcular;

    private RecyclerView _recyclerview;
    private ProductAdapter2 _adapter;
    private LinearLayoutManager _linearLayoutManager;


    ArrayList<Product> _products;
    ArrayList<Product> _products_display;

    private  long idPieza = 1;
    private long idPresentacion = 1;

    private boolean ifnotFistTimePieza = false;
    private boolean ifnotFistTimePresentation = false;


    int curType = 0;


    private ArrayAdapter<String> dataAdapter1;
    private ArrayAdapter<String> dataAdapter2;

    public calculadora() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment calculadora.
     */
    // TODO: Rename and change types and number of parameters
    public static calculadora newInstance(int curtype) {
        calculadora fragment = new calculadora();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        args.putInt("curType", curtype);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       curType = (int) getArguments().getInt("curType");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View _view;
        _view = inflater.inflate( R.layout.fragment_calculadora, container, false );

        Spinner_presentacion = (Spinner) _view.findViewById(R.id.calculadora_Presentacion);
        Spinner_pieza = (Spinner) _view.findViewById(R.id.calculadora_Pieza);
       // calcular = (ImageButton) _view.findViewById(R.id.calculadora_calcular);
        _recyclerview = (RecyclerView) _view.findViewById(R.id.recyclerCalculadora);

        text1 = (TextView) _view.findViewById(R.id.calculadora_text1);
        text2 = (TextView) _view.findViewById(R.id.calculadora_text2);
        text3 = (TextView) _view.findViewById(R.id.calculadora_text3);
        calculadora_type = (TextView) _view.findViewById(R.id.calculadora_type);
        image1 = (ImageView) _view.findViewById(R.id.calculadora_unionImage);


        presentaciones = new ArrayList<>();
        piezas = new ArrayList<>();
        piezas_totales = new ArrayList<>();
        presentaciones_img = new ArrayList<>();
        piezas_img = new ArrayList<>();

       _products = new ArrayList<Product>();
       _products_display = new ArrayList<Product>();
        loadProducts();

/*
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"clicked");
              long idPieza = Spinner_pieza.getSelectedItemId();
              long idPresentacion = Spinner_presentacion.getSelectedItemId();

              idPieza++;
                idPresentacion++;

                // Cargar datos para la calculaldora

                String jsonCalculadora = Common.loadJSONFromAsset(myContext,"calculadora.json");
                JSONObject obj_calculadora;
                JSONObject calculadora;

                try
                {

                    obj_calculadora = new JSONObject(jsonCalculadora);
                    JSONArray res = obj_calculadora.getJSONArray("calculos");

                    for (int i = 0; i < res.length(); i++)
                    {
                        calculadora = res.getJSONObject(i).getJSONObject("calculo");
                        int id = calculadora.getInt("id");

                         if ((int) idPieza == id)  // Si la pieza es igual al calculo seleccionado
                         {
                             // Obtener las presentaciones
                             String presentacion4z = calculadora.getString("4z");
                             String presentacion8z = calculadora.getString("8z");
                             String presentacion16z = calculadora.getString("16z");
                             String presentacion32z = calculadora.getString("32z");
                             String presentacion133z = calculadora.getString("133z");

                             String Resultado = "";
                             if (idPresentacion==1) Resultado = presentacion4z;
                             if (idPresentacion==2) Resultado = presentacion8z;
                             if (idPresentacion==3) Resultado = presentacion16z;
                             if (idPresentacion==4) Resultado = presentacion32z;
                             if (idPresentacion==5) Resultado = presentacion133z;


                             text1.setText("El cemento Contact 202 en presentación de: ");
                             text2.setText(Spinner_presentacion.getSelectedItem().toString());
                             text3.setText("Rinde para:");
                             text4.setText(Resultado);
                             text5.setText(" uniones de:");
                             text6.setText(Spinner_pieza.getSelectedItem().toString());
                             //text7.setText("=");
                             //text8.setText("1");
                             //text9.setText(Resultado);

                             int curId = (int) Spinner_presentacion.getSelectedItemId();
                             String img_name = presentaciones_img.get(curId);
                             int resid = myContext.getResources().getIdentifier(img_name, "drawable", myContext.getPackageName());
                             //img1.setImageResource(resid);

                             int curId2 = (int) Spinner_pieza.getSelectedItemId();
                             String img_name2 = piezas_img.get(curId2);
                             int resid2 = myContext.getResources().getIdentifier(img_name2, "drawable", myContext.getPackageName());
                             // img2.setImageResource(resid2);


                         }



                    }

                }

                catch (Exception e)
                {
                    Log.d(TAG,"Can not read json file calculadora");
                    //return null;

                }



                String curText = Spinner_presentacion.getSelectedItem().toString();
                String curText2 = Spinner_pieza.getSelectedItem().toString();

                Log.d(TAG,curText);
            }
        });

*/


        dataAdapter1 = new ArrayAdapter(myContext, R.layout.spinner_item,presentaciones);
        dataAdapter1.setDropDownViewResource(R.layout.spinner_item);
        Spinner_presentacion.setAdapter(dataAdapter1);


        // Cargar presentaciones
        CargarPresentaciones();

        /////


        /// Cargar Piezas

        dataAdapter2 = new ArrayAdapter(myContext, R.layout.spinner_item,piezas);
        dataAdapter2.setDropDownViewResource(R.layout.spinner_item);
        Spinner_pieza.setAdapter(dataAdapter2);

        CargarPiezas();
        checkIfPiezaCanShow();



        /////

        Spinner_pieza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Log.d("calc","Pieza");
               // String Pieza = parentView.getAdapter().getItem(position).toString();
                Long PiezaId = parentView.getAdapter().getItemId(position);
                idPieza = PiezaId + 1;
                Log.d("pieza id",PiezaId.toString());
                int curIntPieza =   (int) PiezaId.intValue();
                String resname = piezas_img.get(curIntPieza);
                int resid = myContext.getResources().getIdentifier(resname, "drawable", myContext.getPackageName());

                image1.setImageResource(resid);

                Log.d("pieza name",resname);

                if  (ifnotFistTimePieza) {
                    Calculate();
                }

                ifnotFistTimePieza = true;
                //Log.d("pieza selec",Pieza);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });


        Spinner_presentacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Log.d("calc","Presentacion");
                Long PresentationId = parentView.getAdapter().getItemId(position);
                idPresentacion = PresentationId + 1;

                if (ifnotFistTimePresentation){
                    Calculate();
                    checkIfPiezaCanShow();
                }

                ifnotFistTimePresentation = true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        /////  Recycler


        _adapter = new ProductAdapter2(getActivity(), _products_display, new IViewHolderClick() {
            @Override
            public void onClick(int position) {

                Product curProduct = _products_display.get(position);

                Log.d(TAG, String.valueOf(curProduct.getId()));

                productDetail productdetail = productDetail.newInstance(curProduct.getId(), 0);


                myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, productdetail, "Product Detail" ).commit();


            }
        });

        _linearLayoutManager = new LinearLayoutManager( getActivity() );

        _recyclerview.setHasFixedSize( true );
        _recyclerview.setAdapter( _adapter );
        _recyclerview.setLayoutManager( _linearLayoutManager );
        //_recyclerview.addItemDecoration( new SimpleDividerItemDecoration( getActivity() ) );

        String pvc_type =  "";
        if (curType == 1) {pvc_type = "CÉDULA 40";}
        else if (curType == 2) {pvc_type = "CÉDULA 80";}
        else if (curType == 3) {pvc_type = "CONDUIT";}
        else if (curType == 4) {pvc_type = "SIN PRESIÓN";}
        else if (curType == 5) {pvc_type = "SANITARIO NORMA";}

        calculadora_type.setText(pvc_type);


        Calculate();
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


    private void CargarPresentaciones()
    {
        // Cargar presentaciones

        String jsonPresentaciones = Common.loadJSONFromAsset(myContext,"presentaciones.json");
        JSONObject obj_presentacion;
        JSONObject presentacion;

        ///////////////
        try {

            obj_presentacion = new JSONObject(jsonPresentaciones);
            JSONArray res = obj_presentacion.getJSONArray("presentaciones");

            for (int i = 0; i < res.length(); i++) {
                presentacion = res.getJSONObject(i).getJSONObject("presentacion");
                String name = presentacion.getString("name");
                String res_name = presentacion.getString("resname");
                int id = presentacion.getInt("id");
                presentaciones.add(name);
                presentaciones_img.add(res_name);


            }

            dataAdapter1.notifyDataSetChanged();
        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }



    }

private void CargarPiezas()
{

    String filename = "piezas.json";
    if (curType == 6) {
        filename = "piezas_cpvc.json";
    }

    String jsonPiezas = Common.loadJSONFromAsset(myContext,filename);
    JSONObject obj_pieza;
    JSONObject pieza;

    ///////////////
    try {

        obj_pieza = new JSONObject(jsonPiezas);
        JSONArray res = obj_pieza.getJSONArray("piezas");

        for (int i = 0; i < res.length(); i++) {
            pieza = res.getJSONObject(i).getJSONObject("pieza");
            String name = pieza.getString("name");
            String res_name = pieza.getString("resname");
            int id = pieza.getInt("id");
            piezas_totales.add(name);
            piezas_img.add(res_name);

        }
        //dataAdapter2.notifyDataSetChanged();

    }

    catch (Exception e)
    {
        Log.d(TAG,"Can not read json file categories");
        //return null;

    }

}


private void checkIfPiezaCanShow()
{

   ////////////

    String jsonCalculadora = Common.loadJSONFromAsset(myContext,"calculadora.json");
    JSONObject obj_calculadora;
    JSONObject calculadora;

    piezas.clear();

    try
    {

        obj_calculadora = new JSONObject(jsonCalculadora);
        JSONArray res = obj_calculadora.getJSONArray("calculos");

        for (int i = 0; i < res.length(); i++)
        {
            calculadora = res.getJSONObject(i).getJSONObject("calculo");

                // Obtener las presentaciones
                String presentacion4z = calculadora.getString("4z");
                String presentacion8z = calculadora.getString("8z");
                String presentacion16z = calculadora.getString("16z");
                String presentacion32z = calculadora.getString("32z");
                String presentacion133z = calculadora.getString("133z");

                String Resultado = "";
                if (idPresentacion==1) Resultado = presentacion4z;
                if (idPresentacion==2) Resultado = presentacion8z;
                if (idPresentacion==3) Resultado = presentacion16z;
                if (idPresentacion==4) Resultado = presentacion32z;
                if (idPresentacion==5) Resultado = presentacion133z;

                if (!Resultado.equals("0"))
                {
                   piezas.add(piezas_totales.get(i));

                }







        }

        dataAdapter2.notifyDataSetChanged();

    }

    catch (Exception e)
    {
        Log.d(TAG,"Can not read json file calculadora");
        //return null;

    }



    ///////////




}

    private void loadProducts() {

        /////////
        String jsonProducts = Common.loadJSONFromAsset(myContext,"calc_products.json");
        JSONObject obj_products;
        JSONObject producto;

        int curSubCategory = 1;

        ///////////////
        try {

            obj_products = new JSONObject(jsonProducts);
            JSONArray res = obj_products.getJSONArray("calc_products");

            for (int i = 0; i < res.length(); i++) {
                producto = res.getJSONObject(i).getJSONObject("product");
                String name = producto.getString("name");
                String resname1 = producto.getString("pres1");
                String resname2 = producto.getString("pres2");
                String resname3 = producto.getString("pres3");
                String resname4 = producto.getString("pres4");
                String resname5 = producto.getString("pres5");


                int id = producto.getInt("id");

                Product newProduct = new Product();
                newProduct.setName(name);
                newProduct.setId(id);
                newProduct.set_desc_complete("");

                int resid = myContext.getResources().getIdentifier(resname3, "drawable", myContext.getPackageName());
                Log.d(TAG,newProduct.getName());
                newProduct.setResId(resid);

                    _products.add(newProduct);

            }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }


        /////////
    }


    private void Calculate() {
        ///////

        // Cargar datos para la calculaldora

        String jsonCalculadora = Common.loadJSONFromAsset(myContext,"calculadora.json");
        JSONObject obj_calculadora;
        JSONObject calculadora;

        try
        {

            obj_calculadora = new JSONObject(jsonCalculadora);
            JSONArray res = obj_calculadora.getJSONArray("calculos");

            for (int i = 0; i < res.length(); i++)
            {
                calculadora = res.getJSONObject(i).getJSONObject("calculo");
                int id = calculadora.getInt("id");

                if ((int) idPieza == id)  // Si la pieza es igual al calculo seleccionado
                {
                    // Obtener las presentaciones
                    String presentacion4z = calculadora.getString("4z");
                    String presentacion8z = calculadora.getString("8z");
                    String presentacion16z = calculadora.getString("16z");
                    String presentacion32z = calculadora.getString("32z");
                    String presentacion133z = calculadora.getString("133z");

                    String Resultado = "";
                    if (idPresentacion==1) Resultado = presentacion4z;
                    if (idPresentacion==2) Resultado = presentacion8z;
                    if (idPresentacion==3) Resultado = presentacion16z;
                    if (idPresentacion==4) Resultado = presentacion32z;
                    if (idPresentacion==5) Resultado = presentacion133z;


                    text1.setText("Rinde para");
                    text2.setText(Resultado);
                    int intResultado = 0;

                    try {
                        intResultado = Integer.parseInt(Resultado);
                    } catch(NumberFormatException nfe) {
                        System.out.println("Could not parse " + nfe);
                    }

                    if (intResultado <= 1) { text3.setText("De unión");}
                    else {text3.setText("Uniones"); }


                }



            }

        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file calculadora");
            //return null;

        }



       // String curText = Spinner_presentacion.getSelectedItem().toString();
       // String curText2 = Spinner_pieza.getSelectedItem().toString();

       // Log.d(TAG,curText);


        /////// Cargar los productos según las piezas

        String  filename = "piezas.json";

        if (curType == 1) {filename = "cedula40.json";}
        else if (curType == 2) {filename = "cedula80.json";}
        else if (curType == 3) {filename = "conduit.json";}
        else if (curType == 4) {filename = "sinpresion.json";}
        else if (curType == 5) {filename = "sanitario.json";}
        else if (curType == 6) {filename = "cpvc.json";}

        String jsonPiezas = Common.loadJSONFromAsset(myContext,filename);

        JSONObject obj_pieza;
        JSONObject pieza;
        JSONArray productos;

        productos = new JSONArray();

        ///////////////
        try {

            obj_pieza = new JSONObject(jsonPiezas);
            JSONArray res = obj_pieza.getJSONArray("piezas");

            for (int i = 0; i < res.length(); i++) {
                pieza = res.getJSONObject(i).getJSONObject("pieza");
                String name = pieza.getString("name");
                String res_name = pieza.getString("resname");
                int id = pieza.getInt("id");
                if (id == idPieza) {
                    productos = pieza.getJSONArray("productos");
                }

            }



        if (productos.length()>0){
            _products_display.clear();

            for (int j = 0; j < productos.length(); j++)
            {
                int curId = productos.getInt(j);

                for (int i = 0; i< _products.size() ;i++ ){
                    if (curId == _products.get(i).getId()) {
                        _products_display.add(_products.get(i));
                    }
                }


            }

        _adapter.notifyDataSetChanged();

        }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories:" + filename);
            //return null;

        }






    }

}
