package com.hgm.cleannoteapp.model.use_case

import com.hgm.cleannoteapp.model.entity.Note
import com.hgm.cleannoteapp.model.repository.NoteRepository

/**
 * 删除笔记用例
 * @property repository NoteRepository
 * @constructor
 */
class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }

}