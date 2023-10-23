package dev.overlord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class InviteCommand extends ListenerAdapter {

    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        String message[] = event.getMessage().getContentRaw().split(" ");
        int timer = 3600;
        int hour = 3600/3600;
        if(message[0].equalsIgnoreCase("/invite") && message.length == 1){
            event.getChannel().sendMessage("To use this command, do: \n/invite create").queue();
        }
        else if(message[0].equalsIgnoreCase("/invite")
                && message.length == 2 && message[1].equalsIgnoreCase("create")){

            /*event.getChannel().sendMessage("If I remember your name correctly, aren't you, "
                    + event.getMember().getEffectiveName()+ ", You want to invite more of your kind in here?").queue();
            event.getChannel().sendMessage("Tskkk, Here's the link ,take it and begone\n" +
                    event.getChannel().asTextChannel().createInvite().setMaxAge(timer).complete().getUrl()).queue();
            event.getChannel().sendMessage("This link will expire in " + hour +" hour." ).queue();

             */

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Link up or Fade away");
            //eb.setTitle("Link up or Fade away",event.getChannel().asTextChannel().createInvite().setMaxAge(timer).complete().getUrl());
            eb.setImage("https://imgs.search.brave.com/wbZ1tgMsBj72EvR6NNVuwTSzk8T0PSgb3_g9qZrjN94/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJjYXZlLmNv/bS93cC93cDE5NzYw/MTQuanBn");
            eb.setAuthor("ALBEDO SENDS HER WARM REGARDS");
            eb.setThumbnail(event.getGuild().getIconUrl());
            eb.setColor(Color.RED);
            eb.setDescription("Welcome to a realm of unwavering authority and unyielding command. " +
                    "This invite serves as your ultimate chance to step into the shadows and become part of our enigmatic assembly. " +
                    "The message is clear: this is your last warning. Take the link, embrace our dominion, or vanish into obscurity." +
                    "The consequences of hesitating are not to be taken lightly. Join now or face the repercussions of your defiance.\n" +
                    event.getChannel().asTextChannel().createInvite().setMaxAge(timer).complete().getUrl()+"\n\nThis link will expire in "
                    +hour +" hour.");
            eb.setFooter("Death by sword. Death by broken bones. Death by crushing. " +
                    "There's not much difference, right? You die at the end.");
            //eb.setUrl(event.getChannel().asTextChannel().createInvite().setMaxAge(timer).complete().getUrl());
            event.getChannel().sendMessageEmbeds(eb.build()).queue();

        }
        else if(message[0].equalsIgnoreCase("/invite")
                && message.length == 2 && !message[1].equalsIgnoreCase("create")){
            event.getChannel().sendMessage("To use this command, do: \n/invite create").queue();
        }
    }
}
