package com.bahadori.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bahadori.designsystem.component.DynamicAsyncImage
import com.bahadori.designsystem.icon.MetIcons
import com.bahadori.model.MetObject


@ExperimentalMaterial3Api
@Composable
fun MetObjectView(metObject: com.bahadori.model.MetObject, onObjectClicked: (com.bahadori.model.MetObject) -> Unit) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier.size(120.dp, 180.dp),
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
                error = MetIcons.FallBack,
                placeholder = MetIcons.Placeholder,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = metObject.title ?: "",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}