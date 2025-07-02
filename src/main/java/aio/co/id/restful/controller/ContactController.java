package aio.co.id.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import aio.co.id.restful.entity.Contact;
import aio.co.id.restful.model.WebResponse;
import aio.co.id.restful.service.ContactService;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(
        path = "/api/contacts/{id}"
    )
    public WebResponse<Contact> getContactById(@PathVariable String id) {        
        Contact response = contactService.getContactById(id);
        return WebResponse.<Contact>builder().data(response).build();
    }

}
