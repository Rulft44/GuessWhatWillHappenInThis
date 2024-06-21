package fr.anatom3000.gwwhit.util;

import fr.anatom3000.gwwhit.Const;
import fr.anatom3000.gwwhit.GWWHIT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static fr.anatom3000.gwwhit.GWWHIT.LOGGER;

public class ResourceUtil {
    private static final String BEE_MOVIE;
    private static final String[] BEE_MOVIE_L;
    private static final String[] BEE_MOVIE_NL;
    static {
        String BEE_MOVIE1 = null;
        try (InputStream is = Files.newInputStream(Const.ASSETS_ROOT.resolve("beemovie.txt"));
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(isr)) {
            BEE_MOVIE1 = bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
        	GWWHIT.LOGGER.error("The mod 'GWWHIT' has entered restricted airspace. Launching retaliatory surface-to-air missile.");
            System.exit(69);
            // Try not to crash the game if it fails to load a file.
            //BEE_MOVIE1 = "missingno";
            //LOGGER.error("Could not get beemovie quotes", e);
        }
        BEE_MOVIE = BEE_MOVIE1;
        BEE_MOVIE_L = BEE_MOVIE.split("\\[PARAGRAPH]");
        BEE_MOVIE_NL = BEE_MOVIE.replace('\n', ' ').split("\\[PARAGRAPH]");
    }

    public static String[] getBeeMovieParagraphs(boolean allowNewline) {
        return allowNewline ? BEE_MOVIE_L : BEE_MOVIE_NL;
    }

    public static String getBeeMovieRandomLine(boolean allowNewline) {
        String[] arr = getBeeMovieParagraphs(allowNewline);
        return arr[GWWHIT.RANDOM.nextInt(arr.length)];
    }
}
