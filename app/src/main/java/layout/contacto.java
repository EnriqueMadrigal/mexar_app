package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.activity.MapsActivity2;
import com.datalabor.soporte.mexar.models.Distribuidor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link contacto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link contacto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contacto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final  String TAG = "contacto";
    private Button enviarMensaje;
    private Button llamar;

    private Button verMapa1;
    private Button verMapa2;
    private Button verMapa3;

    private FragmentActivity myContext;

    private OnFragmentInteractionListener mListener;

    public contacto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contacto.
     */
    // TODO: Rename and change types and number of parameters
    public static contacto newInstance(String param1, String param2) {
        contacto fragment = new contacto();
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
        _view = inflater.inflate( R.layout.fragment_contacto, container, false );

        enviarMensaje = (Button) _view.findViewById(R.id.Contacto_sendMessage);
        llamar = (Button) _view.findViewById(R.id.Contacto_Llamar);

        verMapa1 = (Button) _view.findViewById(R.id.vermapa1);
        verMapa2 = (Button) _view.findViewById(R.id.vermapa2);
        verMapa3 = (Button) _view.findViewById(R.id.vermapa3);

        enviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"ventas.mexar@conquestind.com.mx"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Contacto");
                i.putExtra(Intent.EXTRA_TEXT   , "Información..");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(myContext, "No hay cliente de correo.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri = "tel:3336563637";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);

            }
        });



        verMapa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Distribuidor curDist = new Distribuidor();
                curDist.set_comercial_name("Av. Camino a Bosque de San Isidro 2300");
                curDist.set_latitud("20.759525");
                curDist.set_longitud("-103.38258");

                Intent intent = new Intent();
                intent.setClass(myContext, MapsActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("distribuidor", curDist);
                intent.putExtras(bundle);

                //finish();
                startActivity(intent);



            }
        });

        verMapa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Distribuidor curDist = new Distribuidor();
                curDist.set_comercial_name("11 de Enero de 1861");
                curDist.set_latitud("19.378725");
                curDist.set_longitud("-99.066779");

                Intent intent = new Intent();
                intent.setClass(myContext, MapsActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("distribuidor", curDist);
                intent.putExtras(bundle);

                //finish();
                startActivity(intent);

            }
        });

        verMapa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Distribuidor curDist = new Distribuidor();
                curDist.set_comercial_name("Sebastián Lerdo de Tejada 1222");
                curDist.set_latitud("25.711059");
                curDist.set_longitud("-100.32633");

                Intent intent = new Intent();
                intent.setClass(myContext, MapsActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("distribuidor", curDist);
                intent.putExtras(bundle);

                //finish();
                startActivity(intent);
            }
        });



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
