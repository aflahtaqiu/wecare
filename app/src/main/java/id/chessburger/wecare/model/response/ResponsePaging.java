    package id.chessburger.wecare.model.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

    /**
     * Created By aflah on 03/06/19.
     * Email : aflahtaqiusondha@gmail.com
     * Github : https://github.com/aflahtaqiu
     */

    @Data
    public class ResponsePaging {

        @SerializedName("page")
        private Integer page;

        @SerializedName("item_per_page")
        private Integer itemPerPage;

        @SerializedName("last_page")
        private Integer lastPage;

        @SerializedName("total_item")
        private Long totalItem;
    }
