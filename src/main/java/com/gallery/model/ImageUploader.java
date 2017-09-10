package com.gallery.model;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gallery.domain.Admin;

import static com.gallery.utils.StringUtils.randomString;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class ImageUploader {

	private final int FILEPATH_LENGTH = 20;
	private final String BUCKET_PREFIX = "mygram-files";
	private final String DASH = "-";

	AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

	private String extension;
	private String contentType;
	private Admin admin;
	private ObjectMetadata meta;
	
	@Deprecated
	public ImageUploader() {
		
	}
	
	private ImageUploader(Admin admin, String extension, String contentType) {
		this.admin = admin;
		this.extension = extension;
		this.contentType = contentType;
	}

	public static ImageUploader getUploaderInstance(Admin admin, String extension, String contentType) {
		return new ImageUploader(admin, extension, contentType);
	}

	private String generateBucketName() {
		StringBuilder sb = new StringBuilder();
		sb.append(BUCKET_PREFIX);
		sb.append(DASH);
		sb.append(this.admin.getName());
		return sb.toString();
	}

	private String generateFileName() {
		StringBuilder sb = new StringBuilder();
		sb.append(randomString(FILEPATH_LENGTH));
		sb.append(this.extension);
		return sb.toString();
	}
	
	private void generateMetadata(int length) {
		this.meta = new ObjectMetadata();
		meta.setContentLength(length);
		meta.setContentType(contentType);
	}
	
	private boolean isExistingBucket() {
		List <Bucket> bucketlist = s3client.listBuckets();
		for (Bucket b : bucketlist) {
			if (b.getName().equals(this.admin.getName())) {
				return true;
			}
		}
		return false;
	}

	private PutObjectRequest generatePutRequest() {
		
	}

	public String sendImageToS3(byte[] imgbytes) {

		try {
			InputStream stream = new ByteArrayInputStream(imgbytes);
			meta.setContentLength(imgbytes.length);
			meta.setContentType(contentType);

		} catch (Exception e) {

		}
	}

}
