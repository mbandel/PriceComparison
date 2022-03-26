package com.example.pricecomparison.ui.compose

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.pricecomparison.R
import com.example.pricecomparison.ui.theme.LargePadding
import com.example.pricecomparison.ui.theme.Shapes
import com.example.pricecomparison.ui.theme.SmallPadding

@Composable
fun NameField(
    modifier: Modifier = Modifier,
    hint: String = "",
    value: String,
    keyboardActions: KeyboardActions,
    isNameCorrect: Boolean,
    errorMessage: String,
    onValueChange: (name: String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = value,
            modifier = modifier.fillMaxWidth(),
            shape = Shapes.medium,
            onValueChange = { onValueChange(it) },
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(SmallPadding))
                    Text(text = stringResource(id = R.string.login_email))
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = keyboardActions,
            isError = !isNameCorrect,
            trailingIcon = {
                if (!isNameCorrect) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            },
        )
        if (!isNameCorrect) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(horizontal = LargePadding)
                    .padding(vertical = SmallPadding)
            )
        }
    }
}
