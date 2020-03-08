package ro.ubb.movie.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movie.core.model.Movie;
import ro.ubb.movie.core.service.MovieService;
import ro.ubb.movie.web.converter.MovieConverter;
import ro.ubb.movie.web.dto.MovieDto;
import ro.ubb.movie.web.dto.MoviesDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class MovieController {
    public static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;


    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    MoviesDto getMovies() {
        log.trace("getMovies --- method entered");

        List<Movie> movies = movieService.getAllMovies();
        Set<MovieDto> result = movieConverter.convertModelsToDtos(movies);
        MoviesDto moviesDto = new MoviesDto(result);
        log.trace("getMovies={}", moviesDto);

        return moviesDto;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDto saveMovie(@RequestBody MovieDto movieDto) {

        Movie savedMovie = movieService.saveMovie(
                movieConverter.convertDtoToModel(movieDto));
        MovieDto result = movieConverter.convertModelToDto(savedMovie);
        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
   MovieDto updateMovie(@PathVariable Long id,
                             @RequestBody MovieDto movieDto) {
        log.trace("updateMovie---method entered");
        Movie movie = movieService.updateMovie(id, movieConverter.convertDtoToModel(movieDto));
        MovieDto result = movieConverter.convertModelToDto(movie);
        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        log.trace("deleteMovie---method entered");
        movieService.deleteMovie(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
