package utilities.drawers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static utilities.drawers.DrawerUtils.drawCenteredText;

public class MessageDrawer
{
    public static final Map<String, Long> messagesWIthTheirExpirationTime = new ConcurrentHashMap<>();

    public static void drawMessage()
    {
        float messageY = 11.5F;

        for (Map.Entry<String, Long> entry : messagesWIthTheirExpirationTime.entrySet())
        {
            String message = entry.getKey();
            Long timeAdded = entry.getValue();
            drawCenteredText(message, messageY, false, 12F);
            if ((System.currentTimeMillis() - timeAdded) / 1000 > 3)
            {
                messagesWIthTheirExpirationTime.remove(message);
            }
            messageY += 1;
        }
    }

    public static void addMessage(String text)
    {
        messagesWIthTheirExpirationTime.put(text, System.currentTimeMillis());
    }
}
