package it.thatskai.aragosta.commands.impl;

import it.thatskai.aragosta.commands.Command;
import it.thatskai.aragosta.utils.PacketsUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C14PacketTabComplete;

public class PluginFinderCommand implements Command {
    private static final String COMMAND_NAME = "pluginfinder";

    @Override
    public boolean check(String string) {
        return string.equalsIgnoreCase(COMMAND_NAME);
    }

    @Override
    public void onCommand(String[] args) {
        Minecraft mc = Minecraft.getMinecraft();
        sendMessage("Provando a reperire i plugins del server.. ", true);
        mc.thePlayer.sendChatMessage("/bukkit:plugins");
        PacketsUtils.sendPacketNoEvent((Packet)new C14PacketTabComplete("/bukkit:version "));
    }
}
