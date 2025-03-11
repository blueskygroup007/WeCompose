package com.bluesky.wecompose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluesky.wecompose.R
import com.bluesky.wecompose.ui.theme.WeComposeTheme

/**
 *
 * @author BlueSky
 * @date 25.3.11
 * Description:
 */
//Gemini的方案一
data class TabItem(
    val title: String,
    val unselectedIcon: Int,
    val selectedIcon: Int
)

@Composable
fun WeBottomBar1(onTabSelected: (Int) -> Unit) {
    val tabItems = listOf(
        TabItem("聊天", R.drawable.chat_outlined, R.drawable.chat_filled),
        TabItem("通讯录", R.drawable.contact_outlined, R.drawable.contact_filled),
        TabItem("发现", R.drawable.discovery_outlined, R.drawable.discovery_filled),
        TabItem("我", R.drawable.me_outlined, R.drawable.me_filled)
    )
    var selectedTabIndex by remember { mutableStateOf(0) }

    NavigationBar {
        tabItems.forEachIndexed { index, tabItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selectedTabIndex == index) {
                                tabItem.selectedIcon
                            } else {
                                tabItem.unselectedIcon
                            }
                        ),
                        contentDescription = tabItem.title
                    )
                },
                label = { Text(text = tabItem.title) }
            )
        }
    }

}


//Gemini的方案二
// 定义一个数据类来表示每个底部导航栏的项
data class BottomBarItem(
    val title: String,
    val icon: Int, // 图标的资源 ID
    val iconSelected: Int, // 选中的图标的资源 ID，默认为未选中的图标
    val screen: @Composable () -> Unit // 屏幕的可组合函数
)

@Composable
fun WeBottomBar2(bottomBarItems: List<BottomBarItem>, onItemSelected: (Int) -> Unit) {


    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        bottomBarItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedIndex == index) painterResource(id = item.iconSelected) else painterResource(
                            id = item.icon
                        ),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    onItemSelected(index)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.error,
                    unselectedIconColor = MaterialTheme.colorScheme.scrim,
                    selectedTextColor = MaterialTheme.colorScheme.error,
                    unselectedTextColor = MaterialTheme.colorScheme.scrim,

                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestWeBottomBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    // 定义底部导航栏项的列表
    val bottomBarItems = listOf(
        BottomBarItem(
            "聊天",
            R.drawable.chat_outlined,
            R.drawable.chat_filled
        ) { Text("聊天 页面") },
        BottomBarItem(
            "通讯录",
            R.drawable.contact_outlined,
            R.drawable.contact_filled
        ) { Text("通讯录 页面") },
        BottomBarItem(
            "发现",
            R.drawable.discovery_outlined,
            R.drawable.discovery_filled
        ) { Text("发现 页面") },
        BottomBarItem("我的", R.drawable.me_outlined, R.drawable.me_filled) { Text("我的 页面") }
    )
    Scaffold(
        bottomBar = {
            WeBottomBar2(bottomBarItems) { index ->
                // 在这里处理点击事件
                println("选中的索引: $index")
                selectedIndex = index

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // 屏幕的内容
            bottomBarItems[selectedIndex].screen()
        }
    }
}


//视频中的方案
@Composable
fun WeBottomBar(selected: Int) {
    var selectedTab by remember { mutableIntStateOf(0) }
    Row(Modifier.fillMaxWidth()) {
        TabItem(
            if (selected == 0) R.drawable.chat_filled else R.drawable.chat_outlined,
            "聊天",
            Modifier
                .weight(1f)
                .clickable { },
            if (selected == 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.scrim
        )
        TabItem(
            if (selected == 1) R.drawable.contact_filled else R.drawable.contact_outlined,
            "通讯录",
            Modifier
                .weight(1f)
                .clickable { },
            if (selected == 1) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.scrim
        )
        TabItem(
            if (selected == 2) R.drawable.discovery_filled else R.drawable.discovery_outlined,
            "发现",
            Modifier
                .weight(1f)
                .clickable { },
            if (selected == 2) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.scrim
        )
        TabItem(
            if (selected == 3) R.drawable.me_filled else R.drawable.me_outlined,
            "我",
            Modifier
                .weight(1f)
                .clickable {

                },
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

