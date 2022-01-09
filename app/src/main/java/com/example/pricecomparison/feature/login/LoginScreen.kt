package com.example.pricecomparison.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import com.example.pricecomparison.feature.login.state.LoginEffect
import com.example.pricecomparison.feature.login.state.LoginEvent
import com.example.pricecomparison.feature.login.state.LoginState
import com.example.pricecomparison.ui.compose.EmailField
import com.example.pricecomparison.ui.compose.ErrorSnackBar
import com.example.pricecomparison.ui.compose.PasswordField
import com.example.pricecomparison.ui.compose.Title
import com.example.pricecomparison.ui.theme.LargePadding
import com.example.pricecomparison.ui.theme.Shapes
import com.example.pricecomparison.ui.theme.SmallPadding
import com.tomcz.ellipse.common.collectAsState
import com.tomcz.ellipse.common.collectEffect
import com.tomcz.ellipse.common.previewProcessor

@Composable
fun LoginScreen(processor: LoginProcessor) {
    val focusManager = LocalFocusManager.current
    val passwordFocus = FocusRequester()
    val isShowingServerError by processor.collectAsState {
        it.isShowingServerError
    }
    val isShowingInvalidCredentialsError by processor.collectAsState {
        it.isShowingInvalidCredentialsError
    }
    val email by processor.collectAsState { it.email }
    val isEmailCorrect by processor.collectAsState { it.isEmailCorrect }
    val password by processor.collectAsState { it.password }
    val isPasswordCorrect by processor.collectAsState { it.isPasswordCorrect }

    processor.collectEffect(collect = { event ->
        when (event) {
            is LoginEffect.GoToCategories -> println("Login Clicked")
        }
    })

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LargePadding)
    ) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))
            Title()
            Spacer(modifier = Modifier.height(30.dp))
            EmailField(
                value = email,
                keyboardActions = KeyboardActions { passwordFocus.requestFocus() },
                isEmailCorrect = isEmailCorrect,
                onValueChange = { processor.sendEvent(LoginEvent.EmailChanged(it)) }
            )
            Spacer(modifier = Modifier.height(30.dp))
            PasswordField(
                value = password,
                isPasswordCorrect = isPasswordCorrect,
                keyboardActions = KeyboardActions { focusManager.clearFocus() },
                modifier = Modifier.focusRequester(passwordFocus),
                onValueChange = { processor.sendEvent(LoginEvent.PasswordChanged(it)) }
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginButton(processor = processor)
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ProgressIndicator(processor = processor)
            }
        }

        Column {
            if (isShowingServerError)
                ErrorSnackBar(
                    message = stringResource(id = R.string.login_server_error),
                    sendEvent = { processor.sendEvent(LoginEvent.ConfirmServerError) }
                )
            if (isShowingInvalidCredentialsError)
                ErrorSnackBar(
                    message = stringResource(id = R.string.login_invalid_credentials_error),
                    sendEvent = { processor.sendEvent(LoginEvent.ConfirmInvalidCredentialsError) }
                )
        }
    }
}

@Composable
private fun LoginButton(processor: LoginProcessor) {
    val email by processor.collectAsState { it.email }
    val password by processor.collectAsState { it.password }
    val isEmailCorrect by processor.collectAsState { it.isEmailCorrect }
    val isPasswordCorrect by processor.collectAsState { it.isPasswordCorrect }
    Button(
        modifier = Modifier.fillMaxWidth(),
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
        ),
        enabled = isEmailCorrect &&
                isPasswordCorrect &&
                email.isNotEmpty() &&
                password.isNotEmpty()
    )
}

@Composable
private fun ProgressIndicator(processor: LoginProcessor) {
    val isLoading by processor.collectAsState { it.isLoading }
    if (isLoading)
        LinearProgressIndicator()
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(processor = previewProcessor(LoginState()))
}
