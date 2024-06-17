package it.thatskai.aragosta.settings;

import it.thatskai.aragosta.annotations.modules.Module;
import it.thatskai.aragosta.settings.Setting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SettingsManager {
    private final ArrayList<it.thatskai.aragosta.settings.Setting> settings;
    public static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    public static Date date = new Date(System.currentTimeMillis());

    public SettingsManager(){
        this.settings = new ArrayList<>();
    }

    public void rSetting(it.thatskai.aragosta.settings.Setting in){
        this.settings.add(in);
    }

    public ArrayList<it.thatskai.aragosta.settings.Setting> getSettings(){
        return this.settings;
    }

    public ArrayList<it.thatskai.aragosta.settings.Setting> getSettingsByMod(Module mod){
        ArrayList<it.thatskai.aragosta.settings.Setting> out = new ArrayList<>();
        for(it.thatskai.aragosta.settings.Setting s : getSettings()){
            if(s.getParentMod().equals(mod)){
                out.add(s);
            }
        }
        if(out.isEmpty()){
            return null;
        }
        return out;
    }

    public it.thatskai.aragosta.settings.Setting getSettingByName(String name){
        for(it.thatskai.aragosta.settings.Setting set : getSettings()){
            if(set.getName().equalsIgnoreCase(name)){
                return set;
            }
        }
        System.err.println("[" + formatter.format(date) + "] " + "[Smart thread/ERROR]:" + "Error Setting NOT found: '" + name +"'!");
        return null;
    }
}
