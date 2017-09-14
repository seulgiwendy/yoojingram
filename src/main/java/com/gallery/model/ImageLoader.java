package com.gallery.model;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.gallery.domain.Admin;
import com.gallery.domain.Photo;

public class ImageLoader {
	
	private final String BUCKET_PREFIX = "mygram-files";
	private final String DASH = "-";
	StringBuilder bucketname = new StringBuilder();
	
	String adminName;
	String photoKey;
	
	private ImageLoader(String adminName, String photoKey) {
		this.adminName = adminName;
		this.photoKey = photoKey;
	}
	
	public static ImageLoader getInstance(Admin admin, Photo photo) {
		return new ImageLoader(admin.getName(), photo.getPath());
		
	}
	
	public GetObjectRequest getS3Request() {
		bucketname.append(BUCKET_PREFIX);
		bucketname.append(DASH);
		bucketname.append(adminName);
		return new GetObjectRequest(bucketname.toString(), this.photoKey);
	}
}
