package orders.bizlogic

import java.math.BigDecimal
import java.util.*

data class PersistableOrder(
    val orderId: UUID,
    val apples: Int,
    val oranges: Int,
    val orderTotal: BigDecimal)
