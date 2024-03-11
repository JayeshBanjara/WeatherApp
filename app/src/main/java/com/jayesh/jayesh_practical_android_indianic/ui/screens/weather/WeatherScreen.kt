package com.jayesh.jayesh_practical_android_indianic.ui.screens.weather

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jayesh.jayesh_practical_android_indianic.ui.common_components.CustomToolBar
import com.jayesh.jayesh_practical_android_indianic.ui.screenPadding
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.current_weather.CurrentWeatherScreen
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.current_weather.CurrentWeatherViewModel
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.forecast.ForecastScreen
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.forecast.ForecastViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherScreenViewModel
) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { viewModel.tabs.size }
    viewModel.tabIndex.intValue = pagerState.currentPage

    Scaffold(
        topBar = {
            CustomToolBar(title = "Weather")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    start = screenPadding(),
                    top = innerPadding.calculateTopPadding(),
                    end = screenPadding(),
                    bottom = screenPadding()
                )
                .fillMaxSize()
        ) {
            TabRow(selectedTabIndex = viewModel.tabIndex.intValue) {
                viewModel.tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = viewModel.tabIndex.intValue == index,
                        onClick = {
                            viewModel.tabIndex.intValue = index
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(text = title) },
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (viewModel.tabIndex.intValue == 0) {
                    val currentWeatherViewModel = hiltViewModel<CurrentWeatherViewModel>()
                    CurrentWeatherScreen(
                        viewModel = currentWeatherViewModel,
                        latitude = viewModel.latitude,
                        longitude = viewModel.longitude
                    )
                } else {
                    val foreCastViewModel = hiltViewModel<ForecastViewModel>()
                    ForecastScreen(
                        viewModel = foreCastViewModel,
                        latitude = viewModel.latitude,
                        longitude = viewModel.longitude
                    )
                }

            }
        }
    }


}