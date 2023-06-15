package com.hgm.cleannoteapp.model.use_case

import androidx.compose.runtime.mutableStateListOf
import com.google.common.truth.Truth.assertThat
import com.hgm.cleannoteapp.intent.NoteSortType
import com.hgm.cleannoteapp.intent.SortOrder
import com.hgm.cleannoteapp.model.data.repository.FakeNoteRepository
import com.hgm.cleannoteapp.model.entity.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesTest {
    private lateinit var getNotes: GetNotes
    private lateinit var fakeNoteRepository: FakeNoteRepository

    //Before 注释告诉junit运行每个测试用例之前执行此函数
    //用于为每个测试用例设置我们需要的东西，例如：初始化对象
    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNotes = GetNotes(fakeNoteRepository)


        //模拟插入数据
        val notesToInsert = mutableStateListOf<Note>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }
        notesToInsert.shuffle()//随机打乱
        runBlocking {
            notesToInsert.forEach { fakeNoteRepository.insertNote(it) }
        }
    }


    @Test
    fun `笔记列表按标题升序排序`() = runBlocking {
        val notes = getNotes(NoteSortType.Title(SortOrder.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

    @Test
    fun `笔记列表按标题降序排序`() = runBlocking {
        val notes = getNotes(NoteSortType.Title(SortOrder.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isGreaterThan(notes[i + 1].title)
        }
    }

    @Test
    fun `笔记列表按日期升序排序`() = runBlocking {
        val notes = getNotes(NoteSortType.Date(SortOrder.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].timestamp).isLessThan(notes[i + 1].timestamp)
        }
    }

    @Test
    fun `笔记列表按日期降序排序`() = runBlocking {
        val notes = getNotes(NoteSortType.Date(SortOrder.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].timestamp).isGreaterThan(notes[i + 1].timestamp)
        }
    }

    @Test
    fun `笔记列表按颜色生序排序`() = runBlocking {
        val notes = getNotes(NoteSortType.Color(SortOrder.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isLessThan(notes[i + 1].color)
        }
    }

    @Test
    fun `笔记列表按颜色降序排序`() = runBlocking {
        val notes = getNotes(NoteSortType.Color(SortOrder.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isGreaterThan(notes[i + 1].color)
        }
    }
}