package it.thatskai.aragosta;

import it.thatskai.aragosta.clickgui.ClickGUI;
import it.thatskai.aragosta.commands.CommandManager;
import it.thatskai.aragosta.commands.impl.*;
import it.thatskai.aragosta.events.EventManager;
import it.thatskai.aragosta.events.listeners.EventListener;
import it.thatskai.aragosta.mods.Discord;
import it.thatskai.aragosta.mods.viamcp.ViaMCP;
import it.thatskai.aragosta.module.ModuleManager;
import it.thatskai.aragosta.settings.SettingsManager;
import it.thatskai.aragosta.utils.DiscordLogger;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.lwjgl.opengl.Display;

public class AragostaMain {

    public String version = "3.0";
    private static AragostaMain client;
    public Minecraft mc = Minecraft.getMinecraft();
    public SettingsManager settingsManager;
    public ModuleManager moduleManager;
    public ClickGUI clickGUI;
    public EventManager eventManager;
    public static AragostaMain instance;
    public static boolean PremiumUUID;
    public static boolean SessionPremium;
    private static String fakenick = "Insert a fakename";
    public static boolean nickHidden = true;
    public static Session session;
    public static String PreUUID;
    public void clientStart(){
        instance = this;
        settingsManager = new SettingsManager();
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        clickGUI = new ClickGUI();
        eventManager.register(new EventListener());
        Display.setTitle("AragostaClient | " + version);
        new Discord().startTask();
        //HWIDUtils.checkHWID();
        CommandManager.getManager().addCommands(
                new AuthorCommand(),
                new HelpCommand(),
                new PluginFinderCommand(),
                new NickHiderCommand()
        );

        Minecraft.getMinecraft().gameSettings.ofFastRender = true;

        try {
            ViaMCP.getInstance().start();
            ViaMCP.getInstance().initAsyncSlider();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        DiscordLogger.sendMessageToDiscord
                ("Client avviato con l'username **" +
                        Minecraft.getMinecraft().getSession().getUsername() +
                        "** | Utente windows: **" +
                        System.getProperty("user.name")+"**");
    }

    public static AragostaMain getClient() {
        if (client == null) {
            client = new AragostaMain();
        }
        return client;
    }

    public void terminate() {
        DiscordRPC.discordShutdown();
    }

    public static String getFakeNick() {
        return fakenick;
    }

    public static void setFakeNick(String name) {
        fakenick = name;
    }

    public static void setSession(Session copiedSession) {
        session = copiedSession;
    }

}
