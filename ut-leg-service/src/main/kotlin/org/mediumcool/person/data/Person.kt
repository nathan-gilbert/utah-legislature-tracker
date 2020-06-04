package org.mediumcool.person.data

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.Where
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Table

/**
 * Person Entity.
 *
 * @property id - auto generated id
 * @property msg - msg of the person
 * @property createdAt - date this person was created
 * @property updatedAt - date this person was updated
 * @property deletedAt - date this person was deleted
 */
@MappedSuperclass
class BasePerson internal constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "msg")
    val msg: String,
    @Column(name = "created_at")
    @CreationTimestamp
    var createdAt: OffsetDateTime? = null,
    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: OffsetDateTime? = null,
    @Column(name = "deleted_at")
    val deletedAt: OffsetDateTime? = null
)

/**
 * Soft deletable Person
 */
@Entity
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE person SET deleted_at = now() WHERE id=?")
class Person internal constructor(
    id: Long?,
    firstName: String,
    lastName: String,
    office: String,
    createdAt: OffsetDateTime?,
    updatedAt: OffsetDateTime?
) : BasePerson(id, firstName, lastName, createdAt, updatedAt)

/**
 * Hard deletable Person
 */
@Entity
@Table(name = "person")
class PersonMaster internal constructor(
    id: Long?,
    firstName: String,
    lastName: String,
    office: String,
    createdAt: OffsetDateTime?,
    updatedAt: OffsetDateTime?,
    deletedAt: OffsetDateTime?
) : BasePerson(id, firstName, lastName, office, createdAt, updatedAt, deletedAt)
