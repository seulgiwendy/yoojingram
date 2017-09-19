package com.gallery.model;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import com.gallery.domain.Admin;

import static com.gallery.utils.StringUtils.randomString;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class ImageUploader {

	private final int FILEPATH_LENGTH = 20;
	private final String BUCKET_PREFIX = "mygram-files";
	private final String DASH = "-";
	private final String PERIOD = ".";

	AmazonS3 s3client = new AmazonS3Client(InstanceProfileCredentialsProvider.getInstance());


	private String extension;
	private String contentType;
	private Admin admin;
	private ObjectMetadata meta;

	private String uploadedFileName;

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
	
	public String getUploadedFileName() {
		return this.uploadedFileName;
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
		sb.append(PERIOD);
		sb.append(this.extension);
		return sb.toString();
	}

	private void generateMetadata(int length) {
		this.meta = new ObjectMetadata();
		meta.setContentLength(length);
		meta.setContentType(contentType);
	}

	private boolean isNonExistingBucket(String bucketname) {
		List<Bucket> bucketlist = s3client.listBuckets();
		for (Bucket b : bucketlist) {
			if (b.getName().equals(bucketname)) {
				return false;
			}
		}
		return true;
	}

	private CreateBucketRequest generateBucketCreateRequest(String bucketname) {
		return new CreateBucketRequest(bucketname, Region.AP_Seoul);
	}

	private PutObjectRequest generatePutRequest(InputStream fileInputStream) {

		String bucketname = BUCKET_PREFIX + DASH + this.admin.getName();
		this.uploadedFileName = generateFileName();

		if (isNonExistingBucket(bucketname)) {
			s3client.createBucket(generateBucketCreateRequest(bucketname));
		}
		return new PutObjectRequest(bucketname, uploadedFileName, fileInputStream, meta);
	}

	public boolean sendImageToS3(byte[] imgbytes) {

		try {
			InputStream stream = new ByteArrayInputStream(imgbytes);
			generateMetadata(imgbytes.length);
			s3client.putObject(generatePutRequest(stream));
			System.out.println(uploadedFileName);
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
