package com.example.hp.datastructuredemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int sycbtnenter=-1;
    private int size,i;
    private int nodes[];
    Datastructures datastructures;
    myLinkList mylist;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String name;
    private OnFragmentInteractionListener mListener;

    public CreationFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreationFragment newInstance(String param1, String param2) {
        CreationFragment fragment = new CreationFragment();
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


        final View rootView1 = inflater.inflate(R.layout.fragment_creation, container, false);
        Button button = (Button) rootView1.findViewById(R.id.entr);
        final TextView textView=(TextView) rootView1.findViewById(R.id.t1);
        final EditText editText=(EditText) rootView1.findViewById(R.id.ed1);
         SharedPreferences mPrefs =this.getActivity().getPreferences(Context.MODE_PRIVATE);
       final SharedPreferences.Editor prefsEditor = mPrefs.edit();
       final Gson gson = new Gson();
        try {

            String json = mPrefs.getString("datastructures", "");

             datastructures = gson.fromJson(json, Datastructures.class);
        }catch (Exception e){}

        if(datastructures==null)
        {
            datastructures= new Datastructures();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(sycbtnenter==-1)
               {
                   textView.setText("name the linklist:");
                   sycbtnenter = 0;
               }
              else {  // do something
                   if (sycbtnenter == 0) {
                       name= editText.getText().toString();
                       textView.setText("how many node u want:");
                       sycbtnenter = 1;
                   } else {

                       if (sycbtnenter == 1) {
                           size = 5;
                           textView.setText("enter value for 1st node :");
                           mylist = new myLinkList(size,name );

                           sycbtnenter = 2;
                           i = 1;
                       } else {
                           if (sycbtnenter == 2) {
                               if (i < size) {
                                   String s = editText.getText().toString();
                                   s.trim();
                                   mylist.addNodeatEnd(s);
                                   String ss = Integer.toString(i + 1);
                                   textView.setText("enter value for " + ss + " node: ");
                                   i++;
                               } else {
                                   if (i == size) {
                                       String s = editText.getText().toString();
                                       s.trim();
                                       mylist.addNodeatEnd(s);
                                       datastructures.setDatastructures(mylist);
                                       try {


                                           String json = gson.toJson(datastructures);
                                           prefsEditor.putString("datastructures", json);
                                           prefsEditor.commit();
                                       } catch (Exception e){}

                                       textView.setText("link list created");
                                       FragmentManager fm = getFragmentManager();
                                       FragmentTransaction ft = fm.beginTransaction();
                                       Fragment fragment = new LinklistWith5Node();
                                       Bundle bundle = new Bundle();
                                       if (mylist != null) {
                                           bundle.putSerializable("mylist", mylist);
                                       }
                                       fragment.setArguments(bundle);
                                       ft.replace(R.id.link_container, fragment);
                                       sycbtnenter = 0;
                                       ft.addToBackStack(null);
                                       ft.commit();
                                   }
                               }
                           }

                       }
                   }

               }   //
            }
        });





        return rootView1;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
