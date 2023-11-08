import dev.overlord.commands.*;
import dev.overlord.events.HelloEvent;
import dev.overlord.events.PingPongEvent;
import dev.overlord.slashcommands.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
//import dev.overlord.events.ChannelEvent;

import javax.security.auth.login.LoginException;


public class Bot {

    private final ShardManager shardManager;
    private final Dotenv config;

    public Bot() throws LoginException{
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setActivity(Activity.playing("with Broski's mom"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_PRESENCES,GatewayIntent.GUILD_VOICE_STATES);
        //builder.setMemberCachePolicy(MemberCachePolicy.ONLINE);
        //builder.setChunkingFilter(ChunkingFilter.ALL);//on startup it will cache all online members rather than lazy loading
        //builder.enableCache(CacheFlag.ACTIVITY,CacheFlag.VOICE_STATE,CacheFlag.ONLINE_STATUS);
        shardManager = builder.build();

        //Register listeners
        shardManager.addEventListener(new CommandRegistrar());
        shardManager.addEventListener(new ButtonsTesting());
        shardManager.addEventListener(new ModalCommandManager());
        shardManager.addEventListener(new SlashCommandManager());
        shardManager.addEventListener(new HelloEvent());
        shardManager.addEventListener(new PingPongEvent());
        //shardManager.addEventListener(new ChannelEvent());
        shardManager.addEventListener(new CalculateCommand());
        shardManager.addEventListener(new GreetCommand());
        shardManager.addEventListener(new InviteCommand());
        shardManager.addEventListener(new FilterCommand());
        shardManager.addEventListener(new WelcomeEvent());
        shardManager.addEventListener(new ModMail());
        shardManager.addEventListener(new DropdownMenu());

        //shardManager.addEventListener(new ActivityCacheEvent()); --> turn it on and and turn on the cache intents too
    }
    public ShardManager getShardManager(){
        return shardManager;
    }
    public Dotenv getConfig(){
        return config;
    }
    public static void main(String args[]) {

        try{
            Bot Albedo = new Bot();
        }
        catch(LoginException e){
            System.out.println("ERROR: INVALID BOT TOKEN PROVIDED");
        }

/*
        JDA jda = JDABuilder.createDefault("")
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("with Broski's mom"))
                //.setMemberCachePolicy(MemberCachePolicy.ALL).
                .build().awaitReady();

        Guild guild = jda.getGuildById("");//-Bot Testing GuildID
        if(guild != null){
            guild.upsertCommand("fart","Farts really hard").queue();
            guild.upsertCommand("add","This does additions")
                    .addOption(OptionType.INTEGER,"num1","first number",true)
                    .addOption(OptionType.INTEGER,"num2","second number",true).queue();
            guild.upsertCommand("say","send a message to the chat")
                    .addOption(OptionType.STRING,"message","type the message you want to send",true)
                    .addOption(OptionType.CHANNEL,"name","name of the channel you " +
                                    "want to send message in", false).queue();
        }

        //Command: /calculate add <num1> <num2>

        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(Commands.slash
                ("calculate","This does calculations")
                .addOption(OptionType.SUB_COMMAND_GROUP,"sub","this does subtraction",true)
                .addOption(OptionType.SUB_COMMAND_GROUP,"mul","this does multiplication",true)
                .addOption(OptionType.SUB_COMMAND_GROUP,"div","this does division",true)).queue();



        jda.addEventListener(new ActionSlashCommand(),new SlashCommandManager());
        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new PingPongEvent());
        //jda.addEventListener(new ChannelEvent());
        jda.addEventListener(new CalculateCommand());
        jda.addEventListener(new GreetCommand());
        jda.addEventListener(new InviteCommand());
        jda.addEventListener(new FilterCommand());

 */

    }
}
