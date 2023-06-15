package com.hgm.cleannoteapp.ui.navigation

import com.hgm.cleannoteapp.ui.screen.AddEditNoteScreen
import com.hgm.cleannoteapp.ui.screen.NotesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hgm.cleannoteapp.model.state.Screen

@Composable
fun NavHostApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        //主页
        composable(route = Screen.NotesScreen.route) {
            NotesScreen(navController = navController)
        }

        //编辑页
        composable(route = Screen.AddEditNoteScreen.route +
                "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(name = "noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(name = "noteColor") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            //接受传递的参数
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(navController = navController, noteColor = color)
        }
    }
}