package com.example.listatareas.Navigation

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.listatareas.Screens.CompletedTasksScreen
import com.example.listatareas.Screens.PendingTasksScreen
import com.example.listatareas.Screens.TaskScreen
import com.example.listatareas.ui.theme.azulClarito


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskApp(context: Context) {
    var currentScreen by remember { mutableStateOf(Screen.TaskScreen) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                       text= when (currentScreen) {
                            Screen.TaskScreen -> "Lista de tareas"
                            Screen.Pending -> "Lista de tareas pendientes"
                            Screen.Completed -> "Lista de tareas completadas"
                        },
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = azulClarito
                )
            )
        }
    )  { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

            when (currentScreen) {
                Screen.TaskScreen -> TaskScreen(
                    context,
                    navigateToPending = { currentScreen = Screen.Pending },
                    navigateToCompleted = { currentScreen = Screen.Completed }
                )

                Screen.Pending -> PendingTasksScreen(
                    context,
                    navigateBack = { currentScreen = Screen.TaskScreen }
                )

                Screen.Completed -> CompletedTasksScreen(
                    context,
                    navigateBack = { currentScreen = Screen.TaskScreen }
                )
            }
        }
    }
}

enum class Screen {
    TaskScreen,
    Pending,
    Completed
}