package com.nishant.currentaffairs.di

import android.content.Context
import androidx.room.Room
import com.nishant.currentaffairs.api.NewsApi
import com.nishant.currentaffairs.db.ArticleDatabase
import com.nishant.currentaffairs.repositories.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class ArchModule {

    @Provides
    @ViewModelScoped
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "article_db.db"
        ).build()
    }

    @Provides
    @ViewModelScoped
    fun provideMainRepository(db: ArticleDatabase, api: NewsApi): NewsRepository {
        return NewsRepository(db, api)
    }
}