package orders

import org.junit.jupiter.api.Test

class OrdersTest {

    @Test
    fun testSomething() {
        val orderController = OrderController()
        val order = OrderJson(12, 13)
        orderController.postOrder(order)
    }
}