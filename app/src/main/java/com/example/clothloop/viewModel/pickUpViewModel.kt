package com.example.clothloop.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.clothloop.data.model.pickUpTextileModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import java.text.NumberFormat
import java.time.LocalDate
import java.util.Locale

class pickUpViewModel : ViewModel(){
    var pickUpState = mutableStateOf(pickUpTextileModel())
        private set
    val db = FirebaseFirestore.getInstance()
    var _isSuccess = MutableStateFlow<Boolean>(false)
    val isSuccess: StateFlow<Boolean> get() = _isSuccess
    private var _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

  fun makeOrder(email: String, userName: String, category: String, weight: Int, pickupDate: String, address: String, phoneNumber: String, note: String){
      _isLoading.value = true
      val orderRef = db.collection("order")
        val data = hashMapOf(
            "email" to email,
            "userName" to userName,
            "category" to category,
            "weight" to weight,
            "pickupdate" to pickupDate,
            "address" to address,
            "phoneNumber"  to phoneNumber,
            "note" to note,
            "status" to "",
            "setAt" to Timestamp.now(),
        )

        orderRef.add(data).addOnSuccessListener {
            _isSuccess.value = true
        }.addOnFailureListener {
            _isSuccess.value = false
            _isLoading.value = false
        }

    }

    fun setUsername(username: String){
        pickUpState.value = pickUpState.value.copy(username = username)
    }

    fun setAddresse(address: String){
        pickUpState.value = pickUpState.value.copy(address = address)
    }

    fun addWeight(){
        pickUpState.value = pickUpState.value.copy(weight = pickUpState.value.weight + 1)
    }

    fun minWeight(){
        pickUpState.value = pickUpState.value.copy(weight = pickUpState.value.weight - 1)
    }

    fun setPickUpDate(date: LocalDate){
        pickUpState.value = pickUpState.value.copy(date = date)
    }

    fun setActivedPhoneNumber(phoneNumber: String){
        pickUpState.value = pickUpState.value.copy(activedPhoneNumber = phoneNumber)
    }

    fun setAdditionalNotes(note: String){
        pickUpState.value = pickUpState.value.copy(additionalNotes = note)
    }

    fun convertWeightToRupiah(weight: Int): String{
        val amount = weight * 10000
        val formatter = NumberFormat.getNumberInstance(Locale("id", "ID"))
        return formatter.format(amount)
    }

    fun setBackToFalse(status: Boolean){
        _isSuccess.value = status
    }
}