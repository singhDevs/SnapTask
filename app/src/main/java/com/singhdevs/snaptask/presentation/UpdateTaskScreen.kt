package com.singhdevs.snaptask.presentation

import android.text.format.DateUtils
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.room.Update
import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.domain.model.TaskStatus
import com.singhdevs.snaptask.presentation.components.CustomTextField
import com.singhdevs.snaptask.presentation.components.PriorityChip
import com.singhdevs.snaptask.presentation.components.StatusChip
import com.singhdevs.snaptask.ui.theme.LocalSnapTaskColors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskScreen(
    navController: NavController,
    taskStatus: TaskStatus,
    viewModel: UpdateTaskViewModel = hiltViewModel()
) {
    val snapTaskColors = LocalSnapTaskColors.current

    val context = LocalContext.current
    val titleState = viewModel.taskTitle
    val subtitleState = viewModel.taskSubtitle
    val descriptionState = viewModel.taskDescription
    val priorityState = viewModel.taskPriority
    val statusState = viewModel.taskStatus
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val millisToLocalDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }
    val dateToLong = millisToLocalDate?.let {
        convertDateToMillis(millisToLocalDate)
    } ?: 0L
    val dateState = viewModel.taskDate

    val priorityList = listOf(
        TaskPriority.URGENT,
        TaskPriority.IMPORTANT,
        TaskPriority.NORMAL,
        TaskPriority.LOW
    )
    val statusList = listOf(
        TaskStatus.NOT_STARTED,
        TaskStatus.IN_PROGRESS,
        TaskStatus.COMPLETED
    )

    val scope = rememberCoroutineScope()
//    val scaffoldState = rememberScaffoldState()

    val taskBackgroundAnimatable = remember {
        Animatable(
            Color(viewModel.taskStatus.value.color.toArgb())
        )
    }

    // for displaying snackbar and after saving a task
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is TaskUI.DisplaySnackbar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message
//                    )
                }

                is TaskUI.SaveTask -> {
                    navController.navigateUp()
                }

                is TaskUI.CancelTask -> {
                    Log.d("SnapTask", "Add Task cancelled.")
                    navController.navigateUp()
                }
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        //Cancel & Save buttons
        Row(
            modifier = Modifier
                .padding(10.dp, top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                border = BorderStroke(2.dp, Color.Red),
                onClick = { viewModel.onEvent(UpdateTaskEvent.CancelTask) }
            ) {
                Icon(Icons.Filled.Clear, contentDescription = "save_task")
            }
            OutlinedButton(
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Green),
                border = BorderStroke(2.dp, Color.Green),
                onClick = { viewModel.onEvent(UpdateTaskEvent.SaveTask) }
            ) {
                Icon(Icons.Filled.Done, contentDescription = "save_task")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Priority Chips
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "priority",
                    tint = snapTaskColors.titleText
                )
                Text(
                    text = "Priority",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = snapTaskColors.titleText
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                priorityList.forEach { priority ->
                    PriorityChip(
                        priority = priority,
                        isSelected = (priority == priorityState.value),
                        onClick = {
                            viewModel.onEvent(UpdateTaskEvent.PriorityChanged(it))
                        }
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            thickness = 2.dp
        )

        // Status Chips
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "status",
                    tint = snapTaskColors.titleText
                )
                Text(
                    text = "Status",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = snapTaskColors.titleText
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                statusList.forEach { status ->
                    StatusChip(
                        status = status,
                        isSelected = (status == statusState.value),
                        onClick = {
                            scope.launch {
                                taskBackgroundAnimatable.animateTo(
                                    targetValue = Color(taskStatus.color.toArgb()),
                                    animationSpec = tween(
                                        durationMillis = 400
                                    )
                                )
                            }
                            viewModel.onEvent(UpdateTaskEvent.StatusChanged(it))
                        }
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            thickness = 2.dp
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                modifier = Modifier.clickable {
                    showDatePicker = !showDatePicker
                },
                value = convertMillisToDate(dateState.value),
                onValueChange = {
                    viewModel.onEvent(
                        UpdateTaskEvent.DateChanged(
                            dateToLong
                        )
                    )
                },
                label = { Text(text = "Due date") },
                readOnly = true,
                leadingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                }
            )
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    Button(
                        onClick = { showDatePicker = false }
                    ) {
                        Text(text = "OK")
                        viewModel.onEvent(UpdateTaskEvent.DateChanged(datePickerState.selectedDateMillis ?: System.currentTimeMillis()))
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDatePicker = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    state = datePickerState,
                    showModeToggle = true

                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            thickness = 2.dp
        )

        CustomTextField(
            text = titleState.value.text,
            hint = titleState.value.hint,
            onValueChange = {
                viewModel.onEvent(UpdateTaskEvent.TitleEntered(it))
            },
            onFocusChange = {
                viewModel.onEvent(UpdateTaskEvent.TitleFocusChanged(it))
            },
            singleLine = false,
            maxLines = 2,
            isHintVisible = titleState.value.isHintVisible,
            textStyle = MaterialTheme.typography.headlineLarge,
            textColor = snapTaskColors.titleText
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomTextField(
            text = subtitleState.value.text,
            hint = subtitleState.value.hint,
            singleLine = false,
            maxLines = 2,
            onValueChange = {
                viewModel.onEvent(UpdateTaskEvent.SubtitleEntered(it))
            },
            onFocusChange = {
                viewModel.onEvent(UpdateTaskEvent.SubtitleFocusChanged(it))
            },
            isHintVisible = subtitleState.value.isHintVisible,
            textStyle = MaterialTheme.typography.headlineSmall,
            textColor = snapTaskColors.subtitleText
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomTextField(
            text = descriptionState.value.text,
            hint = descriptionState.value.hint,
            onValueChange = {
                viewModel.onEvent(UpdateTaskEvent.DescriptionEntered(it))
            },
            onFocusChange = {
                viewModel.onEvent(UpdateTaskEvent.DescriptionFocusChanged(it))
            },
            isHintVisible = descriptionState.value.isHintVisible,
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxHeight(),
            textColor = snapTaskColors.subtitleText
        )
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

fun convertDateToMillis(dateString: String): Long {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.parse(dateString)?.time ?: System.currentTimeMillis()
}
