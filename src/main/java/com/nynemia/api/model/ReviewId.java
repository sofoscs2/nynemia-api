package com.nynemia.api.model;

import java.io.Serializable;
import java.util.Objects;

public class ReviewId implements Serializable {

    private long skipper;

    private long marina;

    public ReviewId() {
    }

    public ReviewId(long skipper, long marina) {
        this.skipper = skipper;
        this.marina = marina;
    }

    public long getSkipper() {
        return skipper;
    }

    public long getMarina() {
        return marina;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ReviewId)) return false;
        ReviewId that = (ReviewId) obj;
        return Objects.equals(getSkipper(), that.getSkipper()) &&
                Objects.equals(getMarina(), that.getMarina());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSkipper(), getMarina());
    }
}
