package com.example.navegacionentreventanasguia

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    val contacts = mutableStateListOf<Contact>()

    fun addContact(contact: Contact) {
        contacts.add(contact)
    }

    fun removeContact(contact: Contact) {
        contacts.remove(contact)
    }

    fun updateContact(updatedContact: Contact) {
        val index = contacts.indexOfFirst { it.telefono == updatedContact.telefono }
        if (index != -1) {
            contacts[index] = updatedContact
        }
    }
}
