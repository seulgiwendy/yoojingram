package com.gallery.domain;

public class PhotoSuccessMessage extends PhotoUploadMessage{

	public PhotoSuccessMessage(String filename, boolean success) {
		super(filename, success);
	}

	@Override
	public void setHtmlForm() {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(DIV_CLASS);
		sbf.append("<p><strong>파일 업로드 완전 성공!</strong></p>");
		sbf.append("<p>파일 업로드에 성공했습니다.</p>");
		sbf.append(DIV_CLOSER);
		this.html = sbf.toString();
	}

}
