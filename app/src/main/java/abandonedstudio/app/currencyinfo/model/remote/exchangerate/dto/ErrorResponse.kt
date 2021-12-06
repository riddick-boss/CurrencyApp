package abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: Int,
    val info: String
)
