package com.example.jetpack_submission1.data.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.data.local.favoriteRoom.FilmDao

class LocalRepository(private val mFilmDao: FilmDao) {

    companion object {
        @Volatile
        private var instance: LocalRepository? = null

        fun getInstance(filmDao: FilmDao): LocalRepository =
            instance ?: synchronized(this) {
                instance ?: LocalRepository(filmDao).apply { instance = this }
            }
    }

    fun getAllMovie(from: Int): LiveData<PagedList<FavoriteEntity>> {
        return LivePagedListBuilder(mFilmDao.getFavorite(from), 20).build()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(favoriteEntity: FavoriteEntity){
        mFilmDao.insert(favoriteEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(favoriteEntity: FavoriteEntity){
        mFilmDao.delete(favoriteEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun checkFavorite(id: Int): Int{
        return mFilmDao.checkFavorite(id)
    }
}