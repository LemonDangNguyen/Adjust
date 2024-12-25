package com.example.testadjust

import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)

        // Các SeekBar
        val seekBarBrightness: SeekBar = findViewById(R.id.seekBarBrightness)
        val seekBarContrast: SeekBar = findViewById(R.id.seekBarContrast)
        val seekBarSaturation: SeekBar = findViewById(R.id.seekBarSaturation)
        val seekBarClarity: SeekBar = findViewById(R.id.seekBarClarity)
        val seekBarShadows: SeekBar = findViewById(R.id.seekBarShadows)
        val seekBarHighlights: SeekBar = findViewById(R.id.seekBarHighlights)
        val seekBarExposure: SeekBar = findViewById(R.id.seekBarExposure)
        val seekBarGamma: SeekBar = findViewById(R.id.seekBarGamma)
        val seekBarBlacks: SeekBar = findViewById(R.id.seekBarBlacks)
        val seekBarWhites: SeekBar = findViewById(R.id.seekBarWhites)
        val seekBarSharpness: SeekBar = findViewById(R.id.seekBarSharpness)
        val seekBarTemperature: SeekBar = findViewById(R.id.seekBarTemperature)

        val viewModel: ImageAdjustmentViewModel by viewModels()

        // Quan sát LiveData của bộ lọc màu
        viewModel.colorFilter.observe(this, Observer { filter ->
            imageView.colorFilter = filter
        })

        // Cập nhật giá trị SeekBar và LiveData
        setSeekBarListener(seekBarBrightness) { value ->
            viewModel.brightness.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarContrast) { value ->
            viewModel.contrast.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarSaturation) { value ->
            viewModel.saturation.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarClarity) { value ->
            viewModel.clarity.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarShadows) { value ->
            viewModel.shadows.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarHighlights) { value ->
            viewModel.highlights.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarExposure) { value ->
            viewModel.exposure.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarGamma) { value ->
            viewModel.gamma.value = value / 100f + 1f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarBlacks) { value ->
            viewModel.blacks.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarWhites) { value ->
            viewModel.whites.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarSharpness) { value ->
            viewModel.sharpness.value = value / 100f
            viewModel.updateFilter()
        }

        setSeekBarListener(seekBarTemperature) { value ->
            viewModel.temperature.value = value / 100f
            viewModel.updateFilter()
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
