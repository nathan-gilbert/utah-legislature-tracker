package org.mediumcool.person.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class PersonRepositoryTest @Autowired constructor(
    private val personRepository: PersonRepository
) {

  val person: Person by lazy {
    personRepository.save(Person(null, "myPerson", null, null))
  }

  @Test
  fun `findById find a person`() {
    val foundPerson = personRepository.findById(person.id!!)
    assertThat(foundPerson).isPresent
    assertThat(foundPerson.get().id).isEqualTo(person.id!!)
    assertThat(foundPerson.get().msg).isEqualTo(person.msg)
    assertThat(foundPerson.get().createdAt).isEqualTo(person.createdAt)
    assertThat(foundPerson.get().updatedAt).isEqualTo(person.updatedAt)
    assertThat(foundPerson.get().deletedAt).isEqualTo(person.deletedAt)
  }
}

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class PersonMasterRepositoryTest @Autowired constructor(
    private val masterPersonRepository: PersonMasterRepository,
    private val personRepository: PersonRepository
) {

  val personMaster: PersonMaster by lazy {
    masterPersonRepository.save(PersonMaster(null, "myPerson", null, null, null))
  }

  val anotherPersonMaster: PersonMaster by lazy {
    masterPersonRepository.save(PersonMaster(null, "myPerson2", null, null, null))
  }

  @Test
  fun `findById find a master person`() {
    val foundPerson = personRepository.findById(personMaster.id!!)
    assertThat(foundPerson).isPresent
    assertThat(foundPerson.get().id).isEqualTo(personMaster.id!!)
    assertThat(foundPerson.get().msg).isEqualTo(personMaster.msg)
    assertThat(foundPerson.get().createdAt).isEqualTo(personMaster.createdAt)
    assertThat(foundPerson.get().updatedAt).isEqualTo(personMaster.updatedAt)
    assertThat(foundPerson.get().deletedAt).isEqualTo(personMaster.deletedAt)
  }

  @Test
  fun `findById should find a soft deleted entity`() {
    assertThat(personRepository.findById(anotherPersonMaster.id!!)).isPresent
    personRepository.deleteById(anotherPersonMaster.id!!)
    assertThat(masterPersonRepository.findById(anotherPersonMaster.id!!)).isPresent
  }
}
