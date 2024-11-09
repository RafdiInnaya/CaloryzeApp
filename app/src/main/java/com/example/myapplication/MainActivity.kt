package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.navigation.NavigationItem
import com.example.myapplication.navigation.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController) }
                ) {
                    NavigationGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Resep.route) { ResepScreen(navController) }
        composable(Screen.About.route) { AboutScreen(navController) }
        composable(
            route = "foodDetail/{name}/{calories}/{imageRes}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("calories") { type = NavType.StringType },
                navArgument("imageRes") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val calories = backStackEntry.arguments?.getString("calories") ?: ""
            val imageRes = backStackEntry.arguments?.getInt("imageRes") ?: 0

            FoodDetailScreen(name, calories, imageRes, navController)
        }
        composable(
            "resepDetailScreen/{recipeName}/{imageResId}/{recipeDescription}",
            arguments = listOf(
                navArgument("recipeName") { type = NavType.StringType },
                navArgument("imageResId") { type = NavType.IntType },
                navArgument("recipeDescription") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ResepDetailScreen(
                navController = navController,
                recipeName = backStackEntry.arguments?.getString("recipeName") ?: "",
                recipeImage = backStackEntry.arguments?.getInt("imageResId") ?: 0,
                recipeDescription = backStackEntry.arguments?.getString("recipeDescription") ?: ""
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0077B6)
                )
            )
        },
        content = { paddingValues ->
            LazyColumn (
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Welcome, Innaya!",
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(start = 15.dp),
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_bold)),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(0.dp))
                }

                item {
                    WeightStatus()

                    Spacer(modifier = Modifier.height(0.dp))
                }

                item {
                    ProgressCard()

                    Spacer(modifier = Modifier.height(0.dp))
                }

                item {
                    FoodMenuList()
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    HealthyFoodRecommendations(navController)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}



@Composable
fun WeightStatus() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painterResource(id = R.drawable.berat),
                    contentDescription = "Weight Status Icon",
                    modifier = Modifier.size(25.dp),
                    tint = Color(0xFF0077B6)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Berat Badan Saat Ini",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "70 kg",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold,
                    color = Color(0xFF0077B6),
                )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Berat Awal: 70 kg",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                )
                Text(
                    text = "Tujuan: 65 kg",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold,
                    color = Color(0xFF64A1CC)
                )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = 0.7f,
                color = Color(0xFF00B4D8),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "70% menuju tujuan!",
                    color = Color(0xFF64A1CC),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold
                )
                )
            }
        }
    }
}

@Composable
fun ProgressCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
    ) {
        Column (
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painterResource(id = R.drawable.screen),
                    contentDescription = "Icon Ringkasan",
                    modifier = Modifier.size(25.dp),
                    tint = Color(0xFF0077B6)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ringkasan Harian",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "1500 kal",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold,
                    color = Color(0xFF0077B6),
                )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Kalori Hari Ini : 1500 kal",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                )
                Text(
                    text = "Tujuan : 2000 kal",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Bold,
                    color = Color(0xFF64A1CC),
                )
                )
            }
                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(
                    progress = 1f,
                    color = Color(0xFF00B4D8),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sisa 500 kal",
                        color = Color(0xFF64A1CC),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_bold)),
                            fontWeight = FontWeight.Bold
                    )
                    )
                }
        }
    }
}


