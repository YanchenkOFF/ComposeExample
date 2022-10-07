package com.example.composefordkc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


fun <T : Any, R : Any> StateFlow<R>.fieldStateFlow(
    scope: CoroutineScope,
    initState: T,
    transform: (R) -> T
): StateFlow<T> {
    return map(transform).stateIn(scope, SharingStarted.Lazily, initState)
}