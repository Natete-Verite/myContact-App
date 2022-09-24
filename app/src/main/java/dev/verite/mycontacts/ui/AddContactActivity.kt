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

        //validate

        val contact = Contact(
            contactId = 0,
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            address = address,
            image = ""
        )
        contactsViewModel.saveContact(contact)
    }
}