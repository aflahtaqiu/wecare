package id.chessburger.wecare.base;

import id.chessburger.wecare.data.remote.api.ApiRajaOngkir;
import id.chessburger.wecare.data.remote.api.ApiRetrofit;
import id.chessburger.wecare.data.remote.api.IApiEndpoint;
import id.chessburger.wecare.data.remote.api.IRajaOngkirEndpoint;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class BaseRemoteDataSource {
    protected IApiEndpoint apiEndpoint = ApiRetrofit.getInstance().create(IApiEndpoint.class);
    protected IRajaOngkirEndpoint rajaOngkirEndpoint = ApiRajaOngkir.getInstance().create(IRajaOngkirEndpoint.class);
}
