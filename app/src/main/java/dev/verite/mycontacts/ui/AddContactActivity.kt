package dev.verite.mycontacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dev.verite.mycontacts.R
import dev.verite.mycontacts.databinding.ActivityAddContactBinding
import dev.verite.mycontacts.databinding.ActivityViewContactBinding
import dev.verite.mycontacts.models.Contact
import dev.verite.mycontacts.viewModel.ContactsViewModel

class AddContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddContactBinding
    val contactsViewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnSave.setOnClickListener {
            validateAddContact()
            finish()
        }
    }

    fun validateAddContact(){

        var name = binding.etName.text.toString()
        var email = binding.etEmail.text.toString()
        var phoneNumber = binding.etPhone.text.toString()
        var address = binding.etAddress.text.toString()
        var error = false

        if (name.isBlank()) {
            error = true
            binding.etName.error = "name is required"
        }
        if (email.isBlank()) {
            error = true
            binding.etEmail.error = " Email is required"
        }
        if (address.isBlank()) {
            error = true
            binding.etAddress.error = "Address is required"
        }
        if (phoneNumber.isBlank()) {
            error = true
            binding.etPhone.error = "PhoneNumber is required"
        }

        if (!error) {

        val contact = Contact(contactId = 0, name, phoneNumber, email, address, "")
        contactsViewModel.saveContact(contact)
        finish()
        }
    }
}