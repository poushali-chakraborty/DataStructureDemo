package com.example.hp.datastructuredemo;

/**
 * Created by hp on 07-11-2016.
 */

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

public class LinklistWith5Node extends Fragment {
  TextView show;
  String s,a;

  private Datastructures datastructures;
  public LinklistWith5Node()
  {

  }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       Bundle bundle=getArguments();
      final myLinkList mylist=(myLinkList)bundle.getSerializable("mylist");
        final View rootview= inflater.inflate(R.layout.linklist_with_5_node_1, container, false);
      show= (TextView) rootview.findViewById(R.id.show);
      final Button b1= (Button) rootview.findViewById(R.id.bu1);
      b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // do something


           s= b1.getText().toString();
           a=mylist.getNode(s);
          show.setText(a);
        }
      });

      final Button b2= (Button) rootview.findViewById(R.id.bu2);
      b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // do something


           s= b2.getText().toString();
           a=mylist.getNode(s);
          show.setText(a);
        }
      });

      final Button b3= (Button) rootview.findViewById(R.id.bu3);
      b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // do something


           s= b3.getText().toString();
           a=mylist.getNode(s);
          show.setText(a);
        }
      });
      final Button b4= (Button) rootview.findViewById(R.id.bu4);
      b4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // do something


           s= b4.getText().toString();
           a=mylist.getNode(s);
          show.setText(a);
        }
      });
      final Button b5= (Button) rootview.findViewById(R.id.bu5);
      b5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // do something


           s= " ";
           a=mylist.getNode(s);
          show.setText(a);
        }
      });
      return rootview;
    }



}
