
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (Carlos Pimentel) 
 * @version (12-08-2020)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    
     public ArrayList<String> getItemsToRate () {
        ArrayList<String> itemsToRate = new ArrayList<String> ();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       
        for (int i = 0; i < 15; i++) {
            Random rand = new Random();
            int random = rand.nextInt(movies.size());
            if (! itemsToRate.contains(movies.get(random))) {
                itemsToRate.add(movies.get(random));
            }
        }        
        return itemsToRate;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings ();
        MovieDatabase.initialize("ratedmoviesfull");
        RaterDatabase.initialize("ratings");
        
        int numSimilarRaters = 20; 
        int minNumOfRatings = 4; 
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minNumOfRatings);
        System.out.println("<h2>These are the movies recommended, as per your ratings </h2>");
        if (similarRatings.size() == 0) {
            System.out.println("No matching movies were found");
        } else {
            String header = ("<table> <tr> <th>Movie Title</th> <th>Rating Value</th>  <th>Genres</th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) {
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>" 
                + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem())
                + "</td> </tr> ";
            }
            System.out.println(header  + body + "</table>");
        }
    }
}