package com.codehuntspk.aivyra.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehuntspk.aivyra.R
import com.codehuntspk.aivyra.ui.components.PrimaryButton
import com.codehuntspk.aivyra.ui.theme.Purple900
import com.codehuntspk.aivyra.ui.theme.Slate900
import com.codehuntspk.aivyra.ui.theme.TextWhite60
import kotlinx.coroutines.delay


@Composable
fun IntroScreen(
    onGetStarted: () -> Unit = {},
) {
    // Animation states
    var startAnimation by remember { mutableStateOf(false) }

    // Floating animation for logo
    val infiniteTransition = rememberInfiniteTransition(label = "floating")
    val floatingOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatingOffset"
    )

    // Fade in animations
    val contentAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 800, delayMillis = 300),
        label = "contentAlpha"
    )

    // Slide up animation
    val slideUp by animateFloatAsState(
        targetValue = if (startAnimation) 0f else 50f,
        animationSpec = tween(durationMillis = 800, delayMillis = 300),
        label = "slideUp"
    )

    // Start animations on composition
    LaunchedEffect(Unit) {
        delay(100)
        startAnimation = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Slate900,
                        Purple900.copy(alpha = 0.6f),
                        Slate900
                    )
                )
            )
    ) {
       Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo with gradient background
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(28.dp)),
                    painter = painterResource(id = R.drawable.img_app),
                    contentDescription = "Aivyra Logo"
                )
            }

            // Main title with gradient text
            Text(
                text = "Aivyra AI",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .offset(y = slideUp.dp)
                    .graphicsLayer(alpha = contentAlpha)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = "Empowering you with cutting-edge artificial intelligence tools to automate, innovate, and elevate your workflow.",
                fontSize = 14.sp,
                color = TextWhite60,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier
                    .offset(y = slideUp.dp)
                    .graphicsLayer(alpha = contentAlpha)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Buttons
            Column(
                modifier = Modifier
                    .offset(y = slideUp.dp)
                    .graphicsLayer(alpha = contentAlpha),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PrimaryButton(
                    text = "Get Started",
                    onClick = onGetStarted,
                    modifier = Modifier.fillMaxWidth(0.85f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 18.dp)
                )
            }

        }
    }
}


@Composable
@Preview(showBackground = true, widthDp = 300, heightDp = 700)
fun IntroScreenPreview() {
    IntroScreen()
}