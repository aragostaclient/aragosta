package it.thatskai.aragosta.module.cheats;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.settings.Setting;
import it.thatskai.aragosta.utils.MessageUtils;
import org.lwjgl.input.Keyboard;

@IModule(
        name = "BedFucker",
        key = Keyboard.KEY_RSHIFT,
        category = Category.CHEATS
)
public class BedFuckerModule extends Module {
    public static boolean isToggled;

    public void setup() {
        AragostaMain.instance.settingsManager.rSetting(new Setting("Range", this, 6, 1, 100, true));
    }
    @Override
    public void onEnable() {
        isToggled = true;
        MessageUtils.sendMessage("BedFucker attivato", true);
    }

    @Override
    public void onDisable() {
        isToggled = false;
        MessageUtils.sendMessage("BedFucker disattivato", true);

    }
}
