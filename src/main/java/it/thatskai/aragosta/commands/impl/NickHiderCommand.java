package it.thatskai.aragosta.commands.impl;

import it.thatskai.aragosta.AragostaMain;
import it.thatskai.aragosta.commands.Command;

public class NickHiderCommand implements Command {
    private static final String COMMAND_NAME = "nickhide";

    @Override
    public boolean check(String string) {
        return string.equalsIgnoreCase(COMMAND_NAME);
    }

    @Override
    public void onCommand(String[] args) {
        if(AragostaMain.nickHidden){
            AragostaMain.nickHidden = false;
            sendMessage("Ora il tuo nick sarà visibile.", true);
        }else{
            AragostaMain.nickHidden = true;
            sendMessage("Ora il tuo nick sarà nascosto.", true);
        }
    }
}
