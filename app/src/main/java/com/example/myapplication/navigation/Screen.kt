package com.example.myapplication.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Resep : Screen("resep")
    object About : Screen("about")
    object FoodDetail : Screen("foodDetail/{name}/{calories}/{imageRes}/{description}") {
        fun createRoute(name: String, calories: String, imageRes: Int, description: String): String {
            return "foodDetail/$name/$calories/$imageRes/$description"
        }
    }
}