package wtf.bot;

import net.staro.bot.api.bus.EventBus;
import net.staro.bot.api.bus.impl.Listener;
import net.staro.bot.api.bus.impl.SubscriberImpl;

// This is how one might make an additional service for handling anything using EventBus
public class CustomService extends SubscriberImpl {
    public CustomService() {
        listen(new Listener<Main.InitEvent>(1) {
            @Override
            public void onEvent(Main.InitEvent initEvent) {
                System.out.println(initEvent.time()); // posts this thing in console
            }
        });
    }
    
}
