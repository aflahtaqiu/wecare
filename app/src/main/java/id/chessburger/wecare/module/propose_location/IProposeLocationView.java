package id.chessburger.wecare.module.propose_location;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.City;

/**
 * Created by aflah on 07/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IProposeLocationView extends IBaseView {

    void setIndonesiaCities (List<City> cities);
}
