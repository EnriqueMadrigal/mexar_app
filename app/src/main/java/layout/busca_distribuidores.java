package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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

    private final  String TAG = "calculadora";
    private FragmentActivity myContext;

    private Spinner Spinner_estados;
    private ArrayList<String> estados;

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

// Carga Estados


        String jsonDistribuidores = Common.loadJSONFromAsset(myContext,"distribuidores.json");
        JSONObject obj_distribuidores;
        JSONObject distribuidor;
        estados = new ArrayList<>();

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



            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter(myContext, R.layout.spinner_item,estados);
            dataAdapter1.setDropDownViewResource(R.layout.spinner_item);
                Spinner_estados.setAdapter(dataAdapter1);

        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file categories");
            //return null;

        }




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
