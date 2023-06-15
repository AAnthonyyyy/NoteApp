package com.hgm.cleannoteapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hgm.cleannoteapp.ui.theme.*

@Entity
data class Note(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
) {
    companion object {
        //笔记的颜色列表
        val noteColor = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

//自定义异常
class InvalidNoteException(message: String) : Exception(message)
