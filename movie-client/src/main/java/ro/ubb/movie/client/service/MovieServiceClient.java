package ro.ubb.movie.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.movie.web.dto.MovieDto;
import ro.ubb.movie.web.dto.MoviesDto;

@Service
public class MovieServiceClient {
    private static final String url = "http://localhost:8080/api/movies";

    @Autowired
    private RestTemplate restTemplate;

    public MoviesDto getAllMovies() {
        return restTemplate.getForObject(url, MoviesDto.class);
    }

    public MovieDto saveMovie(MovieDto movieDto) {
        return restTemplate.postForObject(
                url,
                movieDto,
                MovieDto.class);
    }

    public void updateMovie(Long id, MovieDto movieDto) {
        restTemplate.put(url + "/{id}", movieDto, id);
    }

    public void deleteById(Long id) {
        restTemplate.delete(url + "/{id}", id);
    }
}
