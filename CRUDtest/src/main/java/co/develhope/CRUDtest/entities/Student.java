package co.develhope.CRUDtest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name, surname;
    private boolean isWorking;

}
