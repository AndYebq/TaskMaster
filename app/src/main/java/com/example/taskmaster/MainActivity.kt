package com.example.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmaster.ui.TaskMasterApp
import com.example.taskmaster.ui.components.TaskItem
import com.example.taskmaster.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TaskMasterApp()
            }
        }
    }
}
@Composable
fun MainScreen(viewModel: TaskViewModel) {
    // ... otro código

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.tasks) { task ->
            TaskItem(
                task = task,
                onTaskChecked = { isChecked ->
                    viewModel.toggleTaskCompletion(task.id)
                },
                onDeleteTask = {
                    viewModel.deleteTask(task.id)
                }
            )
        }
    }
}