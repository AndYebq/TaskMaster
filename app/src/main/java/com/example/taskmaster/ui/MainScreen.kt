package com.example.taskmaster.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmaster.ui.components.TaskItem
import com.example.taskmaster.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: TaskViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo para título
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título de la tarea") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            leadingIcon = { Icon(
                Icons.Default.Person,
                contentDescription = "Título")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para descripción
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Descripción",
                    tint = Color.Red
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Selector de fecha
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = selectedDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                onValueChange = {},
                label = { Text("Fecha") },
                modifier = Modifier.weight(1f),
                enabled = false,
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Fecha") },
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "Seleccionar fecha"
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Selector de hora
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = selectedTime?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: "",
                onValueChange = {},
                label = { Text("Hora") },
                modifier = Modifier.weight(1f),
                enabled = false,
                leadingIcon = {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "Hora"
                    )
                              },
                trailingIcon = {
                    IconButton(onClick = { showTimePicker = true }) {
                        Icon(Icons.Default.Check, contentDescription = "Seleccionar hora")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar
        Button(
            onClick = {
                if (title.isNotBlank()) {
                    viewModel.addTask(
                        title = title,
                        description = description,
                        dueDate = selectedDate,
                        dueTime = selectedTime
                    )
                    title = ""
                    description = ""
                    selectedDate = null
                    selectedTime = null
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = title.isNotBlank()
        ) {
            Icon(Icons.Default.Add, contentDescription = "Añadir")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Agregar Tarea")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de tareas
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.tasks) { task ->
                TaskItem(
                    task = task,
                    onTaskChecked = { viewModel.toggleTaskCompletion(task.id) },
                    onDeleteTask = { viewModel.deleteTask(task.id) }
                )
            }
        }
    }

    // Selectores básicos (implementación alternativa sin librerías externas)
    if (showDatePicker) {
        AlertDialog(
            onDismissRequest = { showDatePicker = false },
            title = { Text("Seleccionar fecha") },
            text = { Text("Implementa tu selector de fecha aquí") },
            confirmButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            title = { Text("Seleccionar hora") },
            text = { Text("Implementa tu selector de hora aquí") },
            confirmButton = {
                Button(onClick = { showTimePicker = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}