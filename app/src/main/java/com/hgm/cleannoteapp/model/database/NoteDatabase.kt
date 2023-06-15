package com.hgm.cleannoteapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hgm.cleannoteapp.model.entity.Note

@Database(entities = [Note::class], version = 1,exportSchema=false)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao:NoteDao

    companion object{
        //数据库名字
        const val DATABASE_NAME="notes_db"
    }
}