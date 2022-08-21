package pl.gda.wsb.firebaseapp.base.usecases

abstract class BaseUseCase<P, R> {
    protected abstract suspend fun create(params: P): R
    open suspend fun build(params: P): R = create(params)
}