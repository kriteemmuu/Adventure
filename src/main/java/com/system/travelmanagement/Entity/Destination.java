package com.system.travelmanagement.Entity;

import jakarta.persistence.*;
import jdk.jshell.ImportSnippet;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destination")
public class Destination {
    @Id
    @SequenceGenerator(name = "hrs_user_seq_gen", sequenceName = "pms_user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "hrs_user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column()
    private String city;

    @Column()
    private String country;

    @Column()
    private String price;

    @Column()
    private String image;

    @Transient
    private String imageBase64;
}
