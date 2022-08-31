package orders.bizlogic

import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*


@Singleton
class GetOrderUsecase(@Inject private val orderGateway: OrderGateway) {
    fun execute(orderId: UUID): GetOrderResult {
        val order = orderGateway.getOrder(orderId)
        return GetOrderResult(order.orderId, order.orderTotal, order.apples, order.oranges)
    }
}
