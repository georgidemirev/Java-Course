package bg.sofia.uni.fmi.mjt.youtube;

import org.junit.Assert;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class YoutubeTrendsExplorerTest {

    private static final String STRING_TEST = "video_id	trending_date	title	+" +
            "publish_time	tags	views	likes	dislikes\n" +
            "_6uvQtccJss	17.01.12	BLACK FRIDAY HAUL 2017 + TRY ON | Bethany" +
            " Mota	2017-11-25T18:55:42.000Z	\"bethany mota|" + "bethany moto" +
            "|" + "bethany" + "|" + "mota" + "|" + "macbarbie07" + "|" + "black frid" +
            "ay haul" + "|" + "black friday" + "|" + "haul" + "|" + "clothing" + "|" +
            "forever 21" + "|" + "victorias secret" + "|" + "bath and body works" + "|" +
            "" + "pacsun" + "|" + "shopping" + "|" + "try on" + "|" + "trying on" + "|" +
            "winter outfits" + "|" + "christmas" + "|" + "giveaway" + "\"	1281002	" +
            "74074	1208\n" + "\n" + "\n" +
            "_feQprPLL98	17.01.12	Harry Styles - Kiwi - ARIA Awards 2017	2017-" +
            "11-28T11:21:37.000Z	\"Harry Styles|" + "Kiwi" + "|" + "ARIAS" + "|" +
            "2017" + "|" + "HD Video" + "|" + "HQ Audio" + "|" + "ARIA Awards" + "|" +
            "Australian Record Industry Awards" + "|" + "Live" + "|" + "High Quality Audio" +
            "|" + "High Definition Video" + "|" + "Concert" + "\"	161357	9126	36\n" +
            "_KEN-cxmGw8	15.01.12	What Jeremy Clarkson thinks about Tesla	2017-11" +
            "-28T19:27:30.000Z	\"tech|" + "uk" + "|" + "tesla" + "|" + "top gear" + "|" +
            "jeremy clarkson" + "|" + "tesla model x" + "|" + "the grand tour" + "|" +
            "tesla roadster" + "\"	440154	4320	535\n" +
            "0MyCrMrkMFo	17.01.12	All The Money In The World - TV Spot featuring" +
            " Christopher Plummer	2017-11-29T07:54:36.000Z	\"All The Money In The" +
            " World|" + "Christopher Plummer" + "\"	61225	499931	286\n" +
            "0MyCrMrkMFo	16.01.12	All The Money In The World - TV Spot featuring" +
            " Christopher Plummer	2017-11-29T07:54:36.000Z	\"All The Money In The" +
            " World|" + "Christopher Plummer" + "\"	61225	499931	286\n";
    private YoutubeTrendsExplorer youtubeTrendsExplorer;

    @Before
    public void setUp() throws Exception {
        try (InputStream reader = new ByteArrayInputStream(STRING_TEST.getBytes(StandardCharsets.UTF_8))) {
            youtubeTrendsExplorer = new YoutubeTrendsExplorer(reader);
        }
    }

    /*@org.junit.Test
    public void getTrendingVideos() throws IOException {

    }*/

    @org.junit.Test
    public void findIdOfLeastLikedVideo() throws IOException {
        Assert.assertEquals("_KEN-cxmGw8", youtubeTrendsExplorer.findIdOfLeastLikedVideo());
    }

    @org.junit.Test
    public void findIdOfMostLikedLeastDislikedVideo() {
        Assert.assertEquals("0MyCrMrkMFo", youtubeTrendsExplorer.findIdOfMostLikedLeastDislikedVideo());

    }

    @org.junit.Test
    public void findDistinctTitlesOfTop3VideosByViews() {
        Assert.assertEquals("[BLACK FRIDAY HAUL 2017 + TRY ON | Bethany Mota, What Jeremy Clarkson thinks" +
                        " about Tesla, Harry Styles - Kiwi - ARIA Awards 2017]",
                youtubeTrendsExplorer.findDistinctTitlesOfTop3VideosByViews().toString());

    }

    @org.junit.Test
    public void findIdOfMostTaggedVideo() {
        Assert.assertEquals("_6uvQtccJss", youtubeTrendsExplorer.findIdOfMostTaggedVideo());
    }

    @org.junit.Test
    public void findTitleOfFirstVideoTrendingBefore100KViews() {
        Assert.assertEquals("All The Money In The World - TV Spot featur" +
                "ing Christopher Plummer", youtubeTrendsExplorer.findTitleOfFirstVideoTrendingBefore100KViews());
    }

    @org.junit.Test
    public void findIdOfMostTrendingVideo() {
        Assert.assertEquals("0MyCrMrkMFo", youtubeTrendsExplorer.findIdOfMostTrendingVideo());

    }
}