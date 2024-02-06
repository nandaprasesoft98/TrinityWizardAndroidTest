package com.test.testtrinitywizards.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContactScreen(
    contact: Contact,
    onEditFirstName: (String) -> Unit,
    onEditLastName: (String) -> Unit,
    onEditEmail: (String) -> Unit,
    onEditDob: (String) -> Unit,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
    canSubmit: Boolean
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item(1) {
            Canvas(
                modifier = Modifier.size(100.dp),
                onDraw = {
                    drawCircle(color = Orange)
                }
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Main Information"
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            TextField(
                value = contact.firstName,
                onValueChange = onEditFirstName,
                label = { Text("First Name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            if (contact.firstName.isBlank()) {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "First name is required"
                )
            }
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            TextField(
                value = contact.lastName,
                onValueChange = onEditLastName,
                label = { Text("Last Name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            if (contact.firstName.isBlank()) {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Last name is required"
                )
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Sub Information"
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            TextField(
                value = contact.email,
                onValueChange = onEditEmail,
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            TextField(
                value = contact.dob,
                onValueChange = onEditDob,
                label = { Text("Dob") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onCancel) {
                    Text("Cancel")
                }
                Button(
                    onClick = onSubmit,
                    enabled = canSubmit
                ) {
                    Text("Save")
                }
            }
        }
    }
}