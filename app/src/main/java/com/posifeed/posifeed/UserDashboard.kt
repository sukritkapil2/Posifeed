package com.posifeed.posifeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.posifeed.posifeed.ui.theme.LightPurple
import com.posifeed.posifeed.ui.theme.PosifeedTheme
import com.posifeed.posifeed.ui.theme.White

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal)
)

class LandingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PosifeedTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ToolbarWidget()
                }
            }
        }
    }
}

@Composable
fun ToolbarWidget() {
    TopAppBar(title = {
        Text(text = "Hi, Sukrit",
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Bold,
        )
    },
    backgroundColor = LightPurple,
    contentColor = White,
    navigationIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "User Menu")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PosifeedTheme {
        ToolbarWidget()
    }
}