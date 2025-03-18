package com.example.fetchexercise

import ItemList
import ItemViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fetchexercise.ui.theme.FetchExerciseTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchExerciseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    // Create a ViewModel instance
                    val itemViewModel: ItemViewModel = ItemViewModel()

                    // Display the UI with the list of items
                    Surface(modifier = Modifier.padding(16.dp, 36.dp)) {
                        itemViewModel.items.observeAsState(emptyList()).value.let {
                            ItemList(items = it)
                        }
                    }
                }
            }
        }
    }
}
