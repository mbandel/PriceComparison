package com.example.pricecomparison.feature.login

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
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
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Title()
            Spacer(modifier = Modifier.height(12.dp))
            EmailField(
                value = email,
                keyboardActions = KeyboardActions { passwordFocus.requestFocus() },
                isEmailCorrect = isEmailCorrect,
                onValueChange = { processor.sendEvent(LoginEvent.EmailChanged(it)) }
            )
            PasswordField(
                value = password,
                isPasswordCorrect = isPasswordCorrect,
                keyboardActions = KeyboardActions { focusManager.clearFocus() },
                modifier = Modifier.focusRequester(passwordFocus),
                errorMessage = stringResource(id = R.string.common_wrong_password),
                onValueChange = { processor.sendEvent(LoginEvent.PasswordChanged(it)) }
            )
            LoginButton(processor = processor)
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
        shape = Shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.design_default_color_secondary_variant),
            contentColor = Color.White
        ),
        enabled = isEmailCorrect &&
            isPasswordCorrect &&
            email.isNotEmpty() &&
            password.isNotEmpty()
    ) {
        Text(
            text = stringResource(id = R.string.login_login),
            style = MaterialTheme.typography.h6
        )
    }
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
