package com.minger.lexio.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R

@Composable
fun CardSideSwitcher(
    modifier: Modifier = Modifier,
    selected: Int,
    onSelectedChange: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SwitchItem(
            text = stringResource(R.string.front_side),
            selected = selected == 0,
            onClick = { onSelectedChange(0) },
            modifier = Modifier.weight(1f)
        )

        SwitchItem(
            text = stringResource(R.string.back_side),
            selected = selected == 1,
            onClick = { onSelectedChange(1) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SwitchItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(13.dp))
            .background(
                if (selected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    Color.Transparent
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = if (selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}