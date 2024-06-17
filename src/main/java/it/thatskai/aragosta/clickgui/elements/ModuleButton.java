package it.thatskai.aragosta.clickgui.elements;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.clickgui.Panel;
import it.thatskai.aragosta.clickgui.elements.menu.ElementCheckBox;
import it.thatskai.aragosta.clickgui.elements.menu.ElementComboBox;
import it.thatskai.aragosta.clickgui.elements.menu.ElementSlider;
import it.thatskai.aragosta.clickgui.util.ColorUtil;
import it.thatskai.aragosta.clickgui.util.FontUtil;
import it.thatskai.aragosta.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ModuleButton {
	public Module mod;
	public ArrayList<Element> menuelements;
	public Panel parent;
	public double x;
	public double y;
	public double width;
	public double height;
	public boolean extended = false;
	public boolean listening = false;

	public ModuleButton(Module imod, Panel pl) {
		mod = imod;
		height = 9 + 2;
		parent = pl;
		menuelements = new ArrayList<>();
		if(AragostaMain.instance.settingsManager != null){
			if (AragostaMain.instance.settingsManager.getSettingsByMod(imod) != null){
				for (Setting s : AragostaMain.instance.settingsManager.getSettingsByMod(imod)) {
					if (s.isCheck()) {
						menuelements.add(new ElementCheckBox(this, s));
					} else if (s.isSlider()) {
						menuelements.add(new ElementSlider(this, s));
					} else if (s.isCombo()) {
						menuelements.add(new ElementComboBox(this, s));
					}
				}
			}
		}

	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		Color temp = ColorUtil.getClickGUIColor();
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();

		int textcolor = 0xffafafaf;
		if (mod.isToggled()) {
			Gui.drawRect((int) (x - 2), (int) y, (int) (x + width + 2), (int) (y + height + 1), color);
			textcolor = 0xffefefef;
		}

		if (isHovered(mouseX, mouseY)) {
			Gui.drawRect((int) (x - 2), (int) y, (int) (x + width + 2), (int) (y + height + 1), 0x55111111);
		}

		FontUtil.drawTotalCenteredStringWithShadow(mod.getName(), x + width / 2, y + 1 + height / 2, textcolor);
	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (!isHovered(mouseX, mouseY))
			return false;

		if (mouseButton == 0) {
			mod.toggle();

//			if(AragostaMain.instance.settingsManager.getSettingByName("Sound").getValBoolean())
//				Minecraft.getMinecraft().thePlayer.playSound("random.click", 0.5f, 0.5f);
		} else if (mouseButton == 1) {
			if (menuelements != null && menuelements.size() > 0) {
				boolean b = !this.extended;
				AragostaMain.instance.clickGUI.closeAllSettings();
				this.extended = b;


			}
		} else if (mouseButton == 2) {
			listening = true;
		}
		return true;
	}

	public boolean keyTyped(char typedChar, int keyCode) throws IOException {
		if (listening) {
			if (keyCode != Keyboard.KEY_ESCAPE) {
				//Client.sendChatMessage("Bound '" + mod.getName() + "'" + " to '" + Keyboard.getKeyName(keyCode) + "'");
				mod.setKey(keyCode);
			} else {
				//Client.sendChatMessage("Unbound '" + mod.getName() + "'");
				mod.setKey(Keyboard.KEY_NONE);
			}
			listening = false;
			return true;
		}
		return false;
	}

	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}

}
