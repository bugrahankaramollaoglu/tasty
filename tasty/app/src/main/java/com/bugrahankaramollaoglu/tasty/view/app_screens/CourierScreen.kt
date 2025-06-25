package com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CourierScreen(navController: NavController) {

    var isMapLoaded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed)
    ) {
        val store = LatLng(37.7749, -122.4194)
        val home = LatLng(37.7749, -122.4194)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(store, 10f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = { isMapLoaded = true }
        ) {
            // Buraya ihtiyaç duyduğunuz ek harita bileşenlerini ekleyebilirsiniz
        }

        if (!isMapLoaded) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = CustomColors.CustomYellow)
            }

        } else {
            Box(
                modifier = Modifier
                    .padding(top = 50.dp, start = 20.dp)
                    .size(width = 70.dp, height = 25.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shadow(elevation = 30.dp)
                    .background(CustomColors.CustomRed)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Geri",
                    modifier = Modifier.padding(2.dp),
                    tint = CustomColors.CustomYellow
                )
            }
        }

    }
}