package com.example.penpal.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PenPalDataBase (
    mContext: Context,
    name: String = "penpaldata_base",
    version: Int = 1
        ): SQLiteOpenHelper(
    mContext,
    name,
    null,
    version
) {
    override fun onCreate(DB: SQLiteDatabase?) {
      val createTableUser="""
          CREATE TABLE user(
          id integer PRIMARY KEY,
          name varchar(50),
          email varchar(100),
          password varchar(25))
          """.trimIndent()
        DB?.execSQL(createTableUser)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}