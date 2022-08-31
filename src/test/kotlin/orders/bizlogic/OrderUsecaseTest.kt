package orders.bizlogic

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigDecimal

class OrderUsecaseTest {
    lateinit var result: OrderResult

    @Test
    fun testOrder() {
        val orderRequest = OrderRequest(1, 2)
        whenOrderExecuted(orderRequest)
        thenOrderTotalEquals("1.10")
    }

    @Test
    fun testOrder_BuyOneAppleGetOneFree() {
        val orderRequest = OrderRequest(2, 1)
        whenOrderExecuted(orderRequest)
        thenOrderTotalEquals("0.85")
    }

    @Test
    fun testOrder_BuyTwoApplesGetOneFree() {
        val orderRequest = OrderRequest(3, 1)
        whenOrderExecuted(orderRequest)
        thenOrderTotalEquals("1.45")
    }

    @Test
    fun testOrder_ThreeOrangesForPriceOfTwo() {
        val orderRequest = OrderRequest(1, 3)
        whenOrderExecuted(orderRequest)
        thenOrderTotalEquals("1.10")
    }

    @Test
    fun testOrder_FourOrangesForPriceOfThree() {
        val orderRequest = OrderRequest(1, 4)
        whenOrderExecuted(orderRequest)
        thenOrderTotalEquals("1.35")
    }

    private fun thenOrderTotalEquals(expectedTotal: String) {
        expectThat(result.orderTotal).isEqualTo(BigDecimal(expectedTotal))
    }

    private fun whenOrderExecuted(orderRequest: OrderRequest): OrderResult {
        val orderUsecase = OrderUsecase()

        result = orderUsecase.execute(orderRequest)
        return result
    }


}