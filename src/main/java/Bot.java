import dev.overlord.commands.CalculateCommand;
import dev.overlord.commands.FilterCommand;
import dev.overlord.commands.GreetCommand;
import dev.overlord.commands.InviteCommand;
import dev.overlord.events.ChannelEvent;
import dev.overlord.events.HelloEvent;
import dev.overlord.events.PingPongEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String args[]) throws LoginException {

        JDA jda = JDABuilder.createDefault("YOUR_BOT_TOKEN_HERE")
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT).setActivity(Activity.playing("with Broski's mom"))
                //.setMemberCachePolicy(MemberCachePolicy.ALL).
                .build();

        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new PingPongEvent());
        //jda.addEventListener(new ChannelEvent());
        jda.addEventListener(new CalculateCommand());
        jda.addEventListener(new GreetCommand());
        jda.addEventListener(new InviteCommand());
        jda.addEventListener(new FilterCommand());

    }
}
