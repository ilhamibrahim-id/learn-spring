package aio.co.id.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aio.co.id.restful.entity.Contact;

public interface  ContactRepository extends JpaRepository<Contact, String>{

}
