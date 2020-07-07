package it.arsinfo.ga.ui.vaadin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.vaadin.server.StreamResource.StreamSource;

public class NoQRCodeImageSource implements StreamSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -872836283698220059L;
	ByteArrayOutputStream imagebuffer = null;

    // This method generates the stream contents
    public InputStream getStream () {
    	ByteArrayOutputStream imagebuffer = null;
    	
        BufferedImage image = new BufferedImage (200, 200,
                                  BufferedImage.TYPE_INT_RGB);
        Graphics2D drawable = image.createGraphics();

        // Draw something static
        drawable.setStroke(new BasicStroke(5));
        drawable.setColor(Color.WHITE);
        drawable.fillRect(0, 0, 200, 200);
        drawable.setColor(Color.BLACK);
        drawable.drawOval(50, 50, 100, 100);

        // Draw something dynamic
        drawable.setFont(new Font("Montserrat",
                                  Font.PLAIN, 10));
        drawable.drawString("No QR Code" , 50, 170);
        drawable.setColor(new Color(0, 165, 235));
        int x= (int) (100-10 );
        int y= (int) (100-10 );
        drawable.fillOval(x, y, 30, 30);

        try {
            // Write the image to a buffer
            imagebuffer = new ByteArrayOutputStream();
            ImageIO.write(image, "png", imagebuffer);

            // Return a stream from the buffer
            return new ByteArrayInputStream(
                imagebuffer.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }
}
