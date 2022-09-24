package dev.verite.mycontacts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.verite.mycontacts.models.Contact
import dev.verite.mycontacts.repository.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel:ViewModel() {
    val contactsRepository = ContactsRepository()
    lateinit var contactLiveData: LiveData<Contact>

    fun saveContact(contact: Contact){
        viewModelScope.launch {
            contactsRepository.saveContact(contact)
        }
    }

    fun fetchContactsById(contactId: Int){
        contactLiveData = contactsRepository.getContactById(contactId)
    }
}