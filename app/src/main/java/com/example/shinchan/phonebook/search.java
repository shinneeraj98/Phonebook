package com.example.shinchan.phonebook;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class search extends AppCompatActivity {
LinearLayout l1;
    TextView tv;
    EditText ed1;
    Button b1;
    int m,n,o;
    TextToSpeech textToSpeech;
    int result;
    String Text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        l1=(LinearLayout)findViewById(R.id.ll1);
        b1=(Button)findViewById(R.id.sb1);
        textToSpeech=new TextToSpeech(search.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==textToSpeech.SUCCESS)
                {
                    result=textToSpeech.setLanguage(Locale.US);

                }
                else
                {
                    Toast.makeText(search.this, "Device does not support this Feature", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sd=openOrCreateDatabase("Mydata1",MODE_PRIVATE,null);
                sd.execSQL("create table if not exists Phonebook1(name varchar,phone varchar,comp varchar,work varchar)");
                Cursor cr=sd.rawQuery("select * from Phonebook1 where name = '"+ed1.getText().toString()+"'",null);
                l1.removeAllViews();
                if(cr.moveToFirst())
                {
                    do {
                        Random rand = new Random();
                        m = rand.nextInt(254);
                        n=rand.nextInt(254);
                        o=rand.nextInt(254);
                        m++;
                        n++;
                        o++;
                        TextView tv=new TextView(search.this);
                        tv.setText(cr.getString(0)+"--"+cr.getString(1)+"--"+cr.getString(2)+"--"+cr.getString(3));
                        tv.setTextSize(20);
                        l1.addView(tv);
                        tv.setBackgroundColor(Color.rgb(m,n,o));
                        registerForContextMenu(tv);
                    }while (cr.moveToNext());
                }
                else
                {
                    TextView tv=new TextView(search.this);
                    tv.setText("No record");
                    l1.addView(tv);
                    registerForContextMenu(tv);
                }
            }
        });
        ed1=(EditText)findViewById(R.id.s1);
    }

    @Override
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
                Intent in = new Intent(search.this,edita.class);
                in.putExtra("name",name);
                this.startActivity(in);

                break;
            }
            case R.id.mi7:
            {
                if(result==TextToSpeech.LANG_NOT_SUPPORTED || result==TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(search.this, "Device does not support this Feature", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String Text1=tv.getText().toString();
                    textToSpeech.speak(Text1,TextToSpeech.QUEUE_FLUSH,null);
                }
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
                Intent in = new Intent(search.this,share.class);
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
