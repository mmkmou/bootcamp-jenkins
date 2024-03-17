package xyz.mmkmou.bootcamp.transactions.common.api.restful.request;

import lombok.RequiredArgsConstructor;
import xyz.mmkmou.bootcamp.transactions.common.api.restful.response.ErrorResponse;
import xyz.mmkmou.bootcamp.transactions.common.api.restful.response.HandleErrorResponse;
import xyz.mmkmou.bootcamp.transactions.common.api.restful.response.ResponseCode;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class RequestHandler {

    final Validator validator;

    public Response handleRequest(Object objectDto, Supplier<Response> apiResponseSupplier){

        try {
            if (objectDto != null) {
                Set<ConstraintViolation<Object>> violations = validator.validate(objectDto);
                if (violations.size() > 0) {
                    // Error contraints violations on DTO;
                    ArrayList<String> errors = new ArrayList<>(0);
                    violations.forEach(violation -> errors.add(violation.getPropertyPath() + " " + violation.getMessage()));
                    ErrorResponse errorResponse = HandleErrorResponse.builder()
                            .code(ResponseCode.BAD_REQUEST.getCode())
                            .message(errors.get(0))
                            .build()
                            .handleError();
                    return Response.status(ResponseCode.valueOfCode(errorResponse.getCode()).getStatus())
                            .entity(errorResponse).build();
                }
            }
            return apiResponseSupplier.get();
        } catch (Exception e){
            System.out.println(e.getMessage());
            ErrorResponse errorResponse = HandleErrorResponse.builder()
                    .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                    .message(e.getMessage())
                    .build()
                    .handleError();

            return Response.status(ResponseCode.valueOfCode(errorResponse.getCode()).getStatus())
                    .entity(errorResponse).build();
        }
    }
}
