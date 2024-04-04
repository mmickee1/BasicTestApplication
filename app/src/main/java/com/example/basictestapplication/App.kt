package com.example.basictestapplication

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.basictestapplication.worker.WidgetWorkerAdapter
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    @Inject
    lateinit var appWidgetAdapter: WidgetWorkerAdapter

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appWidgetAdapter.startWidgetAppWorker()
    }
}