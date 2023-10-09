import dev.overlord.events.HelloEvent;
import dev.overlord.events.PingPongEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Bot {
    public static void main(String args[]) throws Exception{

        JDA jda = JDABuilder.createDefault("MTE1OTQ0ODk2ODMyNjAyMTEyMA.G-lVea.zAYeXT9wICLHAOhOvyVgzOEkSGsJXSYXZHTTEo")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                //.setMemberCachePolicy(MemberCachePolicy.ALL).
                .build();


        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new PingPongEvent());
    }

}
