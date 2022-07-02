package com.example.pricecomparison.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.pricecomparison.ui.theme.LargePadding
import com.example.pricecomparison.ui.theme.Shapes
import com.example.pricecomparison.ui.theme.SmallPadding

@Composable
fun PasswordField(
    value: String,
    hint: String = "",
    isPasswordCorrect: Boolean,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    errorMessage: String,
    onValueChange: (password: String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = modifier.fillMaxWidth(),
            shape = Shapes.medium,
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(SmallPadding))
                    Text(text = hint)
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = keyboardActions,
            trailingIcon = {
                if (!isPasswordCorrect) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        )

        if (!isPasswordCorrect) {
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
