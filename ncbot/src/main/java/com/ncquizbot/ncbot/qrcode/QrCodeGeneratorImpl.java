package com.ncquizbot.ncbot.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ncquizbot.ncbot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QrCodeGeneratorImpl implements QrCodeGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(QrCodeGeneratorImpl.class);
    private static final String HOST_URL = "https://nctelegrambotpro.herokuapp.com/qrcode/get/present?chatId=";
    private byte[] qrCode;
    @Override
    public void processGeneratingQrCode(User user) {
        byte[] qrCodeImageByteArray = null;
        QRCodeWriter writer = new QRCodeWriter();
        String FULL_SERVER_ADDRESS = HOST_URL + user.getChatId();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BitMatrix matrix = writer.encode(FULL_SERVER_ADDRESS, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);
            this.qrCode = outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            LOGGER.info("ERROR IN QR CODE GENERATION OCCURS!");
            e.printStackTrace();
        }
    }

    public byte[] getQrCode(){
        return this.qrCode;
    }
}
