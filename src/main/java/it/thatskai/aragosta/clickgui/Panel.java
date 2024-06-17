package it.thatskai.aragosta.clickgui;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.clickgui.elements.ModuleButton;
import it.thatskai.aragosta.clickgui.util.ColorUtil;
import it.thatskai.aragosta.clickgui.util.FontUtil;
import net.minecraft.client.gui.Gui;

import java.awt.Color;
import java.util.ArrayList;


public class Panel {
	public String title;
	public double x;
	public double y;
	private double x2;
	private double y2;
	public double width;
	public double height;
	public boolean dragging;
	public boolean extended;
	public boolean visible;
	public ArrayList<ModuleButton> Elements = new ArrayList<>();
	public ClickGUI clickgui;
		public Panel(String ititle, double ix, double iy, double iwidth, double iheight, boolean iextended, ClickGUI parent) {
			this.title = ititle;
			this.x = ix;
			this.y = iy;
			this.width = iwidth;
			this.height = iheight;
			this.extended = iextended;
			this.dragging = false;
			this.visible = true;
			this.clickgui = parent;
			setup();
		}

		public void setup() {}

		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			if (!this.visible)
				return;

			if (this.dragging) {
				x = x2 + mouseX;
				y = y2 + mouseY;
			}

			Color temp = ColorUtil.getClickGUIColor().darker();
			int outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();

			Gui.drawRect((int) x, (int) y, (int) (x + width), (int) (y + height), 0xff121212);

			Gui.drawRect((int) (x + 4), (int) (y + 2), (int) (x + 4.3), (int) (y + height - 2), 0xffaaaaaa);
			Gui.drawRect((int) (x - 4 + width), (int) (y + 2), (int) (x - 4.3 + width), (int) (y + height - 2), 0xffaaaaaa);
			FontUtil.drawTotalCenteredStringWithShadow(title, x + width / 2, y + height / 2, 0xffefefef);


			if (this.extended && !Elements.isEmpty()) {
				double startY = y + height;
				int epanelcolor =  0xbb151515;
				for (ModuleButton et : Elements) {
					Gui.drawRect((int) x, (int) startY, (int) (x + width), (int) (startY + et.height + 1), epanelcolor);
					et.x = x + 2;
					et.y = startY;
					et.width = width - 4;
					et.drawScreen(mouseX, mouseY, partialTicks);
					startY += et.height + 1;
				}
				Gui.drawRect((int) x, (int) (startY + 1), (int) (x + width), (int) (startY + 1), epanelcolor);

			}
		}

		public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
			if (!this.visible) {
				return false;
			}
			if (mouseButton == 0 && isHovered(mouseX, mouseY)) {
				x2 = this.x - mouseX;
				y2 = this.y - mouseY;
				dragging = true;
				return true;
			} else if (mouseButton == 1 && isHovered(mouseX, mouseY)) {
				extended = !extended;
				return true;
			} else if (extended) {
				for (ModuleButton et : Elements) {
					if (et.mouseClicked(mouseX, mouseY, mouseButton)) {
						return true;
					}
				}
			}
			return false;
		}

		public void mouseReleased(int mouseX, int mouseY, int state) {
			if (!this.visible) {
				return;
			}
			if (state == 0) {
				this.dragging = false;
			}
		}

		public boolean isHovered(int mouseX, int mouseY) {
			return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
		}
	}