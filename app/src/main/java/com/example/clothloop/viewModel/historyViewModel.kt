package com.example.clothloop.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.clothloop.data.model.Order
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class historyViewModel : ViewModel() {
    var _listOrder = MutableStateFlow<List<Order>>(emptyList())
    val listOrder: StateFlow<List<Order>> get() = _listOrder
    val db = FirebaseFirestore.getInstance()

 fun getAllOrder(email: String) {
        val orderRef = db.collection("order").whereEqualTo("email", email).orderBy("setAt", Query.Direction.DESCENDING)
        orderRef.addSnapshotListener { querySnapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            if (querySnapshot != null && !querySnapshot.isEmpty) {
                val orders = querySnapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(Order::class.java)?.copy(orderId = documentSnapshot.id)
                }
                _listOrder.value = orders
            } else {
                println("no orders found")
            }
        }
    }

 fun completeOrder(uid: String, email: String, weight: Int) {
        val orderRef = db.collection("order").document(uid)
        val userRef = db.collection("user").whereEqualTo("email", email)


        orderRef.update("status", "completed")
            .addOnSuccessListener {
                println("Order $uid marked as completed.")
            }
            .addOnFailureListener { exception ->
                println("Error updating order $uid: ${exception.message}")
            }

        userRef.get().addOnSuccessListener {
            snapshot ->
            if (!snapshot.isEmpty){
                val document = snapshot.documents[0]
                val currentTotal = document.getLong("totalWeightSelled") ?: 0
                val newTotal = currentTotal + weight


                document.reference.update("totalWeightSelled", newTotal).addOnSuccessListener {
                    Log.d("Firestore", "success")
                }.addOnFailureListener {e ->
                    Log.d("Firestore", "error updating total: ")
                }
            } else
            {
                Log.d("Firestore", "no document found")
            }
        }
    }

 fun cancelOrder(uid: String) {
        val orderRef = db.collection("order").document(uid)
        orderRef.update("status", "cancelled")
            .addOnSuccessListener {
                println("Order $uid marked as completed.")
            }
            .addOnFailureListener { exception ->
                println("Error updating order $uid: ${exception.message}")
            }
    }

}