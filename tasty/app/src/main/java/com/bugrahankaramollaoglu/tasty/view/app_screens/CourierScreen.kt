package com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.RequestLocationPermission
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("MissingPermission")
@Composable
fun CourierScreen(navController: NavController) {
    var customIcon by remember { mutableStateOf<BitmapDescriptor?>(null) }

    val context = LocalContext.current
    var isMapLoaded by remember { mutableStateOf(false) }
    var locationPermissionGranted by remember { mutableStateOf(false) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    val cameraPositionState = rememberCameraPositionState()

    val removeIcon = painterResource(id = R.drawable.remove)

    val restaurantLocation = LatLng(41.273844, 36.345401)

    RequestLocationPermission(onPermissionGranted = {
        locationPermissionGranted = true
    }, onPermissionDenied = {
        locationPermissionGranted = false
    })

    // Fetch user location once permission is granted
    LaunchedEffect(locationPermissionGranted) {
        if (locationPermissionGranted) {
            Log.d("mesaj", "lokasyon izni VERİLDİ")
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            val locationRequest =
                LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000L).build()

            fusedLocationClient.requestLocationUpdates(
                locationRequest, object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        val location = locationResult.lastLocation ?: return
                        val latLng = LatLng(location.latitude, location.longitude)
                        userLocation = latLng
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                        fusedLocationClient.removeLocationUpdates(this) // sürekli mapi updatelemesini onluyor
                    }
                }, Looper.getMainLooper()
            )
        } else {
            navController.navigate("home")
            Log.d("mesaj", "lokasyon izni verilmedi")
        }
        Log.d("mesaj", "location: ${userLocation}")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed)
    ) {
        if (locationPermissionGranted && userLocation != null) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize(),
//                    .statusBarsPadding(), // Add padding for status bar (top)
//                    .navigationBarsPadding(), // Add padding for navigation bar (bottom)
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true),
                uiSettings = MapUiSettings(
                    compassEnabled = true,
                    myLocationButtonEnabled = false,
                    zoomControlsEnabled = false
                ),
                onMapLoaded = {
                    customIcon =
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                    isMapLoaded = true
                }
            ) {
                Marker(
                    state = MarkerState(position = userLocation!!),
                    title = "You are here"
                )

                if (customIcon != null) {
                    Marker(
                        state = MarkerState(position = restaurantLocation),
                        title = "Your restaurant",
                        icon = customIcon
                    )
                }
            }

            // Custom My Location Button
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 50.dp, end = 20.dp)
                    .size(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(elevation = 4.dp)
                    .background(CustomColors.CustomRed)
                    .clickable {
                        userLocation?.let {
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "My Location",
                    tint = CustomColors.CustomWhite
                )
            }

            // Custom Zoom Controls
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 50.dp, end = 20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(elevation = 4.dp)
                    .background(CustomColors.CustomRed),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Zoom In",
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            val currentZoom = cameraPositionState.position.zoom
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                                cameraPositionState.position.target,
                                currentZoom + 1f
                            )
                        },
                    tint = CustomColors.CustomWhite
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(30.dp)
                        .padding(5.dp),
                    color = CustomColors.CustomWhite2,
                )
                Icon(
                    painter = removeIcon,
                    contentDescription = "Zoom Out",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(horizontal = 5.dp)
                        .clickable {
                            val currentZoom = cameraPositionState.position.zoom
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                                cameraPositionState.position.target,
                                currentZoom - 1f
                            )
                        },
                    tint = CustomColors.CustomWhite
                )
            }
        }

        if (!isMapLoaded) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = CustomColors.CustomWhite)
            }
        }

        // Back button (always shown)
        Box(
            modifier = Modifier
                .padding(top = 50.dp, start = 20.dp)
                .size(width = 70.dp, height = 25.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(elevation = 30.dp)
                .background(CustomColors.CustomRed)
                .clickable {
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Geri",
                modifier = Modifier.padding(2.dp),
                tint = CustomColors.CustomWhite
            )
        }
    }
}