package com.gallery.model;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.gallery.domain.Photo;

@SuppressWarnings("deprecation")

public class PhotoDeleteRequestHandler {

	private AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());

	private static final String BUCKET_NAME_PREFIX = "mygram-files-";
	private Photo photo;
	private List<Photo> photos;

	private PhotoDeleteRequestHandler(Photo photo) {
		this.photo = photo;
	}

	private PhotoDeleteRequestHandler(List<Photo> photos) {
		this.photos = photos;
	}

	public static PhotoDeleteRequestHandler getSingleEraseEntity(Photo photo) {
		return new PhotoDeleteRequestHandler(photo);
	}

	public static PhotoDeleteRequestHandler getMultipleEraseEntity(List<Photo> photos) {
		return new PhotoDeleteRequestHandler(photos);
	}

	private DeleteObjectRequest generateSingleEraseRequest() {
		StringBuilder bucketname = new StringBuilder();
		bucketname.append(BUCKET_NAME_PREFIX);
		bucketname.append(this.photo.getAdmin().getName());
		return new DeleteObjectRequest(bucketname.toString(), photo.getPath());
	}

	private DeleteObjectsRequest generateMultipleEraseRequest() {

		List<KeyVersion> keys = new ArrayList<KeyVersion>();
		StringBuilder bucketname = new StringBuilder();
		bucketname.append(BUCKET_NAME_PREFIX);
		bucketname.append(this.photos.get(0).getAdmin().getName());

		if (this.photos == null) {
			return null;
		}

		for (Photo p : this.photos) {
			keys.add(new KeyVersion(p.getPath()));
		}

		DeleteObjectsRequest returnRequest = new DeleteObjectsRequest(bucketname.toString());
		returnRequest.setKeys(keys);

		return returnRequest;

	}

	public boolean eraseSingle() {
		if (this.photo == null) {
			return false;
		}
		try {
			s3client.deleteObject(generateSingleEraseRequest());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean eraseMultiple() {
		if (this.photos == null) {
			return false;
		}
		try {
			s3client.deleteObjects(generateMultipleEraseRequest());
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

}
