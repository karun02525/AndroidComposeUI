package com.test.pagination.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.test.pagination.R
import com.test.pagination.domain.model.Data
import com.test.pagination.ui.theme.ComposeUITipsTheme
import com.test.pagination.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //https://api.instantwebtools.net/v1/passenger?page=0&size=10
    //https://medium.com/simform-engineering/list-view-with-pagination-using-jetpack-compose-e131174eac8e
    //https://reqres.in/api/users?page=1&per_page=10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeUITipsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lazyPagingItems = viewModel.pager.collectAsLazyPagingItems()

    LazyColumn {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it },
            contentType = lazyPagingItems.itemContentType { "MyPagingItems" }

        ) { index ->
            val item = lazyPagingItems[index]
            if (item != null) {
                FakeItem(item)
            }
        }

        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .align(Alignment.Center)
                            )

                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .align(Alignment.Center)
                            )

                        }
                    }
                }

                loadState.prepend is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .align(Alignment.Center)
                            )

                        }
                    }
                }
            }
        }
    }

}

@Composable
fun FakeItem(item: Data) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(10.dp)
       ) {

           Box(modifier = Modifier.size(100.dp)) {
               AsyncImage(
                   model = item.avatar,
                   contentDescription = "Sample Image",
                   modifier = Modifier.fillMaxSize(),
                   //  placeholder = painterResource(id = R.drawable.placeholder),
                   // error = painterResource(id = R.drawable.error)
               )
           }

           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ) {
               Text(
                   text = "" + item.firstName,
                   fontSize = 12.sp,
                   fontWeight = FontWeight.Bold,
                   modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
               )

               Text(
                   text = "" + item.lastName,
                   fontSize = 12.sp,
                   fontWeight = FontWeight.W100,
                   modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
               )

               Text(
                   text = "" + item.email,
                   fontSize = 12.sp,
                   fontWeight = FontWeight.W300,
                   modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
               )
           }
       }

       HorizontalDivider()
   }
}
