package abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeResponse(
    val success: Boolean,
    val timestamp: Int,
    val base: String,
    val date: String,
    val rates: Rates,
    val error: ErrorResponse
)
