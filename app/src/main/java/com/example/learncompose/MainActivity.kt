package com.example.learncompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import com.example.learncompose.screens.ComposeUnitConverterScreen
import com.example.learncompose.screens.DistancesConverter
import com.example.learncompose.screens.TemperatureConverter
import com.example.learncompose.ui.theme.ComposeUnitConverterTheme
import com.example.learncompose.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val factory = ViewModelFactory(Repository(sp))
        setContent {
            ComposeUnitConverter(factory = factory)
            //LaunchEffectDemo()
            //StopWatchWithoutLaunchEffect()
            //StopWatchWithDisposableEffect()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComposeUnitConverter(factory: ViewModelFactory) {
    val navController = rememberNavController()
    val menuItems = listOf("list item #1", "list item #2")
    val snackbarCoroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    ComposeUnitConverterTheme{
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                ComposeUnitConverterTopBar(menuItems = menuItems) { s ->
                    snackbarCoroutineScope.launch {
                        snackbarHostState.showSnackbar(s)
                    }
                }
            },
            bottomBar = {
                ComposeUnitConverterBottomBar(navController = navController)
            }
        ) {
            ComposeUnitConverterNavHost(
                navController = navController,
                factory = factory,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Preview
@Composable
fun Preview_ComposeUnitConverter() {
    //val factory = ViewModelFactory(Repository())
    
    //ComposeUnitConverter(factory = factory)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeUnitConverterTopBar(
    menuItems: List<String>,
    onClick: (String) -> Unit
) {
    var menuOpened by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        actions = {
            Box {
                IconButton(
                    onClick = {
                        menuOpened = true
                    }
                ) {
                    Icon(Icons.Default.MoreVert, "")
                }

                DropdownMenu(
                    expanded = menuOpened,
                    onDismissRequest = {
                        menuOpened = false
                    }
                ) {
                    menuItems.forEachIndexed{ index, s ->
                        if (index > 0) Divider()

                        DropdownMenuItem(
                            text = {
                                Text(text = s)
                            },
                            onClick = {
                                menuOpened = false
                                onClick(s)
                            }
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_ComposeUnitConverterTopBar() {
    ComposeUnitConverterTopBar(
        menuItems = listOf("optionA", "optionB", "optionC"),
        onClick = {}
    )
}

@Composable
fun ComposeUnitConverterBottomBar(
    navController: NavController
) {
    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        ComposeUnitConverterScreen.screens.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.route } == true,
                //selected = currentDestination?.route == screen.route
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(text = stringResource(id = screen.label))
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = stringResource(id = screen.label)
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun ComposeUnitConverterNavHost(
    navController: NavHostController,
    factory: ViewModelProvider.Factory?,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = ComposeUnitConverterScreen.route_temperature,
        modifier = modifier
    ) {
        composable(ComposeUnitConverterScreen.route_temperature) {
            TemperatureConverter(
                viewModel(factory = factory)
            )
        }

        composable(ComposeUnitConverterScreen.route_distances) {
            DistancesConverter(
                viewModel(factory = factory)
            )
        }
    }
}