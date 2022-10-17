package com.example.applicationservice.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applicationWorkFlow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationWorkFlow{

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Id")
    private Integer id;

    //    @Column(name = "EmployeeId")
    private Integer employeeId;

    //    @Column(name = "CreateDate")
    private String createDate;

    //    @Column(name = "LastModificationDate")
    private String lastModificationDate;

    //    @Column(name = "Status")
    private String status;

    //    @Column(name = "Comment")
    private String comment;

    //Integer id, Integer employeeId, String createDate, String lastModificationDate, String status, String comment

}
