package org.mediumcool

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class TemplateApplicationKtTest {
  @Test
  fun `main executes without exceptions`() {
    Assertions.assertThatCode {
      main(emptyArray())
    }.doesNotThrowAnyException()
  }
}
