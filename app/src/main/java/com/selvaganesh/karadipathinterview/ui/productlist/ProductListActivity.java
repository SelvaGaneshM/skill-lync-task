package com.selvaganesh.karadipathinterview.ui.productlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selvaganesh.karadipathinterview.R;
import com.selvaganesh.karadipathinterview.base.BaseActivity;
import com.selvaganesh.karadipathinterview.response.productlist.ProductListResposen;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends BaseActivity {

    @BindView(R.id.ryc_product_list)
    RecyclerView rycProductList;

    private ProductListAdaprtor adaprtor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        setUI();
    }

    private void setUI() {

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ProductListResposen productList = (ProductListResposen) args.getSerializable("ARRAYLIST");

        rycProductList.setLayoutManager(new GridLayoutManager(this, 2));
        adaprtor = new ProductListAdaprtor(this);
        rycProductList.setAdapter(adaprtor);
        rycProductList.addItemDecoration(new DividerItemDecoration(rycProductList.getContext(), DividerItemDecoration.VERTICAL));
        rycProductList.addItemDecoration(new DividerItemDecoration(rycProductList.getContext(), DividerItemDecoration.HORIZONTAL));
        adaprtor.setData(productList.getSearchList());
    }
}
