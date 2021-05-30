package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(columnDefinition = "B")
@Getter @Setter
public class Book extends Item {

    private String author;
    private String isbn;
}
