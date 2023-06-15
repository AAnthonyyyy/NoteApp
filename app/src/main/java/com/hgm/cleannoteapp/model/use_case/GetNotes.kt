package com.hgm.cleannoteapp.model.use_case

import com.hgm.cleannoteapp.intent.NoteSortType
import com.hgm.cleannoteapp.intent.SortOrder
import com.hgm.cleannoteapp.model.entity.Note
import com.hgm.cleannoteapp.model.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 获取所有笔记
 * @property repository NoteRepository
 * @constructor
 */
class GetNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteSortType: NoteSortType = NoteSortType.Date(SortOrder.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (noteSortType.sortOrder) {
                SortOrder.Ascending -> {
                    when (noteSortType) {
                        is NoteSortType.Color -> notes.sortedBy { it.color }
                        is NoteSortType.Date -> notes.sortedBy { it.timestamp }
                        is NoteSortType.Title -> notes.sortedBy { it.title.toLowerCase() }
                    }
                }
                SortOrder.Descending -> {
                    when (noteSortType) {
                        is NoteSortType.Color -> notes.sortedByDescending { it.color }
                        is NoteSortType.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteSortType.Title -> notes.sortedByDescending { it.title.toLowerCase() }
                    }
                }
            }
        }
    }
}