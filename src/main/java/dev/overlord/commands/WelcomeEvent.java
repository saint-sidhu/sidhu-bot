package dev.overlord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class WelcomeEvent extends ListenerAdapter {

    private MessageEmbed build;
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("**Step into the Domain of the Supreme Beings**");
        eb.setThumbnail(event.getMember().getAvatarUrl());
        eb.setDescription(" Welcome, honorable guests, to the domain of Nazarick. Your presence is both valued " +
                "and acknowledged. Within our realm, you'll find knowledge, camaraderie, and support. We embrace those " +
                "who share our ideals and principles. However, be forewarned that disrespect towards our community and its " +
                "members will not be tolerated. Let us embark on this journey together, where respect and unity define our path.");
        eb.setColor(Color.GREEN);
        eb.setImage(event.getGuild().getIconUrl());
        build = eb.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(" **ENTER THE HALLS OF THE ETERNAL** ");
        eb.setThumbnail(event.getAuthor().getAvatarUrl());
        eb.setDescription(" Welcome, "+ Objects.requireNonNull(event.getMember()).getEffectiveName()+ "! , to the domain of our server. Your presence is both valued " +
                "and acknowledged. Within our realm, you'll find knowledge, camaraderie, and support. We embrace those " +
                "who share our ideals and principles. However, be forewarned that disrespect towards our community and its " +
                "members will not be tolerated. Let us embark on this journey together, where respect and unity define our path.");
        eb.setColor(Color.PINK);
        eb.setImage("https://imgs.search.brave.com/wbZ1tgMsBj72EvR6NNVuwTSzk8T0PSgb3_g9qZrjN94/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJjYXZlLmNv/bS93cC93cDE5NzYw/MTQuanBn");

       if(event.getChannel().getIdLong() == 1167099680061128775L && !event.getAuthor().isBot()){
           event.getChannel().sendMessageEmbeds(eb.build()).queue();
       }
    }
}
