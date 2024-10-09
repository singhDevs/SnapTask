package com.singhdevs.snaptask.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.ui.theme.LocalSnapTaskColors

@Composable
fun PriorityChip(
    priority: TaskPriority,
    isSelected: Boolean,
    onClick: (TaskPriority) -> Unit
) {
    val snapTaskColors = LocalSnapTaskColors.current

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick(priority) }
            .border(
                2.dp,
                snapTaskColors.chipBorder,
                RoundedCornerShape(15.dp)
            ),
    ) {
        Text(
            text = priority.name,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            color = snapTaskColors.chipText,
            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Normal
        )
    }
}