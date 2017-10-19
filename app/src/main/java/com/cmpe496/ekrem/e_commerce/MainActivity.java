package com.cmpe496.ekrem.e_commerce;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @Bind(R.id.tv_item)
    TextView tvItem;
    @Bind(R.id.lv_item)
    ListView lvItem;

    List<Item> itemList = new ArrayList<Item>();
    @Bind(R.id.btn_add_item)
    Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvItem.setText("Welcome to Boat Store");



        itemList.add(new Item(new Long(0), R.drawable.a, "Beneteau 321", "2000", "32'", "Indianflown, FL", "Fiberglass", 63900));
        itemList.add(new Item(new Long(4), R.drawable.e, "Bavaria 37 Cruiser", "2006", "37'", "Croatia", "Fiberglass", 57074));
        itemList.add(new Item(new Long(1), R.drawable.b, "Azuree 46", "2017", "46'", "Istanbul, Turkey", "Fiberglass", 475000));
        itemList.add(new Item(new Long(2), R.drawable.c, "Beneteau 34", "2012", "34'", "Sardis, MS", "Fiberglass", 129995));
        itemList.add(new Item(new Long(3), R.drawable.d, "Grand Soleil 54", "2008", "56'", "Toscana, Italy", "Fiberglass", 428883));



        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                FeedReaderContract.ItemEntry.COLUMN_NAME,
                FeedReaderContract.ItemEntry.COLUMN_YEAR,
                FeedReaderContract.ItemEntry.COLUMN_LENGTH,
                FeedReaderContract.ItemEntry.COLUMN_LOCATION,
                FeedReaderContract.ItemEntry.COLUMN_MATERIAL,
                FeedReaderContract.ItemEntry.COLUMN_PRICE,

        };

        Cursor cursor = db.query(
                FeedReaderContract.ItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){

            String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_NAME));
            String year = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_YEAR));
            String length = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_LENGTH));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_LOCATION));
            String material = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_MATERIAL));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_PRICE));

            double dPrice = Double.valueOf(price).doubleValue();

            Item newItem = new Item(new Long(-1), name, year, length, location, material, dPrice);
            itemList.add(newItem);

            while (cursor.moveToNext()){
                name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_NAME));
                year = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_YEAR));
                length = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_LENGTH));
                location = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_LOCATION));
                material = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_MATERIAL));
                price = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.ItemEntry.COLUMN_PRICE));

                dPrice = Double.valueOf(price).doubleValue();

                newItem = new Item(new Long(-1), name, year, length, location, material, dPrice);
                itemList.add(newItem);
            }

        }







        ItemAdapter adapter = new ItemAdapter(MainActivity.this, (ArrayList<Item>) itemList);
        lvItem.setAdapter(adapter);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item clickedItem = (Item) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                intent.putExtra("clickedItem", clickedItem);
                startActivity(intent);
            }
        });
    }

}
