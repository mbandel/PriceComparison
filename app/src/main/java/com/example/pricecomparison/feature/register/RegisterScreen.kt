package com.example.pricecomparison.feature.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pricecomparison.R
import com.example.pricecomparison.feature.register.state.RegisterEvent
import com.example.pricecomparison.feature.register.state.RegisterState
import com.example.pricecomparison.ui.compose.EmailField
import com.example.pricecomparison.ui.compose.NameField
import com.example.pricecomparison.ui.compose.PasswordField
import com.example.pricecomparison.ui.compose.Title
import com.example.pricecomparison.ui.theme.LargePadding
import com.example.pricecomparison.ui.theme.Shapes
import com.tomcz.ellipse.common.collectAsState
import com.tomcz.ellipse.common.previewProcessor

@Composable
fun RegisterScreen(
    processor: RegisterProcessor = viewModel<RegisterViewModel>().processor
) {
    val firstName by processor.collectAsState { it.firstName }
    val isFirstNameCorrect by processor.collectAsState { it.isFirstNameCorrect }
    val lastName by processor.collectAsState { it.lastName }
    val isLastNameCorrect by processor.collectAsState { it.isLastNameCorrect }
    val username by processor.collectAsState { it.username }
    val isUsernameCorrect by processor.collectAsState { it.isUsernameCorrect }
    val email by processor.collectAsState { it.email }
    val isEmailCorrect by processor.collectAsState { it.isEmailCorrect }
    val password by processor.collectAsState { it.password }
    val isPasswordCorrect by processor.collectAsState { it.isPasswordCorrect }
    val repeatPassword by processor.collectAsState { it.repeatPassword }
    val isRepeatPasswordCorrect by processor.collectAsState { it.isRepeatPasswordCorrect }
    val focusManager = LocalFocusManager.current
    val lastNameFocus = FocusRequester()
    val usernameFocus = FocusRequester()
    val emailFocus = FocusRequester()
    val passwordFocus = FocusRequester()
    val repeatPasswordFocus = FocusRequester()

    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = LargePadding,
            )
            .padding(bottom = 48.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Title()
        Spacer(modifier = Modifier.height(12.dp))
        NameField(
            value = firstName,
            hint = stringResource(id = R.string.register_first_name),
            keyboardActions = KeyboardActions { lastNameFocus.requestFocus() },
            isNameCorrect = isFirstNameCorrect,
            errorMessage = stringResource(id = R.string.register_wrong_first_name),
            onValueChange = { processor.sendEvent(RegisterEvent.FirstNameChanged(it)) },
        )
        NameField(
            value = lastName,
            hint = stringResource(id = R.string.register_last_name),
            modifier = Modifier.focusRequester(lastNameFocus),
            keyboardActions = KeyboardActions { usernameFocus.requestFocus() },
            isNameCorrect = isLastNameCorrect,
            errorMessage = stringResource(id = R.string.register_wrong_last_name),
            onValueChange = { processor.sendEvent(RegisterEvent.LastNameChanged(it)) }
        )
        NameField(
            value = username,
            hint = stringResource(id = R.string.register_username),
            modifier = Modifier.focusRequester(usernameFocus),
            keyboardActions = KeyboardActions { emailFocus.requestFocus() },
            isNameCorrect = isUsernameCorrect,
            errorMessage = stringResource(id = R.string.register_wrong_username),
            onValueChange = { processor.sendEvent(RegisterEvent.UsernameChanged(it)) },
        )
        EmailField(
            value = email,
            modifier = Modifier.focusRequester(emailFocus),
            keyboardActions = KeyboardActions { passwordFocus.requestFocus() },
            isEmailCorrect = isEmailCorrect,
            onValueChange = { processor.sendEvent(RegisterEvent.EmailChanged(it)) }
        )
        PasswordField(
            value = password,
            hint = stringResource(id = R.string.common_password),
            isPasswordCorrect = isPasswordCorrect,
            keyboardActions = KeyboardActions { repeatPasswordFocus.requestFocus() },
            modifier = Modifier.focusRequester(passwordFocus),
            errorMessage = stringResource(id = R.string.common_wrong_password),
            onValueChange = { processor.sendEvent(RegisterEvent.PasswordChanged(it)) }
        )
        PasswordField(
            value = repeatPassword,
            hint = stringResource(id = R.string.register_repeat_password),
            isPasswordCorrect = isRepeatPasswordCorrect,
            keyboardActions = KeyboardActions { focusManager.clearFocus() },
            modifier = Modifier.focusRequester(repeatPasswordFocus),
            errorMessage = stringResource(id = R.string.register_wrong_repeat_password),
            onValueChange = { processor.sendEvent(RegisterEvent.RepeatPasswordChanged(it)) }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                processor.sendEvent(RegisterEvent.RegisterClick)
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
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(processor = previewProcessor(RegisterState()))
}
