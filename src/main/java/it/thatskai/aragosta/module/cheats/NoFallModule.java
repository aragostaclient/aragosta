package it.thatskai.aragosta.module.cheats;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.utils.MessageUtils;


@IModule(
        name = "NoFall",
        key = 0,
        category = Category.CHEATS
)
public class NoFallModule extends Module {
    public static boolean isToggled;
    public void onEnable() {
        isToggled = true;

        MessageUtils.sendMessage("NoFall attivata", true);
    }

    public void onDisable(){
        isToggled = false;
        MessageUtils.sendMessage("NoFall disattivata", true);
    }
}
