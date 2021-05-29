/**
 * 
 */
package com.example.camera.service;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.glxn.qrgen.javase.QRCode;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

/**
 * @author deepakbisht
 *
 */
@Service
public class EncoderService {
	private static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
	
	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
		ByteArrayOutputStream stream = QRCode.from(barcodeText).stream();
		ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

		return ImageIO.read(bis);
	}

	public BufferedImage gen(int height, int width, String text) throws IOException, WriterException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(text), BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "PNG", out);
		ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

		return ImageIO.read(bis);
	}

	public BufferedImage gen2(int height, int width, String text) throws IOException, WriterException {

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix matrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "PNG", out);
		ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());
		
		return ImageIO.read(bis);
	}
	//using Barbecue
	public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
	    Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
	    barcode.setFont(BARCODE_TEXT_FONT);

	    return BarcodeImageHandler.getImage(barcode);
	}
	//Barcode4j 
	public static BufferedImage generateEAN13BarcodeImageByBarCode4j(String barcodeText) {
	    EAN13Bean barcodeGenerator = new EAN13Bean();
	    BitmapCanvasProvider canvas = 
	      new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	    barcodeGenerator.generateBarcode(canvas, barcodeText);
	    return canvas.getBufferedImage();
	}
	
	//Barcode4j 
		public static BufferedImage generateCode128BarcodeImageByBarCode4j(String barcodeText) {
		    Code128Bean barcodeGenerator = new Code128Bean();
		    BitmapCanvasProvider canvas = 
		      new BitmapCanvasProvider(480, BufferedImage.TYPE_BYTE_BINARY, false, 0);

		    barcodeGenerator.setMsgPosition(HumanReadablePlacement.HRP_NONE); // to suppress the msg below barcode.
		    barcodeGenerator.generateBarcode(canvas, barcodeText);
		    return canvas.getBufferedImage();
		}
}
