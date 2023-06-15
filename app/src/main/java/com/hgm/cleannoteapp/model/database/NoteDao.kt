package com.hgm.cleannoteapp.model.database

import androidx.room.*
import com.hgm.cleannoteapp.model.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    //查询所有笔记
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>


    //通过 id 查找笔记
    @Query("SELECT * FROM note where id=:id")
    suspend fun getNoteById(id: Int): Note?

    //插入笔记
    @Insert(onConflict = OnConflictStrategy.REPLACE)//设定冲突策略，如果数据库中已存在该 id 的话，那么只会更新条目
    suspend fun insertNote(note: Note)


    //删除笔记
    @Delete
    suspend fun deleteNote(note: Note)

}