package com.singhdevs.snaptask.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.singhdevs.snaptask.domain.OrderType
import com.singhdevs.snaptask.domain.TaskOrder

@Composable
fun OrderComposable(
    modifier: Modifier = Modifier,
    taskOrder: TaskOrder = TaskOrder.Date(OrderType.DESCENDING),
    onOrderChange: (TaskOrder) -> Unit
) {
    Column(modifier) {
        LazyRow(Modifier.fillMaxWidth()) {
            item {
                DefaultChip(
                    text = "Title",
                    icon = { Icons.Filled.Info },
                    isSelected = taskOrder is TaskOrder.Title,
                    onSelected = { onOrderChange(TaskOrder.Title(taskOrder.orderType)) }
                )

                Spacer(modifier = Modifier.width(10.dp))

                DefaultChip(
                    text = "Status",
                    icon = { Icons.Filled.Menu },
                    isSelected = taskOrder is TaskOrder.Status,
                    onSelected = { onOrderChange(TaskOrder.Status(taskOrder.orderType)) }
                )

                Spacer(modifier = Modifier.width(10.dp))

                DefaultChip(
                    text = "Priority",
                    icon = { Icons.Filled.Warning },
                    isSelected = taskOrder is TaskOrder.Priority,
                    onSelected = { onOrderChange(TaskOrder.Priority(taskOrder.orderType)) }
                )

                Spacer(modifier = Modifier.width(10.dp))

                DefaultChip(
                    text = "Date",
                    icon = { Icons.Filled.DateRange },
                    isSelected = taskOrder is TaskOrder.Date,
                    onSelected = { onOrderChange(TaskOrder.Date(taskOrder.orderType)) }
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Row {
            DefaultChip(
                text = "Ascending",
                icon = { Icons.Filled.KeyboardArrowUp },
                isSelected = taskOrder is TaskOrder.Title,
                onSelected = { onOrderChange(taskOrder.copy(OrderType.ASCENDING)) }
            )

            Spacer(modifier = Modifier.width(10.dp))

            DefaultChip(
                text = "Descending",
                icon = { Icons.Filled.KeyboardArrowDown },
                isSelected = taskOrder is TaskOrder.Status,
                onSelected = { onOrderChange(taskOrder.copy(OrderType.DESCENDING)) }
            )
        }
    }
}