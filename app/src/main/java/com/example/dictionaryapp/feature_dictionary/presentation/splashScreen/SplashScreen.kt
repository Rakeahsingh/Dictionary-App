package com.example.dictionaryapp.feature_dictionary.presentation.splashScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionaryapp.R
import com.example.dictionaryapp.core.navigation.Route
import com.example.dictionaryapp.core.utils.UiEvent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    modifier: Modifier = Modifier
) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(8f)
                    .getInterpolation(it)
            })
        )

        delay(2000L)
        onNavigate(UiEvent.Navigate(Route.MainScreen))

    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Surface(
           modifier = modifier
               .size(500.dp)
               .scale(scale.value)
               .padding(15.dp),
           shape = CircleShape,


       ) {
           Image(
               painter = painterResource(id = R.drawable.img),
               contentDescription = "Dictionary Image"
           )
       }

        Text(
            text = "Welcome to Dictionary App",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.error,
            fontSize = 24.sp
        )

    }

}