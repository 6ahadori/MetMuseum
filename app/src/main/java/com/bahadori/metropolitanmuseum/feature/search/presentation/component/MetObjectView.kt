package com.bahadori.metropolitanmuseum.feature.search.presentation.component

import android.R.attr.maxLines
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bahadori.metropolitanmuseum.R
import com.bahadori.metropolitanmuseum.core.designsystem.component.DynamicAsyncImage
import com.bahadori.metropolitanmuseum.model.data.MetObject


@ExperimentalMaterial3Api
@Composable
fun MetObjectView(metObject: MetObject, onObjectClicked: (MetObject) -> Unit) {

    Card(
        modifier = Modifier.size(120.dp, 160.dp),
        onClick = {
            onObjectClicked(metObject)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val arcShape = RoundedCornerShape(120.dp, 120.dp, 0.dp, 0.dp)
            DynamicAsyncImage(
                imageUrl = metObject.primaryImage ?: "",
                contentDescription = metObject.objectName,
                modifier = Modifier
                    .clip(arcShape)
                    .size(90.dp, 110.dp),
                error = R.drawable.placeholder2,
                placeholder = R.drawable.placeholder,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = metObject.title ?: "",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}