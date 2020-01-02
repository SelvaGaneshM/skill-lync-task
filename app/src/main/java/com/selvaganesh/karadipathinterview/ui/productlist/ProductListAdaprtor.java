package com.selvaganesh.karadipathinterview.ui.productlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selvaganesh.karadipathinterview.R;
import com.selvaganesh.karadipathinterview.response.productlist.SearchListItem;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdaprtor extends RecyclerView.Adapter<ProductListAdaprtor.MyViewHolder> {

    private Context context;
    private List<SearchListItem> productList = new ArrayList<>();

    public ProductListAdaprtor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.with(context)
                .load(productList.get(position).getThumbImg())
                .placeholder(R.drawable.ic_placeholde)
                .error(R.drawable.ic_placeholde)
                .into(holder.imgproduct);
        holder.txtProductName.setText(productList.get(position).getProductTitle());
        holder.txtPrice.setText("₹ "+productList.get(position).getFinalPrice());
        if(productList.get(position).getStockStatus().equals("outofstock")){
            holder.txtStock.setText("Out Of Stock");
            holder.txtStock.setTextColor(context.getResources().getColor(R.color.red));
        }else{
            holder.txtStock.setText("In Stock");
            holder.txtStock.setTextColor(context.getResources().getColor(R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setData(List<SearchListItem> productList) {
        if (productList == null || productList.size() == 0) {
            return;
        }
        this.productList.clear();
        this.productList.addAll(productList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_product)
        ImageView imgproduct;
        @BindView(R.id.txt_product_name)
        TextView txtProductName;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.txt_stock)
        TextView txtStock;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
