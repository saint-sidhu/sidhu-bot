package dev.overlord.slashcommands;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandManager extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if(command.equals("welcome")){
            //run the '/welcome' command
            String usertag = event.getMember().getEffectiveName();
            event.reply("Welcome to the server,** "+usertag+"**").queue();
        }
        else if(command.equals("farts")){
            String usertag = event.getMember().getEffectiveName();
            String channelName = event.getChannel().getAsMention();
            event.reply("**"+usertag+"**, just let a bomb fall in "+channelName).queue();
        }
        else if(command.equals("say")){
            TextChannel channel;
            OptionMapping message = event.getOption("message");
            OptionMapping channelName = event.getOption("name");
            event.deferReply().setEphemeral(true).queue();
            event.getHook().sendMessage("I have delivered your message").queue();
            if(channelName != null){
                channel = channelName.getAsChannel().asTextChannel();
                channel.sendMessage("Albedo has got some message from "+event.getMember().getEffectiveName()+".\n" +
                        message.getAsString()).queue();
            }
            else{
                event.getChannel().sendMessage("Albedo has got some message from "+event.getMember().getEffectiveName()+".\n\n" +
                        message.getAsString()).queue();
            }

        }
        else if(command.equals("roles")){
            //display all roles in the server
            event.deferReply().queue();
            String response ="";
            for(Role role:event.getGuild().getRoles()){
                response += role.getAsMention() + " \n"; //gets the role as @Admin,@Mod , etc
            }
            event.getHook().sendMessage(response).queue();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        //This is for when bot is already running and the is present in the guild as well
        List<CommandData> commandData = new ArrayList<>();
        /*
        if(event.getGuild().getIdLong() == 724947044128653342L){
            commandData.add(Commands.slash("farts","farts out loud"));
        }

         */
        commandData.add(Commands.slash("farts","farts out loud"));
        commandData.add(Commands.slash("welcome","welcomes the user"));
        commandData.add(Commands.slash("say","say something")
                .addOption(OptionType.STRING,"message","send a message",true)
                .addOption(OptionType.CHANNEL,"name","name of the channel, you want your text in",false));
        commandData.add(Commands.slash("roles","gives all roles of the server"));
        event.getGuild().updateCommands().addCommands(commandData).queue();

    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        //This is for when the bot is already running and we want to add it to a new guild
        List<CommandData> commands = new ArrayList<>();
        commands.add(Commands.slash("welcome","welcomes the user"));
        commands.add(Commands.slash("farts","farts out loud"));
        commands.add(Commands.slash("roles","gives all roles of the server"));
        event.getGuild().updateCommands().addCommands(commands).queue();
    }

}
