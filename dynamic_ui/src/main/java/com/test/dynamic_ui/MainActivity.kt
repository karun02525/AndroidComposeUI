@file:OptIn(ExperimentalMaterial3Api::class)

package com.test.dynamic_ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.test.dynamic_ui.ui.theme.ComposeUITipsTheme
import org.json.JSONArray
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeUITipsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DynamicViewExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class DynamicItem(
    var name: String="",
    val imageResId: Int= R.drawable.ic_launcher_foreground,
    var age:String="10",
    var uuid:String= UUID.randomUUID().toString()
)

@Preview(showBackground = true)
@Composable
fun DynamicViewExample(modifier:Modifier=Modifier) {
    // List of dynamic items
    val context= LocalContext.current
    var items by remember { mutableStateOf(listOf(DynamicItem())) }

    Column(modifier = Modifier.padding(36.dp)) {
        // Button to add a new item
        Button(onClick = {
            items = items + DynamicItem() // Add a new item with default text and image
        }) {
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        items.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = "Item Image",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )

                Spacer(modifier = Modifier.width(6.dp))

                // Title
                TextField(
                    value = item.name,
                    onValueChange = { newText ->
                        items = items.toMutableList().apply {
                            this[index] = this[index].copy(name = newText)
                        }
                    },
                    label = { Text("Item $index") },
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                    ,
                )

                //Age
                TextField(
                    value = item.age,
                    onValueChange = { ageText ->
                        items = items.toMutableList().apply {
                            this[index] = this[index].copy(age = ageText)
                        }
                    },
                    label = { Text("Age") },
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.6f),
                )

                IconButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.3f),
                    onClick = {
                    items = items.toMutableList().apply {
                        this.removeAt(index)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear text"
                    )
                }
            }
        }



        Button(onClick = {
            val jsonData = Gson().toJson(items)
            Log.d("TAGS", "DynamicViewExample:$jsonData" )

            Toast.makeText(context,jsonData,Toast.LENGTH_SHORT).show()

        }) {
            Text("Submit")
        }
    }
}