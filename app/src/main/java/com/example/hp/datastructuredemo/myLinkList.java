package com.example.hp.datastructuredemo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hp on 08-11-2016.
 */

class node
{
   private String item;
   private int nextad;
    node(String a,int i)
    {
        item=a;
        nextad=i;
    }
    public void setNextad(int add){
        nextad=add;
    }
    public String getItem() {
        return item;
    }

    public int getNextad() {
        return nextad;
    }
}
public class myLinkList implements Serializable {
    List<node> list;
   int size;
    static int i;
    String name;
    myLinkList(int size,String name){
        list= new LinkedList<node>();
        this.size=size;
        this.name=name;
        i=0;

    }

    int getSize()
    {
        return size;

    }

    public String getName() {
        return name;
    }

    void   addNodeatEnd(String in) {

          if (i < size) {
              node n = new node(in, i+1);

              list.add(n);
              i++;
          }

  }
      String getNode(String in)
      {
          if(in!=" ")
          {
              int ad = Integer.parseInt(in);
              for (int j = 0; j < size-1; j++)
              {
                  if (ad == list.get(j).getNextad())
                  {
                      return list.get(j).getItem();
                  }
              }
          }
          else
          {

              return list.get(size-1).getItem();
          }
      return null;

  }



}
