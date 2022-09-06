package com.gkonstantakis.productsapp.products.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gkonstantakis.productsapp.products.ProductsApplication
import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.data.utils.Datastate
import com.gkonstantakis.productsapp.products.activities.MainActivity
import com.gkonstantakis.productsapp.products.adapters.ProductsListAdapter
import com.gkonstantakis.productsapp.products.databinding.FragmentProductsBinding
import com.gkonstantakis.productsapp.products.mappers.UiMapper
import com.gkonstantakis.productsapp.products.models.UiProduct
import com.gkonstantakis.productsapp.products.viewmodels.ProductsViewModel
import com.gkonstantakis.productsapp.products.viewmodels.StateEvent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var productsApplication: ProductsApplication
    private lateinit var viewModel: ProductsViewModel

    private lateinit var fragmentProductsBinding: FragmentProductsBinding

    private lateinit var reloadButton: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var productsListRecyclerView: RecyclerView
    private lateinit var networkErrorText: TextView

    private var productItems: MutableList<UiProduct> = ArrayList<UiProduct>()
    private var productItemsAdapter: ProductsListAdapter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentProductsBinding = FragmentProductsBinding.inflate(layoutInflater)
        return fragmentProductsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsApplication = (this.requireActivity().application as ProductsApplication)

        viewModel = ProductsViewModel(productsApplication.productRepository)

        reloadButton = fragmentProductsBinding.reloadButton
        progressBar = fragmentProductsBinding.progressBar
        productsListRecyclerView = fragmentProductsBinding.productsList
        networkErrorText = fragmentProductsBinding.networkErrorText

        subscribeObservers()

        reloadButton.setOnClickListener {
            viewModel.setStateEvent(StateEvent.GetNetworkProducts)
        }

        viewModel.setStateEvent(StateEvent.GetDatabaseProducts)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is Datastate.SuccessNetworkGet<List<Product>> -> {
                    dataLoading(false)
                    networkErrorText.visibility = View.GONE
                    if (datastate.data.isNullOrEmpty()) {
                        productItems.clear()
                        //DisplayErrorMessage
                    } else {
                        if (productItems.isNullOrEmpty()) {
                            for (data in datastate.data) {
                                productItems.add(UiMapper().mapToEntity(data))
                            }
                            displayProductsList()
                        } else {
                            productItems.clear()
                            for (data in datastate.data) {
                                productItems.add(UiMapper().mapToEntity(data))
                            }
                            updateProductItems(productItems)
                        }
                    }
                }
                is Datastate.SuccessDatabaseGet<List<Product>> -> {
                    dataLoading(false)
                    if (datastate.data.isNullOrEmpty()) {
                        productItems.clear()
                        //DisplayErrorMessage
                    } else {
                        if (productItems.isNullOrEmpty()) {
                            for (data in datastate.data) {
                                productItems.add(UiMapper().mapToEntity(data))
                            }
                            displayProductsList()
                        } else {
                            productItems.clear()
                            for (data in datastate.data) {
                                productItems.add(UiMapper().mapToEntity(data))
                            }
                            updateProductItems(productItems)

                        }
                    }
                }
                is Datastate.Error -> {
                    if (datastate.message == "DATABASE_ERROR") {
                        viewModel.setStateEvent(StateEvent.GetNetworkProducts)
                    } else if(datastate.message == "NETWORK_ERROR") {
                        networkErrorText.visibility = View.VISIBLE
                    }
                    dataLoading(false)
                }
                is Datastate.Loading -> {
                    deleteProductItems()
                    dataLoading(true)
                }
                else -> {
                    dataLoading(false)
                }
            }
        })
    }

    private fun displayProductsList() {
        if (!productItems.isNullOrEmpty()) {
            productsListRecyclerView.adapter = ProductsListAdapter(
                productItems,
                UiMapper(),
                viewModel,
                this.requireActivity() as MainActivity
            )
            productItemsAdapter = productsListRecyclerView.adapter as ProductsListAdapter
            linearLayoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            productsListRecyclerView.layoutManager = linearLayoutManager
        }
    }

    private fun deleteProductItems() {
        productItemsAdapter?.deleteProductItems()
    }

    fun updateProductItems(newProductItems: List<UiProduct>) {
        productItemsAdapter?.updateProductItems(newProductItems)
    }

    private fun dataLoading(isDataLoading: Boolean) {
        if (isDataLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}