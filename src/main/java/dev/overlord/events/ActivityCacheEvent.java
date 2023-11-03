package dev.overlord.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;
import java.util.List;

public class ActivityCacheEvent extends ListenerAdapter {
    static List<Member> memberList = new ArrayList<>();
    @Override
    public void onUserActivityStart(UserActivityStartEvent event) {

        //System.out.println(event.getMember().getActivities());
        List<Activity> activityList;
        activityList = event.getMember().getActivities();

        for(Activity activity:activityList){
            if(activity.getName().equals("Counter-Strike 2")){
                memberList.add(event.getMember());
                System.out.println(event.getMember().getEffectiveName()+" is playing " +activity.getName());

                //bot should connect to vc

                VoiceChannel csSQUAD1Voice = event.getGuild().getVoiceChannelById("802177960450850816");
                AudioManager audioManager = event.getGuild().getAudioManager();
                audioManager.openAudioConnection(csSQUAD1Voice);


            }
        }
        //RichPresence:VALORANT(applicationId=700136079562375258)
        //[RichPresence:Counter-Strike 2(applicationId=1158877933042143272)]


    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {

        /*
        Guild guild = event.getGuild();
        AudioChannel vc = event.getChannelJoined();
        System.out.println(vc.getIdLong());
        System.out.println(event.getMember().getEffectiveName());

         */
    }


}
