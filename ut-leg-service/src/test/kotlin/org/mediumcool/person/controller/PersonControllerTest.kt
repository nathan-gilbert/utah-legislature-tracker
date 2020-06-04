package org.mediumcool.person.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.mediumcool.person.data.Person
import org.mediumcool.person.data.PersonRepository
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.json.JSONObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import javax.transaction.Transactional

private const val CONTROLLER_PATH = "/person"

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
internal class PersonControllerTest @Autowired constructor(
    private val mvc: MockMvc,
    private val om: ObjectMapper,
    private val personRepository: PersonRepository
) {
  private companion object {
    const val DEFAULT_ID = 1L
    const val DEFAULT_BOO = "myBoo"
  }

  private lateinit var defaultPerson: Person

  @BeforeEach
  fun setup() {
    defaultPerson = personRepository.save(Person(DEFAULT_ID, DEFAULT_BOO, null, null))
  }

  @Test
  fun `get a person`() {
    lateinit var personCreationResponse: PersonResponse
    mvc.perform(get("$CONTROLLER_PATH/${defaultPerson.id}")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk)
        .andExpect(jsonPath("\$.id").exists())
        .andExpect(jsonPath("\$.msg").exists())
        .andDo {
          personCreationResponse = om.readValue(
              it.response.contentAsString,
              PersonResponse::class.java
          )
        }
    assertThat(defaultPerson.id, equalTo(personCreationResponse.id))
    assertThat(defaultPerson.msg, equalTo(personCreationResponse.msg))
  }

  @Test
  fun `doesn't find a person`() {
    mvc.perform(get("$CONTROLLER_PATH/5000")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound)
  }

  @Test
  fun `creates a person`() {
    val newBoo = "NewBoo"
    val personBody = JSONObject(mapOf("msg" to newBoo)).toString()
    lateinit var personCreationResponse: PersonResponse
    mvc.perform(post(CONTROLLER_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(personBody))
        .andExpect(status().isCreated)
        .andExpect(jsonPath("\$.id").exists())
        .andExpect(jsonPath("\$.msg").exists())
        .andDo {
          personCreationResponse = om.readValue(
              it.response.contentAsString,
              PersonResponse::class.java
          )
        }

    val newPerson = personRepository.findById(personCreationResponse.id).get()
    assertThat(newPerson.id, equalTo(personCreationResponse.id))
    assertThat(newPerson.msg, equalTo(personCreationResponse.msg))
  }

  @Test
  fun `deletes a person`() {
    mvc.perform(delete("$CONTROLLER_PATH/${defaultPerson.id}")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk)

    val deletedPerson = personRepository.findById(defaultPerson.id!!)
    assertThat(deletedPerson.isPresent, equalTo(false))
  }

  @Test
  fun `updates a person`() {
    val updatedMsg = "NewMsg"
    val personBody = JSONObject(mapOf("id" to defaultPerson.id!!, "msg" to updatedMsg)).toString()
    lateinit var personUpdateResponse: PersonResponse
    mvc.perform(put(CONTROLLER_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(personBody))
        .andExpect(status().isOk)
        .andExpect(jsonPath("\$.id").exists())
        .andExpect(jsonPath("\$.msg").exists())
        .andDo {
          personUpdateResponse = om.readValue(
              it.response.contentAsString,
              PersonResponse::class.java
          )
        }

    assertThat(defaultPerson.id, equalTo(personUpdateResponse.id))
    assertThat(updatedMsg, equalTo(personUpdateResponse.msg))
  }
}
