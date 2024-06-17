package it.thatskai.aragosta.module.other;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.utils.MessageUtils;
import net.minecraft.world.WorldSettings;

@IModule(
        name = "FakeCreative",
        key = 0,
        category = Category.OTHER
)
public class FakeGamemodeModule extends Module {
    public static boolean isToggled;
    @Override
    public void onEnable() {
        isToggled = true;
        mc.playerController.setGameType(WorldSettings.GameType.CREATIVE);
        MessageUtils.sendMessage("Modalità di gioco cambiata a &aCREATIVE", true);
    }

    @Override
    public void onDisable() {
        isToggled = false;
        mc.playerController.setGameType(WorldSettings.GameType.SURVIVAL);
        MessageUtils.sendMessage("Modalità di gioco cambiata a &aSURVIVAL", true);
    }
}