@Composable
fun FoodMenuList() {
    Column(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Text(
            text = "Makanan Hari Ini",
            modifier = Modifier.padding(start = 8.dp),
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            item {
                FoodItemCard(
                    food = FoodItemData("Nasi Goreng", 250, R.drawable.nasi_goreng)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Ayam Bakar", 300, R.drawable.ayam_bakar)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Sayur Rebus", 100, R.drawable.sayur_rebus)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Sate Ayam", 350, R.drawable.sate_ayam)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Bakso", 200, R.drawable.bakso)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Mie Goreng", 400, R.drawable.mie_goreng)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Ikan Bakar", 150, R.drawable.ikan_bakar)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Tahu Tempe", 180, R.drawable.tahu_tempe)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Karedok", 120, R.drawable.karedok)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Nasi Uduk", 250, R.drawable.nasi_uduk)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Gado-Gado", 150, R.drawable.gado_gado)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Mie Ayam", 350, R.drawable.mie_ayam)
                )
            }
            item {
                FoodItemCard(
                    food = FoodItemData("Pempek", 400, R.drawable.pempek)
                )
            }
        }
    }
}

@Composable
fun FoodItemCard(food: FoodItemData) {
    Card(
        modifier = Modifier
            .padding(end = 16.dp)
            .width(200.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = food.imageRes),
                contentDescription = food.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = food.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${food.calories} kalori",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

data class FoodItemData(val name: String, val calories: Int, val imageRes: Int)

@Composable
fun HealthyFoodRecommendations(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Rekomendasi Makanan Sehat",
            modifier = Modifier.padding(start = 20.dp),
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Makanan untuk Diet Sehat",
            modifier = Modifier.padding(start = 20.dp),
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            items(listOf(
                Triple("Salad Buah", "50 kalori", R.drawable.salad_buah),
                Triple("Smoothie Sayuran", "150 kalori", R.drawable.smoothie_sayuran),
                Triple("Oatmeal dengan Buah", "200 kalori", R.drawable.oatmeal_buah),
                Triple("Avocado Toast", "220 kalori", R.drawable.avocado),
                Triple("Quinoa Salad", "180 kalori", R.drawable.salad),
                Triple("Greek Yogurt", "120 kalori", R.drawable.yogurt),
                Triple("Puding Chia", "140 kalori", R.drawable.puding),
                Triple("Muffin Pisang", "170 kalori", R.drawable.muffin),
                Triple("Granola dengan Buah", "210 kalori", R.drawable.buah),
                Triple("Roti Gandum dengan Alpukat", "200 kalori", R.drawable.roti),
                Triple("Hummus dengan Sayuran", "130 kalori", R.drawable.hummus),
                Triple("Sushi Salmon", "250 kalori", R.drawable.sushi),
                Triple("Egg Avocado", "180 kalori", R.drawable.egg)
            )) { item ->

                FoodRecommendation(
                    name = item.first,
                    calories = item.second,
                    imageRes = item.third,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun FoodRecommendation(name: String, calories: String, imageRes: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF64B5F6), Color(0xFF1976D2))
                    )
                )
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = calories,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            navController.navigate("foodDetail/$name/$calories/$imageRes")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White.copy(alpha = 0.3f)
                        ),
                        shape = RoundedCornerShape(50),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Lihat Detail",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResepScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Resep Makanan Sehat!", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0077B6)
                )
            )
        },
        content = { paddingValues ->
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(12) { index ->
                    ResepItem(index = index, navController = navController)
                }
            }
        }
    )
}

