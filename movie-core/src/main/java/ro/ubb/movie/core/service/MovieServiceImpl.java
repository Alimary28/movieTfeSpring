package ro.ubb.movie.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movie.core.model.Movie;
import ro.ubb.movie.core.repository.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    public static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Movie> getAllMovies() {
        log.trace("getAllMovies --- method entered");

        List<Movie> result = movieRepository.findAll();

        log.trace("getAllMovies: result={}", result);

        return result;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        log.trace("saveMovie---method entered");
        Movie result = movieRepository.save(movie);

        log.trace("saveMovie=", result);
        return result;
    }
    @Override
    @Transactional
    public Movie updateMovie(Long id, Movie movie) {

        log.trace("updateMovie---method entered");
        Movie update = movieRepository.findById(id).orElseThrow();
        update.setTitle(movie.getTitle());
        update.setCategory(movie.getCategory());
        update.setYear(movie.getYear());
        update.setPrice(movie.getPrice());
        log.trace("updateMovie=", update);
        return update;
    }

    @Override
    public void deleteMovie(Long id) {
        log.trace("deleteMovie---method entered");
        movieRepository.deleteById(id);
        log.trace("deleteMovie=");
    }

}
