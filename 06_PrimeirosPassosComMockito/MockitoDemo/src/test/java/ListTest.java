import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListTest {
    // Teste para simular o tamanho de uma lista
    @Test
    void testMockingListWhenSizeIsCalledShouldReturn10() {
        // Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10);
        // Act
        // Assert
        assertEquals(10, list.size());
    }

    @Test
    void testMockingListWhenSizeIsCalledShouldReturnMultipleValues() {
        // Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);
        // Act
        // Assert
        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(20, list.size());
    }

    @Test
    void testMockingListWhenGetIsCalledShouldReturnAninha() {
        // Arrange
        var list = mock(List.class);
        when(list.getFirst()).thenReturn("Aninha");
        // Act
        // Assert
        assertEquals("Aninha", list.getFirst());
        assertNull(list.get(1));
    }

    @Test
    void testMockingListWhenGetIsCalledWithArgumentMatcherShouldReturnAninha() {
        // Arrange
        var list = mock(List.class);
        when(list.get(anyInt())).thenReturn("Aninha");
        // Act
        // Assert
        assertEquals("Aninha", list.get(anyInt()));
    }

    @Test
    void testMockingListWhenThrowsAnException() {
        // Arrange
        var list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar"));
        // Act
        // Assert
        assertThrows(RuntimeException.class, () -> list.get(anyInt()), () -> "Should have thrown a RuntimeException");
    }

}
