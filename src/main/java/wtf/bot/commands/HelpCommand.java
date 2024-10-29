package wtf.bot.commands;

import net.staro.bot.api.Bot;
import net.staro.bot.api.command.Builder;
import net.staro.bot.api.command.impl.BotCommand;

import static net.staro.bot.api.command.CommandMap.COMMANDS;

public class HelpCommand extends BotCommand {
    public HelpCommand(Bot bot) {
        super(bot, "help");
    }

    @Override
    public void build(Builder builder) {
        builder.execute(message -> {
            StringBuilder stringBuilder = new StringBuilder();
            COMMANDS.values().forEach(command -> stringBuilder.append(bot.commandManager().getPrefix())
                    .append(command.getName())
                    .append("\n"));
            return stringBuilder.toString();
        });
    }

    @Override
    public boolean hasPermisions() {
        return false;
    }

}
