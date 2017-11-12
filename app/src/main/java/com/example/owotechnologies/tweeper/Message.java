package com.example.owotechnologies.tweeper;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import java.io.IOException;

/**
 * Created by OWO Technologies on 11/12/2017.
 */

public class Message {
    private final String senderAddress;
    private final String message;
   private final String receiverAddress;
   private final boolean isSender;
    // TODO: 11/12/2017 develop data structure !IMPORTANT

    public Message(String message, String senderAddress, String receiverAddress, boolean isSender)
    {
        this.senderAddress = senderAddress;
        this.message = message;
        this.receiverAddress = receiverAddress;
        this.isSender = isSender;

    }

    private void sendMessage() {
        try {
            Credentials credential = WalletUtils.loadCredentials(StaticInfo.password, StaticInfo.fileLocation);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }


    public boolean getSender() {
        return isSender;
    }
}
