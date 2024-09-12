/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.test.wearosapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.test.wearosapp.R
import com.test.wearosapp.presentation.theme.ComposeUITipsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    ComposeUITipsTheme {
        CircularProgress()
    }
}

@Composable
fun CircularProgress(modifier: Modifier = Modifier) {

    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(Color.Transparent),

        progress = 0.8f,
        strokeWidth = 2.dp,
        indicatorColor = Color.Cyan
    )
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .padding(9.dp)
            .background(Color.Transparent),

        progress = 0.7f,
        strokeWidth = 2.dp,
        indicatorColor = Color.Green
    )


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxHeight()
    ) {
        Text(
            text = "Steps",
            fontSize = 10.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "2023",
            fontSize = 30.sp,
            color = Color.Green,
            fontWeight = FontWeight.W900,
            modifier = Modifier
        )
        Text(
            text = "7000",
            fontSize = 10.sp,
            fontWeight = FontWeight.W200,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            CircleShapeUI(id = R.drawable.monitor_heart,"84","Heart")
            CircleShapeRefreshUI()
            CircleShapeUI(id = R.drawable.baseline_tungsten_24,"146","Kcal")
        }
        Spacer(modifier = Modifier.height(7.dp))
        CircleShapeShareUI()
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun CircleShapeUI(
    @DrawableRes id: Int,
    label1:String,
    label2:String,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .size(35.dp)
            .background(Color.DarkGray, shape = CircleShape)
    ) {

        Image(painter = painterResource(id = id),
            modifier = Modifier.size(10.dp),
            contentDescription = "")

        Text(
            text = label1,
            fontSize = 7.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = label2,
            fontSize = 6.sp,
            fontWeight = FontWeight.W200,
        )

    }
}

@Composable
fun CircleShapeRefreshUI(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .size(35.dp)
            .background(Color.DarkGray, shape = CircleShape)
    ) {

        Image(painter = painterResource(id = R.drawable.baseline_refresh_24),
            modifier = Modifier.size(20.dp),
            contentDescription = "")
    }
}

@Composable
fun CircleShapeShareUI(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .size(35.dp)
            .background(Color.DarkGray, shape = CircleShape)
    ) {

        Image(painter = painterResource(id = R.drawable.baseline_share_24),
            modifier = Modifier.size(10.dp),
            contentDescription = "")

        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = "Share",
            fontSize = 6.sp,
            fontWeight = FontWeight.W200,
        )

    }
}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}