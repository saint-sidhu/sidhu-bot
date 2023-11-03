package dev.overlord.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GreetCommand extends ListenerAdapter {

    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        String message[] = event.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("/greet") &&
                message.length == 1 && event.getAuthor().getEffectiveName().equalsIgnoreCase("Sidhu")){
            event.getChannel().sendMessage("**All hail Overlord-sama** \nI as the overseer of all guardians ,pledge you my loyalty forever").queue();
            event.getChannel().sendMessage("Master , what made you come down here today?").queue();
        }
        else if(message[0].equalsIgnoreCase("/greet") && message.length == 1){
            event.getChannel().sendMessage("**Greetings from my Lord**").queue();
            event.getChannel().sendMessage("How may I help you today," + event.getAuthor().getEffectiveName()+"?").queue();
        }
        else if(message[0].equalsIgnoreCase("/greet") && message.length != 1){
            event.getChannel().sendMessage("To use this command, do: \n/greet").queue();
        }

    }
}
