package xyz.mmkmou.bootcamp.transactions.common.api.restful.response;

import jakarta.ws.rs.core.Response;

import static xyz.mmkmou.bootcamp.transactions.common.api.restful.response.ResponseCode.OK;

public class ResponseHandler {
    public static <T extends BaseResponse> Response handleResponse(T obj){
        Response response;
        if (obj.getCode().equals(OK.getCode())) {
            response = Response.ok().entity(obj).build();
        } else {
            ErrorResponse errorResponse = new HandleErrorResponse(obj.getCode(), obj.getMessage())
                    .handleError();
            response = Response.status(ResponseCode.valueOfCode(obj.getCode()).getStatus())
                    .entity(errorResponse).build();
        }

        return response;
    }
}
