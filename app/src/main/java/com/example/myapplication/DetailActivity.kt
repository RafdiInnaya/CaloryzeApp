package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailScreen(name: String, calories: String, imageRes: Int, navController: NavController) {
    val description = when (name) {
        "Salad Buah" -> "Salad segar yang terbuat dari berbagai macam buah tropis yang kaya akan vitamin dan serat. Nikmati perpaduan rasa manis alami dari buah-buahan segar!"
        "Smoothie Sayuran" -> "Minuman segar yang terbuat dari campuran sayuran hijau seperti bayam dan kale, dicampur dengan buah-buahan seperti pisang dan apel, memberikan banyak nutrisi dengan rasa yang enak."
        "Oatmeal dengan Buah" -> "Oatmeal hangat yang kaya serat, dipadukan dengan potongan buah segar seperti pisang, stroberi, atau blueberry untuk sarapan sehat yang memberikan energi sepanjang hari."
        "Avocado Toast" -> "Roti panggang dengan topping alpukat yang dihaluskan, ditaburi sedikit garam, merica, dan minyak zaitun. Kombinasi lezat yang mengenyangkan dengan kandungan lemak sehat."
        "Quinoa Salad" -> "Salad segar dengan quinoa, sayuran seperti paprika, tomat, dan timun, serta tambahan protein dari kacang-kacangan, membuat hidangan ini penuh gizi dan cocok sebagai makan siang."
        "Greek Yogurt" -> "Yogurt kental dan creamy, tinggi protein, dan kaya probiotik yang baik untuk pencernaan. Nikmati dengan topping madu, buah segar, atau granola untuk pilihan sarapan yang sehat."
        "Puding Chia" -> "Puding berbahan dasar biji chia yang direndam dalam susu almond atau santan, memberi tekstur kenyal dan kaya akan serat serta asam lemak omega-3."
        "Muffin Pisang" -> "Muffin lembut dan manis dengan bahan dasar pisang matang yang kaya kalium dan serat. Pilihan sarapan atau camilan sehat yang mengenyangkan."
        "Granola dengan Pisang" -> "Granola kaya serat yang dipadukan dengan potongan pisang segar, cocok untuk sarapan atau camilan yang sehat dan mengenyangkan."
        "Roti Gandum dengan Alpukat" -> "Roti gandum utuh yang dipanggang, di atasnya dioleskan alpukat yang kaya akan lemak sehat, cocok untuk sarapan sehat yang memberikan energi sepanjang hari."
        "Hummus dengan Sayuran" -> "Hummus yang terbuat dari kacang arab (chickpea), disajikan dengan sayuran segar seperti wortel, timun, dan paprika sebagai camilan sehat yang kaya protein dan serat."
        "Sushi Salmon" -> "Sushi dengan potongan ikan salmon segar, kaya asam lemak omega-3, dibungkus dengan nasi dan rumput laut yang menyehatkan. Pilihan makan sehat dengan cita rasa Jepang."
        "Egg Avocado" -> "Telur rebus yang dipadukan dengan alpukat segar, memberikan kombinasi protein dan lemak sehat yang mengenyangkan dan cocok untuk sarapan atau makan siang cepat."
        else -> "Deskripsi makanan tidak tersedia."
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Makanan",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0077B6)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = name,
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Calories: $calories",
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = description,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )
}
