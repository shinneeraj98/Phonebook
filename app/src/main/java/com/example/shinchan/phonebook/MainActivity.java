package com.example.shinchan.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = this.getMenuInflater();
        mi.inflate(R.menu.menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi1:

            {

                Intent in = new Intent(MainActivity.this, Add.class);
                this.startActivity(in);

                break;
            }
            case R.id.mi2:

            {
                Intent in = new Intent(MainActivity.this,search.class);
                this.startActivity(in);
break;
            }

            case R.id.mi3:

            {
                Intent in = new Intent(MainActivity.this,seeall.class);
                this.startActivity(in);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
