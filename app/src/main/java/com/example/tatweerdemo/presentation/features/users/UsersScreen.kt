@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tatweerdemo.presentation.features.users

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tatweerdemo.R
import com.example.tatweerdemo.core.base.SIDE_EFFECTS_KEY
import com.example.tatweerdemo.utils.common.Progress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */
@Composable
fun Users(
    state: UsersState,
    effectFlow: Flow<UsersSideEffects>?,
    onEventSent: (event: UsersEvent) -> Unit
) {

    val context = LocalContext.current
    val showAddNewUserDialog = remember { mutableStateOf(false) }
    val deleteUserDialog = remember { mutableStateOf(false) }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is UsersSideEffects.ShowErrorMsg -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_LONG).show()
                }
                is UsersSideEffects.ShowSuccessMsg -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_LONG).show()
                }
            }
        }?.collect()
    }

    when {
        state.isLoading -> {
            Progress()
        }
        else -> {}
    }

    LazyColumn(
        state = rememberLazyListState(), modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        item {
            TextButton(onClick = {
                showAddNewUserDialog.value = true
            }) {
                Text(
                    text = stringResource(id = R.string.add)
                )
            }
        }
        items(state.usersList) { userItem ->
            AnimatedVisibility(state.usersList.isNotEmpty()) {
                Column(modifier = Modifier.animateContentSize { _, _ -> }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp).clickable {
                                userItem.id?.let {
                                    deleteUserDialog.value = true
                                }
                            },
                        shape = CardDefaults.shape,
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white))
                    ) {

                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                text = userItem.name ?: "",
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.black),
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(
                                color = colorResource(id = R.color.gray_light),
                                thickness = 1.dp
                            )
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                text = userItem.email ?: "",
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.black),
                                fontWeight = FontWeight.Normal
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(
                                color = colorResource(id = R.color.gray_light),
                                thickness = 1.dp
                            )
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                text = userItem.gender ?: "",
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.black),
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }

                    if (deleteUserDialog.value) {
                        DeleteUserDialog(show = deleteUserDialog.value,
                            onConfirm = {
                                deleteUserDialog.value = false
                                onEventSent(UsersEvent.DeleteUser(userId = userItem.id?:"" ))
                            }, onDismiss = { deleteUserDialog.value = false })
                    }
                }
            }
        }
    }

    if (showAddNewUserDialog.value) {
        AddNewUserDialog(show = showAddNewUserDialog.value,
            onConfirm = { name, email ->
                if (name.isNotBlank() && email.isNotBlank()){
                    showAddNewUserDialog.value = false
                    onEventSent(UsersEvent.AddNewUser(name = name, email = email))
                }
            }, onDismiss = { showAddNewUserDialog.value = false })
    }


}

@Composable
private fun AddNewUserDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (name: String, email: String) -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            Box(modifier = Modifier.height(350.dp)) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Column(modifier = Modifier.padding(25.dp)) {
                            var name by remember { mutableStateOf(TextFieldValue("")) }
                            OutlinedTextField(
                                value = name,
                                placeholder = { Text("Enter Name") },
                                onValueChange = {
                                    name = it
                                }
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            var email by remember { mutableStateOf(TextFieldValue("")) }
                            OutlinedTextField(
                                value = email,
                                placeholder = { Text("Enter Email") },
                                onValueChange = {
                                    email = it
                                }
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            TextButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 30.dp),
                                onClick = { onConfirm(name.text, email.text) },
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black))
                            )
                            {
                                Text(
                                    color = Color.White,
                                    text = stringResource(R.string.done).uppercase()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DeleteUserDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            Box(modifier = Modifier.height(350.dp)) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Column(modifier = Modifier.padding(25.dp)) {
                            Text(
                                text = stringResource(id = R.string.delete_user),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            TextButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 30.dp),
                                onClick = {
                                    onConfirm() },
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black))
                            )
                            {
                                Text(
                                    color = Color.White,
                                    text = stringResource(R.string.yes).uppercase()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
 }

@Preview(showBackground = true)
@Composable
fun UsersScreenPreview() {
    Users(state = UsersState(isLoading = false),
        effectFlow = null, onEventSent = {})
}