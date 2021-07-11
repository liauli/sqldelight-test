package com.liauli.trysqldelight

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

internal actual fun createTestDriver(): SqlDriver{
    val app = ApplicationProvider.getApplicationContext<Application>()
    return AndroidSqliteDriver(LibraryDB.Schema, app, null)
}

@RunWith(RobolectricTestRunner::class)
actual abstract class RobolectricTests

