package it.thatskai.aragosta.module.other;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import org.lwjgl.input.Keyboard;

@IModule(
        name = "Fullbright",
        key = Keyboard.KEY_RSHIFT,
        category = Category.OTHER
)
public class FullbrightModule extends Module {
    private float oldBrightness = 0;
    public static boolean isToggled;
    @Override
    public void onEnable() {
        isToggled = true;
        oldBrightness = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 10F;
    }

    @Override
    public void onDisable() {
        isToggled = false;
        mc.gameSettings.gammaSetting = oldBrightness;
    }
}
