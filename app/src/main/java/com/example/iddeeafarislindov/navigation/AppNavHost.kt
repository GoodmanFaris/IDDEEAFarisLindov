package com.example.iddeeafarislindov.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.iddeeafarislindov.App
import com.example.iddeeafarislindov.data.models.died.DiedRecord
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard
import com.example.iddeeafarislindov.data.models.persons.PersonRecord
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle
import com.example.iddeeafarislindov.data.models.died.DiedRequest
import com.example.iddeeafarislindov.data.models.idcard.IdCardsRequest
import com.example.iddeeafarislindov.data.models.persons.PersonsRequest
import com.example.iddeeafarislindov.data.models.vehicles.VehiclesRequest
import com.example.iddeeafarislindov.screens.DetailScreens.died.DiedDetailScreen
import com.example.iddeeafarislindov.screens.DetailScreens.idcards.IdCardsDetailScreen
import com.example.iddeeafarislindov.screens.DetailScreens.persons.PersonsDetailScreen
import com.example.iddeeafarislindov.screens.DetailScreens.vehicles.VehiclesDetailScreen
import com.example.iddeeafarislindov.screens.about.AboutScreen
import com.example.iddeeafarislindov.screens.died.DiedScreen
import com.example.iddeeafarislindov.screens.died.DiedViewModel
import com.example.iddeeafarislindov.screens.died.DiedViewModelFactory
import com.example.iddeeafarislindov.screens.favorites.FavoritesScreen
import com.example.iddeeafarislindov.screens.favorites.favoritedied.FavoriteDiedScreen
import com.example.iddeeafarislindov.screens.favorites.favoriteidcards.FavoriteIdCardsScreen
import com.example.iddeeafarislindov.screens.favorites.favoritepersons.FavoritePersonsScreen
import com.example.iddeeafarislindov.screens.favorites.favoritevehicles.FavoriteVehiclesScreen
import com.example.iddeeafarislindov.screens.idcards.IdCardsScreen
import com.example.iddeeafarislindov.screens.idcards.IdCardsViewModel
import com.example.iddeeafarislindov.screens.idcards.IdCardsViewModelFactory
import com.example.iddeeafarislindov.screens.main.MainScreen
import com.example.iddeeafarislindov.screens.persons.PersonsScreen
import com.example.iddeeafarislindov.screens.persons.PersonsViewModel
import com.example.iddeeafarislindov.screens.persons.PersonsViewModelFactory
import com.example.iddeeafarislindov.screens.splash.SplashScreen
import com.example.iddeeafarislindov.screens.vehicles.VehicleViewModelFactory
import com.example.iddeeafarislindov.screens.vehicles.VehiclesScreen
import com.example.iddeeafarislindov.screens.vehicles.VehiclesViewModel
import com.example.iddeeafarislindov.ui.components.filter.FilterScreen
import com.google.gson.Gson
import kotlin.jvm.java

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main") { MainScreen(navController = navController) }
        composable("about") { AboutScreen(navController = navController) }

        composable(
            route = "died_details/{diedJson}",
            arguments = listOf(navArgument("diedJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("diedJson")
            val diedRecord = Gson().fromJson(json, DiedRecord::class.java)

            DiedDetailScreen(diedRecord, navController)
        }

        composable(
            "persons_details/{json}",
            arguments = listOf(navArgument("json") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("json")
            val record = Gson().fromJson(json, PersonRecord::class.java)
            PersonsDetailScreen(record, navController)
        }

        composable(
            "idcards_details/{json}",
            arguments = listOf(navArgument("json") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("json")
            val record = Gson().fromJson(json, IssuedIdCard::class.java)
            IdCardsDetailScreen(record, navController)
        }

        composable(
            "vehicles_details/{json}",
            arguments = listOf(navArgument("json") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("json")
            val record = Gson().fromJson(json, RegisteredVehicle::class.java)
            VehiclesDetailScreen(record, navController)
        }

        composable("filter_persons") {
            FilterScreen(
                title = "Filter - Broj osoba",
                navController = navController,
                targetRoute = "persons_results"
            )
        }

        composable("filter_died") {
            FilterScreen(
                title = "Filter - Umrli",
                navController = navController,
                targetRoute = "died_results"
            )
        }

        composable("filter_idcards") {
            FilterScreen(
                title = "Filter - Lične karte",
                navController = navController,
                targetRoute = "idcards_results"
            )
        }

        composable("filter_vehicles") {
            FilterScreen(
                title = "Filter - Vozila",
                navController = navController,
                targetRoute = "vehicles_results"
            )
        }

        composable(
            route = "persons_results/{json}",
            arguments = listOf(navArgument("json") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("json")
            val request = Gson().fromJson(json, PersonsRequest::class.java)
            PersonsScreen(request = request, navController = navController)
        }

        composable(
            route = "died_results/{json}",
            arguments = listOf(navArgument("json") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("json")
            val request = Gson().fromJson(json, DiedRequest::class.java)
            DiedScreen(request = request, navController = navController)
        }

        composable(
            route = "idcards_results/{json}",
            arguments = listOf(navArgument("json") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("json")
            val request = Gson().fromJson(json, IdCardsRequest::class.java)
            IdCardsScreen(request = request, navController = navController)
        }


        composable(
            route = "vehicles_results/{json}",
            arguments = listOf(navArgument("json") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("json")
            val request = Gson().fromJson(json, VehiclesRequest::class.java)
            VehiclesScreen(request = request, navController = navController)
        }

        composable("favorites") {
            FavoritesScreen(navController)
        }

        composable("favorites/persons") {
            val context = LocalContext.current
            val db = (context.applicationContext as App).database
            val viewModel: PersonsViewModel = viewModel(factory = PersonsViewModelFactory(db))

            FavoritePersonsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("favorites/idcards") {
            val context = LocalContext.current
            val db = (context.applicationContext as App).database
            val viewModel: IdCardsViewModel = viewModel(factory = IdCardsViewModelFactory(db))

            FavoriteIdCardsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("favorites/died") {
            val context = LocalContext.current
            val db = (context.applicationContext as App).database
            val viewModel: DiedViewModel = viewModel(factory = DiedViewModelFactory(db))

            FavoriteDiedScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("favorites/vehicles") {
            val context = LocalContext.current
            val db = (context.applicationContext as App).database
            val viewModel: VehiclesViewModel = viewModel(factory = VehicleViewModelFactory(db))

            FavoriteVehiclesScreen(
                navController = navController,
                viewModel = viewModel
            )
        }


    }

}
