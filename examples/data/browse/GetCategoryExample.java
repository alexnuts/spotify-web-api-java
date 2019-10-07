package data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Category;
import com.wrapper.spotify.requests.data.browse.GetCategoryRequest;

import java.io.IOException;
import java.util.concurrent.*;

public class GetCategoryExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String categoryId = "dinner";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetCategoryRequest getCategoryRequest = spotifyApi.getCategory(categoryId)
//          .country(CountryCode.SE)
//          .locale("sv_SE")
          .build();

  public static void getCategory_Sync() {
    try {
      final Category category = getCategoryRequest.execute();

      System.out.println("Name: " + category.getName());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getCategory_Async() {
    try {
      final CompletableFuture<Category> categoryFuture = getCategoryRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Category category = categoryFuture.join();

      System.out.println("Name: " + category.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }
}