package org.mediumcool.person.domain

interface PersonService {
  /**
   * @param msg: message to save with person
   */
  fun create(msg: String): PersonDto

  /**
   * @param id: id of person to retrieve
   */
  fun get(id: Long): PersonDto

  /**
   * @param id: id of person to update
   * @param msg: message to save with person
   */
  fun update(id: Long, msg: String): PersonDto

  /**
   * @param id: id of person to remove
   */
  fun remove(id: Long)

  /**
   * @param id: id of person to restore
   */
  fun restore(id: Long)
}
