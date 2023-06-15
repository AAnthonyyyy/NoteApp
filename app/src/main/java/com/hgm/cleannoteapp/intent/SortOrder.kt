package com.hgm.cleannoteapp.intent

sealed class SortOrder {
    object Ascending : SortOrder()//升序
    object Descending : SortOrder()//降序
}
