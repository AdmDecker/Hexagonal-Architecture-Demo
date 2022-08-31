package orders.bizlogic

import java.util.*

interface OrderGateway {
    fun persistOrder(persistableOrder: PersistableOrder)
    fun getOrder(orderId: UUID): PersistableOrder
    fun getAllOrders(): List<PersistableOrder>
}
