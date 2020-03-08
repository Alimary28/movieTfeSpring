package ro.ubb.movie.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class MovieDto extends BaseEntityDto{
    private String title;
    private String category;
    private String year;
    private double price;

}
