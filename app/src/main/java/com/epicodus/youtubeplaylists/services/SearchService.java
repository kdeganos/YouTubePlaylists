package com.epicodus.youtubeplaylists.services;

import android.util.Log;

import com.epicodus.youtubeplaylists.Constants;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Guest on 7/7/16.
 */
public class SearchService {
    public static final String TAG = SearchService.class.getSimpleName();

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;

    /**
     * Initialize a YouTube object to search for videos on YouTube. Then
     * display the name and thumbnail image of each video in the result set.
     *
     */
    public static void findVideos(String queryTerm, Callback callback) {
//        https://www.googleapis.com/youtube/v3/search?part=snippet&q=cats&key=AIzaSyBlGhwpWvzeZrEzPNlNJl7WOi9O0Hs0vzc
//        https://www.googleapis.com/youtube/v3/search?part=snippet%2Cstatistics%2CcontentDetails&fields=items(id(kind%2CvideoId)%2Csnippet(description%2CpublishedAt%2Cthumbnails%2Fdefault%2Furl%2Ctitle))&q=cats&key={YOUR_API_KEY}

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_SEARCH_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.PART, "snippet");
        urlBuilder.addQueryParameter(Constants.SEARCH_PARAMETER, queryTerm);
        urlBuilder.addQueryParameter(Constants.API_PARAMETER, Constants.YOUTUBE_API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);


//        try {
//            // This object is used to make YouTube Data API requests. The last
//            // argument is required, but since we don't need anything
//            // initialized when the HttpRequest is initialized, we override
//            // the interface and provide a no-op function.
//            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
//                public void initialize(HttpRequest request) throws IOException {
//                }
//            }).setApplicationName("youtube-playlists").build();
//
//            // Define the API request for retrieving search results.
//            YouTube.Search.List search = youtube.search().list("id,snippet");
//
//            // Set your developer key from the Google Developers Console for
//            // non-authenticated requests. See:
//            // https://console.developers.google.com/
//            String apiKey = Constants.YOUTUBE_API_KEY;
//            search.setKey(apiKey);
//            search.setQ(queryTerm);
//
//            // Restrict the search results to only include videos. See:
//            // https://developers.google.com/youtube/v3/docs/search/list#type
//            search.setType("video");
//
//            // To increase efficiency, only retrieve the fields that the
//            // application uses.
//            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
//            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
//
//            // Call the API and print results.
//            SearchListResponse searchResponse = search.execute();
//            List<SearchResult> searchResultList = searchResponse.getItems();
//            Log.d(TAG, "findVideos: " + searchResponse.getItems().getClass());
//
//            if (searchResultList != null) {
//                prettyPrint(searchResultList.iterator(), queryTerm);
//            }
//        } catch (GoogleJsonResponseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
    }

    /*
     * Prompt the user to enter a query term and return the user-specified term.
     */
    private static String getInputQuery() throws IOException {

        String inputQuery = "";

        System.out.print("Please enter a search term: ");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        inputQuery = bReader.readLine();

        if (inputQuery.length() < 1) {
            // Use the string "YouTube Developers Live" as a default.
            inputQuery = "YouTube Developers Live";
        }
        return inputQuery;
    }

    /*
     * Prints out all results in the Iterator. For each result, print the
     * title, video ID, and thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResultsActivity to print
     *
     * @param query SearchService query (String)
     */
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
}
