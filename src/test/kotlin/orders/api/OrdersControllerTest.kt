package orders.api

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import orders.bizlogic.MockOrderGateway
import orders.bizlogic.PlaceOrderUsecase
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess

@MicronautTest
class OrdersControllerTest {
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun testPostOrder() {
        val orderUsecase = PlaceOrderUsecase(MockOrderGateway())
        val orderController = OrderController().also { it.orderUsecase = orderUsecase }
        val order = OrderJson(12, 13)
        expectCatching { orderController.postOrder(order) }.isSuccess()
    }

    @Test
    fun testPostOrderEndpoint() {
        val request = HttpRequest.POST("/order", "{ \"apples\": 7, \"oranges\": 6 }")
        val result = client.toBlocking().retrieve(request)
        expectThat(result).isEqualTo(
            "{\"orderTotal\":3.40,\"orderSummary\":\"You ordered 7 apples at " +
                    "\$0.60 each and 6 oranges at \$0.25 each for a total of \$3.40\"}")
    }

    @Test
    fun testGetOrderEndpoint() {
        val postRequest = HttpRequest.POST("/order", "{ \"apples\": 7, \"oranges\": 6 }")
        client.toBlocking().retrieve(postRequest)
        client.toBlocking().retrieve("/order")
    }
}