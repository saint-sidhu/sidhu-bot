package dev.overlord.slashcommands;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistrar extends ListenerAdapter {

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        //This is for when the bot is already running and we want to add it to a new guild
        List<CommandData> commands = new ArrayList<>();
        commands.add(Commands.slash("welcome","welcomes the user"));
        commands.add(Commands.slash("farts","farts out loud"));
        commands.add(Commands.slash("roles","gives all roles of the server"));

        //say a message in any channel chat
        OptionData channelName = new OptionData(OptionType.CHANNEL,"name","name of the channel, you want your text in",false)
                .setChannelTypes(ChannelType.TEXT,ChannelType.NEWS,ChannelType.GUILD_PUBLIC_THREAD);
        OptionData message = new OptionData(OptionType.STRING,"message","send a message",true);

        commands.add(Commands.slash("say","say something")
                .addOptions(message,channelName));

        //calculate command
        OptionData operatorOption = new OptionData(OptionType.STRING,"operator","name of the operation",true)
                .addChoice("add","add")
                .addChoice("sub","sub")
                .addChoice("mul","mul")
                .addChoice("div","div");
        OptionData num1 = new OptionData(OptionType.INTEGER,"num1","the first number",true);
        OptionData num2 = new OptionData(OptionType.INTEGER,"num2","the second number",true);

        commands.add(Commands.slash("calculate","Albedo helps a dumb user")
                .addOptions(operatorOption,num1,num2));

        //invite people
        SubcommandData subcommandInvite = new SubcommandData("create","invite link");
        commands.add(Commands.slash("invite","Albedo invites you").addSubcommands(subcommandInvite));

        //toggle filter on/off
        SubcommandData subcommandFilter = new SubcommandData("filter","Albedo filters your foul lang when toggled on");
        commands.add(Commands.slash("toggle","toggles something").addSubcommands(subcommandFilter));

        SubcommandData subcommandMe = new SubcommandData("me","Albedo finds the userName and the");
        commands.add(Commands.slash("find","find the user").addSubcommands(subcommandMe));

        //context menus
        commands.add(Commands.context(Command.Type.USER,"find me"));

        //Modals for name, message
        commands.add(Commands.slash("wassup","say what's up"));

        //Buttons-Hello
        commands.add(Commands.slash("hello","Button testing"));

        commands.add(Commands.slash("support","support for your queries"));

        //Dropdown --> StringSelect
        commands.add(Commands.slash("food","Dropdown of some food items to choose from"));
        //Dropdown --> EntitySelect
        commands.add(Commands.slash("highfive","High-Five another peasant present here"));

        event.getGuild().updateCommands().addCommands(commands).queue();
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        //This is for when bot is already running and the is present in the guild as well
        List<CommandData> commandData = new ArrayList<>();
        //option data for some commands


        commandData.add(Commands.slash("farts","farts out loud"));
        commandData.add(Commands.slash("welcome","welcomes the user"));
        commandData.add(Commands.slash("roles","gives all roles of the server"));

        //say a message in any channel chat
        OptionData channelName = new OptionData(OptionType.CHANNEL,"name","name of the channel, you want your text in",false)
                .setChannelTypes(ChannelType.TEXT,ChannelType.NEWS,ChannelType.GUILD_PUBLIC_THREAD);
        OptionData message = new OptionData(OptionType.STRING,"message","send a message",true);

        commandData.add(Commands.slash("say","say something")
                .addOptions(message,channelName));

        //calculate command
        OptionData operatorOption = new OptionData(OptionType.STRING,"operator","name of the operation",true)
                .addChoice("add","add")
                .addChoice("sub","sub")
                .addChoice("mul","mul")
                .addChoice("div","div");
        OptionData num1 = new OptionData(OptionType.INTEGER,"num1","the first number",true);
        OptionData num2 = new OptionData(OptionType.INTEGER,"num2","the second number",true);

        commandData.add(Commands.slash("calculate","Albedo helps a dumb user")
                .addOptions(operatorOption,num1,num2));

        //invite people
        SubcommandData subcommandInvite = new SubcommandData("create","invite link");
        commandData.add(Commands.slash("invite","Albedo invites you").addSubcommands(subcommandInvite));

        //toggle filter on/off
        SubcommandData subcommandFilter = new SubcommandData("filter","Albedo filters your foul lang when toggled on");
        commandData.add(Commands.slash("toggle","toggles something").addSubcommands(subcommandFilter));

        SubcommandData subcommandMe = new SubcommandData("me","Albedo finds the userName and the avatar");
        commandData.add(Commands.slash("find","find the user").addSubcommands(subcommandMe));

        //context menus
        commandData.add(Commands.context(Command.Type.USER,"find me"));

        //Modals for name, message
        commandData.add(Commands.slash("wassup","say what's up"));


        //Buttons-Hello
        commandData.add(Commands.slash("hello","Button testing"));

        commandData.add(Commands.slash("support","support for your queries"));

        //Dropdown --> StringSelect
        commandData.add(Commands.slash("food","Dropdown of some food items to choose from"));
        //Dropdown --> EntitySelect
        commandData.add(Commands.slash("highfive","High-Five another peasant present here"));


        event.getGuild().updateCommands().addCommands(commandData).queue();

    }


    @Override
    public void onReady(ReadyEvent event) {
        //For global commands which can be used everywhere/globally
        List<CommandData> commands = new ArrayList<>();
        commands.add(Commands.slash("greet","Albedo greets the user"));
        event.getJDA().updateCommands().addCommands(commands).queue();
    }
}
