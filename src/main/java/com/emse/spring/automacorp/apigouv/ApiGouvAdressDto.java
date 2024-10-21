package com.emse.spring.automacorp.apigouv;

public record ApiGouvAdressDto(
        String id,
        String label,
        String housenumber,
        Double score,
        String postcode,
        String citycode,
        String city,
        String context,
        String type,
        Double x,
        Double y) {

}
