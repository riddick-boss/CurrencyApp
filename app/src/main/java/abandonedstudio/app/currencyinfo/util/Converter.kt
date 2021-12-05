package abandonedstudio.app.currencyinfo.util

object Converter {

    fun <T> getElementFromLinkedHashMapByIndex(
        map: LinkedHashMap<String, T>,
        index: Int
    ): Pair<String, T> {
        val key = map.keys.elementAt(index)
        val value = map.getValue(key)
        return Pair(key, value)
    }
}