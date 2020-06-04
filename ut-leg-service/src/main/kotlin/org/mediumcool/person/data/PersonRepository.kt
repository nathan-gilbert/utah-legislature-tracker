package org.mediumcool.person.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

/**
 * Repository for soft deletable Persones
 */
interface PersonRepository : JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
  @Modifying(clearAutomatically = true)
  @Query("UPDATE person SET deleted_at = NULL WHERE id=?1", nativeQuery = true)
  fun restore(id: Long)
}

/**
 * Repository for hard deletable Persones
 */
interface PersonMasterRepository : JpaRepository<PersonMaster, Long>
