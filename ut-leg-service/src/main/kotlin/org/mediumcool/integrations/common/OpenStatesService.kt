package org.mediumcool.integrations.common

import com.expediagroup.graphql.client.GraphQLClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.util.KtorExperimentalAPI
import okhttp3.Request
import java.io.File
import java.net.URL


open class OpenStatesService {
  private val openStatesKey: String
  init {
    val credentials = File(".openstates.key").bufferedReader().readLines()
    openStatesKey = credentials[0]
  }

  private val apiUrl = URL("https://openstates.org/graphql")
  @KtorExperimentalAPI
  protected val client = GraphQLClient(url = apiUrl, engineFactory = OkHttp) {
    engine {
      config {
        addInterceptor {
          val newRequest = it.request()
              .newBuilder()
              .addHeader("X-API-KEY", openStatesKey)
              .build()
          it.proceed(newRequest)
        }
      }
    }
  }
}