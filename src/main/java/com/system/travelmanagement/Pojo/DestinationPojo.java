package com.system.travelmanagement.Pojo;

import com.system.travelmanagement.Entity.Destination;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationPojo {
    private Integer id;

    private String city;

    private String country;

    private String price;


    private MultipartFile image;

    public DestinationPojo(Destination destination){
        this.id=destination.getId();
        this.city= destination.getCity();
        this.country=destination.getCountry();
        this.price=destination.getPrice();

    }

}
