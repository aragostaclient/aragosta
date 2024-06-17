package it.thatskai.aragosta.utils;

import net.minecraft.client.Minecraft;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HWIDUtils {


    public static void checkHWID(){
        String url = "https://kailicense.github.io/sdgsgsdgsdgsdgsdgsdgsdgsdg.html";
        String searchString = getHWID();

        boolean isMatch = false;

        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("li");

            for (Element element : elements) {
                String text = element.text();
                if (searchString.equals(text)) {
                    isMatch = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Il tuo HWID non è stato verificato!");
            Minecraft.getMinecraft().shutdown();
        }

        if (isMatch) {
            System.out.println("Il tuo HWID è stato verificato!");
        } else {
            System.out.println("Il tuo HWID non è stato verificato!");
            DiscordLogger.sendMessageToDiscord("<@1091760902233137344> l'user di windows **" +
                    System.getProperty("user.name")+"** ha provato ad avviare il client con un HWID non verificato! (HWID: " + getHWID() + ")");
            Minecraft.getMinecraft().shutdown();
        }

    }
    private static String getHWID() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            byte[] mac = networkInterface.getHardwareAddress();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(mac);

            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02X", b));
            }

            return sb.toString();
        } catch (UnknownHostException | SocketException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Il tuo HWID non è stato verificato!");
            Minecraft.getMinecraft().shutdown();
        }

        return "";
    }

}
