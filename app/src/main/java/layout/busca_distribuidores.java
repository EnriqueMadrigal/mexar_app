package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.activity.MapsActivity;
import com.datalabor.soporte.mexar.activity.MapsActivity2;
import com.datalabor.soporte.mexar.activity.MapsActivity3;
import com.datalabor.soporte.mexar.adapter.DistribuidoresAdapter;
import com.datalabor.soporte.mexar.adapter.IViewHolderClick;
import com.datalabor.soporte.mexar.adapter.NothingSelectedSpinnerAdapter;
import com.datalabor.soporte.mexar.custom.SimpleDividerItemDecoration;
import com.datalabor.soporte.mexar.models.Distribuidor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link busca_distribuidores.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link busca_distribuidores#newInstance} factory method to
 * create an instance of this fragment.
 */
public class busca_distribuidores extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private final  String TAG = "busca_distribuidores";
    private FragmentActivity myContext;
    private LinearLayoutManager _linearLayoutManager;
    private LinearLayoutManager _linearLayoutManager2;

    private Spinner Spinner_estados;
    private ArrayList<String> estados;

    ArrayList<Distribuidor> _distribuidores;

    private RecyclerView _recyclerview;
    private DistribuidoresAdapter _adapter;


    private Spinner Spinner_homedepot;
    private ArrayList<String> estados2;

    ArrayList<Distribuidor> _distribuidores2;

    private RecyclerView _recyclerview2;
    private DistribuidoresAdapter _adapter2;



    TabHost host;

    Button Buttontap1;
    Button Buttontap2;

    Button MapaDistribuidores;
    Button MapaHomeDepot;




    public busca_distribuidores() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment busca_distribuidores.
     */
    // TODO: Rename and change types and number of parameters
    public static busca_distribuidores newInstance(String param1, String param2) {
        busca_distribuidores fragment = new busca_distribuidores();
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
        // Inflate the layout for this fragment
        View _view;
        _view = inflater.inflate( R.layout.fragment_busca_distribuidores, container, false );

        Spinner_estados = (Spinner) _view.findViewById(R.id.distribuidores_estados);
        _recyclerview = (RecyclerView) _view.findViewById(R.id.recyclerBuscaDistribuidores);

        Spinner_homedepot = (Spinner) _view.findViewById(R.id.homedepot_estados);
        _recyclerview2 = (RecyclerView) _view.findViewById(R.id.recyclerBuscaHomeDepot);


        // Tabs

        Buttontap1 = (Button) _view.findViewById(R.id.ButtonTab1);
        Buttontap2 = (Button) _view.findViewById(R.id.ButtonTab2);

        Buttontap1.setBackgroundResource(R.drawable.button_tab1);
        Buttontap2.setBackgroundResource(R.drawable.button_tab2);

        MapaDistribuidores = (Button) _view.findViewById(R.id.distribuidoresMapa);
        MapaHomeDepot = (Button) _view.findViewById(R.id.homedepotMapa);

        host = (TabHost) _view.findViewById(R.id.tabHost1);
        host.setup();

        TabHost.TabSpec spec1 = host.newTabSpec("Mexar");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Mexar");
        host.addTab(spec1);

        //Tab 2
        TabHost.TabSpec spec2 = host.newTabSpec("Home Depot");
        spec2.setContent(R.id.tab2);
        //spec.setIndicator(store);
        spec2.setIndicator("Home Depot");
        host.addTab(spec2);

        host.setCurrentTab(0);


        Buttontap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Intent intent = new Intent();
                //   intent.setClass(myContext, MapsActivity.class);
                //   startActivity(intent);


                Buttontap1.setBackgroundResource(R.drawable.button_tab1);
                Buttontap1.setTextColor(ContextCompat.getColor(myContext, R.color.mexar_blue));

                Buttontap2.setBackgroundResource(R.drawable.button_tab2);
                Buttontap2.setTextColor(ContextCompat.getColor(myContext, R.color.white));
                host.setCurrentTab(0);

            }
        });


        Buttontap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Intent intent = new Intent();
                //   intent.setClass(myContext, MapsActivity.class);
                //   startActivity(intent);

                Buttontap2.setBackgroundResource(R.drawable.button_tab1);
                Buttontap2.setTextColor(ContextCompat.getColor(myContext, R.color.mexar_blue));

                Buttontap1.setBackgroundResource(R.drawable.button_tab2);
                Buttontap1.setTextColor(ContextCompat.getColor(myContext, R.color.white));


                host.setCurrentTab(1);


            }
        });




        MapaDistribuidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent intent = new Intent();
                   intent.setClass(myContext, MapsActivity.class);
                   startActivity(intent);

            }
        });



        MapaHomeDepot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(myContext, MapsActivity3.class);
                startActivity(intent);

            }
        });



