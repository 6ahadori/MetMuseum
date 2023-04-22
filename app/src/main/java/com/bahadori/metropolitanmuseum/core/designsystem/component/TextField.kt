package com.bahadori.metropolitanmuseum.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun MetTextField(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    debounceDuration: Long = 500L
) {
    val debouncedText = remember(value) { mutableStateOf(value) }
    var debounceJob by remember { mutableStateOf<Job?>(null) }

    // Whenever the text value changes, cancel the previous debounce job
    // and launch a new one after the specified debounce duration
    LaunchedEffect(debouncedText.value) {
        debounceJob?.cancel()
        debounceJob = launch {
            delay(debounceDuration)
            onValueChange(debouncedText.value)
        }
    }

    OutlinedTextField(
        value = debouncedText.value,
        onValueChange = { newText ->
            debouncedText.value = newText
        },
        label = { Text(stringResource(id = label)) },
    )
}