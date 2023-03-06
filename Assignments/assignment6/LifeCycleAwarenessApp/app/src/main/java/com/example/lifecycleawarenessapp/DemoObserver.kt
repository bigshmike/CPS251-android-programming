package com.example.lifecycleawarenessapp

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.lifecycleawarenessapp.ui.main.MainViewModel

class DemoObserver(private val viewModel: MainViewModel) : DefaultLifecycleObserver {

    private val logTag = "DemoObserver"

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        viewModel.getMessage("onResume was fired on " + viewModel.getLogTime() + "\n**********")
        Log.i(logTag, "Demo Observer onResume")
    }

    override fun onPause(owner: LifecycleOwner){
        super.onPause(owner)
        viewModel.getMessage("onPause was fired on " + viewModel.getLogTime() + "\n**********")
        Log.i(logTag, "Demo Observer onPause")
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.getMessage("onCreate was fired on " + viewModel.getLogTime())
        Log.i(logTag, "Demo Observer onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        viewModel.getMessage("onStart was fired on " + viewModel.getLogTime())
        Log.i(logTag, "Demo Observer onStart")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        viewModel.getMessage("onStop was fired on " + viewModel.getLogTime())
        Log.i(logTag, "Demo Observer onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        viewModel.getMessage("onDestroy was fired on " + viewModel.getLogTime() + "\n**********")
        Log.i(logTag, "Demo Observer onDestroy")
    }
}