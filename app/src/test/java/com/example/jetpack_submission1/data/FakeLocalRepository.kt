package com.example.jetpack_submission1.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.data.local.favoriteRoom.FilmDao

class FakeLocalRepository(private val mFilmDao: FilmDao) {

    fun getAllMovie(from: Int): LiveData<PagedList<FavoriteEntity>> {
        return LivePagedListBuilder(mFilmDao.getFavorite(from), 20).build()
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(favoriteEntity: FavoriteEntity) {
        mFilmDao.insert(favoriteEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(favoriteEntity: FavoriteEntity) {
        mFilmDao.delete(favoriteEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun checkFavorite(id: Int): Int {
        return mFilmDao.checkFavorite(id)
    }
}