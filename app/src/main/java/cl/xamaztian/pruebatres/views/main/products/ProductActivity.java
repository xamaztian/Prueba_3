package cl.xamaztian.pruebatres.views.main.products;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import cl.xamaztian.pruebatres.R;
import cl.xamaztian.pruebatres.data.CurrentUser;
import cl.xamaztian.pruebatres.data.Nodes;
import cl.xamaztian.pruebatres.models.Product;

public class ProductActivity extends AppCompatActivity {

    private String key;
    private TextView quantityProductTv;
    private EditText nameProductTv;
    private EditText descriptionProductTv;
    private Product product;

    @Override
    protected void onPause() {
        super.onPause();

        if(key == null)
            key = new Nodes().userProducts(new CurrentUser().getCurrentUser().getUid()).push().getKey();

        Product product = new Product();
        product.setKey(key);
        product.setDescription(descriptionProductTv.getText().toString());
        product.setName(nameProductTv.getText().toString());
        product.setQuantity(Integer.parseInt(quantityProductTv.getText().toString().replace("Cantidad : ", "")));
        new Nodes().userProduct(new CurrentUser().getCurrentUser().getUid(), key).setValue(product);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        key = getIntent().getStringExtra("productKey");

        quantityProductTv = findViewById(R.id.quantityProductTv);
        nameProductTv = findViewById(R.id.nameProductTv);
        descriptionProductTv = findViewById(R.id.descriptionProductTv);

        if (key == null) {
            quantityProductTv.setText("Cantidad : 0");
            product = new Product();
            product.setQuantity(0);
        }
        else {
            new Nodes().userProduct(new CurrentUser().getCurrentUser().getUid(), key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        product = dataSnapshot.getValue(Product.class);
                        quantityProductTv.setText("Cantidad : " + String.valueOf(product.getQuantity()));
                        nameProductTv.setText(product.getName());
                        descriptionProductTv.setText(product.getDescription());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        ImageButton buttonAdd = findViewById(R.id.addBtn);
        ImageButton buttonRemove = findViewById(R.id.removeBtn);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setQuantity(product.getQuantity() + 1);
                quantityProductTv.setText("Cantidad : " + String.valueOf(product.getQuantity()));
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setQuantity(product.getQuantity() - 1);
                quantityProductTv.setText("Cantidad : " + String.valueOf(product.getQuantity()));
            }
        });
    }


}
