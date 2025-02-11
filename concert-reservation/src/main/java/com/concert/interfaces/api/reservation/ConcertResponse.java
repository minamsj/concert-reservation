package com.concert.interfaces.api.reservation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ConcertResponse {
    private final Long concert_id;
    private final String concert_title;

    @JsonCreator
    public ConcertResponse(
            @JsonProperty("concert_id") Long concert_id,
            @JsonProperty("concert_title") String concert_title
    ) {
        this.concert_id = concert_id;
        this.concert_title = concert_title;
    }

    // equals와 hashCode 메서드 추가
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcertResponse that = (ConcertResponse) o;
        return Objects.equals(concert_id, that.concert_id) &&
                Objects.equals(concert_title, that.concert_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concert_id, concert_title);
    }
}