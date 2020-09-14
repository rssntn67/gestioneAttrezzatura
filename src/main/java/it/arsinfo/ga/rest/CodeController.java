package it.arsinfo.ga.rest;

import java.awt.image.BufferedImage;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.arsinfo.ga.qrcode.BarcodeGenerator;

@RestController
@RequestMapping("/barcodes")
public class CodeController {
 
	@PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> genQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(BarcodeGenerator.generateQRCodeImage(barcode));
    }

	@PostMapping(value = "/upca", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> genUpcaBarCode(@RequestBody String barcode) throws Exception {
        return okResponse(BarcodeGenerator.generateUPCBarcodeImage(barcode));
    }

	@PostMapping(value = "/ean13", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> genEan13BarCode(@RequestBody String barcode) throws Exception {
        return okResponse(BarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }
	
	@PostMapping(value = "/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> genCode128BarCode(@RequestBody String barcode) throws Exception {
        return okResponse(BarcodeGenerator.generateCode128BarcodeImage(barcode));
    }

	@PostMapping(value = "/pdf147", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> genPdf147BarCode(@RequestBody String barcode) throws Exception {
        return okResponse(BarcodeGenerator.generatePDF417BarcodeImage(barcode));
    }

    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}