package com.example.myapplication;

import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;
    private Button addItem;
    private EditText editText;
    private Button kazanan_Btn;
    private TextView cikti;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout=findViewById(R.id.kura);
        AnimationDrawable animationDrawable=(AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        listView=(ListView) findViewById(R.id.list);
        addItem=(Button)findViewById(R.id.add_item);

        editText=(EditText)findViewById(R.id.deger_ekle);

        kazanan_Btn=(Button)findViewById(R.id.kazanan_btn);
        cikti=(TextView)findViewById(R.id.ekran_cıktı);


        arrayList=new ArrayList<String>();

        adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().toString().trim().length()==0){
                    editText.setError("Alanı Doldurun.");
                }

                else{
                    editText.setError(null);
                    String count=editText.getText().toString();

                    arrayList.add(count);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }



            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which_item=position;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Silmek İstiyor musunuz:")
                        .setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(which_item);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("HAYIR",null)
                        .show();



                return true;
            }
        });

        kazanan_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean kutu=arrayList.isEmpty();
                if(kutu==true){
                    cikti.setText("kutu boş");
                }
                else {
                    int rando=(int)(Math.random()*adapter.getCount());
                    cikti.setText(arrayList.get(rando));
                    YoYo.with(Techniques.RubberBand).duration(700).repeat(1).playOn(cikti);
                }




            }
        });






    }
}
