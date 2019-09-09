package id.chessburger.wecare.model.enumerations;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public enum CommunicationKeys {

    BUNDLE_KEY("bundle"),
    SELECTED_ACTIVITY("selected_activity"),
    SELECTED_USER("selected_user"),
    SELECTED_TYPE("selected type");

    private String key;

    CommunicationKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
