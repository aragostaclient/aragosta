package it.thatskai.aragosta.gui.mainmenu;

import java.io.IOException;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.gui.alt.GuiAltManager;
import it.thatskai.aragosta.mods.viamcp.gui.GuiProtocolSelector;
import it.thatskai.aragosta.utils.HWIDUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiClientMainMenu extends GuiScreen implements GuiYesNoCallback {

    public boolean doesGuiPauseGame() {
        return false;
    }
    protected void keyTyped(char typedChar, int keyCode) throws IOException {}
    public void initGui() {
        int var3 = this.height / 4 + 48;
        addSingleplayerMultiplayerButtons(var3);
        this.buttonList.add(new GuiButton(0, (int)(this.width / 2.0F - 100.0F), var3 + 92 - 30, 102, 20, "Settings"));
        this.buttonList.add(new GuiButton(4, (int)(this.width / 2.0F + 2.0F), var3 + 92 - 30, 98, 20, I18n.format("menu.quit", new Object[0])));
    }
    private void addSingleplayerMultiplayerButtons(int p_73969_1_) {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, p_73969_1_, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, p_73969_1_ + 24 - 4, I18n.format("menu.multiplayer", new Object[0])));

        this.buttonList.add(new GuiButton(14, this.width / 2 - 100, p_73969_1_ + 24 + 16,102, 20, "Accounts"));
        this.buttonList.add(new GuiButton(15,this.width / 2 + 2, p_73969_1_ + 24 + 16,102, 20, "Versions"));

        String nick = "hidden";

        if(AragostaMain.nickHidden){
            nick = "hidden";
        }else{
            nick = "show";
        }


        //this.buttonList.add(new GuiButton(16,this.width / 2 + 2, p_73969_1_ + 60,102, 20, "Nick: " + nick));
    }
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 16:
                if(AragostaMain.nickHidden){
                    AragostaMain.nickHidden = false;
                    button.displayString = "Nick: show";
                }else{
                    AragostaMain.nickHidden =true;
                    button.displayString = "Nick: hidden";
                }

                break;
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 14:
                this.mc.displayGuiScreen(new GuiAltManager());
                break;
            case 15:
                this.mc.displayGuiScreen(new GuiProtocolSelector(this));
                break;
            case 4:
                this.mc.shutdown();
                break;
        }
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/mainmenu.png"));
        this. drawModalRectWithCustomSizedTexture((int) 0.0D, (int) 0.0D, 0.0F, 0.0F, this.width, this.height, this.width, this.height);
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/logo-main.png"));
        drawModalRectWithCustomSizedTexture(width / 2 - 50, height / 4 - 60, 0.0F, 0.0F, 100, 100, (float) 100, (float) 100);

        String nick = "Hidden";

        if(AragostaMain.nickHidden){
            nick = "Hidden";
        }else{
            nick = Minecraft.getMinecraft().getSession().getUsername();
        }

        this.drawString(this.fontRendererObj, "Name: " + nick, 10, 10, -7829368);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
