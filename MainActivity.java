package com.example.anthony.weasleyclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Map<String, Location> locs;
    private Map<String, Integer> colors;
    private Map<String, ClockHand> personHand;
    private String[] people = {"John", "Tony", "Jess", "Craig"};
    private int[] color = {0xffff0000, 0xff0011ff, 0xff000000, 0xff00fb11};
    private FrameLayout clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locs = new HashMap<String, Location>();
        colors = new HashMap<String, Integer>();
        personHand = new HashMap<String, ClockHand>();
        clock = (FrameLayout) findViewById(R.id.Clock);

        int i = 0;
        for (String person : people) {
            ClockHand hand = new ClockHand(this, person);
            personHand.put(person, hand);
            clock.addView(hand);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            clock.updateViewLayout(hand, params);

            locs.put(person, randomLocation());
            colors.put(person, color[i]);
            TextView resultsDisplay=(TextView)findViewById(R.id.resultsDisplay);
            resultsDisplay.setText("The people are located at " + locs);
            i++;

            Log.i("Clock", "map is at " + locs + ".");
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            public void run() {
                Set<ClockHand> hands = new HashSet<ClockHand>();
                Random r = new Random();
                for (String person : people) {
                    if(r.nextInt(20)==0){
                        hands.add(personHand.get(person));
                        locs.put(person, randomLocation());


                        Log.i("Clock", "map is at " + locs + ".");

                    }

                }
                for (ClockHand hand : hands){
                    hand.postInvalidate();
                    }

                }
            };
        timer.schedule(task, 0, 1000 * 1 * 1);
        //TextView resultsDisplay=(TextView)findViewById(R.id.resultsDisplay);
        //resultsDisplay.setText("The people are located at " + locs);

        }

    private Location randomLocation() {

        Random r = new Random();
        switch(r.nextInt(9)){
            case 1:
                return Location.home;
            case 2:
                return Location.school;
            case 3:
                return Location.travelling;
            case 4:
                return Location.dentist;
            case 5:
                return Location.quidditch;
            case 6:
                return Location.holiday;
            case 7:
                return Location.work;
            case 8:
                return Location.prison;
            case 0:
                return Location.forest;
            default:
                return Location.start;
        }
    }


    public int getPersonColor(String person) {
        return colors.get (person);
    }

    public float getPersonLocation(String person) {
        Location loc = locs.get(person);

        if (loc == null){
            return 0;
        } else {
            return loc.getPosition();
        }

    }

    enum Location {
        home, school, lost, travelling, dentist, work, holiday,
        quidditch, prison, mortalPeril, forest, start;

        public int getPosition() {
            switch (this) {
                case home:
                    return 60;
                case school:
                    return 30;
                case lost:
                    return 0;
                case travelling:
                    return 330;
                case dentist:
                    return 300;
                case work:
                    return 275;
                case holiday:
                    return 240;
                case quidditch:
                    return 210;
                case prison:
                    return 185;
                case mortalPeril:
                    return 150;
                case forest:
                    return 120;
                case start:
                    return 95;
                default:
                    return 75;
            }
        }
    }
}
