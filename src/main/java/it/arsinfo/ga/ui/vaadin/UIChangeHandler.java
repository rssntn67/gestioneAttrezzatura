package it.arsinfo.ga.ui.vaadin;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Component;

public abstract class UIChangeHandler {

    /**
     * 
     */
    private ChangeHandler changeHandler;
    /**
     * 
     */
    private Component[] components;
    
    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }
    
    public  void onChange() {
        changeHandler.onChange();
    }
    
    public void setVisible(boolean visible) {
        for (Component component:components) {
            component.setVisible(visible);
        }
    }

    public Component[] getComponents() {
        return components;
    }

    public void addComponents(Component ... components) {
    	if (this.components == null)
    		this.components = components;
    	else
    		this.components=Stream.concat(Arrays.stream(this.components), Arrays.stream(components)).toArray(Component[]::new);	
    }
    
    public void setComponents(Component ... components) {
        this.components = components;
    }

    public StreamResource createStreamResource(BufferedImage bi) {
        return new StreamResource(new StreamSource() {
			
            /**
			 * 
			 */
			private static final long serialVersionUID = 5371943965586727249L;

			@Override
            public InputStream getStream() {
 
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(bi, "png", bos);
                    return new ByteArrayInputStream(bos.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }, "qrcode.png");
    }
}
