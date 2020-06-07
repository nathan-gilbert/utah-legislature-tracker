package org.mediumcool.person.controller

import org.mediumcool.integrations.openstate.person.OpenStatesPersonAPI
import org.mediumcool.person.domain.PersonDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

data class PersonResponse(val name: List<PersonDto>)

@RestController
@RequestMapping("/person")
class PersonController @Autowired constructor(private val openStatesPersonAPI: OpenStatesPersonAPI) {
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  fun getPersons(): PersonResponse {
    val persons = openStatesPersonAPI.getByBody("house")
    return PersonResponse(persons)
  }
}