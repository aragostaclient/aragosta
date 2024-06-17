package it.thatskai.aragosta.module.hud;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;

@IModule(
        name = "WatermarkChanger",
        key = 0,
        category = Category.HUD
)
public class WatermarkChanger extends Module {

    public static boolean otakuMode = false;

    public void onEnable() {
        otakuMode = !otakuMode;
        toggle();
    }
}
