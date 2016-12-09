package com.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Link")
public class Link {
	private String action;
	private String url;
	private String rel;
	private String mediaType;
	
	public Link() {}
	
	public Link(String action, String url, String rel, String mediaType) {
		this.action = action;
		this.url = url;
		this.rel = rel;
		this.mediaType = mediaType;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
}

