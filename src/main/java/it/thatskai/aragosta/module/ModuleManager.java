package it.thatskai.aragosta.module;

import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.module.cheats.FlightModule;
import it.thatskai.aragosta.module.cheats.KillauraModule;
import it.thatskai.aragosta.module.cheats.NoFallModule;
import it.thatskai.aragosta.module.cheats.SpeedModule;
import it.thatskai.aragosta.module.crasher.*;
import it.thatskai.aragosta.module.exploits.*;
import it.thatskai.aragosta.module.hud.AddressHider;
import it.thatskai.aragosta.module.hud.BrandHider;
import it.thatskai.aragosta.module.hud.NameHider;
import it.thatskai.aragosta.module.hud.WatermarkChanger;
import it.thatskai.aragosta.module.cheats.BedFuckerModule;
import it.thatskai.aragosta.module.other.FakeGamemodeModule;
import it.thatskai.aragosta.module.other.FullbrightModule;

import java.util.ArrayList;

public class ModuleManager {

    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {

        //CRASHERS
        modules.add(new OnePacketModule());
        modules.add(new PayloadModule());
        modules.add(new Payload2Module());
        modules.add(new PositionAModule());
        modules.add(new PositionBModule());
        modules.add(new PositionCModule());
        modules.add(new FireworkWinClickModule());
        modules.add(new FireworkBlockPlaceModule());

        //EXPLOITS
        modules.add(new WorldEditModule());
        modules.add(new MultiverseModule());
        modules.add(new FastAsyncWorldEditModule());
        modules.add(new LuckPermsBungeeModule());
        modules.add(new LuckPermsVelocityModule());
        modules.add(new SkillModule());
        modules.add(new RCEModule());
        modules.add(new IRCModule());

        //CHEATS
        modules.add(new FlightModule());
        modules.add(new SpeedModule());
        modules.add(new KillauraModule());
        modules.add(new NoFallModule());

        //OTHERS
        modules.add(new FakeGamemodeModule());
        modules.add(new FullbrightModule());
        modules.add(new BedFuckerModule());

        //hud
        modules.add(new WatermarkChanger());
        modules.add(new NameHider());
        modules.add(new BrandHider());
        modules.add(new AddressHider());

        //GRIEF (acquatical)
        //modules.add(new AutoPermissions());
        //modules.add(new StaffersDepex());
    }
    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
