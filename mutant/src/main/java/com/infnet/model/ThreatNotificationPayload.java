package com.infnet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ThreatNotificationPayload {

    private Long mutantId;
    private String name;
    private int threatLevel;


    @Override
    public String toString() {
        return "ThreatNotificationPayload{" +
                "mutantId=" + mutantId +
                ", name='" + name + '\'' +
                ", threatLevel=" + threatLevel +
                '}';
    }

}
