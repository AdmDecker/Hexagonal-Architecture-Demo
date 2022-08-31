package orders.bizlogic

import java.math.BigDecimal

data class OrderResult(
    val orderTotal: BigDecimal,
    val applePrice: BigDecimal,
    val orangePrice: BigDecimal)
