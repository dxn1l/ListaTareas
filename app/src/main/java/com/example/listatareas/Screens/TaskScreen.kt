package com.example.listatareas.Screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listatareas.BaseDeDatos.TaskDatabaseHelper
import com.example.listatareas.ui.theme.azulClarito

@Composable
fun TaskScreen(context: Context, navigateToPending: () -> Unit, navigateToCompleted: () -> Unit) {
    val dbHelper = TaskDatabaseHelper(context)
    var tasks by remember { mutableStateOf(dbHelper.getAllTasks()) }
    var showDialog by remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = azulClarito
            )
        ) {
            Text("Añadir Tarea")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.weight(1f)) {
            LazyColumn {
                items(tasks) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(task.title ?: "")
                            Row {
                                Checkbox(
                                    checked = task.done,
                                    onCheckedChange = {
                                        dbHelper.markTaskAsDone(task.id, !task.done)
                                        tasks = dbHelper.getAllTasks()
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkmarkColor = Color.White,
                                        uncheckedColor = azulClarito,
                                        checkedColor = azulClarito
                                    )
                                )
                                Button(
                                    onClick = {
                                        dbHelper.deleteTask(task.id)
                                        tasks = dbHelper.getAllTasks()
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = azulClarito
                                    )
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = navigateToPending,
                colors = ButtonDefaults.buttonColors(
                    containerColor = azulClarito
                )
            ) {
                Text("Tareas Pendientes")
            }
            Button(
                onClick = navigateToCompleted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = azulClarito
                )
            ) {
                Text("Tareas Completadas")
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Añadir Tarea") },
            text = {
                Column {
                    BasicTextField(
                        value = taskTitle,
                        onValueChange = { taskTitle = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            if (taskTitle.isEmpty()) {
                                Text("Tarea")
                            }
                            innerTextField()
                        }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        dbHelper.addTask(taskTitle)
                        tasks = dbHelper.getAllTasks()
                        taskTitle = ""
                        showDialog = false
                    },
                    enabled = taskTitle.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = azulClarito
                    )
                ) {
                    Text("Añadir")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = azulClarito
                    )
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}