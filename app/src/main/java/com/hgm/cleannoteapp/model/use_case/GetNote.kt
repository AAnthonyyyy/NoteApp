package com.hgm.cleannoteapp.model.use_case

import com.hgm.cleannoteapp.model.entity.Note
import com.hgm.cleannoteapp.model.repository.NoteRepository

/**
 * 获取笔记详情用例
 * @property repository NoteRepository
 * @constructor
 */
class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}