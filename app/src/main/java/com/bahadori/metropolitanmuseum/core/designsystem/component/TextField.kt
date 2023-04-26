package com.bahadori.metropolitanmuseum.core.designsystem.component

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import com.bahadori.metropolitanmuseum.core.designsystem.theme.gold
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun MetTextField(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
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
        modifier = modifier,
        value = debouncedText.value,
        onValueChange = { newText ->
            debouncedText.value = newText
        },
        trailingIcon = trailingIcon,
        label = { Text(stringResource(id = label)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = gold,
            focusedLabelColor = gold,
            focusedTrailingIconColor = gold,
            cursorColor = gold.copy(alpha = 0.5f)
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}