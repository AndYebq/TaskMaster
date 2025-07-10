package com.example.taskmaster.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmaster.ui.theme.TaskMasterTheme
import com.example.taskmaster.viewmodel.TaskViewModel

@Composable
fun TaskMasterApp(
    viewModel: TaskViewModel = viewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MainScreen(viewModel = viewModel)
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun TaskMasterAppPreview() {
    TaskMasterTheme {
        val testViewModel = TaskViewModel().apply {
            addTask("Tarea de ejemplo 1", null)
            addTask("Tarea completada", null)
        }
        TaskMasterApp(viewModel = testViewModel)
    }
}

private fun TaskViewModel.addTask(s: String, nothing: Nothing?) {

}
