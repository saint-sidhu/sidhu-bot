package dev.overlord.slashcommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SlashCommandManager extends ListenerAdapter {
    static boolean filterOnOff = false;
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
                channel.sendMessage("Albedo has got some message from "+event.getMember().getEffectiveName()+".\n\n" +
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
        else if(command.equals("greet")){
            if(event.getUser().getEffectiveName().equals("Sidhu")){
                event.reply("**All hail Overlord-sama** \n" +
                        "I as the overseer of all guardians ,pledge you my loyalty forever.\n" +
                        "Master , what made you come down here today?").queue();
            }
            else if(event.getUser().getEffectiveName().equals("Harshu\uD83D\uDC9C")){
                event.reply(event.getUser().getEffectiveName()+", how is your day going?\n" +
                        "By the way,Master was looking for you, he is in his chamber. Have a good day!!").queue();
            }
            else{
                event.reply("**Greetings from my Lord**\n" +
                        "How may I help you today,"+ event.getUser().getEffectiveName()+"?").queue();
            }
        }
        else if(command.equals("calculate")){
            OptionMapping operator = event.getOption("operator");
            OptionMapping num1 = event.getOption("num1");
            OptionMapping num2 = event.getOption("num2");
            String reply = "";

            switch (operator.getAsString()){
                case "add" ->{

                    reply = String.valueOf(num1.getAsInt() + num2.getAsInt());
                    event.reply(reply).queue();
                }

                case "sub" ->{
                    reply = String.valueOf(num1.getAsInt()- num2.getAsInt());
                    event.reply(reply).queue();
                }

                case "mul" ->{
                    reply = String.valueOf(num1.getAsInt()* num2.getAsInt());
                    event.reply(reply).queue();
                }

                case "div" ->{
                    if(num2.getAsInt() !=0){
                        reply = String.valueOf(num1.getAsDouble()/ num2.getAsDouble());
                        event.reply(reply).queue();
                    }

                    else{
                        reply = "Even toddlers know that you can't divide a number by zero.\uD83D\uDE44";
                        event.reply(reply).queue();
                    }
                }
                default ->{
                    reply ="I have no knowledge of this operator";
                    event.reply(reply).queue();
                }
            }
        }
        else if(command.equals("invite")){
            //write some code to access subcommand (create)
            if(event.getSubcommandName().equals("create")){
                event.deferReply().setEphemeral(true).queue();
                int timer =3600;
                int hour =timer/3600;
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Link up or Fade away");
                //eb.setTitle("Link up or Fade away",event.getChannel().asTextChannel().createInvite().setMaxAge(timer).complete().getUrl());
                eb.setImage("https://imgs.search.brave.com/wbZ1tgMsBj72EvR6NNVuwTSzk8T0PSgb3_g9qZrjN94/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJjYXZlLmNv/bS93cC93cDE5NzYw/MTQuanBn");
                eb.setAuthor("ALBEDO SENDS HER WARM REGARDS");
                eb.setThumbnail(event.getGuild().getIconUrl());
                eb.setColor(Color.RED);
                eb.setDescription("Welcome to a realm of unwavering authority and unyielding command. " +
                        "This invite serves as your ultimate chance to step into the shadows and become part of our enigmatic assembly. " +
                        "The message is clear: this is your last warning. Take the link, embrace our dominion, or vanish into obscurity." +
                        "The consequences of hesitating are not to be taken lightly. Join now or face the repercussions of your defiance.\n" +
                        event.getChannel().asTextChannel().createInvite().setMaxAge(timer).complete().getUrl()+"\n\nThis link will expire in "
                        +hour +" hour.");
                eb.setFooter("Death by sword. Death by broken bones. Death by crushing. " +
                        "There's not much difference, right? You die at the end.");
                event.getHook().sendMessageEmbeds(eb.build()).queue();
            }
        }

        else if(command.equals("toggle")){
            if(event.getSubcommandName().equals("filter")){

               if(!filterOnOff){
                    event.deferReply().queue();
                    EmbedBuilder eb1 = new EmbedBuilder();
                    eb1.setTitle("PROFANITY FILTER HAS BEEN ENABLED");
                    eb1.setDescription("With the profanity filter enabled, we maintain a realm of respect and decorum, befitting " +
                            event.getGuild().getName()+"'s honor." +
                            "Vulgarities are prohibited and the offenders will be silenced, ensuring a dignified environment.");
                    eb1.setColor(Color.RED);
                    eb1.setThumbnail(event.getGuild().getIconUrl());
                    event.getHook().sendMessageEmbeds(eb1.build()).queue();
                    filterOnOff =true;
               }
               else if(filterOnOff){
                    event.deferReply().queue();
                    EmbedBuilder eb2 = new EmbedBuilder();
                    eb2.setTitle("PROFANITY FILTER HAS BEEN DISABLED");
                    eb2.setDescription("The profanity filter has been deactivated,allowing more expressive communication. However, " +
                            "remember that we shall not tolerate any slurs or disrespect against the principles of " + event.getGuild().getName()+
                            " and those who transgress may find themselves facing the full weight of our judgement.");
                    eb2.setColor(Color.GREEN);
                    eb2.setThumbnail(event.getGuild().getIconUrl());
                    event.getHook().sendMessageEmbeds(eb2.build()).queue();
                    filterOnOff = false;
               }

            }
        }
        else if(command.equals("find")){
            if(event.getSubcommandName().equals("me")){
                event.reply("Right Click on any user>> Click on apps >> Click find me")
                        .setEphemeral(true).queue();
            }
        }
    }
    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event){
        if(event.getName().equals("find me")){
            event.deferReply().queue();
            event.getHook().sendMessage("The name of the person is "+event.getTarget().getEffectiveName()+" and " +
                    "their avatar is:\n "+ event.getTarget().getEffectiveAvatarUrl()).queue();
        }

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        boolean badWord = false;
        String[] LIST_OF_BAD_WORDS = {"4r5e", "5h1t", "5hit", "a55", "anal", "anus", "ar5e", "arrse", "arse", "ass", "ass-fucker", "asses", "assfucker", "assfukka", "asshole", "assholes", "asswhole", "a_s_s", "b!tch", "b00bs", "b17ch", "b1tch", "ballbag", "balls", "ballsack", "bastard", "beastial", "beastiality", "bellend", "bestial", "bestiality", "bi+ch", "biatch", "bitch", "bitcher", "bitchers", "bitches", "bitchin", "bitching", "bloody", "blow job", "blowjob", "blowjobs", "boiolas", "bollock", "bollok", "boner", "boob", "boobs", "booobs", "boooobs", "booooobs", "booooooobs", "breasts", "buceta", "bugger", "bum", "bunny fucker", "butt", "butthole", "buttmuch", "buttplug", "c0ck", "c0cksucker", "carpet muncher", "cawk", "chink", "cipa", "cl1t", "clit", "clitoris", "clits", "cnut", "cock", "cock-sucker", "cockface", "cockhead", "cockmunch", "cockmuncher", "cocks", "cocksuck", "cocksucked", "cocksucker", "cocksucking", "cocksucks", "cocksuka", "cocksukka", "cok", "cokmuncher", "coksucka", "coon", "cox", "crap", "cum", "cummer", "cumming", "cums", "cumshot", "cunilingus", "cunillingus", "cunnilingus", "cunt", "cuntlick", "cuntlicker", "cuntlicking", "cunts", "cyalis", "cyberfuc", "cyberfuck", "cyberfucked", "cyberfucker", "cyberfuckers", "cyberfucking", "d1ck", "damn", "dick", "dickhead", "dildo", "dildos", "dink", "dinks", "dirsa", "dlck", "dog-fucker", "doggin", "dogging", "donkeyribber", "doosh", "duche", "dyke", "ejaculate", "ejaculated", "ejaculates", "ejaculating", "ejaculatings", "ejaculation", "ejakulate", "f u c k", "f u c k e r", "f4nny", "fag", "fagging", "faggitt", "faggot", "faggs", "fagot", "fagots", "fags", "fanny", "fannyflaps", "fannyfucker", "fanyy", "fatass", "fcuk", "fcuker", "fcuking", "feck", "fecker", "felching", "fellate", "fellatio", "fingerfuck", "fingerfucked", "fingerfucker", "fingerfuckers", "fingerfucking", "fingerfucks", "fistfuck", "fistfucked", "fistfucker", "fistfuckers", "fistfucking", "fistfuckings", "fistfucks", "flange", "fook", "fooker", "fuck", "fucka", "fucked", "fucker", "fuckers", "fuckhead", "fuckheads", "fuckin", "fucking", "fuckings", "fuckingshitmotherfucker", "fuckme", "fucks", "fuckwhit", "fuckwit", "fudge packer", "fudgepacker", "fuk", "fuker", "fukker", "fukkin", "fuks", "fukwhit", "fukwit", "fux", "fux0r", "f_u_c_k", "gangbang", "gangbanged", "gangbangs", "gaylord", "gaysex", "goatse", "God", "god-dam", "god-damned", "goddamn", "goddamned", "hardcoresex", "hell", "heshe", "hoar", "hoare", "hoer", "homo", "hore", "horniest", "horny", "hotsex", "jack-off", "jackoff", "jap", "jerk-off", "jism", "jiz", "jizm", "jizz", "kawk", "knob", "knobead", "knobed", "knobend", "knobhead", "knobjocky", "knobjokey", "kock", "kondum", "kondums", "kum", "kummer", "kumming", "kums", "kunilingus", "l3i+ch", "l3itch", "labia", "lust", "lusting", "m0f0", "m0fo", "m45terbate", "ma5terb8", "ma5terbate", "masochist", "master-bate", "masterb8", "masterbat*", "masterbat3", "masterbate", "masterbation", "masterbations", "masturbate", "mo-fo", "mof0", "mofo", "mothafuck", "mothafucka", "mothafuckas", "mothafuckaz", "mothafucked", "mothafucker", "mothafuckers", "mothafuckin", "mothafucking", "mothafuckings", "mothafucks", "mother fucker", "motherfuck", "motherfucked", "motherfucker", "motherfuckers", "motherfuckin", "motherfucking", "motherfuckings", "motherfuckka", "motherfucks", "muff", "mutha", "muthafecker", "muthafuckker", "muther", "mutherfucker", "n1gga", "n1gger", "nazi", "nigg3r", "nigg4h", "nigga", "niggah", "niggas", "niggaz", "nigger", "niggers", "nob", "nob jokey", "nobhead", "nobjocky", "nobjokey", "numbnuts", "nutsack", "orgasim", "orgasims", "orgasm", "orgasms", "p0rn", "pawn", "pecker", "penis", "penisfucker", "phonesex", "phuck", "phuk", "phuked", "phuking", "phukked", "phukking", "phuks", "phuq", "pigfucker", "pimpis", "piss", "pissed", "pisser", "pissers", "pisses", "pissflaps", "pissin", "pissing", "pissoff", "poop", "porn", "porno", "pornography", "pornos", "prick", "pricks", "pron", "pube", "pusse", "pussi", "pussies", "pussy", "pussys", "rectum", "retard", "rimjaw", "rimming", "s hit", "s.o.b.", "sadist", "schlong", "screwing", "scroat", "scrote", "scrotum", "semen", "sex", "sh!+", "sh!t", "sh1t", "shag", "shagger", "shaggin", "shagging", "shemale", "shi+", "shit", "shitdick", "shite", "shited", "shitey", "shitfuck", "shitfull", "shithead", "shiting", "shitings", "shits", "shitted", "shitter", "shitters", "shitting", "shittings", "shitty", "skank", "slut", "sluts", "smegma", "smut", "snatch", "son-of-a-bitch", "spac", "spunk", "s_h_i_t", "t1tt1e5", "t1tties", "teets", "teez", "testical", "testicle", "tit", "titfuck", "tits", "titt", "tittie5", "tittiefucker", "titties", "tittyfuck", "tittywank", "titwank", "tosser", "turd", "tw4t", "twat", "twathead", "twatty", "twunt", "twunter", "v14gra", "v1gra", "vagina", "viagra", "vulva", "w00se", "wang", "wank", "wanker", "wanky", "whoar", "whore", "willies", "willy", "xrated", "xxx"};
        String [] message = event.getMessage().getContentRaw().split(" ");
        if(filterOnOff && event.isFromGuild()){
            for (String s : message) {
                for (String listOfBadWord : LIST_OF_BAD_WORDS) {
                    if (s.equals(listOfBadWord)) {
                        badWord = true;
                        break;
                    }
                }
                if (badWord)
                    break;
            }
            if(badWord && !event.getAuthor().getEffectiveName().equalsIgnoreCase("Sidhu")) {
                event.getMessage().reply("Insolent human! Your vulgarities are an affront to the supreme leader." +
                        " Cease your filth or face the wrath of our disdain.").queue();
                event.getMember().timeoutFor(Duration.ofSeconds(30)).queue();
            }
            else if(badWord && event.getAuthor().getEffectiveName().equalsIgnoreCase("Sidhu")){
                event.getMessage().reply("Forgive my directness, Supreme One, but your choice of words is...unusual for someone of your stature. May I inquire as to the reason for such language? Your commands are my utmost priority, " +
                        "and I am here to assist you in any way possible. If there is anything amiss, please do not hesitate to share.").queue();
            }
        }
    }

}
