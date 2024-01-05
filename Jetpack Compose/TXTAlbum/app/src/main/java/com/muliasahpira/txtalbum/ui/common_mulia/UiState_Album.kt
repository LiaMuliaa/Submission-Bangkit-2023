package com.muliasahpira.txtalbum.ui.common_mulia

sealed class UiState_Album<out T: Any?> {
    object LoadingYa : UiState_Album<Nothing>()

    data class SuccessYeay<out T: Any>(val data: T) : UiState_Album<T>()

    data class ErrorNih(val pesanError: String) : UiState_Album<Nothing>()
}