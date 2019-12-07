package bg.sofia.uni.fmi.mjt.youtube;

import bg.sofia.uni.fmi.mjt.youtube.model.TrendingVideo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class YoutubeTrendsExplorer {

    private static final int MAX_VIEWS = 100000;
    private static final int LIMIT_TITLES = 3;
    private static final int STARTING_TREND_OF_VIDEO = 1;
    private HashMap<TrendingVideo, Integer> videos = new HashMap<>();
    private List<TrendingVideo> list = new ArrayList<>();

    /**
     * Loads the dataset from the given {@code dataInput} stream.
     */
    public YoutubeTrendsExplorer(InputStream dataInput) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(dataInput))) {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                if (line.length() > 0) {
                    TrendingVideo video = TrendingVideo.createTrendingVideo(line);
                    list.add(video);
                    if (!videos.containsKey(video)) {
                        videos.put(video, STARTING_TREND_OF_VIDEO);
                    } else {
                        int size = videos.get(video);
                        videos.put(video, size + 1);
                    }
                }
                line = reader.readLine();
            }
        }
    }

    /**
     * Returns all videos loaded from the dataset
     **/
    public Collection<TrendingVideo> getTrendingVideos() {
        return list;
    }

    // Other methods ...

    public String findIdOfLeastLikedVideo() {
        TrendingVideo video = videos
                .keySet()
                .stream()
                .min(Comparator.comparing(TrendingVideo::getLikes))
                .orElseThrow(NoSuchElementException::new);
        return video.getId();
    }

    public String findIdOfMostLikedLeastDislikedVideo() {
        TrendingVideo video = videos
                .keySet()
                .stream()
                .max(Comparator.comparing(TrendingVideo::getLikesMinusDislikes))
                .orElseThrow(NoSuchElementException::new);
        return video.getId();
    }

    public List<String> findDistinctTitlesOfTop3VideosByViews() {
        return videos
                .keySet()
                .stream()
                .sorted(Comparator.comparing(TrendingVideo::getViews).reversed())
                .limit(LIMIT_TITLES)
                .map(TrendingVideo::getTitle)
                .collect(Collectors.toList());
    }

    public String findIdOfMostTaggedVideo() {
        TrendingVideo video = videos
                .keySet()
                .stream()
                .max(Comparator.comparing(TrendingVideo::getNumberOfTags))
                .orElseThrow(NoSuchElementException::new);
        return video.getId();

    }

    public String findTitleOfFirstVideoTrendingBefore100KViews() {
        TrendingVideo video = list
                .stream()
                .filter(x -> x.getViews() < MAX_VIEWS)
                .min(Comparator.comparing(TrendingVideo::getPublishDate))
                .orElseThrow(NoSuchElementException::new);
        return video.getTitle();
    }

    public String findIdOfMostTrendingVideo() {
        TrendingVideo video = videos
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(NoSuchElementException::new);
        return video.getId();
    }
}