package it.thatskai.aragosta.events;

import it.thatskai.aragosta.AragostaMain;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class Event {

    private boolean cancelled;

    public enum State {
        PRE("PRE", 0), POST("POST", 1);
        State(String string, int number) {
        }
    }

    public Event call() {
        this.cancelled = false;

        final ArrayHelper<EventData> dataList = AragostaMain.getClient().eventManager.get(this.getClass());

        if(dataList != null){
            for(EventData data : dataList){
                try {
                    data.target.invoke(data.source, this);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


        return this;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {

        this.cancelled = cancelled;
    }

    private static void call(Event event) {
        ArrayHelper<EventData> dataList = AragostaMain.getClient().eventManager.get(event.getClass());
        if (dataList != null) {
            for (EventData data : dataList) {
                try {
                    data.target.invoke(data.source, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
