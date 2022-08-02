package pl.gda.wsb.firebaseapp.base.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

abstract class BaseFlowUseCase<N, R>(protected val defaultContext: CoroutineContext = Dispatchers.Main) {
    protected abstract fun create(params: N): Flow<R>
    open fun build(params: N): Flow<R> = create(params)
}
