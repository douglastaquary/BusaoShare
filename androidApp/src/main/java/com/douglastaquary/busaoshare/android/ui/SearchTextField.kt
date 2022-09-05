package com.douglastaquary.busaoshare.android.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction

@Composable

fun SearchTextField(
    search: String,
    onSearch: (String?) -> Unit,
) {
    val textFieldColors = TextFieldDefaults.textFieldColors()
    val focusRequester = remember { FocusRequester() }
    val cursorColor = TextFieldDefaults.textFieldColors().cursorColor(isError = false).value
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = search,
        onValueChange = onSearch,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        cursorBrush = SolidColor(cursorColor),
        textStyle = LocalTextStyle.current.copy(
            color = textFieldColors.textColor(enabled = true).value
        ),
    )
    LaunchedEffect(Unit) {
        if (search.isEmpty()) {
            focusRequester.requestFocus()
        }
    }
}