// Carga Estados


        String jsonDistribuidores = Common.loadJSONFromAsset(myContext,"distribuidores.json");
        JSONObject obj_distribuidores;
        JSONObject distribuidor;
        estados = new ArrayList<>();
        _distribuidores = new ArrayList<>();

        ///////////////
        try {


                obj_distribuidores = new JSONObject(jsonDistribuidores);
                JSONArray res = obj_distribuidores.getJSONArray("distribuidores");


                for (int i = 0; i < res.length(); i++) {
                    distribuidor = res.getJSONObject(i).getJSONObject("distribuidor");
                    int id = distribuidor.getInt("id");
                    String state = distribuidor.getString("state");

                    if (!estados.contains(state)) {
                        estados.add(state);
                    }
                }

            Collections.sort(estados);

            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter(myContext, R.layout.spinner_item,estados);
            dataAdapter1.setDropDownViewResource(R.layout.spinner_item);

            NothingSelectedSpinnerAdapter adapter2 = new NothingSelectedSpinnerAdapter(dataAdapter1, R.layout.contact_spinner_row_nothing_selected, myContext);
            adapter2.setPrompt("Estado:");

            Spinner_estados.setAdapter(adapter2);
            //Spinner_estados.setAdapter(dataAdapter1);

        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }


        Spinner_estados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {

             if (parentView.getSelectedItem() !=null) {
                 String cur_estado = parentView.getItemAtPosition(position).toString();
                 Log.d(TAG, cur_estado);
                 carga_distribuidores(cur_estado);

             }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });


        //////-------- Estados

        ///////---- Carga estados home depot


        String jsonDistribuidores2 = Common.loadJSONFromAsset(myContext,"homedepot.json");
        JSONObject obj_distribuidores2;
        JSONObject distribuidor2;
        estados2 = new ArrayList<>();
        _distribuidores2 = new ArrayList<>();

        ///////////////
        try {


            obj_distribuidores2 = new JSONObject(jsonDistribuidores2);
            JSONArray res = obj_distribuidores2.getJSONArray("distribuidores");


            for (int i = 0; i < res.length(); i++) {
                distribuidor2 = res.getJSONObject(i).getJSONObject("distribuidor");
                int id = distribuidor2.getInt("id");
                String state = distribuidor2.getString("state");

                if (!estados2.contains(state)) {
                    estados2.add(state);
                }
            }

            Collections.sort(estados2);

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter(myContext, R.layout.spinner_item,estados2);
            dataAdapter2.setDropDownViewResource(R.layout.spinner_item);

            NothingSelectedSpinnerAdapter adapter3 = new NothingSelectedSpinnerAdapter(dataAdapter2, R.layout.contact_spinner_row_nothing_selected, myContext);
            adapter3.setPrompt("Estado:");

            Spinner_homedepot.setAdapter(adapter3);
            //Spinner_estados.setAdapter(dataAdapter1);

        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }


        Spinner_homedepot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {

                if (parentView.getSelectedItem() !=null) {
                    String cur_estado = parentView.getItemAtPosition(position).toString();
                    Log.d(TAG, cur_estado);
                    carga_homedepot(cur_estado);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });




        ///////------- home depot


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

    private void carga_distribuidores(String cur_estado)
    {

        _distribuidores.clear();

        String jsonDistribuidores = Common.loadJSONFromAsset(myContext,"distribuidores.json");
        JSONObject obj_distribuidores;
        JSONObject distribuidor;

        try {

            obj_distribuidores = new JSONObject(jsonDistribuidores);
            JSONArray res = obj_distribuidores.getJSONArray("distribuidores");

            for (int i = 0; i < res.length(); i++) {
                distribuidor = res.getJSONObject(i).getJSONObject("distribuidor");
                int id = distribuidor.getInt("id");
                String name = distribuidor.getString("name");
                //String clave = distribuidor.getString("clave");
                String comercial_name = distribuidor.getString("comercial_name");
                String estado = distribuidor.getString("state");
                String ciudad = distribuidor.getString("city");
                String cp = distribuidor.getString("cp");
                String direccion = distribuidor.getString("address");
                String numInt = distribuidor.getString("interior");
                String numExt = distribuidor.getString("exterior");
                String colonia = distribuidor.getString("colonia");
                String phone1 = distribuidor.getString("phone1");
                String phone2 = distribuidor.getString("phone2");
                String lat = distribuidor.getString("lat");
                String lng = distribuidor.getString("lng");


                Distribuidor dist1 = new Distribuidor();
                dist1.set_id(id);
                dist1.set_comercial_name(comercial_name);
                dist1.set_name(name);
                dist1.set_ciudad(ciudad);
                dist1.set_estado(estado);
                dist1.set_colonia(colonia);
                dist1.set_direccion(direccion + " #" + numExt + " " + numInt);
                dist1.set_cp(cp);
                dist1.set_telefono1(phone1);
                dist1.set_latitud(lat);
                dist1.set_longitud(lng);


                if (phone2.length()>1)
                {
                    dist1.set_telefono2("," + phone2);
                }

                if (estado.equals(cur_estado)) {
                    _distribuidores.add(dist1);
                }


            }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }



        _adapter = new DistribuidoresAdapter(getActivity(), _distribuidores, new IViewHolderClick() {
            @Override
            public void onClick(int position) {

                Distribuidor curDist = _distribuidores.get(position);
                //Log.d(TAG,curDist.get_name());
                Intent intent = new Intent();
                intent.setClass(myContext, MapsActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("distribuidor", curDist);
                intent.putExtras(bundle);

                //finish();
                startActivity(intent);


            }
        });


        _linearLayoutManager = new LinearLayoutManager( getActivity() );

        _recyclerview.setHasFixedSize( true );
        _recyclerview.setAdapter( _adapter );
        _recyclerview.setLayoutManager( _linearLayoutManager );
        _recyclerview.addItemDecoration( new SimpleDividerItemDecoration( getActivity() ) );




    }

    private void carga_homedepot(String cur_estado)
    {

        _distribuidores2.clear();

        String jsonDistribuidores = Common.loadJSONFromAsset(myContext,"homedepot.json");
        JSONObject obj_distribuidores;
        JSONObject distribuidor;

        try {

            obj_distribuidores = new JSONObject(jsonDistribuidores);
            JSONArray res = obj_distribuidores.getJSONArray("distribuidores");

            for (int i = 0; i < res.length(); i++) {
                distribuidor = res.getJSONObject(i).getJSONObject("distribuidor");
                int id = distribuidor.getInt("id");
                String name = distribuidor.getString("name");
               // String clave = distribuidor.getString("clave");
                String comercial_name = distribuidor.getString("comercial_name");
                String estado = distribuidor.getString("state");
                String ciudad = distribuidor.getString("city");
                String cp = distribuidor.getString("cp");
                String direccion = distribuidor.getString("address");
               // String numInt = distribuidor.getString("interior");
               // String numExt = distribuidor.getString("exterior");
                String colonia = distribuidor.getString("colonia");
                String phone1 = distribuidor.getString("phone1");
                String phone2 = distribuidor.getString("phone2");
                String lat = distribuidor.getString("lat");
                String lng = distribuidor.getString("lng");


                Distribuidor dist1 = new Distribuidor();
                dist1.set_id(id);
                dist1.set_comercial_name(comercial_name);
                dist1.set_name(name);
                dist1.set_ciudad(ciudad);
                dist1.set_estado(estado);
                dist1.set_colonia(colonia);
                dist1.set_direccion(direccion);
                dist1.set_cp(cp);
                dist1.set_telefono1(phone1);
                dist1.set_latitud(lat);
                dist1.set_longitud(lng);


                if (phone2.length()>1)
                {
                    dist1.set_telefono2("," + phone2);
                }

                if (estado.equals(cur_estado)) {
                    _distribuidores2.add(dist1);
                }


            }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }




        _adapter2 = new DistribuidoresAdapter(getActivity(), _distribuidores2, new IViewHolderClick() {
            @Override
            public void onClick(int position) {

                Distribuidor curDist = _distribuidores2.get(position);
                //Log.d(TAG,curDist.get_name());
                Intent intent = new Intent();
                intent.setClass(myContext, MapsActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("distribuidor", curDist);
                intent.putExtras(bundle);

                //finish();
                startActivity(intent);


            }
        });


        _linearLayoutManager2 = new LinearLayoutManager( getActivity() );

        _recyclerview2.setHasFixedSize( true );
        _recyclerview2.setAdapter( _adapter2 );
        _recyclerview2.setLayoutManager( _linearLayoutManager2 );
        _recyclerview2.addItemDecoration( new SimpleDividerItemDecoration( getActivity() ) );




    }


}
