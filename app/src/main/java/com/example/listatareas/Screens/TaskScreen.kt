package com.example.listatareas.Screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listatareas.BaseDeDatos.TaskDatabaseHelper

@Composable
fun TaskScreen(context: Context) {
    val dbHelper = TaskDatabaseHelper(context)
    var tasks by remember { mutableStateOf(dbHelper.getAllTasks()) }
    var showDialog by remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { showDialog = true }) {
            Text("Add Task")
        }
        Spacer(modifier = Modifier.height(16.dp))
        tasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(task.title ?: "No Title")
                Row {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = {
                            dbHelper.markTaskAsDone(task.id, !task.done)
                            tasks = dbHelper.getAllTasks()
                        }
                    )
                    Button(onClick = {
                        dbHelper.deleteTask(task.id)
                        tasks = dbHelper.getAllTasks()
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add Task") },
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
                                Text("Title")
                            }
                            innerTextField()
                        }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    dbHelper.addTask(taskTitle)
                    tasks = dbHelper.getAllTasks()
                    taskTitle = ""
                    showDialog = false
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}