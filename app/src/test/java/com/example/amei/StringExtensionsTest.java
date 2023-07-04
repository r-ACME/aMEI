package com.example.amei;

import com.example.amei.Negocios.StringExtensions;

import org.junit.Assert;
import org.junit.Test;

public class StringExtensionsTest {

    @Test
    public void testSubstituirQuebrasDeLinha() {
        // Given
        String texto = "Este é um texto com\nquebras de linha.";
        String expectedText = "Este é um texto com(paragrafo)quebras de linha.";

        // When
        String actualText = StringExtensions.substituirQuebrasDeLinha(texto);

        // Then
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void testRestaurarQuebrasDeLinha() {
        // Given
        String texto = "Este é um texto com(paragrafo)quebras de linha.";
        String expectedText = "Este é um texto com\nquebras de linha.";

        // When
        String actualText = StringExtensions.restaurarQuebrasDeLinha(texto);

        // Then
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void testIsEmail_ValidEmail() {
        // Given
        String email = "example@example.com";

        // When
        boolean isEmail = StringExtensions.isEmail(email);

        // Then
        Assert.assertTrue(isEmail);
    }

    @Test
    public void testIsEmail_InvalidEmail() {
        // Given
        String email = "example@example";

        // When
        boolean isEmail = StringExtensions.isEmail(email);

        // Then
        Assert.assertFalse(isEmail);
    }

    @Test
    public void testRemoveNonNumericCharacters() {
        // Given
        String input = "a1b2c3";

        // When
        String result = StringExtensions.removeNonNumericCharacters(input);

        // Then
        Assert.assertEquals("123", result);
    }
}
