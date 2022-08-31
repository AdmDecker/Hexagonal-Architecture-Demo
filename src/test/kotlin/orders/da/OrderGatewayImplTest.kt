package orders.da

import orders.bizlogic.PersistableOrder
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

class OrderGatewayImplTest {
    @Test
    fun testSomething() {
        val gateway = OrderGatewayImpl()
        gateway.persistOrder(PersistableOrder(UUID.randomUUID(), 1, 1, BigDecimal("0.87")))
    }
}