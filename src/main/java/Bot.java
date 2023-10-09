import dev.overlord.events.HelloEvent;
import dev.overlord.events.PingPongEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Bot {
    public static void main(String args[]) throws Exception{

        JDA jda = JDABuilder.createDefault("YOUR_BOT_TOKEN_HERE")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                //.setMemberCachePolicy(MemberCachePolicy.ALL).
                .build();


        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new PingPongEvent());
    }

}
