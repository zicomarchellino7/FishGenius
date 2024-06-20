package id.capstone.fishgenius.view.detection

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import id.capstone.fishgenius.R
import id.capstone.fishgenius.ml.Model4
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException

class DetectionFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var textViewResultLabel: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var buttonSelect: Button
    private lateinit var buttonCapture: Button
    private lateinit var buttonPredict: Button
    private var selectedImage: Bitmap? = null

    private val fishDescriptions = mapOf(
        "Ikan Bandeng" to "Ikan bandeng adalah ikan yang hidup di air laut dan air payau. Ikan ini sering dibudidayakan karena dagingnya yang lezat dan bergizi.",
        "Ikan Bawal" to "Ikan bawal adalah ikan air tawar yang populer di Indonesia. Ikan ini dikenal dengan dagingnya yang gurih dan sedikit duri.",
        "Ikan Gurame" to "Ikan gurame adalah ikan yang sering diolah menjadi masakan lezat. Ikan ini memiliki tekstur daging yang lembut dan rasa yang enak.",
        "Ikan Lele" to "Ikan lele adalah ikan yang mudah ditemukan di berbagai tempat. Ikan ini memiliki ciri khas tubuh licin dan rasa yang lezat.",
        "Ikan Nila" to "Ikan nila adalah ikan air tawar yang sering dibudidayakan. Ikan ini memiliki kandungan protein yang tinggi dan rasa yang enak.",
        "Ikan Patin" to "Ikan patin adalah ikan yang memiliki daging yang lembut dan sedikit duri. Ikan ini sering diolah menjadi berbagai masakan.",
        "Ikan Salmon" to "Ikan salmon adalah ikan yang kaya akan omega-3. Ikan ini sering diolah menjadi sashimi atau dibakar dengan berbagai bumbu.",
        "Ikan Tuna" to "Ikan tuna adalah ikan yang banyak digunakan dalam masakan Jepang, seperti sushi dan sashimi. Ikan ini juga kaya akan protein."
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detection, container, false)

        imageView = view.findViewById(R.id.imageView)
        textViewResultLabel = view.findViewById(R.id.textViewResultLabel)
        textViewDescription = view.findViewById(R.id.textViewDescription)
        buttonSelect = view.findViewById(R.id.buttonSelect)
        buttonCapture = view.findViewById(R.id.buttonCapture)
        buttonPredict = view.findViewById(R.id.buttonPredict)

        val selectImageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val imageUri = result.data?.data
                    imageUri?.let {
                        val inputStream = requireContext().contentResolver.openInputStream(it)
                        selectedImage = BitmapFactory.decodeStream(inputStream)
                        imageView.setImageBitmap(selectedImage)
                    }
                }
            }

        val captureImageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val imageBitmap = result.data?.extras?.get("data") as Bitmap
                    selectedImage = imageBitmap
                    imageView.setImageBitmap(imageBitmap)
                }
            }

        val requestCameraPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    captureImageLauncher.launch(intent)
                } else {

                }
            }

        buttonSelect.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            selectImageLauncher.launch(intent)
        }

        buttonCapture.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    captureImageLauncher.launch(intent)
                }
                else -> {
                    requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        }

        buttonPredict.setOnClickListener {
            selectedImage?.let { bitmap ->
                classifyImage(bitmap)
            }
        }

        return view
    }

    private fun classifyImage(bitmap: Bitmap) {
        try {
            val model = Model4.newInstance(requireContext())

            val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true)
            val tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(resizedBitmap)

            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 300, 300, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(tensorImage.buffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val maxIdx =
                outputFeature0.floatArray.indices.maxByOrNull { outputFeature0.floatArray[it] }
                    ?: -1
            val maxConfidence = outputFeature0.floatArray[maxIdx]
            val confidenceThreshold = 0.5f
            val labels = arrayOf(
                "Ikan Bandeng",
                "Ikan Bawal",
                "Ikan Gurame",
                "Ikan Lele",
                "Ikan Nila",
                "Ikan Patin",
                "Ikan Salmon",
                "Ikan Tuna"
            )


            val fishName =
                if (maxIdx != -1 && maxConfidence >= confidenceThreshold && maxIdx < labels.size) {
                    labels[maxIdx]
                } else {
                    "Unknown"
                }

            val description = fishDescriptions[fishName] ?: "Deskripsi tidak ditemukan."

            textViewResultLabel.text = "Ikan mu adalah: \n $fishName"
            textViewDescription.text = description

            model.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
