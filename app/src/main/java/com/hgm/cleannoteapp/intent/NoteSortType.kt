package com.hgm.cleannoteapp.intent

sealed class NoteSortType(val sortOrder: SortOrder) {
    class Title(sortOrder: SortOrder) : NoteSortType(sortOrder)
    class Date(sortOrder: SortOrder) : NoteSortType(sortOrder)
    class Color(sortOrder: SortOrder) : NoteSortType(sortOrder)

    fun copy(sortOrder: SortOrder):NoteSortType{
        return when(this){
            is Title -> Title(sortOrder)
            is Date -> Date(sortOrder)
            is Color -> Color(sortOrder)
        }
    }
}
