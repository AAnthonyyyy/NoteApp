package com.hgm.cleannoteapp.model.viewmodel

import com.hgm.cleannoteapp.model.use_case.NoteUseCases
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.cleannoteapp.intent.NoteSortType
import com.hgm.cleannoteapp.intent.NotesEvent
import com.hgm.cleannoteapp.intent.SortOrder
import com.hgm.cleannoteapp.model.entity.Note
import com.hgm.cleannoteapp.model.state.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentDeleteNote: Note? = null
    private var getNoteJob: Job? = null

    init {
        getNotes(noteSortType = NoteSortType.Date(SortOrder.Descending))
    }


    fun onEvent(event: NotesEvent) {
        when (event) {
            //加载笔记
            is NotesEvent.Order -> {
                if (state.value.noteSortType::class == event.noteSortType::class &&
                    state.value.noteSortType.sortOrder == event.noteSortType.sortOrder
                ) {
                    return
                }
                getNotes(event.noteSortType)
            }
            //删除笔记
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(note = event.note)
                    recentDeleteNote = event.note
                }
            }
            //恢复笔记
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentDeleteNote ?: return@launch)
                    recentDeleteNote = null
                }
            }
            //菜单开关
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }


    private fun getNotes(noteSortType: NoteSortType) {
        getNoteJob?.cancel()
        getNoteJob = noteUseCases.getNotes(noteSortType = noteSortType)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteSortType = noteSortType
                )
            }
            .launchIn(viewModelScope)
    }
}