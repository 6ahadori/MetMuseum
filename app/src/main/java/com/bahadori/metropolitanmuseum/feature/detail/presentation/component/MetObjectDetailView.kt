package com.bahadori.metropolitanmuseum.feature.detail.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.bahadori.metropolitanmuseum.R
import com.bahadori.metropolitanmuseum.core.designsystem.theme.gold
import com.bahadori.metropolitanmuseum.model.data.MetObject


@Composable
fun MetObjectDetailView(
    modifier: Modifier = Modifier,
    metObject: MetObject,
) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 160.dp, start = 16.dp, end = 16.dp), colors =
        CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.8f
            )
        ),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp, 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                text = metObject.title ?: "",
                style = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold
                ),
                textAlign = TextAlign.Center
            )

            val content = buildAnnotatedString {
                // Artist Name
                metObject.artistDisplayName?.let { artistName ->
                    if (artistName.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.artist))
                        }
                        withStyle(style = SpanStyle(gold)) {
                            append(artistName)
                        }
                        append("\n")
                    }
                }

                // Department Name
                metObject.department?.let { departmentName ->
                    if (departmentName.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.department))
                        }
                        withStyle(style = SpanStyle(gold)) {
                            append(departmentName)
                        }
                        append("\n")
                    }
                }

                // Year Name
                metObject.objectDate?.let { objectDate ->
                    if (objectDate.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.year))
                        }
                        withStyle(style = SpanStyle(gold)) {
                            append(objectDate)
                        }
                        append("\n")
                    }
                }

                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Black
                    )
                ) {
                    append(stringResource(id = R.string.more))
                    append("\n")
                }

                // Constituents
                metObject.constituents?.forEach { constituent ->
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("${constituent.role}: ")
                    }
                    withStyle(style = SpanStyle(gold)) {
                        append(constituent.name)
                    }
                    append("\n")
                }

                // Geography
                metObject.geographyType?.let { type ->
                    val geos = listOf(
                        metObject.city,
                        metObject.state,
                        metObject.country
                    ).filterNot { it.isNullOrEmpty() }
                        .joinToString(", ")

                    if (type.isNotEmpty() && geos.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.geography))
                        }
                        withStyle(style = SpanStyle(gold)) {
                            append(geos)
                        }
                        append("\n")
                    }
                }

                // Culture
                metObject.culture?.let { culture ->
                    if (culture.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.culture))
                        }
                        withStyle(style = SpanStyle(gold)) {
                            append(culture)
                        }
                        append("\n")
                    }
                }

                // Medium
                metObject.medium?.let { medium ->
                    if (medium.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.medium))
                        }
                        withStyle(style = SpanStyle(gold)) {
                            append(medium)
                        }
                        append("\n")
                    }
                }

                // Dimensions
                metObject.dimensions?.let { dimensions ->
                    if (dimensions.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.dimensions))
                        }
                        withStyle(style = SpanStyle(gold)) {
                            append(dimensions)
                        }
                        append("\n")
                    }
                }

                // Credit Line
                metObject.creditLine?.let { creditLine ->
                    if (creditLine.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.credit_line))
                        }
                        withStyle(style = SpanStyle(gold)) {
                            append(creditLine)
                        }
                        append("\n")
                    }
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = content,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                if (!metObject.objectWikidataUrl.isNullOrEmpty())
                    OutlinedButton(
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White.copy(alpha = 0.6f),
                            contentColor = MaterialTheme.colorScheme.tertiaryContainer
                        ),
                        border = BorderStroke(0.5.dp, Color.Black),
                        onClick = {
                            uriHandler.openUri(metObject.objectWikidataUrl)
                        }) {
                        Icon(
                            modifier = Modifier.padding(end = 4.dp),
                            painter = painterResource(id = R.drawable.ic_wikipedia),
                            contentDescription = stringResource(
                                id = R.string.wikipedia_link
                            )
                        )
                        Text(text = stringResource(id = R.string.wikipedia))
                    }


                if (!metObject.objectURL.isNullOrEmpty() && !metObject.objectWikidataUrl.isNullOrEmpty())
                    Spacer(modifier = Modifier.width(8.dp))

                if (!metObject.objectURL.isNullOrEmpty())
                    OutlinedButton(
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White.copy(alpha = 0.6f),
                            contentColor = MaterialTheme.colorScheme.tertiaryContainer
                        ),
                        border = BorderStroke(0.5.dp, Color.Black),
                        onClick = {
                            uriHandler.openUri(metObject.objectURL)
                        }) {
                        Icon(
                            modifier = Modifier.padding(end = 4.dp),
                            painter = painterResource(id = R.drawable.ic_metmuseum),
                            contentDescription = stringResource(
                                id = R.string.metmuseum_link
                            )
                        )
                        Text(text = stringResource(id = R.string.metmuseum))
                    }
            }
        }
    }
}