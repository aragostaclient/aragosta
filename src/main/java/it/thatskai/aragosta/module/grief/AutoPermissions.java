package it.thatskai.aragosta.module.grief;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

@IModule(
        name = "AutoPermissions",
        key = 0,
        category = Category.GRIEF
)
public class AutoPermissions extends Module {

    @Override
    public void onEnable() {

        List<String> nicks = new ArrayList<>();
        nicks.add("pescheria");
        nicks.add("SortinoLobby");
        nicks.add("Bannandoh");
        nicks.add("ssru");
        nicks.add("Skrahs");
        nicks.add("JumpResettato");


        for(String n : nicks){
            mc.thePlayer.sendChatMessage("/lpb user " + n + " permission set *");
            mc.thePlayer.sendChatMessage("/lp user " + n + " permission set *");
            mc.thePlayer.sendChatMessage("/unban " + n);
            mc.thePlayer.sendChatMessage("/unblacklist " + n);
        }

        MessageUtils.sendMessage("&7Hai dato i permessi e unbannato/unblacklistato a pescheria, sortinolobby,ssru, skrahs (godiamo!)", false);
        toggle();
    }
}
