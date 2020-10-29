package com.example.todoapp.dagger

import android.app.Application
import com.example.todoapp.BaseApplication
import com.example.todoapp.data.database.AppDatabaseModule
import com.example.todoapp.data.local.note.NoteUseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppDatabaseModule::class,
        NoteUseCaseModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}