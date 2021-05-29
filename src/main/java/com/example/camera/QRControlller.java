/**
 * 
 */
package com.example.camera;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.camera.service.EncoderService;

/**
 * @author deepakbisht
 *
 */
@Controller
public class QRControlller {
	@Autowired
	EncoderService service;
	
	@GetMapping("/qrPage")
	public String getImagePage() {
		return "imagePage";
	}
	
	@GetMapping(value="/genCode", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody()
	public BufferedImage generateQRcode(@RequestParam String msg) throws Exception {
		BufferedImage img= EncoderService.generateQRCodeImage(msg);
		return img;
	}

	@GetMapping(value="/genCodeInBytes")
	@ResponseBody()
	public String generateQRcodeString(@RequestParam String msg) throws Exception {
		BufferedImage img= EncoderService.generateQRCodeImage(msg);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(img,"PNG",output);
		String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
		return imageAsBase64;
	}
	
	@GetMapping(value="/genBarCode4j", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody()
	public BufferedImage generateBarCodeBarCode4j(@RequestParam String msg) throws Exception {
		BufferedImage img= EncoderService.generateCode128BarcodeImageByBarCode4j(msg);
		return img;
	}

	@GetMapping(value="/genBarCodeInBytes4j")
	@ResponseBody()
	public String generateBarCodeBarCode4jString(@RequestParam String msg) throws Exception {
		BufferedImage img= EncoderService.generateEAN13BarcodeImageByBarCode4j(msg);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(img,"PNG",output);
		String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
		return imageAsBase64;
	}
	
	@GetMapping(value="/genBarCode", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody()
	public BufferedImage generateBarCode(@RequestParam String msg) throws Exception {
		BufferedImage img= EncoderService.generateEAN13BarcodeImage(msg);
		return img;
	}

	@GetMapping(value="/genBarCodeInBytes")
	@ResponseBody()
	public String generateBarCodeString(@RequestParam String msg) throws Exception {
		BufferedImage img= EncoderService.generateEAN13BarcodeImage(msg);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(img,"PNG",output);
		String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
		return imageAsBase64;
	}
	

}
