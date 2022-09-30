package dev.verite.mycontacts.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.verite.mycontacts.databinding.ActivityMainBinding
import dev.verite.mycontacts.models.Contact
import dev.verite.mycontacts.viewModel.ContactsViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val contactsViewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactsViewModel.getAllContacts()
    }

    override fun onResume() {
        super.onResume()
        binding.fabAddContact.setOnClickListener {
        startActivity(Intent(this, AddContactActivity::class.java))
        }

        contactsViewModel.contactsLiveData.observe(this, Observer { contacts ->
            displayContacts(contacts)
        })
    }

    fun displayContacts(contacts: List<Contact>){

        var contactAdapter= ContactRvAdapter(contacts)
        binding.rvContacts.layoutManager= LinearLayoutManager(this)
        binding.rvContacts.adapter=contactAdapter

    }
}