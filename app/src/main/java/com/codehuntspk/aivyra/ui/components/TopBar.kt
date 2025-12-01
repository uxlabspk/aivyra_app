package com.codehuntspk.aivyra.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun TopBar(onBackPressed: () -> Unit) {
//    Row (
//        modifier = Modifier,
//    ) {
//        Icon(
//            Icons.Default.ArrowBack,
//            contentDescription = null
//        )
//        Text(
//            "TILE"
//        )
//    }

    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

    ) {
        Text("Hello, World!")
        Box(
            modifier = Modifier
                .background(Color(0xff444444))
                .height(128.dp)
        )
        Text("Hello, World!")
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 100)
@Composable
fun TopBarPreview() {
    TopBar({})
}