package com.hasankanal.kotlinsearchphotoapp.ui.capturePhoto

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.common.BaseFragment
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentCapturePhotoBinding
import kotlinx.android.synthetic.main.fragment_capture_photo.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream


class CapturePhotoFragment : BaseFragment<FragmentCapturePhotoBinding, CapturePhotoViewModel>(){

    override fun getLayoutRes(): Int = R.layout.fragment_capture_photo

    override fun getViewModel(): Class<CapturePhotoViewModel> = CapturePhotoViewModel::class.java

    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
    val REQUEST_CODE = 200
    lateinit var bitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.viewCaptur = viewModel

        viewModel.navigateTo.observe(viewLifecycleOwner){ hasSaved ->
            if(hasSaved == true){
                Log.i("MYTAG","insidi goToProfile")
                goToProfile()
                viewModel.doneNavigating()
            }
        }

        viewModel.errorToast.observe(viewLifecycleOwner){ hasError ->
            if(hasError == true){
                Toast.makeText(requireContext(),"Eksik bilgi var", Toast.LENGTH_SHORT).show()
                viewModel.doneToast()
            }

        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_save_photo.isEnabled = false

        handleClick()
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.hasNoPermissions(requireContext())) {
            viewModel.requestPermission(requireActivity(),permissions,0)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE ){
            val extras = data?.extras
            if (extras != null) {
                bitmap = extras.get("data") as Bitmap
                iv_capture.setImageBitmap(data.extras!!.get("data") as Bitmap)
                var stringImage = convertBase64(bitmap)
                viewModel.savePhotoBitmap = stringImage
                bt_save_photo.isEnabled = true
                Log.i("MYTAG", "Result icinde")
            }
        }
    }

    private fun handleClick(){
        bt_take_photo.setOnClickListener {
            val openCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(openCamera, REQUEST_CODE)
        }

        bt_save_photo.setOnClickListener {
            viewModel.clickedSavePhoto()
        }
    }

    private fun goToProfile(){
        var action = CapturePhotoFragmentDirections.actionCapturePhotoFragmentToProfileFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun convertBase64(bitmap: Bitmap) : String{
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageBytes: ByteArray = byteArrayOutputStream.toByteArray()
        val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        return imageString
    }



}