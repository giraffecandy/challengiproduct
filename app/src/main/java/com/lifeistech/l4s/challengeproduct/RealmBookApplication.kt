package com.lifeistech.l4s.challengeproduct

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmBookApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmConfig = Realmconfigration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}