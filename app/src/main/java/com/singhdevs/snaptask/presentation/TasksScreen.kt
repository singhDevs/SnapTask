package com.singhdevs.snaptask.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.singhdevs.snaptask.R
import com.singhdevs.snaptask.presentation.components.OrderComposable
import com.singhdevs.snaptask.presentation.components.TaskComposable
import com.singhdevs.snaptask.ui.theme.LocalSnapTaskColors
import java.time.LocalTime

private const val TAG = "SnapTask"

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel(),
    navController: NavController,
    toggleDarkTheme: () -> Unit
) {
    val snapTaskColors = LocalSnapTaskColors.current
    val state = viewModel.state.value
    var checked by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = getMessage() + "Guransh!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold
                )

                IconButton(
                    onClick = {
                        viewModel.onEvent(TasksEvent.ToggleOrderButtonVisibility)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Build,
                        contentDescription = "sort_tasks"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderButtonVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Want to switch to Dark theme?",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                            color = snapTaskColors.titleText
                        )
                        Switch(
                            checked = viewModel.darkThemeState.value,
                            onCheckedChange = {
                                toggleDarkTheme()
                                viewModel.onEvent(TasksEvent.DarkThemeToggled)
                                checked = it
                            },
                            thumbContent = if (viewModel.darkThemeState.value) {
                                {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_moon),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp),
                                    )
                                }
                            } else {
                                null
                            }
                        )
                    }
                    OrderComposable(taskOrder = state.taskOrder) {
                        viewModel.onEvent(TasksEvent.Order(it))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(state.taskList) { task ->
                    TaskComposable(
                        navController = navController,
                        modifier = Modifier.fillMaxWidth(),
                        task = task,
                        cardCornerRadius = 12.dp
                    ) {
                        viewModel.onEvent(TasksEvent.DeleteTask(task))
                        Log.d(TAG, "TasksScreen: Task deleted")
//                        Toast.makeText(, "Task deleted.", Toast.LENGTH_SHORT).show()
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp),
            onClick = { navController.navigate(Screen.UpdateTaskScreen.route) },
            shape = CircleShape,
            containerColor = Color.Green,
            contentColor = Color.Black,
            elevation = FloatingActionButtonDefaults.elevation(10.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "add_task")
        }
    }
}

fun getMessage(): String {
    val hr = LocalTime.now().hour
    return when (hr) {
        in 0..11 -> "Good Morning, "
        in 12..17 -> "Good Afternoon, "
        else -> "Good Evening, "
    }
}
