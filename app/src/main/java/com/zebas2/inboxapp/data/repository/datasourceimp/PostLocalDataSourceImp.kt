package com.zebas2.inboxapp.data.repository.datasourceimp

import com.zebas2.inboxapp.data.db.PostDao
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.data.repository.datasource.PostLocalDataSource

class PostLocalDataSourceImp(
    private val postDao: PostDao
) : PostLocalDataSource {

    override suspend fun savePostList(postList: List<Post>) {
        postDao.insertList(postList)
    }

    override suspend fun savePost(post: Post) {
        postDao.insert(post)
    }

    override suspend fun deletePost(post: Post) {
        postDao.deletePost(post)
    }

    override suspend fun getPosts(): List<Post> {
        return postDao.getPosts()
    }

    override suspend fun updatePost(post: Post) {
        return postDao.updatePost(post)
    }

    override suspend fun getFavoritesPost(): List<Post> {
        return postDao.getFavoritesPosts(1)// 1 = true
    }

}