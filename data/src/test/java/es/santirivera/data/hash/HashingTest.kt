package es.santirivera.data.hash

import es.santirivera.data.api.hash.Hash
import org.junit.Assert
import org.junit.Test

class HashingTest {
    @Test
    fun testHash() {
        val private = "Asdf"
        val public = "Asdf2"
        val timestamp = 1234L
        val expectedResult = "7b3c3576aa87a25aa003010c97bf8bf9"

        Assert.assertEquals(expectedResult, Hash.generate(timestamp, private, public))
    }
}