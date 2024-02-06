package com.test.testtrinitywizards

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.test.testtrinitywizards.ui.ContactListScreen
import com.test.testtrinitywizards.ui.theme.TestTrinityWizardsTheme
import com.test.testtrinitywizards.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    companion object {
        private const val REQUEST_CODE_CHANGE = 1
        private const val REQUEST_CODE_ADD = 2
    }

    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(true) {
                contactViewModel.getAllContact()
            }
            TestTrinityWizardsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by contactViewModel.uiState.collectAsState()
                    Log.d("Kampret", state.contactList.toString())
                    ContactListScreen(
                        contactList = state.contactList,
                        searchText = state.searchText,
                        onSearch = {
                            contactViewModel.searchContact(it)
                        },
                        onSelectItem = {
                            val intent = Intent(
                                this@MainActivity,
                                EditContactActivity::class.java
                            )
                            intent.putExtra("edit-contact", it)
                            startActivityForResult(intent, REQUEST_CODE_CHANGE)
                        },
                        onAddItem = {
                            val intent = Intent(
                                this@MainActivity,
                                EditContactActivity::class.java
                            )
                            startActivityForResult(intent, REQUEST_CODE_ADD)
                        },
                        isRefreshing = state.isRefreshing,
                        onRefresh = {
                            contactViewModel.getAllContact()
                        }
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHANGE && resultCode == RESULT_OK) {
            if (data != null) {
                val editContactResult = data.getParcelableExtra<Contact>("edit-contact-result")
                if (editContactResult != null) {
                    contactViewModel.changeContact(editContactResult)
                }
            }
        } else if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            if (data != null) {
                val addContactResult = data.getParcelableExtra<Contact>("add-contact-result")
                if (addContactResult != null) {
                    contactViewModel.addContact(addContactResult)
                }
            }
        }
    }
}