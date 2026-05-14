package com.solvoku.solvokuservice.inbound.http.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val errorMessage: String, val errorCode: String? = null)