package dev.overlord.events;

import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.update.GenericChannelUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChannelEvent extends ListenerAdapter {
    public void onChannelCreate(ChannelCreateEvent event){

        //when we create a new channel/category it sends this message in #general
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage
                ("Someone just created a Category/Channel in " + event.getGuild().getName()).queue();

        //event.getGuild().getDefaultChannel().asTextChannel().sendMessage
                //("Someone just created a Voice Channel in " + event.getGuild().getName()).queue();

    }

    public void onChannelDelete(ChannelDeleteEvent event){
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage
                ("Someone just deleted a Category/Channel in " + event.getGuild().getName()).queue();
    }

    public void onGenericChannelUpdate(GenericChannelUpdateEvent event){
    }
}
