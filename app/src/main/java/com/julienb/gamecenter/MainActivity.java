package com.julienb.gamecenter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.julienb.gamecenter.Morpion.Morpion;

public class MainActivity extends AppCompatActivity  {
static int frag;
    DisplayMetrics metrics = new DisplayMetrics();
    private static final String TAG = "MainActivity";
            /**
             * The {@link android.support.v4.view.PagerAdapter} that will provide
             * fragments for each of the sections. We use a
             * {@link FragmentPagerAdapter} derivative, which will keep every
             * loaded fragment in memory. If this becomes too memory intensive, it
             * may be best to switch to a
             * {@link android.support.v4.app.FragmentStatePagerAdapter}.
             */
            private SectionsPagerAdapter mSectionsPagerAdapter;

            /**
             * The {@link ViewPager} that will host the section contents.
             */
            private ViewPager mViewPager;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                // Create the adapter that will return a fragment for each of the three
                // primary sections of the activity.
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                // Set up the ViewPager with the sections adapter.
                mViewPager = (ViewPager) findViewById(R.id.container);
                mViewPager.setAdapter(mSectionsPagerAdapter);

                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int WidthScreen = metrics.widthPixels;
                int HeigthScreen = metrics.heightPixels;
                Log.d(TAG,"WIDTH SCREEN /4 /2 = "+(WidthScreen/4)/2+"");
                Log.d(TAG,"WIDTH SCREEN /4 = "+WidthScreen/4+"");

            }


            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

            /**
             * A placeholder fragment containing a simple view.
             */
            public static class PlaceholderFragment extends Fragment {
                /**
                 * The fragment argument representing the section number for this
                 * fragment.
                 */
                private static final String ARG_SECTION_NUMBER = "section_number";

                public PlaceholderFragment() {
                }

                /**
                 * Returns a new instance of this fragment for the given section
                 * number.
                 */
                public static PlaceholderFragment newInstance(int sectionNumber) {
                    PlaceholderFragment fragment = new PlaceholderFragment();
                    Bundle args = new Bundle();
                    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                    fragment.setArguments(args);
                    return fragment;
                }

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                         Bundle savedInstanceState) {
                    switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                        case 1:
                             frag = getArguments().getInt(ARG_SECTION_NUMBER);

                            System.out.println("******************* view case 1 : "+frag+"*************************");
                             View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                             rootView.setBackgroundResource(R.drawable.morpion_game_background);
                             TextView textView = (TextView) rootView.findViewById(R.id.frag_label);
                             textView.setText("Morpion");
                            (rootView.findViewById(R.id.frag_layout)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getContext(),Morpion.class);
                                    startActivity(intent);

                                }
                            });
                            return rootView;
                        case 2:
                            frag = getArguments().getInt(ARG_SECTION_NUMBER);
                            System.out.println("******************* view case 2 : "+frag+"*************************");
                            rootView = inflater.inflate(R.layout.fragment_main, container, false);
                            textView = (TextView) rootView.findViewById(R.id.frag_label);
                            textView.setText("jeu 2");
                            (rootView.findViewById(R.id.frag_layout)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d("Test", "2");

                                }
                            });
                            return rootView;
                        case 3:
                            frag = getArguments().getInt(ARG_SECTION_NUMBER);
                            System.out.println("******************* view case 3 : "+frag+"*************************");
                            rootView = inflater.inflate(R.layout.fragment_main, container, false);
                            textView = (TextView) rootView.findViewById(R.id.frag_label);
                            textView.setText("jeu 3");

                            (rootView.findViewById(R.id.frag_layout)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d("Test", "3");

                                }
                            });
                            return rootView;
                    }
                    return null;
                }



            }



            /**
             * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
             * one of the sections/tabs/pages.
             */
            public class SectionsPagerAdapter extends FragmentPagerAdapter {

                public SectionsPagerAdapter(FragmentManager fm) {
                    super(fm);
                }

                @Override
                public Fragment getItem(int position) {
                    // getItem is called to instantiate the fragment for the given page.
                    // Return a PlaceholderFragment (defined as a static inner class below).
                    return PlaceholderFragment.newInstance(position + 1);
                }

                @Override
                public int getCount() {
                    // Show 3 total pages.
                    return 3;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    switch (position) {
                        case 0:
                            return "Morpion";
                        case 1:
                            return "SECTION 2";
                        case 2:
                            return "SECTION 3";
                    }
                    return null;
                }
            }



    }

