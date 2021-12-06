package abandonedstudio.app.currencyinfo

import abandonedstudio.app.currencyinfo.databinding.MainActivityBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Pawel Kremienowski <Kremienowski33@gmail.com>
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
