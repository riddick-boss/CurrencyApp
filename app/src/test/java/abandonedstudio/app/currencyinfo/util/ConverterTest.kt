package abandonedstudio.app.currencyinfo.util

import com.google.common.truth.Truth
import org.junit.Test

class ConverterTest{

    @Test
    fun getElementFromLinkedHashMapByIndexTest(){
        val map = LinkedHashMap<String, Int>()
        map["firstKey"] = 1
        map["thirdKey"] = 3
        map["secondKey"] = 2
        val (key, value) = Converter.getElementFromLinkedHashMapByIndex(map, 1)
        Truth.assertThat(key).isEqualTo("thirdKey")
        Truth.assertThat(value).isEqualTo(3)
    }
}