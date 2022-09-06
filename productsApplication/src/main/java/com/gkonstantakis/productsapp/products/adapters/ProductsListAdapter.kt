package com.gkonstantakis.productsapp.products.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gkonstantakis.productsapp.products.R
import com.gkonstantakis.productsapp.products.activities.MainActivity
import com.gkonstantakis.productsapp.products.databinding.ProductListItemBinding
import com.gkonstantakis.productsapp.products.mappers.UiMapper
import com.gkonstantakis.productsapp.products.models.UiProduct
import com.gkonstantakis.productsapp.products.utils.Utils
import com.gkonstantakis.productsapp.products.viewmodels.ProductsViewModel

class ProductsListAdapter(
    var productItems: MutableList<UiProduct>,
    val uiMapper: UiMapper,
    val mainViewModel: ProductsViewModel,
    private val activity: MainActivity
) :
    RecyclerView.Adapter<ProductsListAdapter.ProductItemViewHolder>() {

    inner class ProductItemViewHolder(var productItemBinding: ProductListItemBinding) :
        RecyclerView.ViewHolder(productItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val binding = holder.productItemBinding
        holder.itemView.apply {
            val productItem = productItems[holder.adapterPosition]


            val productArea = binding.productArea
            val name = binding.name
            val price = binding.price
            val thumbnail = binding.thumbnail

            name.text = productItem.name
            price.text = productItem.price

            Glide.with(context).load(Utils().safeUrl(productItem.thumbnail))
                .apply(RequestOptions.circleCropTransform())
                .into(thumbnail);

            productArea.setOnClickListener {
                navigateToProductScreen(productItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return productItems.size
    }

    fun deleteProductItems() {
        productItems.clear()
        this.notifyDataSetChanged();
    }

    fun updateProductItems(newProductItems: List<UiProduct>) {
        productItems.addAll(newProductItems)
        this.notifyDataSetChanged();
    }

    fun navigateToProductScreen(productItem: UiProduct) {
        val bundle = Bundle()
        bundle.putString("name", productItem.name)
        bundle.putString("price", productItem.price)
        bundle.putString("image", productItem.image)
        bundle.putString("description", productItem.description)
        Navigation.findNavController(activity, R.id.nav_host_fragment)
            .navigate(R.id.action_productsFragment_to_productFragment, bundle)
    }

}