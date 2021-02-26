package za.co.newplant.musicapi.service;

import java.util.Optional;

public class MusicUtility {

    public static String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
    }
}
