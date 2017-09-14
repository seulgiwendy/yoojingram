package com.gallery.domain;

public class PhotoFailMessage extends PhotoUploadMessage{

	public PhotoFailMessage(String filename, boolean success) {
		super(filename, success);
	}

	@Override
	public void setHtmlForm() {
		// TODO Auto-generated method stub
		
	}

}
