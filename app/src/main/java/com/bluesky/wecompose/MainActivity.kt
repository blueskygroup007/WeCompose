package com.bluesky.wecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluesky.wecompose.ui.theme.WeComposeTheme
import com.bluesky.wecompose.ui.theme.primaryLight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            WeComposeTheme {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                    Column {

                        WeBottomBar(0)
                    }
                }
            }
        }
    }

}

@Composable
private fun WeBottomBar(selected: Int) {
    Row(Modifier.fillMaxWidth()) {
        TabItem(
            if (selected == 0) R.drawable.chat_filled else R.drawable.chat_outlined,
            "聊天",
            Modifier.weight(1f),
            if (selected == 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.scrim
        )
        TabItem(
            if (selected == 1) R.drawable.contact_filled else R.drawable.contact_outlined,
            "通讯录", Modifier.weight(1f),
            if (selected == 1) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.scrim
        )
        TabItem(
            if (selected == 2) R.drawable.discovery_filled else R.drawable.discovery_outlined,
            "发现", Modifier.weight(1f),
            if (selected == 2) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.scrim
        )
        TabItem(
            if (selected == 3) R.drawable.me_filled else R.drawable.me_outlined,
            "我",
            Modifier.weight(1f),
            if (selected == 3) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.scrim
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreWeBottomBar() {
    WeComposeTheme {
        WeBottomBar(0)
    }
}

@Composable
fun TabItem(@DrawableRes iconId: Int, title: String, modifier: Modifier = Modifier, tint: Color) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconId),
            title,
            Modifier.size(24.dp),
            tint = tint
        )
        Text(text = title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun PreTabItem() {
    WeComposeTheme {
        TabItem(R.drawable.chat_outlined, "聊天", tint = MaterialTheme.colorScheme.primary)
    }
}