package dev.overlord.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CalculateCommand extends ListenerAdapter {

    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        String message[] = event.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("/calculate") && message.length>1){

            if(message[1].equalsIgnoreCase("add")){
                event.getChannel().sendMessage(Integer.toString((Integer.parseInt(message[2])+Integer.parseInt(message[3])))).queue();
            }
            else if(message[1].equalsIgnoreCase("sub")){
                event.getChannel().sendMessage(Integer.toString((Integer.parseInt(message[2])-Integer.parseInt(message[3])))).queue();
            }
            else if(message[1].equalsIgnoreCase("mul")){
                event.getChannel().sendMessage(Integer.toString((Integer.parseInt(message[2])*Integer.parseInt(message[3])))).queue();
            }
            else if(message[1].equalsIgnoreCase("div")){
                if(!message[3].equalsIgnoreCase("0")){
                    event.getChannel().sendMessage(Double.toString((Double.parseDouble(message[2])/Double.parseDouble(message[3])))).queue();
                }
                else{
                    event.getChannel().sendMessage("Even toddlers know that you can't divide a number by zero.\uD83D\uDE44").queue();
                }
            }
            else {
                event.getChannel().sendMessage("Don't be a dick, and use add/sub/mul/div keywords only \uD83D\uDE11 ").queue();
            }
        }
        else{
            if(message[0].equalsIgnoreCase("/calculate") || message[0].equalsIgnoreCase("calculate")){
                event.getChannel().sendMessage
                        ("Were you looking to use this calculate command? \nType(without brackets): /calculate [add/sub/mul/div] [first-num] [second-num]").queue();
            }
        }
    }
}
