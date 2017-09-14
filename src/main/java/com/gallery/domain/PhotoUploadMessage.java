package com.gallery.domain;

public abstract class PhotoUploadMessage {
	
	protected static final String DIV_CLASS = "<div class=\"alert alert-success\" role=\"alert\">";
	protected static final String DIV_CLASS_FAIL = "<div class=\"alert alert-danger\" role=\"alert\">";
	protected static final String DIV_CLOSER = "</div>";
	
	protected boolean success;
	protected String html;
	protected String filename;
	
	protected PhotoUploadMessage(String filename, boolean success) {
		this.success = success;
		this.filename = filename;
	}
	
	public abstract void setHtmlForm();
	
	public String getHtml() {
		return this.html;
	}
	
	public String getFilename() {
		return this.filename;
	}
	
	public boolean isSuccess() {
		return this.success;
	}

}
