package com.test.testtrinitywizards.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen() {
    var searchText by remember { mutableStateOf("") }
    var filteredContacts by remember { mutableStateOf(contactList) }

    Column {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                filteredContacts = if (it.isBlank()) {
                    contactList
                } else {
                    contactList.filter { contact ->
                        contact.name.contains(it, ignoreCase = true) ||
                                contact.phoneNumber.contains(it, ignoreCase = true)
                    }
                }
            },
            label = { Text("Search contacts") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn {
            items(filteredContacts) { contact ->
                ContactItem(contact = contact)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                isExpanded = !isExpanded
            },
        color = Color.Gray
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = contact.name)
            Text(text = contact.phoneNumber)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ContactListScreenPreview() {
    ContactListScreen()
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreview() {
    ContactItem(Contact("John Doe", "123-456-7890"))
}

val contactList = listOf(
    Contact("John Doe", "123-456-7890"),
    Contact("Jane Smith", "987-654-3210"),
    // Add more contacts as needed
)

data class Contact(val name: String, val phoneNumber: String)