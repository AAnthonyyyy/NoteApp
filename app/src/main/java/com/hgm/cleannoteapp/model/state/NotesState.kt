package com.hgm.cleannoteapp.model.state

import com.hgm.cleannoteapp.intent.NoteSortType
import com.hgm.cleannoteapp.intent.SortOrder
import com.hgm.cleannoteapp.model.entity.Note

data class NotesState(
    //笔记列表：默认空
    val notes: List<Note> = emptyList(),
    //笔记排序类型：默认降序
    val noteSortType: NoteSortType = NoteSortType.Date(SortOrder.Descending),
    //笔记排序菜单框：默认隐藏
    val isOrderSectionVisible: Boolean = false
)
