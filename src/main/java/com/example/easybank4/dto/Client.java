package com.example.easybank4.dto;


import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted = false")
public class Client extends Person {

    @Id
    private String Code;
}
