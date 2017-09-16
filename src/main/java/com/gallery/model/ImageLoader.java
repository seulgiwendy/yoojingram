package com.gallery.model;

import java.io.InputStream;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.gallery.domain.Admin;
import com.gallery.domain.Photo;

public class ImageLoader {
	
	private final String BUCKET_PREFIX = "mygram-files";
	private final String DASH = "-";
	StringBuilder bucketname = new StringBuilder();
	
	private AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
	
	String adminName;
	String photoKey;

	private ImageLoader(String adminName, String photoKey) {
		this.adminName = adminName;
		this.photoKey = photoKey;

	}

	public static ImageLoader getInstance(Admin admin, Photo photo) {
		return new ImageLoader(admin.getName(), photo.getPath());

	}
	
	public static ImageLoader getInstanceByPhoto(Photo photo) {
		return new ImageLoader(photo.getAdmin().getName(), photo.getPath());
	}

	private GetObjectRequest getS3Request() {
		bucketname.append(BUCKET_PREFIX);
		bucketname.append(DASH);
		bucketname.append(adminName);
		return new GetObjectRequest(bucketname.toString(), this.photoKey);
	}
	
	public InputStream getBytesFromS3() {
		return s3client.getObject(getS3Request()).getObjectContent();
	}
}
