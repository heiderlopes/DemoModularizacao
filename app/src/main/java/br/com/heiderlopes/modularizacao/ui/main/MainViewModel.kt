package br.com.heiderlopes.modularizacao.ui.main

import androidx.lifecycle.MutableLiveData
import br.com.heiderlopes.domain.entity.Product
import br.com.heiderlopes.domain.usecases.GetProductsUseCase
import br.com.heiderlopes.modularizacao.viewmodel.BaseViewModel
import br.com.heiderlopes.modularizacao.viewmodel.StateMachineSingle
import br.com.heiderlopes.modularizacao.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class MainViewModel(
    private val useCase: GetProductsUseCase,
    private val uiScheduler: Scheduler
): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Product>>>().apply {
        value = ViewState.Loading
    }

    fun getProducts(forceUpdate: Boolean = false) {
        disposables += useCase.execute(forceUpdate = forceUpdate)
            .compose(StateMachineSingle())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    state.postValue(it)
                },
                {
                    state.postValue(ViewState.Failed(it))
                }
            )
    }
}
