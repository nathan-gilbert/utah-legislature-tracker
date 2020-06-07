package org.mediumcool.integrations.common

import com.expediagroup.graphql.client.GraphQLClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.util.KtorExperimentalAPI
import okhttp3.Request
import java.net.URL


open class OpenStatesService {
  private val apiUrl = URL("https://openstates.org/graphql")
  @KtorExperimentalAPI
  protected val client = GraphQLClient(url = apiUrl, engineFactory = OkHttp) {
    engine {
      config {
        addInterceptor {
          val request: Request = it.request()
          val newRequest: Request

          newRequest = request.newBuilder()
              .addHeader("X-API-KEY", "")
              .build()
          it.proceed(newRequest)
        }
      }
    }
  }
}