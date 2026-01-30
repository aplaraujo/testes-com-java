import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestTest {
    @Test
    void testHamcrestMatchers() {
        // Arrange
        List<Integer> scores = List.of(23, 83, 55, 75, 77);
        // Act & Assert
        assertThat(scores, hasSize(5)); // verificar o tamanho da lista
        assertThat(scores, hasItems(83, 55, 75)); // verificar se alguns itens estão presentes na lista
        assertThat(scores, everyItem(greaterThan(20))); // verificar se todos os itens são maiores que um determinado número
        assertThat(scores, everyItem(lessThan(90))); // verificar se todos os itens são menores que um determinado número

        // Verificações com Strings
        assertThat("", is(emptyString())); // Verificar se uma cadeia de caracteres está vazia
        assertThat("", is(emptyOrNullString())); // Verificar se uma cadeia de caracteres está vazia ou se é nula

        // Verificações com vetores
        Integer[] arr = {27, 62, 9, 71, 56};
        assertThat(arr, arrayWithSize(5)); // Verifica o tamanho do vetor
        assertThat(arr, arrayContaining(27, 62, 9, 71, 56)); // Verifica se alguns itens estão presentes no vetor
        assertThat(arr, arrayContainingInAnyOrder(56, 71, 9, 62, 27)); // Verifica se alguns itens estão presentes no vetor em qualquer ordem
    }
}
