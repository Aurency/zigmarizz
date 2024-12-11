package com.example.clothloop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clothloop.R

@Composable
fun typesTextile(navController: NavHostController){
    val scrollState = rememberScrollState()

Column(
    Modifier
        .fillMaxSize()
        .background(color = Color(0XFFF2E8D8))
        .verticalScroll(scrollState)) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(120.dp), contentAlignment = Alignment.BottomStart
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(PaddingValues(start = 20.dp, bottom = 20.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "",
                tint = Color(0XFF0A4635),
                modifier = Modifier.size(40.dp).clickable { navController.navigate("home") }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Types of Textile Waste",
                fontWeight = FontWeight.W900,
                color = Color(0XFF0A4635),
                fontSize = 20.sp
            )
        }

    }
    Spacer(modifier = Modifier.height(20.dp))
    Column(Modifier.padding(horizontal = 20.dp)) {
        Text(text = "Limbah tekstil adalah sisa-sisa bahan atau produk yang dihasilkan dari industri tekstil, seperti pakaian, kain, karpet, dan boneka. Limbah tekstil dapat berupa: Kain bekas, Pakaian yang sudah tidak terpakai, Sisa produksi tekstil, Limbah cair yang dihasilkan dari proses pewarnaan.",
            color = Color(0XFF0A4635), textAlign = TextAlign.Start)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Limbah tekstil dapat dimanfaatkan atau didaur ulang menjadi benda-benda yang bermanfaat, seperti ikat rambut, hiasan tutup toples, dan lain-lain. Limbah Tekstil dapat dibedakan menjadi beberapa macam, yaitu:",
            color = Color(0XFF0A4635), textAlign = TextAlign.Start)
        Spacer(modifier = Modifier.height(20.dp))
        boxTypes(types = "Limbah Pre-Konsumen", desc = "Limbah ini dihasilkan dari proses produksi tekstil sebelum mencapai konsumen, seperti sisa potongan kain, benang, dan produk cacat dari pabrik. Limbah ini sering dianggap lebih \"bersih\" karena belum terkontaminasi oleh penggunaan sehari-hari\u200B", image = R.drawable.pre)
        Spacer(modifier = Modifier.height(20.dp))
        boxTypes(types = "Limbah Pasca-Konsumen", desc = "Limbah ini berasal dari pakaian atau produk tekstil lain yang telah digunakan oleh konsumen, misalnya pakaian bekas, seprai, dan tirai yang sudah tidak dipakai lagi. Limbah ini lebih sulit didaur ulang karena sudah terpakai dan mungkin terkontaminasi\u200B.", image = R.drawable.pasca)
        Spacer(modifier = Modifier.height(20.dp))
        boxTypes(types = "Limbah Serat Alamiah", desc = "Limbah ini berasal dari serat alam seperti katun, wol, dan sutra. Meskipun serat ini lebih ramah lingkungan karena bisa terurai secara alami, proses produksinya tetap menggunakan banyak sumber daya seperti air dan lahan.", image = R.drawable.alamiah)
        Spacer(modifier = Modifier.height(20.dp))
        boxTypes(types = "Limbah Serat Sintetis", desc = "Limbah yang berasal dari serat buatan seperti polyester, nylon, dan spandex, yang lebih sulit terurai di lingkungan. Limbah ini menyumbang sebagian besar limbah tekstil global dan berdampak besar pada polusi.", image = R.drawable.sintesis)
        Spacer(modifier = Modifier.height(40.dp))   
    }
}}

@Composable
fun boxTypes(types: String, desc: String, image: Int){
    Box(Modifier.fillMaxWidth()) {
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                    ), contentAlignment = Alignment.Center) {
                Text(text = types, color = Color(0XFF0A4635), fontWeight = FontWeight.W900, fontSize = 20.sp)
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(205.dp)
                    .background(color = Color.Gray)) {
                Image(painter = painterResource(id = image), contentDescription = "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                    ), contentAlignment = Alignment.Center) {
                Text(text = desc,
                    textAlign = TextAlign.Center, color = Color(0XFF0A4635), modifier = Modifier.padding(
                        PaddingValues(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 20.dp)
                    ))
            }

        }
    }
}