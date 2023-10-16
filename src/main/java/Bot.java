import dev.overlord.events.ChannelEvent;
import dev.overlord.events.HelloEvent;
import dev.overlord.events.PingPongEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
    public static void main(String args[]) throws Exception{

        JDA jda = JDABuilder.createDefault("MTE1OTQ0ODk2ODMyNjAyMTEyMA.GYBoTP.Dww-M24Xrnpz1p9viTtiQ_cOaQc56uZ5S8-LUA")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                //.setMemberCachePolicy(MemberCachePolicy.ALL).
                .build();


        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new PingPongEvent());
        jda.addEventListener(new ChannelEvent());
    }

}
