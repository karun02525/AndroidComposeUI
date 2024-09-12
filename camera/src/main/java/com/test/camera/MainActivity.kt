package com.test.camera

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.test.camera.ui.theme.ComposeUITipsTheme

class MainActivity : ComponentActivity() {

    private lateinit var getContent: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeUITipsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GalleryPermissionScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }


    @Composable
    fun GalleryPermissionScreen(modifier: Modifier = Modifier) {
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        getContent =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                imageUri = uri
            }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = { getContent.launch("image/*") }) {
                Text(text = "Open camera")
            }
            Spacer(modifier = Modifier.height(20.dp))

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(20.dp)
                        .size(200.dp)
                        .clip(RoundedCornerShape(30.dp))

                )
            }
        }

    }


}


@Composable
fun TestCounter(modifier: Modifier = Modifier) {
    var counter by remember {
        mutableStateOf(0)
    }

    Column {
        Button(onClick = { counter++ }) {
            Text(text = "Click Counter")
        }
        Text(text = ": $counter")
    }
}

@Composable
fun TestCompose() {

}

@Preview
@Composable
fun DefaultPreview(modifier: Modifier = Modifier) {
    ComposeUITipsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            TestCounter(modifier = Modifier.padding(innerPadding))
        }
    }
}