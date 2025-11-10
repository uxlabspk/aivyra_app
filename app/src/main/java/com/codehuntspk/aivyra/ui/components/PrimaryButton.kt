package com.codehuntspk.aivyra.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehuntspk.aivyra.ui.theme.Cyan500
import com.codehuntspk.aivyra.ui.theme.Purple600
import com.codehuntspk.aivyra.ui.theme.Cyan500
import com.codehuntspk.aivyra.ui.theme.GradientColors
import com.codehuntspk.aivyra.ui.theme.Pink400
import com.codehuntspk.aivyra.ui.theme.Purple600

/**
 * Primary button component matching the Aivyra landing page style
 * Features gradient background, hover effects, and smooth animations
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    contentPadding: PaddingValues = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
    cornerRadius: Dp = 50.dp,
    gradient: List<Color> = GradientColors.cyanToPurple,
    shadowColor: Color = Cyan500.copy(alpha = 0.5f),
    shadowElevation: Dp = 8.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Animate scale on press
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "buttonScale"
    )

    // Animate shadow elevation on press
    val elevation by animateFloatAsState(
        targetValue = if (isPressed) shadowElevation.value / 2 else shadowElevation.value,
        animationSpec = tween(durationMillis = 100),
        label = "buttonElevation"
    )

    Box(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = elevation.dp,
                shape = RoundedCornerShape(cornerRadius),
                spotColor = shadowColor,
                ambientColor = shadowColor
            )
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                brush = if (enabled) {
                    Brush.linearGradient(gradient)
                } else {
                    Brush.linearGradient(
                        listOf(
                            Color.Gray.copy(alpha = 0.5f),
                            Color.Gray.copy(alpha = 0.5f)
                        )
                    )
                }
            )
            .clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null
            )
            .defaultMinSize(minHeight = 48.dp)
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (enabled) Color.White else Color.White.copy(alpha = 0.5f),
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    }
}

/**
 * Secondary button with glassmorphism effect
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    contentPadding: PaddingValues = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
    cornerRadius: Dp = 50.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "buttonScale"
    )

    Box(
        modifier = modifier
            .scale(scale)
            .clip(RoundedCornerShape(cornerRadius))
            .background(MaterialTheme.colorScheme.surface)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null
            )
            .defaultMinSize(minHeight = 48.dp)
            .padding(1.dp) // Border width
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp))
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (enabled) Color.White else Color.White.copy(alpha = 0.5f),
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    }
}

/**
 * Small button variant for compact spaces
 */
@Composable
fun PrimaryButtonSmall(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    PrimaryButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
        cornerRadius = 24.dp,
        shadowElevation = 4.dp
    )
}

/**
 * Large button variant for hero sections
 */
@Composable
fun PrimaryButtonLarge(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    PrimaryButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp),
        cornerRadius = 50.dp,
        shadowElevation = 12.dp
    )
}


@Preview(showBackground = true, widthDp = 300, heightDp = 400)
@Composable
fun PreviewPrimaryButton() {

    Column() {
        PrimaryButton(
            text = "Start Free Trial",
            onClick = { /* navigate to trial */ }
        )

        SecondaryButton(
            text = "Watch Demo",
            onClick = { /* play demo */ }
        )


        PrimaryButtonLarge(
            text = "Start Your Free Trial",
            onClick = { /* navigate */ }
        )


        PrimaryButton(
            text = "Custom Button",
            onClick = { },
            gradient = GradientColors.cyanViaPurpleToPink,
            cornerRadius = 16.dp,
            shadowColor = Pink400.copy(alpha = 0.5f)
        )
    }

}