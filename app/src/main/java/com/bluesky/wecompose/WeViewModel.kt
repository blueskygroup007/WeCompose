package com.bluesky.wecompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 *
 * @author BlueSky
 * @date 25.3.11
 * Description:
 */
class WeViewModel: ViewModel() {
    var selectedTab by mutableStateOf(0)

}