package orders.bizlogic

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class GetAllOrdersUsecase(@Inject val gateway: OrderGateway) {

    fun execute(): List<GetOrderResult> {
        return gateway.getAllOrders().map {
            GetOrderResult(it.orderId, it.orderTotal, it.apples, it.oranges)
        }
    }
}