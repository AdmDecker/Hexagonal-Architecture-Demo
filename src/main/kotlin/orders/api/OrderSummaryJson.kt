package orders.api

import java.math.BigDecimal

data class OrderSummaryJson(val orderTotal: BigDecimal, val orderSummary: String)
