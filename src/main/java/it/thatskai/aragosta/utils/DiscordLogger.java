package it.thatskai.aragosta.utils;

import okhttp3.*;

public class DiscordLogger {
    static String loggerWebhook = "https://discord.com/api/webhooks/1119219320623476739/K8R8Noj0BKZ0uh2f9UaQbUv3GZyGu1EsJF9J9O_tST18lue5R5xvFixHXgyAfO4pgLtQ";

    public static void sendMessageToDiscord(String message){

        /*Thread thread = new Thread(() -> {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"content\": \"" + message + "\"}");

            Request request = new Request.Builder()
                    .url(loggerWebhook)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.out.println("Errore durante l'invio del webhook: " + response.code() + " " + response.message());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();*/
    }
}
