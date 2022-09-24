package dev.verite.mycontacts.repository

import androidx.lifecycle.LiveData
import dev.verite.mycontacts.MyContacts
import dev.verite.mycontacts.database.ContactsDb
import dev.verite.mycontacts.models.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {

    val database = ContactsDb.getDatabase(MyContacts.appContext)

    suspend fun saveContact(contact:Contact){
        withContext(Dispatchers.IO){
            database.contactDao().insertContact(contact)
        }
    }

    fun getContactById(contactId: Int): LiveData<Contact>{
        return database.contactDao().getContactById(contactId)
    }
}