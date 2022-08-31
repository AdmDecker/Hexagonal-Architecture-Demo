package orders.bizlogic

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class GetAllOrdersUsecaseTest {
    @Test
    fun testGetAllOrdersUsecase() {
        val usecase = GetAllOrdersUsecase(MockOrderGateway())
        val result = usecase.execute()
        expectThat(result.size).isEqualTo(2)
    }
}