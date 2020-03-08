package ro.ubb.movie.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movie.core.model.Movie;
import ro.ubb.movie.web.dto.MovieDto;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {

    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .category(dto.getCategory())
                .year(dto.getYear())
                .price(dto.getPrice())
                .build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = MovieDto.builder()
                .title(movie.getTitle())
                .category(movie.getCategory())
                .year(movie.getYear())
                .price(movie.getPrice())
                .build();
        dto.setId(movie.getId());
        return dto;
    }
}
