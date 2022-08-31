package orders.api

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import orders.bizlogic.OrderRequest
import orders.bizlogic.OrderResult
import orders.bizlogic.OrderUsecase

@Controller("/order")
class OrderController {

    @Post(consumes = [MediaType.APPLICATION_JSON])
    fun postOrder(order: OrderJson): OrderSummaryJson {
        val result = OrderUsecase().execute(OrderRequest(order.apples, order.oranges))
        val orderSummary = buildOrderSummary(order, result)
        return OrderSummaryJson(result.orderTotal, orderSummary)
    }

    private fun buildOrderSummary(order: OrderJson, result: OrderResult) =
        "You ordered ${order.apples} apples at \$${result.applePrice} each " +
                "and ${order.oranges} oranges at \$${result.orangePrice} each " +
                "for a total of \$${result.orderTotal}"
}
