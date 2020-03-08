package ro.ubb.movie.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MoviesDto {
    private Set<MovieDto> movies;
}
