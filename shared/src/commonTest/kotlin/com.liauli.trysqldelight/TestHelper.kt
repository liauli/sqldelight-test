package com.liauli.trysqldelight

import com.squareup.sqldelight.db.SqlDriver

internal expect fun createTestDriver(): SqlDriver

expect abstract class RobolectricTests()
