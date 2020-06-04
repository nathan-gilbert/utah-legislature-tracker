package org.mediumcool.person.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import demo.person.data.Person
import demo.person.data.PersonMaster
import demo.person.data.PersonMasterRepository
import demo.person.data.PersonRepository
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.OffsetDateTime.now
import java.util.Optional

@ExtendWith(SpringExtension::class)
internal class PersonServiceImplTest {

  @Mock
  private lateinit var personRepository: PersonRepository

  @Mock
  private lateinit var masterPersonRepository: PersonMasterRepository

  @Captor
  private lateinit var personCaptor: ArgumentCaptor<Person>

  @InjectMocks
  private lateinit var personService: PersonServiceImpl

  private companion object {
    const val DEFAULT_BOO = "myBoo"
    const val DEFAULT_ID = 1L
    const val DEFAULT_MSG = "myBooTwo"
    val DEFAULT_BAZ = Person(2L, DEFAULT_MSG, null, null).apply { createdAt = now(); updatedAt = now() }
  }

  @Test
  fun `creates new Person`() {
    whenever(personRepository.save(any<Person>()))
        .thenReturn(DEFAULT_BAZ)

    val createdPersonDto = personService.create(DEFAULT_MSG)

    assertNotNull(createdPersonDto.id)
    assertThat(createdPersonDto.id, equalTo(DEFAULT_BAZ.id))
    assertThat(createdPersonDto.msg, equalTo(DEFAULT_MSG))
    assertNotNull(createdPersonDto.createdAt)
    assertNotNull(createdPersonDto.updatedAt)

    verify(personRepository).save(personCaptor.capture())
    val savedPerson = personCaptor.value
    assertThat(savedPerson.id, nullValue())
    assertThat(savedPerson.msg, equalTo(DEFAULT_MSG))
  }

  @Test
  fun `restores a deletes a Person`() {
    val tempPerson = Person(DEFAULT_ID, DEFAULT_BOO, null, null).apply { createdAt = now(); updatedAt = now() }
    val masterPerson = PersonMaster(DEFAULT_ID, DEFAULT_BOO, tempPerson.createdAt, tempPerson.updatedAt, null)

    whenever(personRepository.findById(DEFAULT_ID))
        .thenReturn(Optional.of(tempPerson))

    personService.remove(DEFAULT_ID)

    whenever(masterPersonRepository.findById(DEFAULT_ID))
        .thenReturn(Optional.of(masterPerson))

    personService.restore(DEFAULT_ID)
    verify(personRepository).restore(DEFAULT_ID)
  }
}
