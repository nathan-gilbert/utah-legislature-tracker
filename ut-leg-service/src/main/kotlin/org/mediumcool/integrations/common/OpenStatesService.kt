package org.mediumcool.integrations.common

import com.expediagroup.graphql.client.GraphQLClient
import io.ktor.util.KtorExperimentalAPI
import java.net.URL

open class OpenStatesService {
  private val apiUrl = URL("https://openstates.org/graphql")
  @KtorExperimentalAPI
  protected val client = GraphQLClient(apiUrl)
}