package id.chessburger.wecare.model.enumerations;

/**
 * Created by aflah on 27/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public enum TipeKegiatan {
    CARI_RELAWAN("Cari Relawan"),
    CARTI_LOKASI("Cari Lokasi");

    private String tipeKegiatan;

    TipeKegiatan(String tipeKegiatan) {
        this.tipeKegiatan = tipeKegiatan;
    }

    public String getTipeKegiatan() {
        return tipeKegiatan;
    }
}
