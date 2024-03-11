package com.jayesh.jayesh_practical_android_indianic.ui.screens.map

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jayesh.jayesh_practical_android_indianic.ui.navigation.Screens
import com.jayesh.jayesh_practical_android_indianic.utility.UiState
import com.jayesh.jayesh_practical_android_indianic.utility.toast

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(navController: NavController, viewModel: MapViewModel) {

    val context = LocalContext.current

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val permissionsGranted = permissions.values.reduce { acc, isPermissionGranted ->
                acc && isPermissionGranted
            }

            if (permissionsGranted) {
                viewModel.getCurrentLocation()
            } else {
                context.toast(message = "Location permission is mandatory")
            }
        })

    Box(modifier = Modifier.fillMaxSize()) {

        val state by remember { viewModel.uiState }.collectAsStateWithLifecycle()

        when (state) {
            is UiState.Empty -> {
                LaunchedEffect(key1 = locationPermissions.allPermissionsGranted) {
                    if (locationPermissions.allPermissionsGranted) {
                        viewModel.getCurrentLocation()
                    } else {
                        locationPermissions.launchMultiplePermissionRequest()
                    }
                }
            }

            is UiState.Error -> context.toast(message = null)
            is UiState.Loading -> {}
            is UiState.Success -> {

                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(
                        LatLng(
                            viewModel.currentLocation!!.latitude,
                            viewModel.currentLocation!!.longitude
                        ), 10f
                    )
                }

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                viewModel.currentLocation!!.latitude,
                                viewModel.currentLocation!!.longitude
                            )
                        ),
                        title = "Singapore",
                        snippet = "Marker in Singapore"
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val address by viewModel.markerAddressDetail.collectAsStateWithLifecycle()
            when (address) {
                is UiState.Empty -> {}
                is UiState.Error -> {}
                is UiState.Loading -> {}
                is UiState.Success -> {
                    val addressData = (address as UiState.Success).data
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = addressData.getAddressLine(0))
                        }
                    }
                }
            }

            Button(
                onClick = {
                    navController.navigate(
                        Screens.WeatherScreen.withArgs(
                            viewModel.currentLocation!!.latitude,
                            viewModel.currentLocation!!.longitude
                        )
                    )
                }
            ) {
                Text(text = "Choose")
            }
        }
    }
}