package id.chessburger.wecare.model.enumerations;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public enum ApiKey {

    RAJA_ONGKIR ("c807e974c155656150c14d92f8ce5ad4");

    private String apiKey;

    ApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }
}
