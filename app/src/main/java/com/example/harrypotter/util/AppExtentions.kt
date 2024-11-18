package com.example.harrypotter.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectLifecycleAware(owner: LifecycleOwner, flowCollector: FlowCollector<T>) {
    owner.lifecycleScope.launch {
        this@collectLifecycleAware.flowWithLifecycle(owner.lifecycle)
            .collect(flowCollector)
    }
}