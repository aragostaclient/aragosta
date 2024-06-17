package it.thatskai.aragosta.utils;

import it.thatskai.aragosta.helper.ChatHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class MessageUtils {
    public static String prefix = "&6Aragosta &8» ";

    public static void sendMessage(String message, boolean prefix) {
        if(prefix){
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatHelper.fix(MessageUtils.prefix + "&7" + message)));
        }else{
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatHelper.fix(message)));
        }
    }
}
