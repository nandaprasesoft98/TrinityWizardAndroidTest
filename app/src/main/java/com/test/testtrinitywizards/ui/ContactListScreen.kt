package com.test.testtrinitywizards.ui

import android.util.Log
import android.view.MenuItem
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ContactListScreen(
    contactList: List<Contact>,
    searchText: String,
    onSearch: (String) -> Unit,
    onSelectItem: (Contact) -> Unit,
    onAddItem: () -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchText,
                onValueChange = {
                    onSearch(it)
                },
                label = { Text("Search contacts") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
            )
            IconButton(
                onClick = {
                    onAddItem()
                }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Favorite",
                    tint = Orange,
                    modifier = Modifier.size(48.dp)
                )
            }

        }
        val pullRefreshState = rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = onRefresh
        )
        Box(
            Modifier.pullRefresh(pullRefreshState)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(contactList.size) { index ->
                    val contact: Contact = contactList[index]
                    Box(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        ContactItem(
                            contact = contact,
                            onSelectItem = {
                                onSelectItem(it)
                            }
                        )
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun ContactItem(
    contact: Contact,
    onSelectItem: (Contact) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.LightGray),
            )
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = {
                    onSelectItem(contact)
                }
            )
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFDEEEFF), CircleShape)
        )
        {
            Canvas(
                modifier = Modifier.size(100.dp),
                onDraw = {
                    drawCircle(color = Orange)
                }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "${contact.firstName} ${contact.lastName}", textAlign = TextAlign.Center, fontSize = 16.sp)
    }
}