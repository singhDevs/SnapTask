package com.singhdevs.snaptask.domain

sealed class TaskOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): TaskOrder(orderType)
    class Status(orderType: OrderType): TaskOrder(orderType)
    class Priority(orderType: OrderType): TaskOrder(orderType)
    class Date(orderType: OrderType): TaskOrder(orderType)

    fun copy(orderType: OrderType): TaskOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Status -> Status(orderType)
            is Priority -> Priority(orderType)
            is Date -> Date(orderType)
        }
    }
}