@Composable
fun ResepItem(index: Int, navController: NavController) {
    val imageResId = when (index) {
        0 -> R.drawable.salad_avocado
        1 -> R.drawable.salad_sayuran
        2 -> R.drawable.pisang
        3 -> R.drawable.oatmeal
        4 -> R.drawable.ayam
        5 -> R.drawable.kentang
        6 -> R.drawable.burger
        7 -> R.drawable.salad
        8 -> R.drawable.puding
        9 -> R.drawable.juice
        10 -> R.drawable.bowl
        11 -> R.drawable.sup
        else -> R.drawable.avocado
    }

    val recipeName = when (index) {
        0 -> "Salad Alpukat"
        1 -> "Salad Sayuran"
        2 -> "Smoothie Pisang"
        3 -> "Oatmeal Pisang"
        4 -> "Ayam Panggang Sehat"
        5 -> "Kentang Manis Panggang"
        6 -> "Vegan Burger"
        7 -> "Salad Quinoa"
        8 -> "Chia Pudding"
        9 -> "Green Juice"
        10 -> "Poke Bowl"
        11 -> "Sup Sayuran"
        else -> "Resep Unik"
    }

    val recipeDescription = when (index) {
        0 -> "Salad alpukat yang kaya akan lemak sehat dan vitamin."
        1 -> "Salad segar dengan berbagai sayuran dan saus rendah kalori."
        2 -> "Smoothie pisang dengan bahan alami yang penuh energi."
        3 -> "Oatmeal dengan pisang dan kacang untuk sarapan sehat."
        4 -> "Ayam panggang dengan bumbu alami untuk makan siang bergizi."
        5 -> "Kentang manis panggang kaya serat dan rendah kalori."
        6 -> "Burger vegan dengan bahan sehat dan lezat."
        7 -> "Salad quinoa dengan sayuran dan dressing lemon."
        8 -> "Chia pudding dengan buah segar dan topping kacang."
        9 -> "Green juice dengan sayuran segar untuk detoks tubuh."
        10 -> "Poke bowl dengan tuna segar dan berbagai topping."
        11 -> "Sup sayuran penuh dengan wortel, bayam, tomat, dan kaldu sayuran rendah garam."
        else -> "Deskripsi Resep."
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .shadow(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // Image of the Recipe
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = recipeName,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Recipe Name
            Text(
                text = recipeName,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Recipe Description
            Text(
                text = recipeDescription,
                style = TextStyle(fontSize = 13.sp),
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Button to View Recipe Detail
            Button(
                onClick = {
                    navController.navigate("resepDetailScreen/$recipeName/$imageResId/$recipeDescription")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B4D8))
            ) {
                Text("Lihat Resep", color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "About", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0077B6) // Dark Blue
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Foto Profil
                Card(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    shape = RoundedCornerShape(100.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Foto Profil",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nama dan Email
                Text(
                    text = "Rafdi Innaya",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)), color = Color(0xFF333333)),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "rafdiinnaya1501@gmail.com",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)), color = Color.Gray),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Perguruan Tinggi
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Politeknik Negeri Batam",
                            style = TextStyle(
                                fontSize = 19.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_bold)), color = Color(0xFF0077B6))
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Teknologi Rekayasa Perangkat Lunak",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_medium)), color = Color(0xFF555555))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Deskripsi
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0077B6).copy(alpha = 0.1f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Tentang Saya",
                            style = TextStyle(
                                fontSize = 19.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_bold)), color = Color(0xFF0077B6))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Saya adalah mahasiswa yang sedang menjalani Studi Independen di Infinite Learning. Saya dari program Android Mobile Development & UI/UX design.",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)), color = Color(0xFF333333))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Motto/kutipan saya
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF00B4D8).copy(alpha = 0.2f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "\"Tantangan bukanlah penghalang, melainkan kesempatan untuk tumbuh lebih kuat dan lebih bijaksana. Setiap langkah kecil yang kita ambil membawa kita lebih dekat pada impian yang lebih besar.\"",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_italic)), color = Color(0xFF0077B6)),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    )
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        NavigationItem("Home", painterResource(id = R.drawable.home), Screen.Home),
        NavigationItem("Resep", painterResource(id = R.drawable.resep), Screen.Resep),
        NavigationItem("About", painterResource(id = R.drawable.about), Screen.About)
    )
    NavigationBar(
        containerColor = Color(0xFF0077B6) // Dark Blue
    ) {
        val currentRoute by navController.currentBackStackEntryAsState()
        val currentRouteName = currentRoute?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, modifier = Modifier.size(25.dp), contentDescription = item.title) },
                label = { Text(item.title, color = Color.White) },
                selected = currentRouteName == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MyApplicationTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomBar(navController) }
        ) {
            NavigationGraph(navController = navController)
        }
    }
}
