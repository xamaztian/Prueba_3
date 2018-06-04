package cl.xamaztian.pruebatres.adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import cl.xamaztian.pruebatres.R;
import cl.xamaztian.pruebatres.data.CurrentUser;
import cl.xamaztian.pruebatres.data.Nodes;
import cl.xamaztian.pruebatres.models.Product;
import cl.xamaztian.pruebatres.views.main.products.ProductActivity;

public class ProductsAdapter extends FirebaseRecyclerAdapter<Product, ProductsAdapter.ProductHolder>{
    private ProductListener productListener;

    public ProductsAdapter(LifecycleOwner lifecycleOwner, ProductListener callback) {
        super(new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(new Nodes().userProducts(new CurrentUser().getCurrentUser().getUid()), Product.class)
                .setLifecycleOwner(lifecycleOwner)
                .build());
        productListener = callback;
    }

    @Override
    protected void onBindViewHolder(@NonNull final ProductHolder holder, int position, @NonNull Product model) {
        holder.nameTv.setText(model.getName());
        holder.descriptionTv.setText(model.getDescription());
        holder.quantityTv.setText(String.valueOf(model.getQuantity()) + " Unidades");
        holder.nameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product auxProduct = getItem(holder.getAdapterPosition());
                productListener.clicked(auxProduct);
            }
        });
    }

    @Override
    @NonNull
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new ProductHolder(view);
    }

    public class ProductHolder extends RecyclerView.ViewHolder{

        private TextView nameTv, descriptionTv, quantityTv;

        public ProductHolder(View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nameTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            quantityTv = itemView.findViewById(R.id.quantityTv);
        }
    }
}
