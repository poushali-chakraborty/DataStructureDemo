package com.example.hp.datastructuredemo;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import android.content.res.Configuration;
import android.os.Handler;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    static int st=0;
private String[] slide={"img1","img2","img3","img4","img1"};
    class NavItem
    {
        String mTitle;
        String mSubtitle;
        int mIcon;
        public NavItem(String title,String subtitle,int icon)
        {
            mTitle=title;
            mSubtitle=subtitle;
            mIcon=icon;
        }
    }
ImageView img;
Handler handler;
    class DrawerListAdapter extends BaseAdapter
    {
        Context mContext;
        ArrayList<NavItem> mnavItems;
        public DrawerListAdapter(Context context,ArrayList<NavItem> navItems)
        {
            mContext=context;
            mnavItems=navItems;
        }
        @Override
        public int getCount() {
            return mnavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mnavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView==null)
            {
                LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.drawer_item,null);
            }
            else {
                view=convertView;
            }
            TextView titleView=(TextView)view.findViewById(R.id.title);
            TextView subtitleView=(TextView)view.findViewById(R.id.subTitle);
            ImageView iconView=(ImageView)view.findViewById(R.id.icon);
            titleView.setText(mnavItems.get(position).mTitle);
            subtitleView.setText(mnavItems.get(position).mSubtitle);
            iconView.setImageResource(mnavItems.get(position).mIcon);
            return view;
        }
    }
    private static String TAG=MainActivity.class.getSimpleName();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems=new ArrayList<NavItem>();

    class ToDoTask implements Runnable {

        int i = 0;

        @Override
        public void run() {

            try {
                if (i < 5) {

                    int resId = getResources().getIdentifier(slide[i], "drawable", getPackageName());
                    img.setImageResource(resId);
                }
                Thread.sleep(30);
                handler.postDelayed(this, 100);

                i++;

                if (i == 5) {
                    handler.removeCallbacks(this);

                }
            } catch (InterruptedException e) {

            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavItems.add(new NavItem("Home","is a app",R.drawable.ac));
        mNavItems.add(new NavItem("Apple","is a fruit",R.drawable.a));
        mNavItems.add(new NavItem("Ford","is a car",R.drawable.e));
        mNavItems.add(new NavItem("Creation","create a linklist",R.drawable.i));
        mNavItems.add(new NavItem("Traverse","show linklist",R.drawable.e));

        //drawerlayout
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        //populate navigation drawer
        mDrawerPane= (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList=(ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter=new DrawerListAdapter(this,mNavItems);
        mDrawerList.setAdapter(adapter);
        //drawer item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              selectItemFromDrawer(position);
            }


        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View v)
            {
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        img=(ImageView) findViewById(R.id.iView);
        handler= new Handler();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       if(mDrawerToggle.onOptionsItemSelected(item))
       {
           return true;

       }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
       super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfiguration)
    {
        super.onConfigurationChanged(newConfiguration);
        mDrawerToggle.onConfigurationChanged(newConfiguration);
    }





    private void selectItemFromDrawer(int position)
    {


        mDrawerList.setItemChecked(position,true);


        if(mNavItems.get(position).mTitle!="Home") {

            if(mNavItems.get(position).mTitle=="Creation")
            {
                fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                setTitle(mNavItems.get(position).mTitle);


                ft.replace(R.id.container, new CreationFragment());
                st++;
                ft.addToBackStack(null);
                ft.commit();

                //close drawer
                mDrawerLayout.closeDrawer(mDrawerPane);
            }
            else {


                if(mNavItems.get(position).mTitle=="Traverse")
                {
                    fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    setTitle(mNavItems.get(position).mTitle);


                    ft.replace(R.id.container, new TraverseFragment());
                    st++;
                    ft.addToBackStack(null);
                    ft.commit();

                    //close drawer
                    mDrawerLayout.closeDrawer(mDrawerPane);
                }
                else


                {
                    fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    setTitle(mNavItems.get(position).mTitle);


                    ft.replace(R.id.container, new PreferencesFragment());
                    st++;
                    ft.addToBackStack(null);
                    ft.commit();

                    //close drawer
                    mDrawerLayout.closeDrawer(mDrawerPane);
                }
            }
        }
        else
        {
            setTitle(R.string.app_name);
            while(st>0) {
                fm.popBackStack();
                st--;
            }
            mDrawerLayout.closeDrawer(mDrawerPane);
        }
    }
    public void changeText(View v)
    {
        ToDoTask t = new ToDoTask();
        handler.postDelayed(t,100);
    }

}
