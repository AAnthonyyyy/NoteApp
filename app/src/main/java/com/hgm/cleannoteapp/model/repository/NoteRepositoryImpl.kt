package com.hgm.cleannoteapp.model.repository

import com.hgm.cleannoteapp.model.database.NoteDao
import com.hgm.cleannoteapp.model.entity.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id = id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note = note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note = note)
    }
}