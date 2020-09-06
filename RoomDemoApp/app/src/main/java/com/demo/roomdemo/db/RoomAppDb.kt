package com.demo.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [UserEntity::class, Address::class], version = 2)
abstract class RoomAppDb: RoomDatabase() {


    abstract fun userDao(): UserDao?

    companion object {
        private var INSTANCE: RoomAppDb?= null

        val migration_1_2: Migration = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE userinfo ADD COLUMN phone TEXT DEFAULT ''")
            }
        }

       /* val migration_2_3: Migration = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `address` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `street` TEXT NOT NULL, `city` TEXT NOT NULL,`state` TEXT NOT NULL, `zip` TEXT NOT NULL, FOREIGN KEY(`id`) REFERENCES `userinfo`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
            }
        }*/

        fun getAppDatabase(context: Context): RoomAppDb? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext, RoomAppDb::class.java, "AppDBB"
                )
                    .addMigrations(migration_1_2/*, migration_2_3*/)
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}