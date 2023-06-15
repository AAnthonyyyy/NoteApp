package com.hgm.cleannoteapp.model.di

import com.hgm.cleannoteapp.model.use_case.NoteUseCases
import android.app.Application
import androidx.room.Room
import com.hgm.cleannoteapp.model.database.NoteDatabase
import com.hgm.cleannoteapp.model.repository.NoteRepository
import com.hgm.cleannoteapp.model.repository.NoteRepositoryImpl
import com.hgm.cleannoteapp.model.use_case.AddNote
import com.hgm.cleannoteapp.model.use_case.DeleteNote
import com.hgm.cleannoteapp.model.use_case.GetNote
import com.hgm.cleannoteapp.model.use_case.GetNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    /**
     * 提供笔记数据库
     * @param app Application
     * @return NoteDatabase
     */
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }


    /**
     * 提供笔记存储库--操作
     * @param db NoteDatabase
     * @return NoteRepository
     */
    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }


    /**
     * 提供笔记用例
     * @param repository NoteRepository
     * @return com.hgm.cleannoteapp.model.use_case.NoteUseCases
     */
    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}