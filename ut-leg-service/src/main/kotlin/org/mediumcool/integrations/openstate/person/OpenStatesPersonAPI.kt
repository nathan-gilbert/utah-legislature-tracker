package org.mediumcool.integrations.openstate.person

import org.mediumcool.person.domain.PersonDto

interface OpenStatesPersonAPI {
  fun getByBody(body: String): List<PersonDto>
}