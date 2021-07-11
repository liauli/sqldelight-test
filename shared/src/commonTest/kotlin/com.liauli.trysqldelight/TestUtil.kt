package com.liauli.trysqldelight

import kotlin.random.Random

class TestUtil {
    companion object {
        fun createBook(id: String): Book {
            return Book(
                id = Random.nextInt().toString(),
                title = Random.nextInt().toString(),
                author = Random.nextInt().toString(),
                price = Random.nextLong()
            )
        }

        fun createBook(): Book {
            return createBook(Random.nextInt().toString())
        }

        fun createBooks(times: Int): List<Book>{
            val books = mutableListOf<Book>()
            for (i in 1..times){
                books.add(createBook())
            }
            return books
        }
    }
}
