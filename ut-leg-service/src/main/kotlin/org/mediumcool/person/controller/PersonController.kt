package org.mediumcool.person.controller

import demo.person.domain.PersonService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime

data class CreatePersonRequest(
    val msg: String
)

data class UpdatePersonRequest(
    val id: Long,
    val msg: String
)

data class PersonResponse(
    val id: Long,
    val msg: String,
    val createdAt: OffsetDateTime?,
    val updatedAt: OffsetDateTime?,
    val deletedAt: OffsetDateTime?
)

@RestController
@RequestMapping("/person")
class PersonController(
    private val personService: PersonService
) {
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun getPerson(@PathVariable id: Long): PersonResponse {
    val result = personService.get(id)
    return PersonResponse(result.id!!, result.msg, result.createdAt, result.updatedAt, null)
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun deletePerson(@PathVariable id: Long) {
    personService.remove(id)
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createPerson(
      @Validated @RequestBody createPersonBody: CreatePersonRequest
  ): PersonResponse {
    val newPerson = personService.create(createPersonBody.msg)
    return PersonResponse(newPerson.id!!, newPerson.msg, newPerson.createdAt, newPerson.updatedAt, newPerson.deletedAt)
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  fun updatePerson(
      @Validated @RequestBody updatePersonBody: UpdatePersonRequest
  ): PersonResponse {
    personService.update(updatePersonBody.id, updatePersonBody.msg)
    val updatedPerson = personService.get(updatePersonBody.id)
    return PersonResponse(updatedPerson.id!!,
        updatedPerson.msg,
        updatedPerson.createdAt,
        updatedPerson.updatedAt,
        updatedPerson.deletedAt)
  }
}
