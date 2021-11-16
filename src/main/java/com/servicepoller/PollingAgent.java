package com.servicepoller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpTimeoutException;
import java.nio.channels.UnresolvedAddressException;
import java.net.ConnectException;
import java.util.List;
import java.util.Optional;
import java.time.*;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class PollingAgent extends AbstractVerticle {
	final static Logger logger = LoggerFactory.getLogger(PollingAgent.class);
	ServiceUrlDAO serviceUrlDAO = new ServiceUrlDAO();

	@Override
	public void start() {
		vertx.setPeriodic(20000, (r) -> {
			List<ServiceUrl> listServiceUrl = serviceUrlDAO.getAll();
			listServiceUrl.forEach(s -> {
				logger.info("Polling service: " + s.url);
				String state = "FAIL";
				try {
					HttpRequest request = HttpRequest.newBuilder().uri(new URI(s.url)).timeout(Duration.ofSeconds(10))
							.GET().build();
					HttpClient client = HttpClient.newHttpClient();
					logger.info("Will send out the request now");
					client.send(request, BodyHandlers.ofString());
					logger.info("Request sent");
					state = "OK";
				} catch (URISyntaxException e) {
					e.printStackTrace();
					state = "FAIL";
				} catch (HttpTimeoutException e2) {
					e2.printStackTrace();
					state = "FAIL";
				} catch (InterruptedException e) {
					e.printStackTrace();
					state = "FAIL";
				} catch (UnresolvedAddressException e) {
					e.printStackTrace();
					state = "FAIL";
				} catch (ConnectException e) {
					e.printStackTrace();
					state = "FAIL";
				} catch (IOException e) {
					e.printStackTrace();
					state = "FAIL";
				} catch (Exception e) {
					e.printStackTrace();
					state = "FAIL";
				} finally {
					s.setStatus(state);
					logger.info("ID for row: "+s.getId());
					logger.info("Name for row: "+s.getName());
					logger.info("Status for row: "+s.getStatus());
					serviceUrlDAO.update(s.getId(), s);
					
				}
			});
		});
	}
}
