package dev.overlord.events;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelloEvent extends ListenerAdapter {

    public void onMessageReceived(@NotNull MessageReceivedEvent event){

        //If harshu <3 sends a text (case-insensitive) then it would send ily or else it texts heyy to all others
        String messageSent = event.getMessage().getContentRaw();
        if (messageSent.equalsIgnoreCase("hello")||
                messageSent.equalsIgnoreCase("hi") ||
                messageSent.equalsIgnoreCase("hey")) {
            if(event.isFromType(ChannelType.PRIVATE) && event.getAuthor().getEffectiveName().equalsIgnoreCase("Harshu\uD83D\uDC9C")){
                event.getChannel().sendMessage("I Love you babyy!!").queue();
                event.getChannel().sendMessage("But why are you texting me secretly?").queue();
            }
            else if(event.getAuthor().getEffectiveName().equalsIgnoreCase("Sidhu")){
                event.getChannel().sendMessage("How may I help you today, "+event.getMember().getEffectiveName() +"-sama ?").queue();
            }
            else if(event.getAuthor().getEffectiveName().equalsIgnoreCase("ankita_nayak")){
                event.getChannel().sendMessage("Ughhh , it's you again, "+event.getMember().getEffectiveName() +" ?").queue();
            }
            else if(!event.isFromType(ChannelType.PRIVATE) && event.getAuthor().getEffectiveName().equalsIgnoreCase("Harshu\uD83D\uDC9C")){
                event.getChannel().sendMessage("I Love you babyy!!").queue();
            }
            else if(event.isFromType(ChannelType.PRIVATE)){
                event.getChannel().sendMessage("Hello but why are you texting me secretly?").queue();
            }

            else if(!event.isFromType(ChannelType.PRIVATE))
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
