package utilities.drawers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static utilities.drawers.DrawerUtils.drawCenteredText;

public class MessageDrawer
{
    public static final Map<String, Long> messagesWIthTheirExpirationTime = new ConcurrentHashMap<>();

    public static void drawMessage()
    {
        AtomicInteger messageY = new AtomicInteger(11);

        messagesWIthTheirExpirationTime.forEach((message, timeAdded) ->
        {
            drawCenteredText(message, messageY.get(), false, 12F);
            if ((System.currentTimeMillis() - timeAdded) / 1000 > 3)
            {
                messagesWIthTheirExpirationTime.remove(message);
            }
            messageY.addAndGet(-1);
        });
    }

    public static void addMessage(String text)
    {
        messagesWIthTheirExpirationTime.put(text, System.currentTimeMillis());
    }
}
