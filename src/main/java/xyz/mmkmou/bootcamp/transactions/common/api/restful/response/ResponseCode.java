package xyz.mmkmou.bootcamp.transactions.common.api.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum ResponseCode {
    OK("00", Response.Status.OK,"Operation successful" ),
    UNAUTHORIZED("18", Response.Status.UNAUTHORIZED ,"Authorization failed"),
    BAD_REQUEST("21", Response.Status.BAD_REQUEST, "Invalid parameter processing"),
    NOT_FOUND("15", Response.Status.NOT_FOUND, "Unable to locate record"),
    INTERNAL_SERVER_ERROR("96", Response.Status.INTERNAL_SERVER_ERROR, "Unable to process request do to internal error");

    private final String code;
    private final Response.Status status;
    private final String description;

    private static final Map<String, ResponseCode> lookup = new HashMap<>();
    public static ResponseCode valueOfCode(String code) {
        return lookup.get(code);
    }
    static
    {
        for(ResponseCode e : values())
        {
            lookup.put(e.getCode(), e);
        }
    }
}
