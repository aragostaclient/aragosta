package it.thatskai.aragosta.clickgui.elements;

import it.thatskai.aragosta.clickgui.ClickGUI;
import it.thatskai.aragosta.clickgui.util.FontUtil;
import it.thatskai.aragosta.settings.Setting;

public class Element {
	public ClickGUI clickgui;
	public ModuleButton parent;
	public Setting set;
	public double offset;
	public double x;
	public double y;
	public double width;
	public double height;

	public String setstrg;

	public boolean comboextended;

	public void setup(){
		//clickgui = parent.parent.clickgui;
	}

	public void update(){
		x = parent.x + parent.width + 2;
		y = parent.y + offset;
		width = parent.width + 10;
		height = 15;

		String sname = set.getName();
		if(set.isCheck()){
			setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1);
			double textx = x + width - FontUtil.getStringWidth(setstrg);
			if (textx < x + 13) {
				width += (x + 13) - textx + 1;
			}
		}else if(set.isCombo()){
			height = comboextended ? set.getOptions().size() * (FontUtil.getFontHeight() + 2) + 15 : 15;

			setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1);
			int longest = FontUtil.getStringWidth(setstrg);
			for (String s : set.getOptions()) {
				int temp = FontUtil.getStringWidth(s);
				if (temp > longest) {
					longest = temp;
				}
			}
			double textx = x + width - longest;
			if (textx < x) {
				width += x - textx + 1;
			}
		}else if(set.isSlider()){
			setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1);
			String displaymax = "" + Math.round(set.getMax() * 100D)/ 100D;
			double textx = x + width - FontUtil.getStringWidth(setstrg) - FontUtil.getStringWidth(displaymax) - 4;
			if (textx < x) {
				width += x - textx + 1;
			}
		}
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		return isHovered(mouseX, mouseY);
	}

	public void mouseReleased(int mouseX, int mouseY, int state) {}

	public boolean isHovered(int mouseX, int mouseY)
	{

		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
}