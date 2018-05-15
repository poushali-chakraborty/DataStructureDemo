package com.example.hp.datastructuredemo;

import java.util.ArrayList;

/**
 * Created by hp on 09-11-2016.
 */
public class Datastructures {
  private ArrayList <myLinkList> linkList;
    Datastructures()
    {

        linkList= new ArrayList<myLinkList>();
    }
   public void setDatastructures(myLinkList l )
    {
       linkList.add(l);
    }
  public   myLinkList getDatastructure(int i)
    {
        myLinkList li=linkList.get(i);
        return li;
    }
public ArrayList<myLinkList> getAll()
{
    return linkList;
}

}
