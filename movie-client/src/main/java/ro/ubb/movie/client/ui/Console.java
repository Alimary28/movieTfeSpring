package ro.ubb.movie.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.movie.client.service.MovieServiceClient;
import ro.ubb.movie.web.dto.MovieDto;
import ro.ubb.movie.web.dto.MoviesDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Console {
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private MovieServiceClient serviceClient;

   /* public void runConsole2() {
        String url = "http://localhost:8080/api/movies";

        MoviesDto movies = restTemplate.getForObject(url, MoviesDto.class);
        movies.getMovies()
                .forEach(System.out::println);

//        System.out.println("save:");
        MovieDto savedMovie = restTemplate.postForObject(
                url,
                new MovieDto("title1", "action", "2019", 75.5),
                MovieDto.class);
        System.out.println("saved movie: " + savedMovie);

        savedMovie.setTitle("update!!!");
        restTemplate.put(url + "/{id}", savedMovie, savedMovie.getId());
        System.out.println("after update:");
        MoviesDto allMovies = restTemplate.getForObject(url, MoviesDto.class);
        allMovies.getMovies()
                .forEach(System.out::println);

        //delete
        restTemplate.delete(url + "/{id}", savedMovie.getId());
        System.out.println("after delete:");
        allMovies = restTemplate.getForObject(url, MoviesDto.class);
        allMovies.getMovies()
                .forEach(System.out::println);

    }
*/
   // private String title;
   //    private String category;
   //    private String year;
   //    private double price;
   public void runConsole() {
       printMenu();

       while (true) {
           String option = scanner.nextLine();
           if (option.equals("x")) {
               break;
           }
           switch (option) {
               case "1":
                   readMovie();
                   break;
               case "2":
                   printAllMovies();
                   break;
               case "3":
                   updateMovie();
                   break;
               case "4":
                   deleteMovie();
                   break;
               default:
                   System.out.println("this option is not yet implemented");
           }
           printMenu();
       }
   }
////////////////////////////////////////////////////////////////////////////////////
//           System.out.println("all movies:");
//           MoviesDto allMovies = serviceClient.getAllMovies();
//           System.out.println(allMovies);
//
//           Optional<MovieDto> first = allMovies.getMovies().stream().findFirst();
//           first.ifPresent(movieDto -> {
//               movieDto.setTitle("bad boys");
//               serviceClient.updateMovie(movieDto.getId(), movieDto);
//           });
//
//           System.out.println("after update");
//           allMovies = serviceClient.getAllMovies();
//           System.out.println(allMovies);
//
//
//           first = allMovies.getMovies().stream().findFirst();
//           first.ifPresent(movieDto -> {
//               serviceClient.deleteById(movieDto.getId());
//           });
//
//           System.out.println("after delete");
//           allMovies = serviceClient.getAllMovies();
//           System.out.println(allMovies);


    private void deleteMovie() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Movie id: ");
            Long id = Long.parseLong(bufferRead.readLine());
            serviceClient.deleteById(id);

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void updateMovie() {
        System.out.println("Movie id: ");
        System.out.println("Read movie (title, category, year, price): ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.parseLong(bufferedReader.readLine());
            String title = bufferedReader.readLine();
            String category = bufferedReader.readLine();
            String year = bufferedReader.readLine();
            double price = Double.parseDouble(bufferedReader.readLine());

            MovieDto movieDto = new MovieDto(title, category, year, price);
            serviceClient.updateMovie(id,movieDto);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void printAllMovies() {
        System.out.println("all movies:");
        MoviesDto allMovies = serviceClient.getAllMovies();
        System.out.println(allMovies);

    }

    private void readMovie () {
           System.out.println("Read movie (title, category, year, price): ");
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
           try {
               String title = bufferedReader.readLine();
               String category = bufferedReader.readLine();
               String year = bufferedReader.readLine();
               double price = Double.parseDouble(bufferedReader.readLine());

               MovieDto movieDto = new MovieDto(title, category, year, price);
               serviceClient.saveMovie(movieDto);
           } catch (IOException e) {
               e.printStackTrace();
           }

       }
       private void printMenu () {
           System.out.println("1-Add movie\n" +
                   "2-All movies\n" +
                   "3-Update movies\n" +
                   "4-Delete movies\n" +
                   "x- Exit");
       }


   }