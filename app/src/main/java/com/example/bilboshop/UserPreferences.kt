package com.example.bilboshop

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.bilboshop.data.Product
import com.example.bilboshop.data.Shop
import com.example.bilboshop.data.User
import com.google.gson.Gson

class UserPreferences {

    private val sharedPreferences: SharedPreferences by lazy {
        MyApp.context.getSharedPreferences(MyApp.context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

    companion object {
        const val LOGGED_USER = "logged_user"
    }


    fun saveLoggedUser(user: User) {
        val editor = sharedPreferences.edit()

        val userJson = Gson().toJson(user)
        editor.putString(LOGGED_USER, userJson)
        editor.apply()
    }

    fun unLogUser() {
        val editor = sharedPreferences.edit()
        editor.remove(LOGGED_USER)
        editor.apply()
    }

    fun createDemoData() : List<Shop> {
        val listShop: List<Shop>

        //TIENDA 1
        val productListShop1: List<Product>
        val product1 = Product( 1,"Camiseta Algodon", 10.0, "Camiseta de algodón","Camisetas", "https://www.tiendaropa.com/camiseta1.jpg")
        val product2 = Product( 2,"Camisa Blanca", 15.0, "Camisa Blanca","Camisa", "https://www.tiendaropa.com/camisa.jpg")
        val product3 = Product( 3,"Pantalón Vaquero", 20.0, "Pantalón Vaquero","Pantalones", "https://www.tiendaropa.com/pantalon.jpg")
        val product4 = Product( 4,"Zapatos", 25.0, "Zapatos","Zapatos", "https://www.tiendaropa.com/zapatos.jpg")
        productListShop1 = listOf(product1, product2, product3, product4)

        val shop1 = Shop(1, "Tienda de Ropa 1", "Ropa", "Calle 1", "999999999", "tiendaropa@gmail.com", "https://www.tiendaropa.com","img", "Tienda de Ropa 1", productListShop1)

        //TIENDA 2
        val productListShop2: List<Product>
        val product5 = Product( 5,"Leche", 1.0, "Leche","Lacteos", "https://www.tiendaalimentacion.com/leche.jpg")
        val product6 = Product( 6,"Pan", 1.0, "Pan","Pan", "https://www.tiendaalimentacion.com/pan.jpg")
        val product7 = Product( 7,"Huevos", 2.0, "Huevos","Huevos", "https://www.tiendaalimentacion.com/huevos.jpg")
        val product8 = Product( 8,"Yogures", 1.0, "Yogures","Lacteos", "https://www.tiendaalimentacion.com/yogures.jpg")
        productListShop2 = listOf(product5, product6, product7, product8)

        val shop2 = Shop(2, "Tienda de Alimentacion", "Alimentacion", "Calle 2", "999999998", "tiendaalimentacion@gmail.com", "https://www.tiendaalimentacion.com","img", "Tienda de Alimentacion", productListShop2)

        //TIENDA 3
        val productListShop3: List<Product>
        val product9 = Product( 9,"Movil", 200.0, "Movil","Electronica", "https://www.tiendaelectronica.com/movil.jpg")
        val product10 = Product( 10,"Portatil", 500.0, "Portatil","Electronica", "https://www.tiendaelectronica.com/portatil.jpg")
        val product11 = Product( 11,"Tablet", 300.0, "Tablet","Electronica", "https://www.tiendaelectronica.com/tablet.jpg")
        val product12 = Product( 12,"Cargador", 10.0, "Cargador","Electronica", "https://www.tiendaelectronica.com/cargador.jpg")
        productListShop3 = listOf(product9, product10, product11, product12)

        val shop3 = Shop(3, "Tienda de Electronica", "Electronica", "Calle 3", "999999997", "tiendaelectronica@gmail.com", "https://www.tiendaelectronica.com","img", "Tienda de Electronica", productListShop3)

        listShop = listOf(shop1, shop2, shop3)

        return listShop

    }



    fun getLoggedUser(): User? {
        Log.i("USERPREFERENCES","GetLoggedUser")
        val userJson = sharedPreferences.getString(LOGGED_USER, null)
        return if (userJson != null) {
            // Aquí, utilizamos Gson para convertir el JSON almacenado de nuevo a un objeto User.
            Gson().fromJson(userJson, User::class.java)
        } else {
            null
        }
    }

}