package dev.overlord.slashcommands;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.util.ArrayList;
import java.util.List;

public class ModalCommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        String command = event.getName();
        if(command.equals("wassup")){
            TextInput name = TextInput.create("wassup-name","Name", TextInputStyle.SHORT)
                    .setMinLength(1)
                    .setPlaceholder("Write receipient's name")
                    .setRequired(true)
                    .build();

            TextInput message = TextInput.create("wassup-message","message",TextInputStyle.PARAGRAPH)
                    .setMinLength(10)
                    .setMaxLength(100)
                    .setRequired(true)
                    .setPlaceholder("Write something here")
                    .build();

            Modal modal = Modal.create("sup-modal","What's up?")
                    .addActionRows(ActionRow.of(name),ActionRow.of(message))
                    .build();

            event.replyModal(modal).queue();
        }

    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {

        if(event.getModalId().equals("sup-modal")){
            String name = event.getValue("wassup-name").getAsString();
            String message = event.getValue("wassup-message").getAsString();

            event.reply("Hello, "+ name +".Message came from somewhere\n"+message).queue();
        }
    }
}
