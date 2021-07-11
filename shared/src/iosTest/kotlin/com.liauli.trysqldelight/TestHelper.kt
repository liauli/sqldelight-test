package com.liauli.trysqldelight

import co.touchlab.sqliter.DatabaseConfiguration
import com.liauli.trysqldelight.LibraryDB
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

internal actual fun createTestDriver(): SqlDriver {
    val schema = LibraryDB.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "library.db",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            inMemory = true
        )
    )
}

actual abstract class RobolectricTests
