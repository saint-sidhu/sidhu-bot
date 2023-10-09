package dev.overlord.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingPongEvent extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event){
        String messageSent = event.getMessage().getContentRaw();

        if(messageSent.equalsIgnoreCase("!ping")){
            event.getChannel().sendMessage("pong").queue();
        }
    }
}
