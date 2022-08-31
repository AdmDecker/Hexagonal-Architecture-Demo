package orders.api

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigDecimal

@MicronautTest
class OrdersControllerTest {
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun testPostOrder() {
        val orderController = OrderController()
        val order = OrderJson(12, 13)
        val result = orderController.postOrder(order)
        expectThat(result.orderTotal).isEqualTo(BigDecimal("10.45"))
    }

    @Test
    fun testControllerEndpointWorks() {
        val request = HttpRequest.POST("/order", "{ \"apples\": 7, \"oranges\": 6 }")
        val result = client.toBlocking().retrieve(request)
        expectThat(result).isEqualTo(
            "{\"orderTotal\":5.70,\"orderSummary\":\"You ordered 7 apples at " +
                    "\$0.60 each and 6 oranges at \$0.25 each for a total of \$5.70\"}")
    }
}