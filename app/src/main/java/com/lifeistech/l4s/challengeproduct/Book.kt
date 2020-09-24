package com.lifeistech.l4s.challengeproduct

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Book (
    @PrimaryKey
    open var title: String,
    open var auther: String,
    open var description: String,
    open var price: Int,
    open var time: Int
) : RealmObject()