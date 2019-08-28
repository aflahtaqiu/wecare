package id.chessburger.wecare.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
@Builder
public class Activity {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String nameActivity;

    @SerializedName("photo")
    private String photo;

    @SerializedName("start")
    private Date startDate;

    @SerializedName("end")
    private Date endDate;

    @SerializedName("registerDeadline")
    private Date registerDeadlineDate;

    @SerializedName("description")
    private String description;

    @SerializedName("volunteerTasks")
    private String volunteerTasks;

    @SerializedName("volunteerEquipments")
    private String volunteerEquipments;

    @SerializedName("volunteerRequirements")
    private String volunteerRequirements;

    @SerializedName("briefs")
    private String briefs;

    @SerializedName("minVolunteers")
    private int minVolunteers;

    @SerializedName("donationTarget")
    private int donationTarget;

    @SerializedName("volunteersTotal")
    private int volunteersTotal;

    @SerializedName("donationsTotal")
    private int donationsTotal;

    @SerializedName("cashedDown")
    private boolean cashedDown;

    @SerializedName("area")
    private String area;

    @SerializedName("maxParticipants")
    private int maxParticipants;

    @SerializedName("isDone")
    private boolean isDone;

    @SerializedName("type")
    private ActivityType type;
}
