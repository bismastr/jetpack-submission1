package com.example.jetpack_submission1.data.local.favoriteRoom


import androidx.paging.DataSource
import androidx.room.*
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: FavoriteEntity)

    @Delete
    suspend fun delete(favorite: FavoriteEntity)

    @Query("SELECT * FROM favorite_table WHERE `from` = :from")
    fun getFavorite(from: Int): DataSource.Factory<Int, FavoriteEntity>

    @Query("SELECT count(*) FROM favorite_table WHERE id = :id")
    suspend fun checkFavorite(id: Int): Int

}