package com.ncquizbot.ncbot.qrcode;

import com.ncquizbot.ncbot.model.User;

public interface QrCodeGenerator {
    void processGeneratingQrCode(User user);
    byte[] getQrCode();
}
