/*
 *   Copyright 2020 Leon Latsch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package dev.leonlatsch.photok.ui.process.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

/**
 * Abstract base for all processing view models.
 * Holds live data for [ProcessState] and [ProcessProgress].
 * Provides abstract functions called by the ui.
 *
 * @sine 1.0.0
 * @author Leon Latsch
 */
abstract class BaseProcessViewModel : ViewModel() {

    /**
     * The processing state should be checked every time in [process].
     */
    val processState: MutableLiveData<ProcessState> = MutableLiveData()

    /**
     * An [ProcessProgress] instance which is bound to the ui.
     */
    var progress: MutableLiveData<ProcessProgress> = MutableLiveData(ProcessProgress())

    /**
     * Indicates if failures occurred.
     * Gets evaluated by base DialogFragment to show warning. Should be set in [process] if an elements fails.
     */
    var failuresOccurred = false

    /**
     * Handles processing in children.
     * Needs to be launched in a coroutine.
     * called by ui.
     */
    abstract fun process(): Job

    /**
     * Updates the state to [ProcessState.ABORTED].
     */
    open fun cancel() {
        processState.postValue(ProcessState.ABORTED)
    }
}