package dev.overlord.slashcommands;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;


public class ButtonsTesting extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if(command.equals("hello")){
            event.reply("CLICK ON THE BUTTON BELOW TO SAY HEYY")
                    .addActionRow(
                            Button.primary("hello","Click Me"),//Button with only a label
                            Button.success("emoji", Emoji.fromFormatted("<:minn:245267426227388416>"))//Button with only emoji
                    ).queue();
        }

    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getComponentId().equals("hello")){
            event.reply("Hello,how are you doing?").queue();
        }
        else if(event.getComponentId().equals("emoji")){
            event.reply("Did I ask you to click this button??").queue();
        }
    }
}
