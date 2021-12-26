package com.example.pricecomparison.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pricecomparison.R
import com.example.pricecomparison.feature.login.state.LoginEvent
import com.example.pricecomparison.feature.login.state.LoginState
import com.example.pricecomparison.ui.theme.LargePadding
import com.example.pricecomparison.ui.theme.Shapes
import com.example.pricecomparison.ui.theme.SmallPadding
import com.tomcz.ellipse.common.collectAsState
import com.tomcz.ellipse.common.previewProcessor

@Composable
fun LoginScreen(processor: LoginProcessor) {
    val focusManager = LocalFocusManager.current
    val passwordFocus = FocusRequester()
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Title()
        Spacer(modifier = Modifier.height(30.dp))
        EmailField(
            processor = processor,
            keyboardActions = KeyboardActions { passwordFocus.requestFocus() }
        )
        Spacer(modifier = Modifier.height(30.dp))
        PasswordField(
            processor = processor,
            keyboardActions = KeyboardActions { focusManager.clearFocus() },
            modifier = Modifier.focusRequester(passwordFocus)
        )
        Spacer(modifier = Modifier.height(30.dp))
        LoginButton(processor = processor)
        Spacer(modifier = Modifier.height(30.dp))
        ProgressIndicator(processor = processor)
    }
}

@Composable
private fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LargePadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_sale),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(64.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
private fun EmailField(
    processor: LoginProcessor,
    keyboardActions: KeyboardActions
) {
    val email by processor.collectAsState { it.email }
    val isEmailCorrect by processor.collectAsState { it.isEmailCorrect }

    Column {
        OutlinedTextField(
            value = email,
            modifier = Modifier
                .padding(horizontal = LargePadding)
                .fillMaxWidth(),
            shape = Shapes.medium,
            onValueChange = { processor.sendEvent(LoginEvent.EmailChanged(it)) },
            label = {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(SmallPadding))
                    Text(text = stringResource(id = R.string.login_email))
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = keyboardActions,
            trailingIcon = {
                if (!isEmailCorrect) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = stringResource(id = R.string.login_wrong_email),
                        tint = Color.Red
                    )
                }
            }
        )
        if (!isEmailCorrect) {
            Text(
                text = stringResource(id = R.string.login_wrong_email),
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(horizontal = LargePadding)
                    .padding(vertical = SmallPadding)

            )
        }
    }
}

@Composable
private fun PasswordField(
    processor: LoginProcessor,
    keyboardActions: KeyboardActions,
    modifier: Modifier
) {
    val password by processor.collectAsState { it.password }
    val isPasswordCorrect by processor.collectAsState { it.isPasswordCorrect }

    Column {
        OutlinedTextField(
            value = password,
            onValueChange = { processor.sendEvent(LoginEvent.PasswordChanged(it)) },
            modifier = modifier
                .padding(horizontal = LargePadding)
                .fillMaxWidth(),
            shape = Shapes.medium,
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(SmallPadding))
                    Text(text = stringResource(id = R.string.login_password))
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = keyboardActions,
            trailingIcon = {
                if (!isPasswordCorrect) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = stringResource(id = R.string.login_wrong_password),
                        tint = Color.Red
                    )
                }
            }
        )

        if (!isPasswordCorrect) {
            Text(
                text = stringResource(id = R.string.login_wrong_password),
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(horizontal = LargePadding)
                    .padding(vertical = SmallPadding)
            )
        }
    }
}

@Composable
private fun LoginButton(processor: LoginProcessor) {
    val email by processor.collectAsState { it.email }
    val password by processor.collectAsState { it.password }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LargePadding),
        onClick = {
            processor.sendEvent(
                LoginEvent.LoginClick(
                    email = email,
                    password = password
                )
            )
        },
        content = {
            Text(
                text = stringResource(id = R.string.login_login),
                style = MaterialTheme.typography.h6
            )
        },
        shape = Shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.design_default_color_secondary_variant),
            contentColor = Color.White
        )
    )
}

@Composable
private fun ProgressIndicator(processor: LoginProcessor) {
    val isLoading by processor.collectAsState { it.isLoading }
    if (isLoading)
        CircularProgressIndicator()
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(processor = previewProcessor(LoginState()))
}
