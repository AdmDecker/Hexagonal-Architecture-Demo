package orders.bizlogic

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigDecimal

class OrderUsecaseTest {
    @Test
    fun testSomething() {
        val orderUsecase = OrderUsecase()
        val orderRequest = OrderRequest(5, 4)
        val result = orderUsecase.execute(orderRequest)
        expectThat(result.orderTotal).isEqualTo(BigDecimal("4.00"))
    }
}