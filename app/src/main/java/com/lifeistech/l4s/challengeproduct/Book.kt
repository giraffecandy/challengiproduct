package com.lifeistech.l4s.challengeproduct

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Book (
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var title: String = "",
    open var auther: String = "",
    open var description: String = "",
    open var price: Int = 0,
    open var time: Int = 0,
    open var createdAt: Date = Date(System.currentTimeMillis())
) : RealmObject()