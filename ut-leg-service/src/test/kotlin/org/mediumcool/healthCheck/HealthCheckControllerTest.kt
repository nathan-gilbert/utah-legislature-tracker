package org.mediumcool.healthCheck

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


private const val CONTROLLER_PATH = "/health"

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
internal class HealthCheckControllerTest @Autowired constructor(
    private val mvc: MockMvc
) {
  @Test
  fun `health endpoint returns valid response`() {
    mvc.perform(get(CONTROLLER_PATH)).andExpect(status().isOk)
  }
}
