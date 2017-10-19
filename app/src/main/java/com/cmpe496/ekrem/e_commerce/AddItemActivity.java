package com.cmpe496.ekrem.e_commerce;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ekrem on 09/05/2017.
 */

public class AddItemActivity extends AppCompatActivity {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_year)
    EditText etYear;
    @Bind(R.id.et_length)
    EditText etLength;
    @Bind(R.id.et_location)
    EditText etLocation;
    @Bind(R.id.et_material)
    EditText etMaterial;
    @Bind(R.id.et_price)
    EditText etPrice;
    @Bind(R.id.btn_add_item)
    Button btnAddItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);

        final FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String year = etYear.getText().toString();
                String length = etLength.getText().toString();
                String location = etLocation.getText().toString();
                String material = etMaterial.getText().toString();
                String price = etPrice.getText().toString();

                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put(FeedReaderContract.ItemEntry.COLUMN_NAME, name);
                values.put(FeedReaderContract.ItemEntry.COLUMN_YEAR, year);
                values.put(FeedReaderContract.ItemEntry.COLUMN_LENGTH, length);
                values.put(FeedReaderContract.ItemEntry.COLUMN_LOCATION, location);
                values.put(FeedReaderContract.ItemEntry.COLUMN_MATERIAL, material);
                values.put(FeedReaderContract.ItemEntry.COLUMN_PRICE, price);

                long newRowID = db.insert(FeedReaderContract.ItemEntry.TABLE_NAME, null, values);

                Toast toast = Toast.makeText(getApplicationContext(), "The new item id is " +newRowID, Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        });

    }

}
