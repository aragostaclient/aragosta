package it.thatskai.aragosta.gui.hook;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.helper.ChatHelper;
import it.thatskai.aragosta.helper.PacketHelper;
import it.thatskai.aragosta.module.hud.AddressHider;
import it.thatskai.aragosta.module.hud.BrandHider;
import it.thatskai.aragosta.module.hud.NameHider;
import it.thatskai.aragosta.module.hud.WatermarkChanger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import java.text.DecimalFormat;

public class GuiClientInGame extends GuiIngame {

    private final Minecraft mc;
    private final ScaledResolution scaledResolution;
    private final FontRenderer fontRenderer;
    DecimalFormat df = new DecimalFormat("#.##");
    String color = "&f";

    public GuiClientInGame(Minecraft mc) {
        super(mc);
        this.mc = mc;
        this.scaledResolution = new ScaledResolution(mc);
        this.fontRenderer = mc.fontRendererObj;
    }

    @Override
    public void renderGameOverlay(float partialTicks) {
        super.renderGameOverlay(partialTicks);

        String brand;
        if (mc.thePlayer.getClientBrand() != null) {
            brand = mc.thePlayer.getClientBrand().contains("<- ") ? mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand().split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];
        } else {
            brand = "NegroSpigot";
        }

        int lag = (int) PacketHelper.getServerLagTime();

        if (lag == 0) {
            color = "&f";
        } else if (lag > 15000) {
            color = "&c";
        } else if (lag > 10000) {
            color = "&6";
        } else if (lag > 5000) {
            color = "&e";
        } else if (lag > 1000) {
            color = "&2";
        } else {
            color = "&a";
        }


        String nick = "Hidden";

        if(!AragostaMain.nickHidden){
            nick = Minecraft.getMinecraft().getSession().getUsername();
        }

        if(WatermarkChanger.otakuMode){
            mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/otaku-logo.png"));
        }else{
            mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/logo.png"));
        }

        drawModalRectWithCustomSizedTexture(4, 4, 0.0F, 0.0F, 75, 75, (float) 75, (float) 75);

        if(!NameHider.hide){
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&6Nome &8» &f" + nick), 6, 20, -1);
        }
        if(!AddressHider.hide){
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&6Indirizzo IP &8» &f" + mc.getCurrentServerData().serverIP), 6, 30, -1);
        }

        if(!BrandHider.hide){
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&6Brand &8» &f" + brand), 6, 40, -1);
        }

        if(PacketHelper.getServerLagTime() > 500){
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&6Lag &8» " + color + PacketHelper.getServerLagTime() + " ms"), 6, 60, -1);
        }
    }
}