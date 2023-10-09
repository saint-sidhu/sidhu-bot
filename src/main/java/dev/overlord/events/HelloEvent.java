package dev.overlord.events;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){

        //If harshu <3 sends a text (case-insensitive) then it would send ily or else it texts heyy to all others
        String messageSent = event.getMessage().getContentRaw();
        if (messageSent.equalsIgnoreCase("hello")) {
            if(event.getMember().getEffectiveName().equalsIgnoreCase("Harshu\uD83D\uDC9C"))
                event.getChannel().sendMessage("I Love you babyy!!").queue();
            else
                event.getChannel().sendMessage("Heyy ! " + event.getMember().getEffectiveName()).queue();
        }
        //String[] messaging = event.getMessage().getContentRaw().split(" ");
        if (messageSent.equalsIgnoreCase("I love you too")){
            event.getChannel().sendMessage("WOW!! WHY YOU GUYS ARE MAKING ME THIRD WHEEL HERE ,urghhhhhh").queue();
        }

        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }

    }
}