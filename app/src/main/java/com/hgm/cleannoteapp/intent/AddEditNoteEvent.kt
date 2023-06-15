package com.hgm.cleannoteapp.intent

import androidx.compose.ui.focus.FocusState

/**
 * 编辑页的操作意图
 */
sealed class AddEditNoteEvent {
    //输入标题
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    //标题输入的焦点
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
    //输入内容
    data class EnteredContent(val value: String) : AddEditNoteEvent()
    //内容输入的焦点
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()
    //改变颜色
    data class ChangeColor(val color: Int) : AddEditNoteEvent()
    //保存笔记
    object SaveNote : AddEditNoteEvent()
}
