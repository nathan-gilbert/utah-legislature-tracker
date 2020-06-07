package org.mediumcool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class PoliticalTrackerApplication

fun main(args: Array<String>) {
  @Suppress("SpreadOperator")
  runApplication<PoliticalTrackerApplication>(*args)
}
