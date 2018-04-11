package Logic;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class KeyGenerator {
    public static Key generateKey() {
        String keyString = "ynC7I9nFNB3ALIrjZUaIPyOYhiSWDge579LaTP";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }
}
