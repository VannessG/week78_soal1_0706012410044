package com.example.week78_0706012410044.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.week78_0706012410044.R
import com.example.week78_0706012410044.ui.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ViewWeather() {
    val viewModel: WeatherViewModel = viewModel()
    var city by remember { mutableStateOf("") }
    val state by viewModel.weatherState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "BG",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 15.dp)
            ) {
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    placeholder = {
                        Text(
                            text = "Enter city name...",
                            color = Color.LightGray,
                            fontSize = 16.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.LightGray
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.White.copy(alpha = 0.1f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(30))
                        .fillMaxHeight()
                )
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = { if (city.isNotEmpty()) viewModel.showWeather(city) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(CornerSize(20.dp)),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "Search",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.data == null && !state.isError) {
                    item {
                        Spacer(Modifier.height(200.dp))
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(80.dp)
                        )
                        Text(
                            text = "Search for a city to get started",
                            color = Color.LightGray,
                            fontSize = 16.sp
                        )
                    }
                }
                if (state.isError) {
                    item {
                        Spacer(Modifier.height(270.dp))
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Error",
                            tint = Color.Red,
                            modifier = Modifier.size(90.dp)
                        )
                        Text(
                            text = "Oops! Something went wrong",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "HTTP 404 Not Found",
                            color = Color.LightGray,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    }
                }
                val data = state.data
                if (data != null) {
                    val date = SimpleDateFormat("MMMM dd", Locale.getDefault()).format(Date())
                    item {
                        Row() {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = data.city,
                                color = Color.White,
                                fontSize = 24.sp,
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = date,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Updated as of ${SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())}",
                            fontSize = 16.sp, color = Color.LightGray
                        )
                        Spacer(Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 50.dp, bottom = 50.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = state.data?.iconUrl,
                                    contentDescription = "Weather Icon",
                                    modifier = Modifier.size(80.dp),
                                    colorFilter =
                                        if (state.data?.condition == "Clear")
                                            ColorFilter.tint(Color(0xFFda7454))
                                        else
                                            null
                                )
                                Text(
                                    text = data.condition,
                                    color = Color.White,
                                    fontSize = 28.sp
                                )
                                Text(
                                    text = "${data.temperature}°C",
                                    fontSize = 80.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            val pandaImage = when (data.condition.lowercase()) {
                                "rain" -> R.drawable.panda_rain
                                "clouds" -> R.drawable.panda_cloud
                                "clear" -> R.drawable.panda_clear
                                else -> R.drawable.panda_cloud
                            }
                            Image(
                                painter = painterResource(id = pandaImage),
                                contentDescription = null,
                                modifier = Modifier.size(200.dp)
                            )
                        }
                        Spacer(Modifier.height(15.dp))
                    }
                    val infoWeather = listOf(
                        "HUMIDITY" to "${data.humidity}%",
                        "WIND" to "${data.windSpeed} km/h",
                        "FEELS LIKE" to "${data.feelsLike}°",
                        "RAIN FALL" to "${data.rainfall} mm",
                        "PRESSURE" to "${data.pressure}hPa",
                        "CLOUDS" to "${data.clouds}%"
                    )
                    for (row in 0 until 2) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                for (col in 0 until 3) {
                                    val index = row * 3 + col
                                    val (key, value) = infoWeather[index]
                                    val iconId = when (key.uppercase()) {
                                        "HUMIDITY" -> R.drawable.humidity
                                        "WIND" -> R.drawable.wind
                                        "FEELS LIKE" -> R.drawable.feels_like
                                        "RAIN FALL" -> R.drawable.rainfall
                                        "PRESSURE" -> R.drawable.pressure
                                        else -> R.drawable.cloud
                                    }
                                    WeatherInfoBox(
                                        key,
                                        value,
                                        iconId,
                                        Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.sunrise),
                                    contentDescription = "Sunrise",
                                    modifier = Modifier.size(32.dp)
                                )
                                Spacer(Modifier.height(5.dp))
                                Text(
                                    text = "SUNRISE",
                                    color = Color.LightGray,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(data.sunrise * 1000)),
                                    color = Color.White
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.sunset),
                                    contentDescription = "Sunset",
                                    modifier = Modifier.size(32.dp)
                                )
                                Spacer(Modifier.height(5.dp))
                                Text(
                                    text = "SUNSET",
                                    color = Color.LightGray,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(data.sunset * 1000)),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherInfoBox(
    key: String,
    value: String,
    iconId: Int,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f)),
        modifier = modifier.clip(RoundedCornerShape(25.dp))
    ) {
        Column(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = key,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = key,
                color = Color.LightGray,
                fontSize = 12.sp
            )
            Text(
                text = value,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWeather() {
    ViewWeather()
}