package br.com.heiderlopes.modularizacao.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.heiderlopes.domain.entity.Product
import br.com.heiderlopes.modularizacao.R
import br.com.heiderlopes.modularizacao.databinding.ProductRowBinding
import br.com.heiderlopes.modularizacao.extensions.inflate
import com.squareup.picasso.Picasso

class MainListAdapter(
    private val picasso: Picasso
) : RecyclerView.Adapter<MainListAdapter.MainViewHolder>() {

    var products: List<Product> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemBinding =
            ProductRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(picasso, itemBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val product: Product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    class MainViewHolder(
        private val picasso: Picasso,
        private val itemBinding: ProductRowBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(product: Product) {
            itemBinding.tvTitleProduct.text = product.name
            itemBinding.tvDescriptionProduct.text = product.description
            picasso.load(product.imageURL).into(itemBinding.ivPhotoProduct)
        }
    }
}