package it.thatskai.aragosta.clickgui.elements.menu;

import it.thatskai.aragosta.clickgui.elements.Element;
import it.thatskai.aragosta.clickgui.elements.ModuleButton;
import it.thatskai.aragosta.clickgui.util.ColorUtil;
import it.thatskai.aragosta.clickgui.util.FontUtil;
import it.thatskai.aragosta.settings.Setting;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.MathHelper;

import java.awt.Color;


public class ElementSlider extends Element {
	public boolean dragging;

	public ElementSlider(ModuleButton iparent, Setting iset) {
		parent = iparent;
		set = iset;
		dragging = false;
		super.setup();
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		String displayval = "" + Math.round(set.getValDouble() * 100D)/ 100D;
		boolean hoveredORdragged = isSliderHovered(mouseX, mouseY) || dragging;

		Color temp = ColorUtil.getClickGUIColor();
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 250 : 200).getRGB();
		int color2 = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 255 : 230).getRGB();

		//selected = iset.getValDouble() / iset.getMax();
		double percentBar = (set.getValDouble() - set.getMin())/(set.getMax() - set.getMin());
		Gui.drawRect((int) x, (int) y, (int) (x + width), (int) (y + height), 0xff1a1a1a);
		FontUtil.drawString(setstrg, x + 1, y + 2, 0xffffffff);
		FontUtil.drawString(displayval, x + width - FontUtil.getStringWidth(displayval), y + 2, 0xffffffff);
		Gui.drawRect((int) x, (int) (y + 12), (int) (x + width), (int) (y + 13.5), 0xff101010);
		Gui.drawRect((int) x, (int) (y + 12), (int) (x + (percentBar * width)), (int) (y + 13.5), color);

		if(percentBar > 0 && percentBar < 1)
			Gui.drawRect((int) (x + (percentBar * width)-1), (int) (y + 12), (int) (x + Math.min((percentBar * width), width)), (int) (y + 13.5), color2);

		if (this.dragging) {
			double diff = set.getMax() - set.getMin();
			double val = set.getMin() + (MathHelper.clamp_double((mouseX - x) / width, 0, 1)) * diff;
			set.setValDouble(val); //Die Value im Setting updaten
		}

	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0 && isSliderHovered(mouseX, mouseY)) {
			this.dragging = true;
			return true;
		}

		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public void mouseReleased(int mouseX, int mouseY, int state) {
		this.dragging = false;
	}

	public boolean isSliderHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y + 11 && mouseY <= y + 14;
	}
}