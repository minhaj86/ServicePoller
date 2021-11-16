package com.servicepoller;

import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.http.HttpServerResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.util.List;


@Path("/service/url")
public class ServiceUrlResouce {

    final static Logger logger = LoggerFactory.getLogger(ServiceUrlResouce.class);
	ServiceUrlDAO suDao = new ServiceUrlDAO();

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public ServiceUrl addServiceUrl(
                    @Context Vertx vertx,
                    @Context HttpServerResponse response,
                    ServiceUrl serviceUrl)
    {
        logger.info("Service Url to be added");
        ServiceUrl result = suDao.create(serviceUrl);
        if (result==null) {
			response.setStatusCode(400);
			response.setStatusMessage("Invalid Argument");
			response.end();
			return null;
		}
        return result;
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public ServiceUrl editServiceUrl(
                    @Context Vertx vertx,
                    @PathParam("id") int id,
                    ServiceUrl serviceUrl)
    {
        logger.info("Service Url to be updated");
        return suDao.update(id,serviceUrl);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ServiceUrl> getServiceUrl(
                    @Context Vertx vertx)
    {
        logger.info("Service Url to be added");
        return suDao.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ServiceUrl getServiceUrlById(
                    @Context Vertx vertx,
                    @PathParam("id") int id)
    {
        logger.info("Service Url to be fetched");
        return suDao.get(id);
    }
}
