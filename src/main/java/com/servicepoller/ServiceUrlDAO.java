package com.servicepoller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.utils.URIUtils;

import io.netty.handler.codec.http.HttpUtil;

public class ServiceUrlDAO extends BaseDAO<ServiceUrl> {
    private static Pattern UrlRegex = Pattern.compile("^https?://([a-z0-9.]+)(:[0-9]+)?(/[a-z0-9]+)$");

	public ServiceUrlDAO() {
		super(ServiceUrl.class);
	}
	ServiceUrl get(int id) {
		return this.findById(id);
	}
	List<ServiceUrl> getAll() {
		return this.find();
	}
	ServiceUrl create(ServiceUrl entity) {
		logger.info(entity.getUrl());
	    try {
            new URL(entity.getUrl()).toURI();
//            return true;
        }
        catch (URISyntaxException exception) {
            return null;
        }
        catch (MalformedURLException exception) {
            return null;
        }
	    return this.add(entity);
	}
	ServiceUrl update(int id, ServiceUrl entity) {
		ServiceUrl e = get(id);
		e.setStatus(entity.getStatus());
		e.setUrl(entity.getUrl());
		return this.edit(id,e);
	}
}
