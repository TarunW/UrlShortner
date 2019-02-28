package com.tk.app.tinyurl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/v2/shorturl")
@Api(value = "Url Shortner APIs")
public class TinyUrlController {

	@Autowired
	private TinyUrlService service;

	@ApiOperation(value = "Generate an ShortURL")
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public TinyUrl createTinyUrl(
			@ApiParam(value = "URL to be shortend", required = true) @Valid @RequestBody TinyUrl tinyUrl) {
		
		TinyUrl obj = service.findByUrl(tinyUrl.getUrl());
		
		if (obj == null) {
			System.out.println("Object not found");
			obj =  service.save(tinyUrl);
		}
		
		return obj;
	}

	@ApiOperation(value = "View a list of available Urls", response = List.class)
	@RequestMapping(value = "/listall/", method = RequestMethod.GET)
	public List<TinyUrl> getAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/url/{shortUrl}", method = RequestMethod.GET)
	@ApiOperation(value = "Get URL for short key", response = List.class)
	public TinyUrl getUrl(@PathVariable("shortUrl") String shortUrl) {

		return service.findByShortUrl(shortUrl);
	}
}