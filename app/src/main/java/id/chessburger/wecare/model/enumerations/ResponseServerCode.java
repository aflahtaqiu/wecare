package id.chessburger.wecare.model.enumerations;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public enum ResponseServerCode {

    ACCEPTED(202),
    BAD_REQUEST(400),
    CREATED(201),
    INTERNAL_SERVER_ERROR(500),
    METHID_NOT_ALLOWED(405),
    NOT_FOUND(404),
    OK(200),
    REQUEST_TIMEOUT(408),
    UNAUTHORIZED(401),
    UNPROCESSABLE_ENTITY(422);

    private int code;

    ResponseServerCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
