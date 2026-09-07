package com.minger.lexio.ui.decks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R
import com.minger.lexio.presentation.decks.DecksState
import com.minger.lexio.presentation.decks.DecksViewModel
import com.minger.lexio.ui.common.ErrorScreen
import com.minger.lexio.ui.common.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun DecksScreen(
    modifier: Modifier = Modifier,
    onAddNewDeck: () -> Unit = {},
    viewModel: DecksViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var selectedTab by remember { mutableStateOf("all") }
    when (val currentState = state) {
        DecksState.Loading -> LoadingScreen(modifier = modifier)
        DecksState.Error -> ErrorScreen(modifier = modifier)
        is DecksState.Success -> SuccessContent(
            modifier = modifier,
            addNewDeck = onAddNewDeck,
            query = "",
            onQueryChange = {},
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            decks = currentState.decks.map {
                Deck(
                    deckName = it.deckName,
                    deckIcon = it.deckIcon,
                    wordValue = it.wordValue,
                    wordToday = it.wordToday,
                    learningPercent = it.learningPercent,
                )
            },
        )
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    addNewDeck: () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    decks: List<Deck>? = emptyList(),
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.decks_screen_title),
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                )
                FloatingActionButton(
                    onClick = addNewDeck,
                    modifier = Modifier.size(40.dp),
                    shape = RoundedCornerShape(15.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_new_deck),
                        modifier = Modifier.size(25.dp),
                    )
                }
            }
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                placeholder = { Text(stringResource(R.string.search)) },
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = stringResource(R.string.clear_icon_content_description)
                            )
                        }
                    }
                },
            )
            Tabs(selected = selectedTab, onSelected = onTabSelected)
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(decks.orEmpty()) { deck ->
                    DeckRow(
                        modifier = Modifier,
                        deck = deck,
                        onDeckClick = {},
                    )
                }
            }
        }
    }
}