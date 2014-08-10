package com.itechart.training.tsvilik.contactsapp.web.helpers;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.entities.Photo;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;

public class PhotoHelper {
	private static Logger logger = Logger.getLogger(PhotoHelper.class);

	private static final String SAVE_DIR;
	private static final String PHOTO_TYPE;
	private static final int PHOTO_WIDTH;
	private static final int PHOTO_HEIGHT;

	static {
		String filename = "web.properties";
		ClassLoader classLoader = AttachmentHelper.class.getClassLoader();
		InputStream input = classLoader.getResourceAsStream(filename);
		Properties properties = new Properties();
		String photosDir = null;
		String photoType = null;
		Integer maxPhotoWidth = null;
		Integer maxPhotoHeight = null;
		try {
			properties.load(input);
			photosDir = properties.getProperty("photos_dir");
			photoType = properties.getProperty("photos_type");
			maxPhotoHeight = Integer.parseInt(properties
					.getProperty("max_photo_height"));
			maxPhotoWidth = Integer.parseInt(properties
					.getProperty("max_photo_width"));
		} catch (IOException | NumberFormatException e) {
			logger.error("Failed to get photo properties.", e);
		} finally {
			SAVE_DIR = photosDir == null ? "c:\\uploads" : photosDir;
			PHOTO_TYPE = photoType == null ? "jpg" : photoType;
			PHOTO_WIDTH = maxPhotoWidth == null ? 200 : maxPhotoWidth;
			PHOTO_HEIGHT = maxPhotoHeight == null ? 200 : maxPhotoHeight;
		}
	}

	public static Photo getPhoto(HttpServletRequest request) {

		Photo photo = new Photo();

		String photoFilename = uploadPhoto(request);
		if (photoFilename == null) {
			logger.debug("No photo has been loaded.");
			return null;
		}
		photo.setFileName(photoFilename);
		try {
			photo = BlManager.getPhotoManager().save(photo);
			logger.debug("File: " + photo.getFileName()
					+ " has been saved successfully.");

			return photo;
		} catch (ModelException e) {
			logger.error("Failed to save new photo.");
			return null;
		}
	}

	public static File getPhotoFile(Photo photo) {
		File requestedFile = new File(SAVE_DIR + File.separator
				+ photo.getFileName());
		return requestedFile.exists() ? requestedFile : null;
	}

	private static String uploadPhoto(HttpServletRequest request) {
		logger.debug("load photo");
		try {
			File fileSaveDir = new File(SAVE_DIR);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			FileItem fileItem = (FileItem) request.getAttribute("photo");
			if (fileItem == null) {
				return null;
			}
			String uuidFileName = UUID.randomUUID().toString() + "."
					+ PHOTO_TYPE;
			File uploadedFile = new File(SAVE_DIR + File.separator
					+ uuidFileName);
			uploadedFile.createNewFile();
			OutputStream imageOutput = new FileOutputStream(uploadedFile);
			if (!resizeImage(fileItem.getInputStream(), imageOutput,
					PHOTO_WIDTH, PHOTO_HEIGHT)) {
				throw new Exception("Failed to resize the image");
			}
			imageOutput.flush();
			imageOutput.close();
//			fileItem.write(uploadedFile);
			return uuidFileName;
		} catch (Exception e) {
			logger.error("Error on saving uploaded photo.", e);
			return null;
		}
	}

	public static Boolean resizeImage(InputStream inputImage,
			OutputStream outputImage, Integer Width, Integer Height) {
		BufferedImage origImage;
		try {

			origImage = ImageIO.read(inputImage);
			int type = origImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: origImage.getType();

			// *Special* if the width or height is 0 use image src dimensions
			if (Width == 0) {
				Width = origImage.getWidth();
			}
			if (Height == 0) {
				Height = origImage.getHeight();
			}

			int fHeight = Height;
			int fWidth = Width;

			// Work out the resized width/height
			if (origImage.getHeight() > Height || origImage.getWidth() > Width) {
				fHeight = Height;
				int wid = Width;
				float sum = (float) origImage.getWidth()
						/ (float) origImage.getHeight();
				fWidth = Math.round(fHeight * sum);

				if (fWidth > wid) {
					// rezise again for the width this time
					fHeight = Math.round(wid / sum);
					fWidth = wid;
				}
			}

			BufferedImage resizedImage = new BufferedImage(fWidth, fHeight,
					type);
			Graphics2D g = resizedImage.createGraphics();
			g.setComposite(AlphaComposite.Src);

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			g.drawImage(origImage, 0, 0, fWidth, fHeight, null);
			g.dispose();

			ImageIO.write(resizedImage, PHOTO_TYPE, outputImage);

		} catch (IOException e) {
			logger.error("IO error while resizing an image", e);
			return false;
		}

		return true;
	}

}
