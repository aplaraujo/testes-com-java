import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class ListWithBDDTest {
    // Teste para simular o tamanho de uma lista
    @Test
    void testMockingListWhenSizeIsCalledShouldReturn10() {
        // Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10);
        // Act
        // Assert
        assertThat(list.size(), is(10));
    }

    @Test
    void testMockingListWhenSizeIsCalledShouldReturnMultipleValues() {
        // Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10).willReturn(20);
        // Act
        // Assert
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(20));
        assertThat(list.size(), is(20));
    }

    @Test
    void testMockingListWhenGetIsCalledShouldReturnAninha() {
        // Arrange
        var list = mock(List.class);
        given(list.getFirst()).willReturn("Aninha");
        // Act
        // Assert
        assertThat(list.getFirst(), is("Aninha"));
        assertNull(list.get(1));
    }

    @Test
    void testMockingListWhenGetIsCalledWithArgumentMatcherShouldReturnAninha() {
        // Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willReturn("Aninha");
        // Act
        // Assert
        assertThat(list.get(anyInt()), is("Aninha"));
    }

    @Test
    void testMockingListWhenThrowsAnException() {
        // Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willThrow(new RuntimeException("Foo Bar"));
        // Act
        // Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> list.get(anyInt()), () -> "Should have thrown a RuntimeException");
        assertThat(exception, is(instanceOf(RuntimeException.class)));
    }

}
