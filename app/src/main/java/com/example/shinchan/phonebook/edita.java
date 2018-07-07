package com.example.shinchan.phonebook;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class edita extends AppCompatActivity {
    TextToSpeech textToSpeech;
    EditText ed1,ed2,ed3,ed4;
    Button b1,b2;
    ImageView im;
    String []str;
    int result;
    String Text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita);
        Intent in=getIntent();
        final String name=in.getStringExtra("name");
        str=name.split("--");
        ed1=(EditText)findViewById(R.id.ae1);
        ed2=(EditText)findViewById(R.id.ae2);
        ed3=(EditText)findViewById(R.id.ae3);
        ed4=(EditText)findViewById(R.id.ae4);
        b1=(Button)findViewById(R.id.ad1);
        b2=(Button)findViewById(R.id.rd1);
        im=(ImageView)findViewById(R.id.pi1);

        ed1.setText(str[0]);
        ed2.setText(str[1]);
        ed3.setText(str[2]);
        ed4.setText(str[3]);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");
                startActivityForResult(it,1);
            }
        });
        textToSpeech=new TextToSpeech(edita.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==textToSpeech.SUCCESS)
                {
                    result=textToSpeech.setLanguage(Locale.US);

                }
                else
                {
                    Toast.makeText(edita.this, "Device does not support this Feature", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sd=openOrCreateDatabase("Mydata1",MODE_PRIVATE,null);
                sd.execSQL("create table if not exists Phonebook1(name varchar,phone varchar,comp varchar,work varchar)");
                sd.execSQL("update Phonebook1 set phone='"+ed2.getText().toString()+"',name='"+ed1.getText().toString()+"',comp='"+ed3.getText().toString()+"',work='"+ed4.getText().toString()+"' where name='"+str[0]+"'");
                sd.close();
                Toast.makeText(edita.this, "Data updated", Toast.LENGTH_SHORT).show();
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(edita.this, "Update Record cancel", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
