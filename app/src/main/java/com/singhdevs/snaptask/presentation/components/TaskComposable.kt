package com.singhdevs.snaptask.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.model.TaskStatus
import com.singhdevs.snaptask.presentation.Screen
import com.singhdevs.snaptask.ui.theme.LocalSnapTaskColors


@Composable
fun TaskComposable(
    navController: NavController,
    modifier: Modifier = Modifier,
    task: Task,
    cardCornerRadius: Dp = 10.dp,
    onDelete: () -> Unit
) {
    val snapTaskColors = LocalSnapTaskColors.current

    Card(
        modifier = modifier
            .border(
                width = 2.dp,
                color = when (task.status) {
                    TaskStatus.COMPLETED -> snapTaskColors.greenTaskCardBorder
                    TaskStatus.IN_PROGRESS -> snapTaskColors.yellowTaskCardBorder
                    else -> snapTaskColors.grayTaskCardBorder
                },
                RoundedCornerShape(cardCornerRadius)
            ),
        border = CardDefaults.outlinedCardBorder(),
        colors = CardDefaults.cardColors(
            containerColor =
            when (task.status) {
                TaskStatus.COMPLETED -> snapTaskColors.greenTaskCard
                TaskStatus.IN_PROGRESS -> snapTaskColors.yellowTaskCard
                else -> snapTaskColors.grayTaskCard
            }
        ),
        onClick = {
            navController.navigate(
                Screen.UpdateTaskScreen.route +
                        "?taskId=${task.id}&taskStatus=${task.status}"
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = task.title,
                fontWeight = FontWeight.Black,
                fontSize = 26.sp,
                color = snapTaskColors.titleText
            )

            Text(
                text = task.subtitle,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                color = snapTaskColors.subtitleText
            )
        }

        Icon(
            Icons.Filled.Delete,
            contentDescription = "delete_task",
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.End)
                .clickable {
                    onDelete()
                }
        )
    }
}