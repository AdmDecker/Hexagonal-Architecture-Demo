package orders.bizlogic

import java.math.BigDecimal
import java.util.*

data class GetOrderResult(val orderId: UUID, val orderTotal: BigDecimal, val apples: Int, val oranges: Int)
