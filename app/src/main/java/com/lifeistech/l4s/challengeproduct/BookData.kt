package com.lifeistech.l4s.challengeproduct

import io.realm.annotations.PrimaryKey

open class BookData(
    @PrimaryKey
    val title: String,
    val auther: String,
    val description: String,
    val price: Int,
    var time: Int
)