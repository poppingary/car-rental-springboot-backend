package agk.wow.carrental.config;

import java.util.UUID;

final public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}