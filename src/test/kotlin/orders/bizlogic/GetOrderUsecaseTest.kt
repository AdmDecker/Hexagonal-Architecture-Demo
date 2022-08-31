package orders.bizlogic

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigDecimal
import java.util.*

class GetOrderUsecaseTest {
    @Test
    fun testGetOrder() {
        val uuid = UUID.randomUUID()
        val order = PersistableOrder(uuid, 7, 7, BigDecimal("2"))
        val usecase = GetOrderUsecase(MockOrderGateway().also { it.capturedOrder = order })
        val result = usecase.execute(uuid)
        expectThat(result).isEqualTo(GetOrderResult(order.orderId, order.orderTotal, order.apples, order.oranges))
    }
}