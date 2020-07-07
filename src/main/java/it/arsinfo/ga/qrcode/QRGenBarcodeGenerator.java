package it.arsinfo.ga.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

public class QRGenBarcodeGenerator {

	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
	    ByteArrayOutputStream stream = net.glxn.qrgen.javase.QRCode
	      .from(barcodeText)
	      .withSize(250, 250)
	      .stream();
	    ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
	 
	    return ImageIO.read(bis);
	}
	

}
