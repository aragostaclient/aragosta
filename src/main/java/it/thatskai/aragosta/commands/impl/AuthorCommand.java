package it.thatskai.aragosta.commands.impl;

import it.thatskai.aragosta.commands.Command;

public class AuthorCommand implements Command {
    private static final String COMMAND_NAME = "author";

    @Override
    public boolean check(String string) {
        return string.equalsIgnoreCase(COMMAND_NAME);
    }

    @Override
    public void onCommand(String[] args) {
        sendMessage("", false);
        sendMessage("&aAragostaClient by @ThatsKai & @SoySkrahs", true);
        sendMessage("", false);
    }
}