package com.cmpe496.ekrem.e_commerce;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ekrem on 06/03/2017.
 */

public class CheckoutActivity extends AppCompatActivity {

    @Bind(R.id.iv_item_image)
    ImageView ivItemImage;
    @Bind(R.id.tv_item_name)
    TextView tvItemName;
    @Bind(R.id.tv_year)
    TextView tvBrandName;
    @Bind(R.id.tv_item_price)
    TextView tvItemPrice;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.et_purchaser_name)
    EditText etPurchaserName;
    @Bind(R.id.et_purchaser_address)
    EditText etPurchaserAddress;
    @Bind(R.id.et_purchaser_phone)
    EditText etPurchaserPhone;
    @Bind(R.id.btn_complete_purchase)
    Button btnCompletePurchase;
    @Bind(R.id.sp_quantity)
    Spinner spQuantity;

    private final String[] quantity = {"0", "1", "2", "3", "4", "5"};
    @Bind(R.id.ib_visa)
    ImageView ibVisa;
    @Bind(R.id.ib_master)
    ImageView ibMaster;
    @Bind(R.id.ib_ax)
    ImageView ibAx;
    @Bind(R.id.ib_discover)
    ImageView ibDiscover;
    @Bind(R.id.et_card_number)
    EditText etCardNumber;
    @Bind(R.id.et_expiry_date)
    EditText etExpiryDate;
    @Bind(R.id.et_CVC)
    EditText etCVC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);

        final Item item = (Item) getIntent().getSerializableExtra("clickedItem");

        tvItemName.setText(item.getName());
        tvBrandName.setText(item.getYear());
        String price = "" + item.getPrice() + "$";
        tvItemPrice.setText(price);
        ivItemImage.setImageResource(item.getPhoto());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, quantity);
        spQuantity.setAdapter(adapter);

        tvTotalPrice.setText("0$");

        spQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selected = Integer.parseInt(parent.getItemAtPosition(position).toString());
                double DtotalPrice = selected * item.getPrice();
                String totalPrice = "" + DtotalPrice + "$";
                tvTotalPrice.setText(totalPrice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCompletePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);

                builder.setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        if (spQuantity.getSelectedItem().equals("0")
                                || etPurchaserName.getText().toString().length() < 1
                                || etPurchaserAddress.getText().toString().length() < 1
                                || etPurchaserPhone.getText().toString().length() < 10
                                || etCardNumber.getText().toString().length() != 16
                                || etExpiryDate.getText().toString().length() < 4
                                || etCVC.getText().toString().length() != 3) {
                            CharSequence text = "Purchase Failed!";
                            Toast fail = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                            fail.show();
                        } else {
                            startActivity(intent);
                            CharSequence text = "Item(s) successfully purchased!";
                            Toast success = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                            success.show();
                        }


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CharSequence text = "Purchase Failed!";
                        Toast fail = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                        fail.show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.setTitle("Checkout");
                dialog.setMessage("Do you want to purchase " + spQuantity.getSelectedItem() + " " + item.getName() + " for " + tvTotalPrice.getText() + " ?");

                dialog.show();

            }
        });

    }
}
