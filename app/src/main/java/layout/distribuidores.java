package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.adapter.DistribuidoresAdapter;
import com.datalabor.soporte.mexar.adapter.IViewHolderClick;
import com.datalabor.soporte.mexar.custom.SimpleDividerItemDecoration;
import com.datalabor.soporte.mexar.models.Distribuidor;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link distribuidores.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link distribuidores#newInstance} factory method to
 * create an instance of this fragment.
 */
public class distribuidores extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DistribuidoresAdapter _adapter;
    private FragmentActivity myContext;
    private RecyclerView _recyclerview;
    private LinearLayoutManager _linearLayoutManager;

    private View _view;

    ArrayList<Distribuidor> _distribuidores;

    private OnFragmentInteractionListener mListener;

    public distribuidores() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment distribuidores.
     */
    // TODO: Rename and change types and number of parameters
    public static distribuidores newInstance(String param1, String param2) {
        distribuidores fragment = new distribuidores();
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

        _view = inflater.inflate( R.layout.fragment_distribuidores, container, false );

        _recyclerview = (RecyclerView) _view.findViewById(R.id.recyclerDistribuidores);

        _distribuidores = new ArrayList<>();

        Distribuidor dist1 = new Distribuidor();
        dist1.set_name("Matriz Guadalajara");
        dist1.set_ciudad("Guadalajara");
        dist1.set_estado("Jalisco");
        dist1.set_colonia("Col. Bosques de San Isidro");
        dist1.set_direccion("Bosques de San Isidro #2300");
        dist1.set_telefono1("Tel: (0133) 3656 3637");
        _distribuidores.add(dist1);

        _adapter = new DistribuidoresAdapter(getActivity(), _distribuidores, new IViewHolderClick() {
            @Override
            public void onClick(int position) {

            }
        });


        _linearLayoutManager = new LinearLayoutManager( getActivity() );

        _recyclerview.setHasFixedSize( true );
        _recyclerview.setAdapter( _adapter );
        _recyclerview.setLayoutManager( _linearLayoutManager );
        _recyclerview.addItemDecoration( new SimpleDividerItemDecoration( getActivity() ) );



        return _view;
        //return inflater.inflate(R.layout.fragment_distribuidores, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
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
