package com.example.appmovie.response

interface ResponseListener<T> {

    fun onResponse(response: RepositoryResponse<T>)

    fun onError(repositoryError: RepositoryError)

}
