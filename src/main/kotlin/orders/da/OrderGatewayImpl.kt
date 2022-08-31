package orders.da

import jakarta.inject.Singleton
import orders.bizlogic.OrderGateway
import orders.bizlogic.PersistableOrder
import java.util.*
import kotlin.collections.HashMap

@Singleton
class OrderGatewayImpl : OrderGateway {
    private val storedOrders = HashMap<UUID, PersistableOrder>()

    override fun persistOrder(persistableOrder: PersistableOrder) {
        storedOrders[persistableOrder.orderId] = persistableOrder
    }

    override fun getOrder(orderId: UUID): PersistableOrder {
        return storedOrders[orderId]!!;
    }

    override fun getAllOrders(): List<PersistableOrder> {
        return storedOrders.values.toList()
    }

}
