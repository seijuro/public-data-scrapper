package com.github.seijuro.publicdata.result.item;

import com.google.gson.annotations.SerializedName;
import com.github.seijuro.publicdata.property.RecallProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class RecallExtraData extends Object {
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.Extra.TIME)
    private final Long time;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.Extra.NEW)
    private final Boolean _new;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.Extra.MACHINE)
    private final Long machine;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.Extra.TIMESECOND)
    private final Long timeSecond;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.Extra.INC)
    private final Long inc;

    /**
     * C'tor
     * @param $time
     * @param $new
     * @param $machine
     * @param $timeSecond
     * @param $inc
     */
    public RecallExtraData(Long $time, Boolean $new, Long $machine, Long $timeSecond, Long $inc) {
        this.time = $time;
        this._new = $new;
        this.machine = $machine;
        this.timeSecond = $timeSecond;
        this.inc = $inc;
    }
}
