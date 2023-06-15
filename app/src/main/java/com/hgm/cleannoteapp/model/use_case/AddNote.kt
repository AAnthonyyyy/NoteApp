package com.hgm.cleannoteapp.model.use_case

import com.hgm.cleannoteapp.model.entity.InvalidNoteException
import com.hgm.cleannoteapp.model.entity.Note
import com.hgm.cleannoteapp.model.repository.NoteRepository

/**
 * 添加笔记用例
 * @property repository NoteRepository
 * @constructor
 */
class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}