package it.thatskai.aragosta.module.cheats;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.utils.MessageUtils;

@IModule(
        name = "Flight",
        key = 0,
        category = Category.CHEATS
)
public class FlightModule extends Module {
    public static boolean isToggled;
    public void onEnable() {
        isToggled = true;

        MessageUtils.sendMessage("Flight attivata", true);
        mc.thePlayer.capabilities.isFlying = true;

    }

    public void onDisable(){
        isToggled = false;
        MessageUtils.sendMessage("Flight disattivata", true);
        mc.thePlayer.capabilities.isFlying = false;
    }
}
