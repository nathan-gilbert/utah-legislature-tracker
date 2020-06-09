package org.mediumcool.integrations.openstate.person

import org.mediumcool.person.domain.PersonDto

interface OpenStatesPersonAPI {
  fun getHouseMembers(): List<PersonDto>
  fun getSenateMembers(): List<PersonDto>
}