package com.k4nd4.core.data


sealed class Resource<out R> {
    data object Success : Resource<Nothing>()
    data object Error : Resource<Nothing>()
    data object Empty : Resource<Nothing>()
}