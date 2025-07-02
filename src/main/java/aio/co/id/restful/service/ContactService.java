package aio.co.id.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aio.co.id.restful.entity.Contact;
import aio.co.id.restful.model.ContactRespone;
import aio.co.id.restful.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactRespone getContactById(String id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        ContactRespone response = new ContactRespone();
        response.setId(contact.getId());
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setEmail(contact.getEmail());
        response.setUser(contact.getUser()); 
        return response;
    }

}
