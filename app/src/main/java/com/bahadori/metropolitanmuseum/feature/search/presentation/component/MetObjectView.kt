package com.bahadori.metropolitanmuseum.feature.search.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bahadori.metropolitanmuseum.R
import com.bahadori.metropolitanmuseum.core.designsystem.component.DynamicAsyncImage
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.MetObject

@ExperimentalMaterial3Api
@Composable
fun MetObjectView(metObject: MetObject, onObjectClicked: (MetObject) -> Unit) {

    Card(
        onClick = {
            onObjectClicked(metObject)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DynamicAsyncImage(
                imageUrl = metObject.primaryImage ?: "",
                contentDescription = metObject.objectName,
                modifier = Modifier
                    .clip(RoundedCornerShape(120.dp, 120.dp, 0.dp, 0.dp))
                    .size(110.dp, 120.dp),
                error = R.drawable.placeholder,
                placeholder = R.drawable.placeholder,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = metObject.objectName ?: "")
        }
    }
}