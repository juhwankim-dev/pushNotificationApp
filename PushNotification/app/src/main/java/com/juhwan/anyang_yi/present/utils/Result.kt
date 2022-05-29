package com.juhwan.anyang_yi.present.utils

data class Result<out T>(
    val status: Status,
    val data: T?,
    val message:String?
){
    companion object{
        fun <T> success(data:T?): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(msg:String, data:T?): Result<T> {
            return Result(Status.ERROR, data, msg)
        }

        fun <T> fail(): Result<T> {
            return Result(Status.FAIL, null, null)
        }

        fun <T> loading(data:T?): Result<T> {
            return Result(Status.LOADING, data, null)
        }
    }
}