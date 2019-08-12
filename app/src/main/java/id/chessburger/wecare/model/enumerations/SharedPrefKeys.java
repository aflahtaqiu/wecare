package id.chessburger.wecare.model.enumerations;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public enum SharedPrefKeys {

    PROFIL("profile"),
    TOKEN("token");

    private String key;

    SharedPrefKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
