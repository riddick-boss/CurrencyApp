package abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto

import kotlinx.serialization.Serializable

@Serializable
data class Rates(
    val USD: Float,
    val AUD: Float,
    val CAD: Float,
    val PLN: Float,
    val MXN: Float,
)
