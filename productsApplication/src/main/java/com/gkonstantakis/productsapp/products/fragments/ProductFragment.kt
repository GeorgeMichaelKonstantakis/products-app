package com.gkonstantakis.productsapp.products.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.gkonstantakis.productsapp.products.R
import com.gkonstantakis.productsapp.products.databinding.FragmentProductBinding
import com.gkonstantakis.productsapp.products.utils.Utils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var name: String? = null
    private var price: String? = null
    private var image: String? = null
    private var description: String? = null

    private lateinit var fragmentProductBinding: FragmentProductBinding

    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productImage: ImageView
    private lateinit var productDescription: TextView
    private lateinit var backButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            name = it.getString("name")
            price = it.getString("price")
            image = it.getString("image")
            description = it.getString("description")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentProductBinding = FragmentProductBinding.inflate(layoutInflater)
        return fragmentProductBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productName = fragmentProductBinding.productName
        productPrice = fragmentProductBinding.productPrice
        productImage = fragmentProductBinding.productImage
        productDescription = fragmentProductBinding.productDescription
        backButton = fragmentProductBinding.backButton

        productName.text = name
        productPrice.text = price
        productDescription.text = description

        Glide.with(this).load(Utils().safeUrl(image!!))
            .into(fragmentProductBinding.productImage);

        backButton.setOnClickListener {
            navigateToProductsScreen()
        }
    }

    private fun navigateToProductsScreen() {
        val bundle = Bundle()
        Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
            .navigate(R.id.action_productFragment_to_productsFragment, bundle)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}