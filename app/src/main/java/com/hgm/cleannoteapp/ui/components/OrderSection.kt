package com.hgm.cleannoteapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hgm.cleannoteapp.intent.NoteSortType
import com.hgm.cleannoteapp.intent.SortOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteSortType: NoteSortType = NoteSortType.Date(SortOrder.Descending),
    onOrderChange: (NoteSortType) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = noteSortType is NoteSortType.Title,
                onSelect = { onOrderChange(NoteSortType.Title(noteSortType.sortOrder)) })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = noteSortType is NoteSortType.Date,
                onSelect = { onOrderChange(NoteSortType.Date(noteSortType.sortOrder)) })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = noteSortType is NoteSortType.Color,
                onSelect = { onOrderChange(NoteSortType.Color(noteSortType.sortOrder)) })
            Spacer(modifier = Modifier.width(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = noteSortType.sortOrder is SortOrder.Ascending,
                onSelect = {
                    onOrderChange(noteSortType.copy(SortOrder.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = noteSortType.sortOrder is SortOrder.Descending,
                onSelect = {
                    onOrderChange(noteSortType.copy(SortOrder.Descending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}