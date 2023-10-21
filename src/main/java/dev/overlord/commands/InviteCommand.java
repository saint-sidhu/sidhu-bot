package dev.overlord.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InviteCommand extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){
        String message[] = event.getMessage().getContentRaw().split(" ");
        int timer = 3600;
        int hour = 3600/3600;
        if(message[0].equalsIgnoreCase("/invite") && message.length == 1){
            event.getChannel().sendMessage("To use this command, do: \n/invite create").queue();
        }
        else if(message[0].equalsIgnoreCase("/invite")
                && message.length == 2 && message[1].equalsIgnoreCase("create")){
            event.getChannel().sendMessage("If I remember your name correctly, aren't you, "
                    + event.getMember().getEffectiveName()+ ", You want to invite more of your kind in here?").queue();
            event.getChannel().sendMessage("Tskkk, Here's the link ,take it and begone\n" +
                    event.getChannel().asTextChannel().createInvite().setMaxAge(timer).complete().getUrl()).queue();
            event.getChannel().sendMessage("This link will expire in " + hour +" hour." ).queue();
        }
        else if(message[0].equalsIgnoreCase("/invite")
                && message.length == 2 && !message[1].equalsIgnoreCase("create")){
            event.getChannel().sendMessage("To use this command, do: \n/invite create").queue();
        }
    }
}
