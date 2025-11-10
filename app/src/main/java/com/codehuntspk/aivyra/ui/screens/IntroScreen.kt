package com.codehuntspk.aivyra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehuntspk.aivyra.R
import com.codehuntspk.aivyra.ui.components.PrimaryButton


@Composable
fun IntroScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(150.dp, 150.dp),
            painter = painterResource(id = R.drawable.img_app),
            contentDescription = null
        )

        Text(
            "Aivyra AI"
        )

        Text(
            "Aivyra AI"
        )

        PrimaryButton(
            text = "Start Free Trial",
            onClick = { /* navigate to trial */ }
        )

    }
}


@Composable
@Preview(showBackground = true, widthDp = 300, heightDp = 700)
fun IntroScreenPreview() {
    IntroScreen()
}