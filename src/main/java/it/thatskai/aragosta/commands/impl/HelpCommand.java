package it.thatskai.aragosta.commands.impl;

import it.thatskai.aragosta.commands.Command;

public class HelpCommand implements Command {
    private static final String COMMAND_NAME = "help";

    @Override
    public boolean check(String string) {
        return string.equalsIgnoreCase(COMMAND_NAME);
    }

    @Override
    public void onCommand(String[] args) {
        sendMessage(".help - Mostra questo messaggio", true);
        sendMessage(".author - Mostra i crediti", true);
        sendMessage(".pluginfinder - Trova i plugins del server", true);
        sendMessage("Tasto §lR-SHIFT &7 per aprire la click-gui", true);
    }
}