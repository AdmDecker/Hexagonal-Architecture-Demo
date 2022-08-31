package orders.bizlogic

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigDecimal
import java.util.*

class PlaceOrderUsecaseTest {
    lateinit var result: OrderResult
    lateinit var orderGateway: MockOrderGateway

    @Test
    fun testOrder() {
        val orderRequest = OrderRequest(1, 2)
        whenOrderExecuted(orderRequest)
        thenOrderTotalEquals("1.10")
        thenOrderWasPersisted(PersistableOrder(UUID.randomUUID(), 1, 2, BigDecimal("1.10")))
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

    private fun whenOrderExecuted(orderRequest: OrderRequest) {
        orderGateway = MockOrderGateway()
        val orderUsecase = PlaceOrderUsecase(orderGateway)
        result = orderUsecase.execute(orderRequest)
    }

    private fun thenOrderWasPersisted(persistableOrder: PersistableOrder) {
        expectThat(orderGateway.capturedOrder.orderTotal)
            .isEqualTo(persistableOrder.orderTotal)
    }
}

class MockOrderGateway : OrderGateway {

    lateinit var capturedOrder: PersistableOrder
    override fun persistOrder(persistableOrder: PersistableOrder) {
        capturedOrder = persistableOrder
    }

    override fun getOrder(orderId: UUID): PersistableOrder {
        return capturedOrder
    }

    override fun getAllOrders(): List<PersistableOrder> {
        return listOf(
            PersistableOrder(UUID.randomUUID(), 7, 7, BigDecimal("1")),
            PersistableOrder(UUID.randomUUID(), 8, 8, BigDecimal("7"))
        )
    }
}