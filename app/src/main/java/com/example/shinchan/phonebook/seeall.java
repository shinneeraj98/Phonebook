package com.example.shinchan.phonebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class seeall extends AppCompatActivity {

    LinearLayout l1;
    int m,n,o;
    Random rand;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeall);

        l1=(LinearLayout)findViewById(R.id.lll1);
        SQLiteDatabase sd=openOrCreateDatabase("Mydata1",MODE_PRIVATE,null);
        sd.execSQL("create table if not exists Phonebook1(name varchar,phone varchar,comp varchar,work varchar)");
        Cursor cr=sd.rawQuery("select * from Phonebook1",null);
        l1.removeAllViews();
        if(cr.moveToFirst())
        {
            do {
                rand= new Random();
                m = rand.nextInt(254);
                n=rand.nextInt(254);
                o=rand.nextInt(254);
                m++;
                n++;
                o++;
                tv=new TextView(seeall.this);
                tv.setText(cr.getString(0)+"--"+cr.getString(1)+"--"+cr.getString(2)+"--"+cr.getString(3));
                tv.setTextSize(20);
                l1.addView(tv);
                tv.setBackgroundColor(Color.rgb(m,n,o));
                registerForContextMenu(tv);
            }while (cr.moveToNext());
        }
        else
        {
            TextView tv=new TextView(seeall.this);
            tv.setText("No record");
            l1.addView(tv);
        }

    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        tv=(TextView)v;
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menu1,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi4:

            {
                String name=tv.getText().toString();
                Intent in = new Intent(seeall.this,edita.class);
                in.putExtra("name",name);
                this.startActivity(in);

                break;
            }
            case R.id.mi5:

            {
                String name=tv.getText().toString();
                String []str=name.split("--");
                SQLiteDatabase sd=openOrCreateDatabase("Mydata1",MODE_PRIVATE,null);
                sd.execSQL("create table if not exists Phonebook1(name varchar,phone varchar,comp varchar,work varchar)");
                sd.execSQL("delete from Phonebook1 where name='"+str[0]+"'");
                sd.close();
                tv.setTextColor(Color.rgb(255,255,255));
                tv.setTextColor(Color.rgb(255,255,255));
                Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();
                tv.setText("");
                tv.setTextColor(Color.rgb(255,255,255));
                break;
            }

            case R.id.mi6:

            {
                Intent in = new Intent(seeall.this,share.class);
                this.startActivity(in);
                break;
            }
        }
        return super.onContextItemSelected(item);

    }


    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
