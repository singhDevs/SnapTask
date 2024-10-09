package com.singhdevs.snaptask.domain.model

class Errors {
    class FieldsNotFilledException(message: String) : Exception(message)
}