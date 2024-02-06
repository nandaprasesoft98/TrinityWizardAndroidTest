package com.test.testtrinitywizards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.ui.EditContactScreen
import com.test.testtrinitywizards.ui.theme.TestTrinityWizardsTheme
import com.test.testtrinitywizards.viewmodel.EditContactViewModel

class EditContactActivity: ComponentActivity() {
    private val editContactViewModel: EditContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val editedContact = this.intent.getParcelableExtra<Contact>("edit-contact")
        setContent {
            LaunchedEffect(true) {
                if (editedContact != null) {
                    editContactViewModel.setSelectedContact(editedContact)
                } else {
                    editContactViewModel.setSelectedContact(
                        Contact(
                            id = "",
                            firstName = "",
                            lastName = "",
                            email = "",
                            dob = ""
                        )
                    )
                }
            }
            TestTrinityWizardsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by editContactViewModel.uiState.collectAsState()
                    if (state.editedContact != null) {
                        EditContactScreen(
                            contact = state.editedContact!!,
                            onEditFirstName = { editContactViewModel.editFirstNameContact(it) },
                            onEditLastName = { editContactViewModel.editLastNameContact(it) },
                            onEditEmail = { editContactViewModel.editEmailContact(it) },
                            onEditDob = { editContactViewModel.editDobContact(it) },
                            onSubmit = {
                                if (state.editedContact!!.firstName.isNotBlank() && state.editedContact!!.firstName.isNotBlank()) {
                                    val resultIntent = Intent()
                                    if (editedContact != null) {
                                        resultIntent.putExtra("edit-contact-result", state.editedContact!!)
                                    } else {
                                        resultIntent.putExtra("add-contact-result", state.editedContact!!)
                                    }
                                    setResult(RESULT_OK, resultIntent)
                                    finish()
                                }
                            },
                            onCancel = { onBackPressed() },
                            canSubmit = state.editedContact!!.firstName.isNotBlank() && state.editedContact!!.firstName.isNotBlank()
                        )
                    }
                }
            }
        }
    }
}