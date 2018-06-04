package cl.xamaztian.pruebatres.views.main.products;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.xamaztian.pruebatres.R;
import cl.xamaztian.pruebatres.adapters.ProductListener;
import cl.xamaztian.pruebatres.adapters.ProductsAdapter;
import cl.xamaztian.pruebatres.models.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements ProductListener{



    private RecyclerView recyclerView;
    private ProductsAdapter adapter;


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.productsRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        adapter = new ProductsAdapter(getActivity(), this);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void clicked(Product product) {
        Intent intent = new Intent(getActivity(), ProductActivity.class);
        intent.putExtra("productKey", product.getKey());
        startActivity(intent);
    }
}
