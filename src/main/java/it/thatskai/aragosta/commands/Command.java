package it.thatskai.aragosta.commands;

import it.thatskai.aragosta.helper.ChatHelper;
import it.thatskai.aragosta.utils.MessageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public interface Command {
    boolean check(String string);
    void onCommand(String[] args);

    default void sendMessage(String message, boolean prefix) {
        if(prefix){
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatHelper.fix(MessageUtils.prefix + "&7" + message)));
        }else{
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatHelper.fix(message)));
        }
    }
}
