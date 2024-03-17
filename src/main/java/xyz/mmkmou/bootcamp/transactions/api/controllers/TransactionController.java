package xyz.mmkmou.bootcamp.transactions.api.controllers;

import lombok.RequiredArgsConstructor;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionDto;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionListResponse;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionResponse;
import xyz.mmkmou.bootcamp.transactions.common.api.restful.request.RequestHandler;
import xyz.mmkmou.bootcamp.transactions.common.api.restful.response.*;
import xyz.mmkmou.bootcamp.transactions.services.TransactionServices;
import org.springframework.stereotype.Component;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.function.Supplier;


@Path("/transactions")
@Component
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServices transactionServices;
    private final RequestHandler requestHandler;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTransaction(TransactionDto transactionDto){

        Supplier<Response> apiResponseSupplier = () -> {
            TransactionResponse transactionResponse = transactionServices.createTransaction(transactionDto);
            return ResponseHandler.handleResponse(transactionResponse);
        };
        return requestHandler.handleRequest(transactionDto, apiResponseSupplier);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllTransaction(){
        Supplier<Response> apiResponseSupplier = () -> {
            TransactionListResponse transactionResponse = transactionServices.getAllTransaction();
            return ResponseHandler.handleResponse(transactionResponse);
        };
        return requestHandler.handleRequest(null, apiResponseSupplier);
    }

    @GET
    @Path("/{fref}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTransactionByFref(@PathParam("fref") String fref){
        Supplier<Response> apiResponseSupplier = () -> {
            TransactionListResponse transactionResponse = transactionServices.getTransactionByFref(fref);
            return ResponseHandler.handleResponse(transactionResponse);
        };
        return requestHandler.handleRequest(null, apiResponseSupplier);
    }

    @GET
    @Path("/{fref}/{amount}/{phone}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response validateTransaction(@PathParam("fref") String fref, @PathParam("amount") String amount, @PathParam("phone") String phone) {
        Supplier<Response> apiResponseSupplier = () -> {
            TransactionResponse transactionResponse = transactionServices.validateTransaction(fref, amount, phone);
            return ResponseHandler.handleResponse(transactionResponse);
        };
        return requestHandler.handleRequest(null, apiResponseSupplier);
    }

    @POST
    @Path("/{fref}/{amount}/{phone}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateTransaction(@PathParam("fref") String fref, @PathParam("amount") String amount, @PathParam("phone") String phone, TransactionDto transactionDto) {
        Supplier<Response> apiResponseSupplier = () -> {
            TransactionResponse transactionResponse = transactionServices.updateTransaction(fref, amount, phone, transactionDto);
            return ResponseHandler.handleResponse(transactionResponse);
        };
        return requestHandler.handleRequest(transactionDto, apiResponseSupplier);
    }

}
