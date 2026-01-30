package service;

import entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

class OrderServiceTest {

    OrderService service = new OrderService();
    UUID defaultUUID = UUID.fromString("8cf98bf0-8f43-4806-ad0b-93f2cf9b386e");

    @DisplayName("Test should include random orderId when orderId does not exist")
    @Test
    public void testShouldIncludeRandomOrderId_When_OrderIdDoesNotExist() {
        // Given

        try(MockedStatic<UUID> mockedStatic = mockStatic(UUID.class)) {
            mockedStatic.when(UUID::randomUUID).thenReturn(defaultUUID);
            Order result = service.createOrder("MacBook Pro", 2L, null);
            assertEquals(defaultUUID.toString(), result.getId());
        }

    }
}