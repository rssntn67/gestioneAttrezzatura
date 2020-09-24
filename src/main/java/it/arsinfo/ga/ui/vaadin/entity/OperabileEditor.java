package it.arsinfo.ga.ui.vaadin.entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Binder;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Image;

import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.qrcode.BarcodeGenerator;
import it.arsinfo.ga.service.OperabileService;
import it.arsinfo.ga.ui.vaadin.NoQRCodeImageSource;

public abstract class OperabileEditor<M extends Modello, T extends Operabile<M>, S extends Operazione<T>>
        extends Editor<T> {

    private static StreamResource noqrcoderesource = new StreamResource(new NoQRCodeImageSource(),"noqrcode.png");
	private final Image qrcode = new Image();
	private final Image barcode = new Image();

    private static final Logger log = LoggerFactory.getLogger(OperabileEditor.class);

    private final OperabileService<M, T, S> service;
    public OperabileEditor(OperabileService<M,T,S> service, Binder<T> binder) {
    	super(service, binder);
    	this.service=service;
    }

    public List<S> findOperazioni() {
    	return service.findOperazioni(get());
    }

    public void edit(T c) {
    	super.edit(c);
    	T smdObj = get();
        if (smdObj.getId() != null) {
            log.info("edit: code: {}", smdObj.getCode());
			qrcode.setCaption("QR Code");
			qrcode.setIcon(createQRCodeStreamResource(smdObj.getCode()));
			barcode.setCaption("Bar Code");
			barcode.setIcon(createBarCodeStreamResource(smdObj.getCode()));
			qrcode.setVisible(true);
			barcode.setVisible(true);
        } else {
			qrcode.setCaption("No QR Code yet");
			qrcode.setIcon(noqrcoderesource);
			barcode.setCaption("No Barcode yet");
			barcode.setIcon(noqrcoderesource);
			qrcode.setVisible(false);
			barcode.setVisible(false);
        }
    }

	public Image getQrCodeImage() {
		return qrcode;
	}

	public Image getBarCodeImage() {
		return barcode;
	}

	public static StreamResource createBarCodeStreamResource(String code) {
		String barcode=code;
		if (code.length() > 79) {
			barcode=code.substring(0, 79);
		}
		try {
			return createStreamResource(BarcodeGenerator.generateCode128BarcodeImage(barcode));
		} catch (Exception e) {
			log.error("createBarCodeStreamResource: {}", e.getMessage(),e);
		}
		return noqrcoderesource;
	}

	public static StreamResource createQRCodeStreamResource(String qrcode) {
		try {
			return createStreamResource(BarcodeGenerator.generateQRCodeImage(qrcode));
		} catch (Exception e) {
			log.error("createQRCodeStreamResource: {}", e.getMessage(),e);
		}
		return noqrcoderesource;
	}
    
	public static StreamResource createStreamResource(BufferedImage bi) {
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
        }, "code.png");
    }

}
