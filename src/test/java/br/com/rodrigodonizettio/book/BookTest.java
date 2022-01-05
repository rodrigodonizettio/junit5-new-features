package br.com.rodrigodonizettio.book;

import br.com.rodrigodonizettio.book.converter.NumberOfChaptersConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    @ParameterizedTest
    @ValueSource(strings = "The Hard Thing About Hard Things")
    void implicitFallbackArgumentConversionTest(Book book) {
        assertEquals("The Hard Thing About Hard Things", book.getTitle());
    }

    @ParameterizedTest
    @ValueSource(strings = "289")
    void explicitArgumentConversionWithAnnotatedParameterTest(@ConvertWith(NumberOfChaptersConverter.class) Integer numberOfChapters) {
        assertEquals(289, numberOfChapters);
    }
}
