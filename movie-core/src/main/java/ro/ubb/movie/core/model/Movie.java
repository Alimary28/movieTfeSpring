package ro.ubb.movie.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Movie extends BaseEntity<Long> {
    private String title;
    private String category;
    private String year;
    private double price;

}
