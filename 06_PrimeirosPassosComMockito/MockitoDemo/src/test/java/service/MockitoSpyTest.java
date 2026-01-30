package service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

// Mockito spy - faz o "empacotamento" de uma instância existente, porém permitindo a simulação parcial dessa mesma instância
public class MockitoSpyTest {
    @Test
    void testV1() {
        List<String> mockArr = spy(ArrayList.class);
        assertEquals(0, mockArr.size());

        when(mockArr.size()).thenReturn(5);
        mockArr.add("Foo");
        assertEquals(5, mockArr.size());
    }

    @Test
    void testV2() {
        List<String> spyArr = spy(ArrayList.class);
        assertEquals(0, spyArr.size());

        spyArr.add("Foo");
        assertEquals(1, spyArr.size());

        spyArr.remove("Foo");
        assertEquals(0, spyArr.size());
    }

    @Test
    void testV3() {
        List<String> spyArr = spy(ArrayList.class);
        assertEquals(0, spyArr.size());
        when(spyArr.size()).thenReturn(5);
        assertEquals(5, spyArr.size());
    }

    @Test
    void testV4() {
        List<String> spyArr = spy(ArrayList.class);
        spyArr.add("Foo");

        verify(spyArr).add("Foo");
        // verify(spyArr, never()).remove("Foo");
        verify(spyArr, never()).remove(anyString());
        verify(spyArr, never()).clear();
        // assertEquals(5, spyArr.size());
    }
}
