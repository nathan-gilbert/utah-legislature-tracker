package org.mediumcool.person.domain

import com.fasterxml.jackson.annotation.JsonFormat
import demo.person.data.Person
import demo.person.data.PersonRepository
import demo.person.validation.RecordNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

data class PersonDto(
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val id: Long?,
    val msg: String,
    val createdAt: OffsetDateTime?,
    val updatedAt: OffsetDateTime?,
    val deletedAt: OffsetDateTime?
)

@Service
class PersonServiceImpl(
    private val personRepository: PersonRepository
) : PersonService {

  override fun create(msg: String): PersonDto {
    val newPerson = personRepository.save(Person(null, msg, null, null))
    return newPerson.toDto()
  }

  override fun get(id: Long): PersonDto {
    val person = personRepository.findById(id)
        .orElseThrow { RecordNotFoundException("Invalid person id: $id"); }
    return person.toDto()
  }

  @Transactional
  override fun update(id: Long, msg: String): PersonDto {
    val personToUpdate = get(id)
    personRepository.save(Person(personToUpdate.id, msg, personToUpdate.createdAt, null))
    return get(id)
  }

  override fun remove(id: Long) {
    personRepository.deleteById(id)
  }

  override fun restore(id: Long) {
    personRepository.restore(id)
  }

  private fun Person.toDto() =
      PersonDto(
          id,
          msg,
          createdAt,
          updatedAt,
          deletedAt
      )
}
