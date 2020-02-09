import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.List;

public class Commands extends ListenerAdapter {
    Boolean chat = true;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        String msg = args[0].toLowerCase();
        Member member = event.getMember();
        TextChannel channel = event.getChannel();
        String prefix = "x";
        MessageHistory history = new MessageHistory(channel);
        List<Message> msgs;
        String rawMessage = event.getMessage().getContentRaw();
        String mentionedUser = event.getMember().getAsMention();

        if(msg.equals(prefix + "server")){
            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("Help Menu");
            help.setDescription("*Personal bot for the discord server: Hentai Gods.*");
            help.addField("Fun", "?", false);
            help.addField("AutoBot", "?", false);
            help.addField("IRL", "?", false);
            help.addField("Hentai", "?", false);
            help.addField("Moderation", "?", false);
            help.setImage("https://images-ext-2.discordapp.net/external/rk1VPqo1ZSerVHZ4yZr_ISX0fdTYSfRK1ZM8jkJSlhQ/https/imagizer.imageshack.us/v2/xq90/924/n17BXD.jpg?width=720&height=450");
            help.setColor(0x0000FF);
            help.setFooter("Created by Zain(HentaiGod#8584)", event.getMember().getUser().getAvatarUrl());
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(help.build()).queue();
            help.clear();
        }

        //Stop Advertising
        if(rawMessage.startsWith("https://discord.gg/")){
            event.getMessage().delete().queue();
            channel.sendMessage(mentionedUser + "! Please Do Not Advertise!").queue();
        }

        //Purge Messages
        if(msg.startsWith(prefix + "purge") && member.hasPermission(Permission.MESSAGE_MANAGE)){
            int amount = Integer.parseInt(args[1]);
            msgs = history.retrievePast(amount).complete();
            if(amount <= 100){
                for(int i = 0; i < amount; i++){
                    channel.deleteMessages(msgs).queue();
                }
            }
            else{
                channel.sendMessage("Amount exceeds 100! Try again.").queue();
            }
        }

        //Help Menu
        if(msg.equals(prefix + "help")){
                EmbedBuilder help = new EmbedBuilder();
                help.setTitle("Help Menu");
                help.setDescription("*Personal bot for the discord server: Hentai Gods.*");
                help.addField("Fun", "`8Ball`", false);
                help.addField("AutoBot", "?", false);
                help.addField("IRL", "?", false);
                help.addField("Hentai", "?", false);
                help.addField("Moderation", "`Purge #`", false);
                help.setImage("https://images-ext-2.discordapp.net/external/rk1VPqo1ZSerVHZ4yZr_ISX0fdTYSfRK1ZM8jkJSlhQ/https/imagizer.imageshack.us/v2/xq90/924/n17BXD.jpg?width=720&height=450");
                help.setColor(0x0000FF);
                help.setFooter("Created by HentaiGod#8584", "https://images-ext-1.discordapp.net/external/d5CqxU_5uTLf-7D8p9xfLBJ4Sq5zdSHqe6ptyFW5Y3c/%3Fsize%3D2048/https/cdn.discordapp.com/avatars/668230837090648086/1f80e33fb0336665495906cf3b81c373.png?width=676&height=676");
                event.getChannel().sendMessage(help.build()).queue();
                help.clear();
        }

        //8 Ball
        if(msg.startsWith(prefix + "8ball")){
                int rand = (int) (Math.random() * 15) + 1;

                if(rand == 1){
                    event.getChannel().sendMessage("It is certain.").queue();
                }
                else if(rand == 2){
                    event.getChannel().sendMessage("It is decidedly so.").queue();
                }
                else if(rand == 3){
                    event.getChannel().sendMessage("Without a doubt.").queue();
                }
                else if(rand == 4){
                    event.getChannel().sendMessage("Yes - definitely.").queue();
                }
                else if(rand == 5){
                    event.getChannel().sendMessage("You may rely on it.").queue();
                }
                else if(rand == 6){
                    event.getChannel().sendMessage("Reply hazy, try again.").queue();
                }
                else if(rand == 7){
                    event.getChannel().sendMessage("Better not tell you now.").queue();
                }
                else if(rand == 8){
                    event.getChannel().sendMessage("Cannot predict now.").queue();
                }
                else if(rand == 9){
                    event.getChannel().sendMessage("Concentrate and ask again.").queue();
                }
                else if(rand == 10){
                    event.getChannel().sendMessage("Ask again later.").queue();
                }
                else if(rand == 11){
                    event.getChannel().sendMessage("Don't count on it.").queue();
                }
                else if(rand == 12){
                    event.getChannel().sendMessage("My reply is no.").queue();
                }
                else if(rand == 13){
                    event.getChannel().sendMessage("My sources say no.").queue();
                }
                else if(rand == 14){
                    event.getChannel().sendMessage("Outlook not so good.\n").queue();
                }
                else if(rand == 15){
                    event.getChannel().sendMessage("Very doubtful.").queue();
                }
            }

        //Ping
        if(msg.equals(prefix + "ping")){
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!").queue(response -> {
                response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
            });
        }

        /*if(rawMessage.equals(prefix + "chat true")) {
            this.chat = true;
            channel.sendMessage(chat + " Bot can now chat.").queue();
        }

        if(rawMessage.equals(prefix + "chat false")) {
            this.chat = false;
            channel.sendMessage(chat + " Bot can now chat.").queue();
        }

        if(this.chat = true && rawMessage.startsWith("hi") || rawMessage.startsWith("hey") ||
                rawMessage.startsWith("hello")){
            System.out.println(chat);
            channel.sendMessage("Hiii").queue();
        }
        */
    }
}
