package org.mediumcool.integrations.openstate.person

import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking
import org.mediumcool.integrations.common.OpenStatesService
import org.mediumcool.person.domain.PersonDto
import org.mediumcool.graphql.generated.UtahHouseMembers
import org.mediumcool.graphql.generated.UtahSenateMembers
import org.springframework.stereotype.Service

@Service
@KtorExperimentalAPI
class OpenStatesPersonAPIImpl: OpenStatesPersonAPI, OpenStatesService() {
  override fun getHouseMembers(): List<PersonDto> {
    val utahLegislators = UtahHouseMembers(client)
    val houseNames = mutableListOf<PersonDto>()
    runBlocking {
      val results = utahLegislators.execute()
      results.data?.people?.edges?.forEach {
        houseNames.add(PersonDto(
            it?.node?.id!!,
            it.node.name!!))
      }
    }
    return houseNames
  }

  override fun getSenateMembers(): List<PersonDto> {
    val utahLegislators = UtahSenateMembers(client)
    val senateNames = mutableListOf<PersonDto>()
    runBlocking {
      val results = utahLegislators.execute()
      results.data?.people?.edges?.forEach {
        senateNames.add(PersonDto(
            it?.node?.id!!,
            it.node.name!!)
        )
      }
    }
    return senateNames
  }

}