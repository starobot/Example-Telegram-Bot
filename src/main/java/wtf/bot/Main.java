package wtf.bot;

import net.staro.bot.api.Bot;
import net.staro.bot.api.BotFactory;
import net.staro.bot.api.bus.EventBus;
import net.staro.bot.api.command.CommandRegistry;
import net.staro.bot.api.response.ResponseManager;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import wtf.bot.commands.HelpCommand;
import wtf.bot.commands.TestCommand;

public class Main {
    private static final String BOT_NAME = "example_bot";
    private static final String BOT_TOKEN = ""; // Enter your own token.

    public static void main(String[] args) throws TelegramApiException {
        // register a new bot using the default command prefix "/".
        BotFactory.registerNewTelegramBot(BOT_NAME, BOT_TOKEN);

        // In case you are wondering where to take an instance of a Bot object.
        Bot bot = BotFactory.getBot();
        EventBus eventBus = bot.eventBus();
        eventBus.subscribe(new CustomService());

        // Create an instance of a new custom registry class and initialize the commands.
        new CustomCommandRegistry().initialize(bot);

        // A lazy way to register responses. One might also make a class that implements ResponseRegistry
        ResponseManager.RESPONSES.add(() -> "Here's a command that might help you /help");
        eventBus.post(new InitEvent(System.currentTimeMillis()));
    }

    // A custom registry class for commands.
    private static class CustomCommandRegistry implements CommandRegistry {
        @Override
        public void initialize(Bot bot) {
            register(new HelpCommand(bot));
            register(new TestCommand(bot));
        }

    }

    public record InitEvent(long time) {}

}