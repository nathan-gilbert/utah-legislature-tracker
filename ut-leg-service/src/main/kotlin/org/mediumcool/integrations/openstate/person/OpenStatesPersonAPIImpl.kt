package org.mediumcool.integrations.openstate.person

import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking
import org.mediumcool.integrations.common.OpenStatesService
import org.mediumcool.person.domain.PersonDto
import org.mediumcool.graphql.generated.UtahLegislators
import org.springframework.stereotype.Service

@Service
class OpenStatesPersonAPIImpl: OpenStatesPersonAPI, OpenStatesService() {
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