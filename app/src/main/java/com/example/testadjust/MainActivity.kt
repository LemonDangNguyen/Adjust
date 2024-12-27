package com.example.testadjust

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testadjust.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ImageAdjustmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng ViewBinding để inflate layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Quan sát LiveData của bộ lọc màu
        viewModel.colorFilter.observe(this, Observer { filter ->
            binding.imageView.colorFilter = filter
        })
        binding.seekBarBrightness.max = 100 // Phạm vi từ 0 đến 100
        binding.seekBarBrightness.progress = 50 // Đặt giá trị ban đầu ở giữa
        setSeekBarListener(binding.seekBarBrightness) { value ->
            // Tính toán giá trị normalizedValue, với 50 là giá trị trung tâm
            val normalizedValue = (value - 50) / 100f // Phạm vi từ -0.5 đến 0.5

            // Cập nhật giá trị brightness trong ViewModel
            viewModel.brightness.value = normalizedValue

            // Gọi hàm cập nhật bộ lọc để áp dụng thay đổi
            viewModel.updateFilter()
        }



        // Thiết lập giá trị tối đa và tối thiểu cho SeekBar
        binding.seekBarContrast.setMin(-100);
        binding.seekBarContrast.max = 100 // Phạm vi từ 0 đến 100
        binding.seekBarContrast.progress = 0 // Đặt giá trị ban đầu ở giữa

// Thiết lập listener cho SeekBar
        setSeekBarListener(binding.seekBarContrast) { value ->
            // Tính toán giá trị normalizedValue, với 50 là giá trị trung tâm
            val normalizedValue = (value - 50) / 100f // Phạm vi từ -0.5 đến 0.5

            // Cập nhật giá trị contrast trong ViewModel
            viewModel.contrast.value = normalizedValue

            // Gọi hàm cập nhật bộ lọc để áp dụng thay đổi
            viewModel.updateFilter()
        }



        // Thiết lập giá trị tối đa và tối thiểu cho SeekBar
        binding.seekBarSaturation.max = 1000 // Phạm vi từ -100 đến 1000
        binding.seekBarSaturation.min = -100 // Phạm vi tối thiểu
        binding.seekBarSaturation.progress = 500 // Đặt giá trị ban đầu ở giữa (tương ứng với giá trị 0)


        setSeekBarListener(binding.seekBarSaturation) { value ->
            // Chuyển đổi giá trị SeekBar về phạm vi -1 đến 1
            val normalizedValue = (value - 500) / 500f // Phạm vi từ -1 đến 1

            // Cập nhật độ bão hòa trong ViewModel
            viewModel.saturation.value = normalizedValue

            // Cập nhật bộ lọc với giá trị bão hòa mới
            viewModel.updateFilter()
        }

        binding.seekBarShadows.min = 20
        binding.seekBarShadows.max = 100
        binding.seekBarShadows.progress = 30

        setSeekBarListener(binding.seekBarShadows) { value ->
            // Làm chậm sự thay đổi bằng cách chia giá trị difference (tỷ lệ nhỏ hơn)
            val slowShadowsValue = (value - 50) / 50f * 0.2f  // Điều chỉnh với hệ số nhỏ hơn để giảm tốc độ thay đổi
            viewModel.shadows.value = slowShadowsValue
            viewModel.updateFilter()
        }

        // Cấu hình SeekBar cho highlights
        binding.seekBarHighlights.min = 20
        binding.seekBarHighlights.max = 100
        binding.seekBarHighlights.progress = 50 // Giá trị mặc định là trung lập

        setSeekBarListener(binding.seekBarHighlights) { value ->
            // Tính toán giá trị highlights với dải điều chỉnh từ -1 đến 1
            val highlightsValue = (value - 50) / 50f // Chuyển đổi giá trị từ SeekBar thành tỷ lệ từ -1 đến 1
            viewModel.highlights.value = highlightsValue // Cập nhật giá trị vào ViewModel
            viewModel.updateFilter() // Cập nhật bộ lọc với giá trị mới
        }


    }

    private fun setSeekBarListener(seekBar: SeekBar, onProgressChanged: (Int) -> Unit) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                onProgressChanged(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
