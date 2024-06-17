package it.thatskai.aragosta.module.hud;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;

@IModule(
        name = "NameHider",
        key = 0,
        category = Category.HUD
)
public class NameHider extends Module {

    public static boolean hide = false;

    public void onEnable() {
        hide = !hide;
        toggle();
    }
}
