package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
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
    private TextView text4;
    private TextView text5;
    private TextView text6;


    private ArrayList<String> presentaciones;
    private ArrayList<String> piezas;

    private OnFragmentInteractionListener mListener;
    private ImageButton calcular;
    public calculadora() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calculadora.
     */
    // TODO: Rename and change types and number of parameters
    public static calculadora newInstance(String param1, String param2) {
        calculadora fragment = new calculadora();
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
        _view = inflater.inflate( R.layout.fragment_calculadora, container, false );

        Spinner_presentacion = (Spinner) _view.findViewById(R.id.calculadora_Presentacion);
        Spinner_pieza = (Spinner) _view.findViewById(R.id.calculadora_Pieza);
        calcular = (ImageButton) _view.findViewById(R.id.calculadora_calcular);

        text1 = (TextView) _view.findViewById(R.id.calculadora_text1);
        text2 = (TextView) _view.findViewById(R.id.calculadora_text2);
        text3 = (TextView) _view.findViewById(R.id.calculadora_text3);
        text4 = (TextView) _view.findViewById(R.id.calculadora_text4);
        text5 = (TextView) _view.findViewById(R.id.calculadora_text5);
        text6 = (TextView) _view.findViewById(R.id.calculadora_text6);



        presentaciones = new ArrayList<>();
        piezas = new ArrayList<>();

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
                int id = presentacion.getInt("id");
                presentaciones.add(name);


            }

            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter(myContext, R.layout.spinner_item,presentaciones);
            dataAdapter1.setDropDownViewResource(R.layout.spinner_item);
            Spinner_presentacion.setAdapter(dataAdapter1);
        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }


        /////


        /// Cargar Piezas
        // Cargar presentaciones

        String jsonPiezas = Common.loadJSONFromAsset(myContext,"piezas.json");
        JSONObject obj_pieza;
        JSONObject pieza;

        ///////////////
        try {

            obj_pieza = new JSONObject(jsonPiezas);
            JSONArray res = obj_pieza.getJSONArray("piezas");

            for (int i = 0; i < res.length(); i++) {
                pieza = res.getJSONObject(i).getJSONObject("pieza");
                String name = pieza.getString("name");
                int id = pieza.getInt("id");
                piezas.add(name);


            }

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter(myContext, R.layout.spinner_item,piezas);
            dataAdapter2.setDropDownViewResource(R.layout.spinner_item);
            Spinner_pieza.setAdapter(dataAdapter2);
        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }


        /////


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
