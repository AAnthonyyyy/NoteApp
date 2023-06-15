package com.hgm.cleannoteapp.intent

import com.hgm.cleannoteapp.model.entity.Note

sealed class NotesEvent {
    //获取
    data class Order(val noteSortType: NoteSortType) : NotesEvent()
    //删除笔记
    data class DeleteNote(val note: Note) : NotesEvent()
    //撤回笔记
    object RestoreNote : NotesEvent()
    //切换笔记排序
    object ToggleOrderSection : NotesEvent()
}
