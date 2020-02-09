import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

/*
    Invite Link: https://discordapp.com/api/oauth2/authorize?client_id=668230837090648086&permissions=49544262&scope=bot
 */

public class HentaiBot extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(token);
        builder.addEventListeners(new Commands());
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("xHelp"));
        builder.build();
    }
}