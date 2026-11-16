
package com.athirah.composelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.lazy.items as listItems
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var showStudents by rememberSaveable {
                    mutableStateOf(false)
                }

                Scaffold(
                    topBar = {
                        // No top bar needed for this exercise
                    }
                ) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        Button(
                            onClick = {
                                showStudents = !showStudents
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(
                                if (showStudents)
                                    "Show Registration Form"
                                else
                                    "Show Student List"
                            )
                        }

                        if (showStudents) {
                            StudentList(
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            RegisterScreen(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

// Exercise 1: Registration Form
@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Create Account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {

            Button(
                onClick = {
                    name = ""
                    email = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }

            Button(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text("Submit")
            }
        }
    }
}

// Student Data
data class Student(
    val name: String,
    val score: Int
)

// Exercise 6: Student List
@Composable
fun StudentList(modifier: Modifier = Modifier) {

    var grid by rememberSaveable {
        mutableStateOf(false)
    }

    val students = remember {
        List(50) {
            Student(
                "Student ${it + 1}",
                50 + it
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                grid = !grid
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (grid)
                    "Switch to List"
                else
                    "Switch to Grid"
            )
        }

        if (grid) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f)
            ) {
                gridItems(students) {
                    StudentCard(it)
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                listItems(students) {
                    StudentCard(it)
                }
            }
        }
    }
}

// Student Card
@Composable
fun StudentCard(student: Student) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color(0xFFEEEEEE))
            .padding(12.dp)
    ) {

        Text(
            text = student.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "Marks: ${student.score}",
            color = Color.Black
        )
    }
}
