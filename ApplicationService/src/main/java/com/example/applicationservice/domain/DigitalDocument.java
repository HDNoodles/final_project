package com.example.applicationservice.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "digitalDocument")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DigitalDocument {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Id")
    private Integer id;

    //    @Column(name = "Type")
    private String type;

    //    @Column(name = "IsRequired")
    private String isRequired;

    //    @Column(name = "Path")
    private String path;

    //    @Column(name = "Description")
    private String description;

    //    @Column(name = "Title")
    private String title;
}
