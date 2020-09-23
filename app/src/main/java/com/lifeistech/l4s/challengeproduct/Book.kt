package com.lifeistech.l4s.challengeproduct

import android.icu.text.CaseMap
import io.realm.RealmObject

open class Book (
    open var title: String,
    open var auther: String,
    open var desdription: String,
    open var price: Int,
    open var time: Int
) : RealmObject()