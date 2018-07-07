package com.example.shinchan.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add extends AppCompatActivity  {
EditText ed1,ed2,ed3,ed4;
    Button b1,b2;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ed1=(EditText)findViewById(R.id.ae1);
        ed2=(EditText)findViewById(R.id.ae2);
        ed3=(EditText)findViewById(R.id.ae3);
        ed4=(EditText)findViewById(R.id.ae4);
        b1=(Button)findViewById(R.id.ad1);
        b2=(Button)findViewById(R.id.rd1);
        im=(ImageView)findViewById(R.id.pi1);
      im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");
                startActivityForResult(it,1);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sd=openOrCreateDatabase("Mydata1",MODE_PRIVATE,null);
                sd.execSQL("create table if not exists Phonebook1(name varchar,phone varchar,comp varchar,work varchar)");
                sd.execSQL("insert into Phonebook1 values('"+ed1.getText().toString()+"','"+ed2.getText().toString()+"','"+ed3.getText().toString()+"','"+ed4.getText().toString()+"')");
                sd.close();
                Toast.makeText(Add.this, "Data Saved", Toast.LENGTH_SHORT).show();
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                Add.this.finish();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                Toast.makeText(Add.this, "Data cleared", Toast.LENGTH_SHORT).show();
                Add.this.finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1)
            {
                im.setImageURI(data.getData());
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}

