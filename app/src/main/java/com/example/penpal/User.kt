package com.example.penpal

data class User(
    var uuid:String,
    val fullname:String,
    val email:String

){
    constructor():this("","","")
}
