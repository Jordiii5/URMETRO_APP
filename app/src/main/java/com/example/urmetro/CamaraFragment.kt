package com.example.urmetro
import com.example.urmetro.databinding.FragmentCamaraBinding
import com.example.urmetro.viewModel.MyViewModel
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.util.Locale
import java.util.concurrent.ExecutorService
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.concurrent.Executors

/**
 * Fragment que permet capturar una foto mitjançant la càmera del dispositiu.
 */
class CamaraFragment : Fragment() {

    lateinit var binding: FragmentCamaraBinding // Binding per accedir als elements de la UI
    private var imageCapture: ImageCapture? = null // Capturador d'imatges
    private lateinit var outputDirectory: File  // Directori de sortida per emmagatzemar les imatges capturades
    private lateinit var cameraExecutor: ExecutorService // Executor per a tasques de càmera
    private val viewModel: MyViewModel by activityViewModels() // ViewModel per gestionar l'estat de l'aplicació

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCamaraBinding.inflate(layoutInflater)
        // Comprovar si s'han concedit tots els permisos necessaris
        if (allPermissionsGranted()) {
            // Iniciar la càmera
            startCamera()
        } else {
            // Sol·licitar els permisos necessaris si no s'han concedit
            ActivityCompat.requestPermissions(
                requireActivity(), CamaraFragment.REQUIRED_PERMISSIONS, CamaraFragment.REQUEST_CODE_PERMISSIONS
            )
        }
        // Configurar el listener per al botó de captura d'imatges
        binding.camaraCaptureButton.setOnClickListener {
            takePhoto()
        }

        // Obtenir el directori de sortida per emmagatzemar les imatges capturades
        outputDirectory = getOutputDirectory()
        // Inicialitzar l'executor de càmera
        cameraExecutor = Executors.newSingleThreadExecutor()

        return binding.root
    }

    /**
     * Funció que comprova si s'han concedit tots els permisos necessaris per a la càmera
     */
    private fun allPermissionsGranted() = CamaraFragment.REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Inicia la càmera per a la captura d'imatges.
     */
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }
            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch(exc: Exception) {
                Log.e(CamaraFragment.TAG, "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    /**
     * Captura una foto utilitzant la càmera.
     */
    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = File(outputDirectory,
            SimpleDateFormat(CamaraFragment.FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(outputOptions,
            ContextCompat.getMainExecutor(requireContext()), object: ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(CamaraFragment.TAG, "Photo capture failed: ${exc.message}", exc)
                }
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $savedUri"
                    viewModel.fotohecha = true
                    viewModel.image = savedUri
                    Log.d(CamaraFragment.TAG, msg)
                    findNavController().navigate(R.id.action_camaraFragment_to_afegirImatgeFragment)
                }
            })
    }

    /**
     * Obté el directori de sortida per emmagatzemar les imatges capturades.
     *
     * @return Un objecte File que representa el directori on es guardaran les imatges capturades.
     */
    private fun getOutputDirectory(): File {
        val mediaDir = requireContext().externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireContext().filesDir
    }

    /**
     * Aquest mètode es crida quan el fragment es destrueix per alliberar recursos.
     */
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    /**
     * Objecte de companyia que conté constants i permisos necessaris per a l'ús de la càmera.
     */
    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

}