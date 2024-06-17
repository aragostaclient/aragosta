package it.thatskai.aragosta.module.cheats;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.utils.MessageUtils;

@IModule(
        name = "Speed",
        key = 0,
        category = Category.CHEATS
)
public class SpeedModule extends Module {

    public static boolean isToggled;
    public void onEnable() {
        isToggled = true;

        MessageUtils.sendMessage("Speed attivata", true);
        mc.thePlayer.setSprinting(true);

    }

    public void onDisable(){
        isToggled = false;
        MessageUtils.sendMessage("Speed disattivata", true);
        mc.thePlayer.setSprinting(false);
    }
}
