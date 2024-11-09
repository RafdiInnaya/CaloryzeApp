package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResepDetailScreen(
    navController: NavController,
    recipeName: String,
    recipeImage: Int,
    recipeDescription: String
) {
    val (ingredients, instructions) = getRecipeContent(recipeName)
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Resep $recipeName",
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
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {
                Image(
                    painter = painterResource(id = recipeImage),
                    contentDescription = recipeName,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = recipeName,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = recipeDescription,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Bahan-Bahan:",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                ingredients.forEachIndexed { index, ingredient ->
                    Text(
                        text = "${index + 1}. $ingredient",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Cara Membuat:",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                instructions.forEachIndexed { index, instruction ->
                    Text(
                        text = "${index + 1}. $instruction",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

@Composable
fun getRecipeContent(recipeName: String): Pair<List<String>, List<String>> {
    return when (recipeName) {
        "Salad Alpukat" -> Pair(
            listOf("1 buah alpukat matang", "1 buah tomat", "1 buah mentimun", "1 sendok makan minyak zaitun", "1 sendok makan air lemon", "Garam dan merica secukupnya"),
            listOf(
                "Potong alpukat, tomat, dan mentimun menjadi potongan kecil.",
                "Campurkan semua bahan dalam mangkuk besar.",
                "Tambahkan minyak zaitun, air lemon, garam, dan merica.",
                "Aduk rata dan sajikan segar."
            )
        )
        "Salad Sayuran" -> Pair(
            listOf("Lettuce atau sayuran hijau lainnya", "Tomat", "Mentimun", "Paprika", "Bawang bombay", "Minyak zaitun", "Cuka apel", "Garam dan merica"),
            listOf(
                "Cuci bersih semua sayuran.",
                "Iris dan serut sayuran sesuai selera.",
                "Campurkan semua sayuran dalam mangkuk.",
                "Tambahkan minyak zaitun, cuka apel, garam, dan merica.",
                "Aduk rata dan sajikan."
            )
        )
        "Smoothie Pisang" -> Pair(
            listOf("1 buah pisang matang", "200 ml susu almond", "1 sendok makan madu (opsional)", "Es batu secukupnya"),
            listOf(
                "Masukkan pisang, susu almond, madu (jika suka), dan es batu ke dalam blender.",
                "Blender hingga halus.",
                "Tuang ke dalam gelas dan nikmati."
            )
        )
        "Oatmeal Pisang" -> Pair(
            listOf("1/2 cangkir oatmeal", "200 ml susu almond", "1 buah pisang iris", "1 sendok makan madu"),
            listOf(
                "Masak oatmeal dengan susu almond di atas api kecil selama 5-7 menit.",
                "Setelah oatmeal matang, tambahkan pisang iris dan madu.",
                "Aduk rata dan sajikan dengan tambahan kacang atau buah sesuai selera."
            )
        )
        "Ayam Panggang Sehat" -> Pair(
            listOf("2 dada ayam tanpa tulang", "2 sendok makan minyak zaitun", "1 sendok teh paprika", "2 siung bawang putih cincang", "Garam dan merica secukupnya", "1 sendok makan air lemon"),
            listOf(
                "Lumuri dada ayam dengan minyak zaitun, paprika, bawang putih, garam, merica, dan air lemon.",
                "Diamkan selama 10 menit agar bumbu meresap.",
                "Panggang ayam di oven pada suhu 180°C selama 25-30 menit atau hingga matang."
            )
        )
        "Kentang Manis Panggang" -> Pair(
            listOf("2 kentang manis", "2 sendok makan minyak zaitun", "1 sendok teh paprika", "Garam dan merica secukupnya"),
            listOf(
                "Panaskan oven pada suhu 200°C.",
                "Campurkan kentang manis dengan minyak zaitun, paprika, garam, dan merica.",
                "Panggang kentang di dalam oven selama 30-40 menit hingga empuk dan kecokelatan."
            )
        )
        "Vegan Burger" -> Pair(
            listOf("1 cangkir kacang hitam", "1/2 cangkir oatmeal", "1/4 cangkir wortel parut", "1 sendok makan kecap asin", "1 sendok teh paprika", "Minyak untuk memasak"),
            listOf(
                "Campurkan semua bahan dalam mangkuk besar dan haluskan dengan menggunakan garpu atau food processor.",
                "Bentuk adonan menjadi patty (bulat pipih).",
                "Panggang di atas teflon selama 4-5 menit di setiap sisi hingga kecokelatan.",
                "Sajikan dengan roti burger dan sayuran."
            )
        )
        "Salad Quinoa" -> Pair(
            listOf("1 cangkir quinoa", "1/2 cangkir tomat potong dadu", "1/4 cangkir jagung manis", "1/4 cangkir kacang hitam", "2 sendok makan minyak zaitun", "1 sendok makan air lemon", "Garam dan merica"),
            listOf(
                "Setelah quinoa matang, biarkan dingin selama beberapa menit.",
                "Campurkan quinoa dengan tomat, jagung, dan kacang hitam.",
                "Tambahkan minyak zaitun, lemon, garam, dan merica. Aduk rata.",
                "Sajikan dingin."
            )
        )
        "Chia Pudding" -> Pair(
            listOf("3 sendok makan chia seeds", "200 ml susu almond", "1 sendok makan madu (opsional)"),
            listOf(
                "Campurkan chia seeds dengan susu almond dan madu.",
                "Diamkan dalam kulkas selama minimal 3 jam atau semalaman.",
                "Aduk rata sebelum disajikan, bisa ditambahkan buah segar."
            )
        )
        "Green Juice" -> Pair(
            listOf("1/2 cangkir bayam", "1 buah mentimun", "1/2 apel hijau", "1/2 lemon", "Air secukupnya"),
            listOf(
                "Masukkan semua bahan ke dalam blender.",
                "Blender hingga halus.",
                "Saring jika perlu dan sajikan."
            )
        )
        "Poke Bowl" -> Pair(
            listOf("1 cangkir nasi merah", "100 gr ikan tuna segar", "1/4 cangkir edamame", "1/4 cangkir alpukat", "1 sendok makan kecap asin", "1 sendok teh minyak wijen"),
            listOf(
                "Masak nasi merah hingga matang.",
                "Potong ikan tuna dan alpukat menjadi dadu kecil.",
                "Susun nasi, ikan tuna, edamame, dan alpukat dalam mangkuk.",
                "Tuang kecap asin dan minyak wijen di atasnya, aduk rata."
            )
        )
        "Sup Sayuran" -> Pair(
            listOf("1 buah wortel", "1 batang seledri", "1/2 cangkir kacang polong", "1/2 cangkir jagung manis", "1 bawang bombay", "2 siung bawang putih", "Kaldu sayuran"),
            listOf(
                "Potong semua sayuran menjadi potongan kecil.",
                "Rebus semua sayuran dalam air kaldu selama 30-40 menit hingga empuk.",
                "Tambahkan garam dan merica secukupnya dan sajikan."
            )
        )
        else -> Pair(emptyList(), emptyList())
    }
}
