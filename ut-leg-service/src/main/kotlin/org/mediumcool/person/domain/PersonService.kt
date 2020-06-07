package org.mediumcool.person.domain

interface PersonService {

  fun get(): List<PersonDto>
}