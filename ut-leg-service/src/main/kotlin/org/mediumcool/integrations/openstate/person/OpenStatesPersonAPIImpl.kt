package org.mediumcool.integrations.openstate.person

import com.expediagroup.graphql.types.GraphQLResponse
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking
import org.mediumcool.integrations.common.OpenStatesService
import org.mediumcool.person.domain.PersonDto
import org.mediumcool.graphql.generated.UtahLegislators

class OpenStatesPersonAPIImpl: OpenStatesPeopleAPI, OpenStatesService() {

  @KtorExperimentalAPI
  override fun getByBody(body: String): List<PersonDto> {
    val utahLegislators = UtahLegislators(client)
    val orgNames = mutableListOf<PersonDto>()
    runBlocking {
      val results = utahLegislators.execute()
      results.data?.jurisdiction?.organizations?.edges?.forEach {
        orgNames.add(PersonDto(it?.node?.name!!))
      }
    }
    return orgNames
  }

}