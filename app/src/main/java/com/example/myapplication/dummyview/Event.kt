package com.example.myapplication.dummyview

sealed class Event {

    data class OnSetTextClick(val text: String) : Event()
}