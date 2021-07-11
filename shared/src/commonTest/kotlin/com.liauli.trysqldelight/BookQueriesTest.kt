package com.liauli.trysqldelight

import kotlin.random.Random
import kotlin.test.*

class BookQueriesTest: RobolectricTests() {
    private lateinit var bookQueries: BookQueries

    @BeforeTest
    fun setup(){
        val driver = createTestDriver()
        val database = LibraryDB(driver)
        bookQueries = database.bookQueries
    }


    @AfterTest
    fun tearDown(){
        bookQueries.removeAll()
    }

    @Test
    fun getAll(){
        val booksToBeAdded = listOf(
            createBook(),
            createBook()
        )
        bookQueries.transaction {
            booksToBeAdded.forEach { book ->
                bookQueries.addBook(book)
            }
        }
        val result = bookQueries.getAll().executeAsList()
        assertEquals(booksToBeAdded, result)
    }

    @Test
    fun insertAndGetById(){
        val booksToBeAdded = TestUtil.createBooks(2)
        val expectedBook = booksToBeAdded[1]
        bookQueries.transaction {
            booksToBeAdded.forEach { book ->
                bookQueries.addBook(book)
            }
        }
        val result = bookQueries.getById(expectedBook.id).executeAsOne()
        assertEquals(expectedBook, result)
    }

    @Test
    fun insertAndRemoveAll(){
        val booksToBeAdded = TestUtil.createBooks(2)
        bookQueries.transaction {
            booksToBeAdded.forEach { book ->
                bookQueries.addBook(book)
            }
        }
        val result = bookQueries.getAll().executeAsList()
        assertEquals(booksToBeAdded, result)
        bookQueries.removeAll()

        val resultAfterRemoved = bookQueries.getAll().executeAsList()
        assertTrue(resultAfterRemoved.isEmpty())
    }

    private fun createBook(): Book {
        return Book(
            id = Random.nextInt().toString(),
            title = Random.nextInt().toString(),
            author = Random.nextInt().toString(),
            price = Random.nextLong()
        )
    }
}
