package orders.bizlogic
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.math.BigDecimal
import java.util.*

@Singleton
class PlaceOrderUsecase(@Inject private val orderGateway: OrderGateway) {
    private val costOfApples = BigDecimal("0.60")
    private val costOfOranges = BigDecimal("0.25")

    fun execute(order: OrderRequest): OrderResult {
        val orderTotal = calculateAppleCost(order) + calculateOrangeCost(order)
        orderGateway.persistOrder(PersistableOrder(UUID.randomUUID(), order.apples, order.oranges, orderTotal))
        return OrderResult(orderTotal, costOfApples, costOfOranges)
    }

    private fun calculateOrangeCost(order: OrderRequest) =
        costOfOranges * BigDecimal(order.oranges - order.oranges / 3)

    private fun calculateAppleCost(order: OrderRequest) =
        costOfApples * (BigDecimal(order.apples / 2 + order.apples % 2))
}