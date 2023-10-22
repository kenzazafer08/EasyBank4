package com.example.easybank4.dto;


import lombok.*;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client extends Person {

    @Id
    private String Code;
}
