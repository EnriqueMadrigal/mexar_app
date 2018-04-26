package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link cedulas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link cedulas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cedulas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private FragmentActivity myContext;

    private Button cedula40;
    private Button cedula80;
    private Button conduit;
    private Button sinpresion;

    private Button sanitario;

    public cedulas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cedulas.
     */
    // TODO: Rename and change types and number of parameters
    public static cedulas newInstance(String param1, String param2) {
        cedulas fragment = new cedulas();
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
        _view = inflater.inflate( R.layout.fragment_cedulas, container, false );
        cedula40 = (Button) _view.findViewById(R.id.cedula40);
        cedula80 = (Button) _view.findViewById(R.id.cedula80);
        conduit = (Button) _view.findViewById(R.id.cedula_conduit);
        sinpresion = (Button) _view.findViewById(R.id.cedula_sinpresion);

        sanitario = (Button) _view.findViewById(R.id.cedula_normasanitaria);


        cedula40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora _calculadora = calculadora.newInstance(1);
               Common.SetPage(10);
               myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, _calculadora, "Calculadora" ).commit();
         }
        });

        cedula80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora _calculadora = calculadora.newInstance(2);
                Common.SetPage(10);
                myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, _calculadora, "Calculadora" ).commit();
            }
        });

        conduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora _calculadora = calculadora.newInstance(3);
                Common.SetPage(10);
                myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, _calculadora, "Calculadora" ).commit();
            }
        });

        sinpresion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora _calculadora = calculadora.newInstance(4);
                Common.SetPage(10);
                myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, _calculadora, "Calculadora" ).commit();
            }
        });


        sanitario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora _calculadora = calculadora.newInstance(5);
                Common.SetPage(10);
                myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, _calculadora, "Calculadora" ).commit();
            }
        });


        return _view;
        //return inflater.inflate(R.layout.fragment_cedulas, container, false);
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
