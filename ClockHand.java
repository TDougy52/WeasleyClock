package com.example.anthony.weasleyclock;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Anthony on 5/3/2016.
 */
public class ClockHand extends View{

    public ClockHand(MainActivity context, AttributeSet attrs, String person) {
        super(context, attrs);
        activity=context;
        personDisplayed=person;
    }
    public ClockHand(MainActivity context, String person) {
        super(context);
        activity=context;
        personDisplayed=person;
    }
    protected void onDraw(Canvas canvas){
        float h=getHeight();
        float w=getWidth();
        float length=h*0.25f;
        float thick=30;
        double radians=(activity.getPersonLocation(personDisplayed) / 360) * 2 * Math.PI;
        //float cos=(float)Math.cos(radians);
        //float sin=(float)Math.sin(radians);



        //hand at 12
            //float left=(w/2-thick/2);
            //float right=(w/2+thick/2);
            //float top=h/2-length;
            //float bottom=h/2;
        //hand at 1(home)
        float cx=w/2;
        float cy=h/2;
        float dx=(float)Math.cos(radians)*thick/2;
        float dy=(float)Math.sin(radians)*thick/2;
        float ex=w/2+length*(float)Math.cos(radians);
        float ey=h/2-length*(float)Math.sin(radians);
        //hand at 3
            //float left=(w/2);
            //float right=(w/2+length);
            //float top=h/2-thick/2;
            //float bottom=h/2+thick/2;
        //hand at 6
            //float left=(w/2-thick/2);
            //float right=(w/2+thick/2);
            //float top=h/2;
            //float bottom=h/2+length;
        //hand at 9
            //float left=w/2-length;
            //float right=(w/2);
            //float top=(h/2-thick/2);
            //float bottom=h/2+thick/2;



        Paint p= new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(activity.getPersonColor(personDisplayed));

        //canvas.drawLine(w/2, h/2, w/2+(float)Math.cos(radians)*length, h/2-(float)Math.sin(radians)*length, p);
        //canvas.drawRect(left, top, right, bottom, p);
        Path path=new Path();
        path.moveTo(cx - dx, cy - dy);
        path.lineTo(ex-dx, ey-dy);
        path.lineTo(ex+dx, ey+dy);
        path.lineTo(cx+dx, cy+dy);
        path.close();
        canvas.drawPath(path,p);

        //Log.i("Hand","Drawing Rect at "+left+","+right+","+top+","+bottom+".");
    }
    private MainActivity activity;
    private String personDisplayed;
}
