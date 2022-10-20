package com.example.applicationservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ApplicationWorkFlow_id")
    private Integer applicationWorkFlow_id;

//    @Column(name = "EmployeeId")
    private String employeeId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "CreateDate")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "LastModificationDate")
    private Date lastModificationDate;

    //    @Column(name = "Status")
    private String status;

    //    @Column(name = "Comment")
    private String comment;

    public ApplicationWorkFlow(Integer applicationWorkFlow_id, String status, String comment) {
    }

    //Integer id, Integer employeeId, String createDate, String lastModificationDate, String status, String comment

}
