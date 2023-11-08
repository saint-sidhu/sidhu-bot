package dev.overlord.slashcommands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.util.List;

public class DropdownMenu extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("food")){
            event.reply("Select your favourite food!")
                    .addActionRow(
                            StringSelectMenu.create("choose-food")
                                    //.addOption("Pizza","pizza","Margheritta")//dropdown with label,value and description
                                    .addOptions(SelectOption.of("Burger","burger") //another way to create dropdowns
                                            .withDescription("Double-patty Chicken Burger")
                                            .withEmoji(Emoji.fromUnicode("\uD83C\uDF54"))
                                            .withDefault(true))
                                    .addOptions(SelectOption.of("Pasta","pasta")// we can add emoji in this way unlike first one
                                            .withDescription("Americano white sauce pasta")
                                            .withEmoji(Emoji.fromUnicode("\uD83C\uDF5D")))
                                    .addOptions(SelectOption.of("Pizza","pizza")// we can add emoji in this way unlike first one
                                            .withDescription("Classic Margheritta with veggies")
                                            .withEmoji(Emoji.fromUnicode("\uD83C\uDF55")))
                                    .build()
                    )
                    .setEphemeral(true)
                    .queue();

        }
        else if(event.getName().equals("highfive")){
            event.reply("Choose the user to give high-five")
                    .addActionRow(
                            EntitySelectMenu.create("choose-user", EntitySelectMenu.SelectTarget.USER)
                                    .build()
                    ).queue();
        }
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if(event.getComponentId().equals("choose-food")){
            event.reply("You chose " + event.getValues().get(0)).queue();
        }
    }

    @Override
    public void onEntitySelectInteraction(EntitySelectInteractionEvent event) {
        if(event.getComponentId().equals("choose-user")){
            List<User> users = event.getMentions().getUsers();
            event.reply("You just high-fived "+users.get(0).getAsMention()).queue();
        }
    }
}
