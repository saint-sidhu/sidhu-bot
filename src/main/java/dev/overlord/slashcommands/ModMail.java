package dev.overlord.slashcommands;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.awt.*;

public class ModMail extends ListenerAdapter {

    private final Dotenv config;
    public ModMail(){
        config = Dotenv.configure().load();
    }
    Member member;
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if(command.equals("support")){
            event.deferReply().setEphemeral(true).queue();
            event.getHook().sendMessage("Do you want to raise a query ticket?")
                    .addActionRow(
                            Button.success("yes","YES"),
                            Button.danger("no","NAUR")
                    ).queue();
            member = event.getMember();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getComponentId().equals("yes")){
            TextInput subject = TextInput.create("modmail-subject","Subject", TextInputStyle.SHORT)
                    .setMinLength(1)
                    .setPlaceholder("Write the subject of query")
                    .setRequired(true)
                    .build();
            TextInput query = TextInput.create("modmail-details","Details",TextInputStyle.PARAGRAPH)
                    .setMinLength(10)
                    .setMaxLength(100)
                    .setPlaceholder("Write your query(in details)")
                    .setRequired(true)
                    .build();

            Modal modal = Modal.create("modmail","STATE YOUR QUERY")
                    .addActionRows(ActionRow.of(subject),ActionRow.of(query))
                    .build();

            event.replyModal(modal).queue();
            event.getComponent().withDisabled(true);

        }
        else if(event.getComponentId().equals("no")){
            event.reply("So you just decided to waste my time?").queue();
        }
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if(event.getModalId().equals("modmail")){
            TextChannel staff = event.getJDA().getTextChannelById(config.get("staff"));
            TextChannel staffEthereal = event.getJDA().getTextChannelById(config.get("staff-ethereal"));

            String sub = event.getValue("modmail-subject").getAsString();
            String query = event.getValue("modmail-details").getAsString();
            event.reply("Your response has been delivered").setEphemeral(true).queue();

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("TICKET BY " +member.getEffectiveName().toUpperCase());
            eb.setThumbnail(member.getAvatarUrl());
            eb.setColor(Color.MAGENTA);
            eb.setDescription("Subject: "+ sub +"\n Query: "+query);
            if(event.getGuild().getName().equals("Bot Testing"))
                staff.sendMessageEmbeds(eb.build()).queue();
            else if(event.getGuild().getName().equals("AISE KAISE BHAMIYAAA")){
                staffEthereal.sendMessageEmbeds(eb.build()).queue();
            }
        }
    }
}
