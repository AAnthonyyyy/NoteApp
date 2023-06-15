package com.hgm.cleannoteapp.model.repository

import com.hgm.cleannoteapp.model.entity.Note
import kotlinx.coroutines.flow.Flow

/**
 * 存储库
 */
interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}