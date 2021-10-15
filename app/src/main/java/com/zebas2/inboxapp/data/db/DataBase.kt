package com.zebas2.inboxapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zebas2.inboxapp.data.model.Post

@Database(
    entities = [Post::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

}