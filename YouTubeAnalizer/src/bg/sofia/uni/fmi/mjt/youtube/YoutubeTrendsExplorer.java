package bg.sofia.uni.fmi.mjt.youtube;

import bg.sofia.uni.fmi.mjt.youtube.model.TrendingVideo;

import java.io.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class YoutubeTrendsExplorer {

    HashSet<TrendingVideo> videos = new HashSet<>();

    /**
     * Loads the dataset from the given {@code dataInput} stream.
     */
    public YoutubeTrendsExplorer(InputStream dataInput) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataInput.toString()))){//"c:\\users\\georgi\\desktop\\USvideos.txt"))) {
            String line = reader.readLine();
            while( line != null) {

                // read() reads the next byte of data from the input stream.
                // The value byte is returned as an int in the range 0 to 255.
                // If no byte is available because the end of the stream
                // has been reached, the value -1 is returned
                videos.add(TrendingVideo.createTrendingVideo(line));
                line = reader.readLine();
            }
        }
    }

    /**
     * Returns all videos loaded from the dataset.
     **/
    public Collection<TrendingVideo> getTrendingVideos() {
        return null;
    }

    // Other methods ...

    public String findIdOfLeastLikedVideo() throws IOException {
        videos.stream().map(TrendingVideo::getDislikes).max(Comparator<? super Long >);
        return null;
    };

    public String findIdOfMostLikedLeastDislikedVideo(){
        return null;

    };

    public List<String> findDistinctTitlesOfTop3VideosByViews(){
        return null;
    };

    public String findIdOfMostTaggedVideo(){
        return null;

    };

    public String findTitleOfFirstVideoTrendingBefore100KViews(){
        return null;

    };

    public String findIdOfMostTrendingVideo(){
        return null;

    };

}