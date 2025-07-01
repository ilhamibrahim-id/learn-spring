package aio.co.id.restful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")

public class Address {

    @Id
    private String id;
    
    private String street;

    private String city;

    private String province;

    private String country;

    @Column(name = "postal_code")
    private String postalCode;
    
    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

}
