package org.mediumcool.person.validation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class RecordNotFoundException(exception: String?) : RuntimeException(exception)
