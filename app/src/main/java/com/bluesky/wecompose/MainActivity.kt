package com.bluesky.wecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bluesky.wecompose.ui.BottomBarItem
import com.bluesky.wecompose.ui.WeBottomBar
import com.bluesky.wecompose.ui.WeBottomBar1
import com.bluesky.wecompose.ui.WeBottomBar2
import com.bluesky.wecompose.ui.theme.WeComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            WeComposeTheme {
/*                var selectedIndex by remember { mutableIntStateOf(0) }

                // 定义底部导航栏项的列表
                val bottomBarItems = listOf(BottomBarItem(
                    "聊天", R.drawable.chat_outlined, R.drawable.chat_filled
                ) { Text("聊天 页面") }, BottomBarItem(
                    "通讯录", R.drawable.contact_outlined, R.drawable.contact_filled
                ) { Text("通讯录 页面") }, BottomBarItem(
                    "发现", R.drawable.discovery_outlined, R.drawable.discovery_filled
                ) { Text("发现 页面") }, BottomBarItem(
                    "我的", R.drawable.me_outlined, R.drawable.me_filled
                ) { Text("我的 页面") })
                Scaffold(bottomBar = {
                    WeBottomBar2(bottomBarItems) { index ->
                        // 在这里处理点击事件
                        selectedIndex = index
                    }
                }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        // 屏幕的内容
                        bottomBarItems[selectedIndex].screen()
                    }
                }
            }*/
                MainScreen()
            }
        }

    }

    sealed class Screen(val route: String, val title: String, val icon: Int) {
        object Chat : Screen("chat", "Chat", R.drawable.chat_filled)
        object Contact : Screen("contact", "Contact", R.drawable.contact_filled)
        object Discovery : Screen("discovery", "Discovery", R.drawable.discovery_filled)
        object Me : Screen("me", "Me", R.drawable.me_filled)
    }

    @Composable
    fun MainScreen() {
        var selectedIndex by remember { mutableIntStateOf(0) }

        // 定义底部导航栏项的列表
        val bottomBarItems = listOf(BottomBarItem(
            "聊天", R.drawable.chat_outlined, R.drawable.chat_filled
        ) { Text("聊天 页面") }, BottomBarItem(
            "通讯录", R.drawable.contact_outlined, R.drawable.contact_filled
        ) { Text("通讯录 页面") }, BottomBarItem(
            "发现", R.drawable.discovery_outlined, R.drawable.discovery_filled
        ) { Text("发现 页面") }, BottomBarItem(
            "我的", R.drawable.me_outlined, R.drawable.me_filled
        ) { Text("我的 页面") })

        val navController = androidx.navigation.compose.rememberNavController()
        val items = listOf(
            Screen.Chat,
            Screen.Contact,
            Screen.Discovery,
            Screen.Me
        )

        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painterResource(id = screen.icon),
                                    contentDescription = null
                                )
                            },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Chat.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Chat.route) { HomeScreen() }
                composable(Screen.Contact.route) { DashboardScreen() }
                composable(Screen.Discovery.route) { NotificationsScreen() }
                composable(Screen.Me.route) { NotificationsScreen() }
            }
        }
    }

    @Composable
    fun HomeScreen() {
        Column {
            Text("Home Screen")
        }
    }

    @Composable
    fun DashboardScreen() {
        Column {
            Text("Dashboard Screen")
        }
    }

    @Composable
    fun NotificationsScreen() {
        Column {
            Text("Notifications Screen")
        }
    }
}