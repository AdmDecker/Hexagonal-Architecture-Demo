package orders.api

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import orders.bizlogic.*
import java.util.*

@Controller("/order")
class OrderController {

    @Inject
    lateinit var orderUsecase: PlaceOrderUsecase

    @Inject lateinit var getOrderUsecase: GetOrderUsecase

    @Inject lateinit var getOrdersUsecase: GetAllOrdersUsecase

    @Post(consumes = [MediaType.APPLICATION_JSON])
    fun postOrder(order: OrderJson): OrderSummaryJson {
        val result = orderUsecase.execute(OrderRequest(order.apples, order.oranges))
        val orderSummary = buildOrderSummary(order, result)
        return OrderSummaryJson(result.orderTotal, orderSummary)
    }

    @Get
    fun getOrders(): List<GetOrderResult> {
        return getOrdersUsecase.execute();
    }

    @Get("/{orderId}")
    fun getOrder(orderId: UUID): GetOrderResult {
        return getOrderUsecase.execute(orderId)
    }

    private fun buildOrderSummary(order: OrderJson, result: OrderResult) =
        "You ordered ${order.apples} apples at \$${result.applePrice} each " +
                "and ${order.oranges} oranges at \$${result.orangePrice} each " +
                "for a total of \$${result.orderTotal}"
}
