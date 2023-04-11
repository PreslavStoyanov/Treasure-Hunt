package assets;

import assets.entities.objects.StorableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utilities.drawers.InventoryWindowDrawer.INVENTORY_COLS;
import static utilities.drawers.MessageDrawer.addMessage;

public class Inventory
{
    private static final int CAPACITY = 20;
    public int inventorySlotCursorCol = 0;
    public int inventorySlotCursorRow = 0;
    private final List<StorableObject> storage;

    public Inventory()
    {
        this(new ArrayList<>());
    }

    public Inventory(List<StorableObject> storage)
    {
        this.storage = storage;
    }


    public List<StorableObject> getStorage()
    {
        return storage;
    }

    public boolean haveSpace()
    {
        if (storage.size() == CAPACITY)
        {
            addMessage("Inventory storage is full!");
            return false;
        }
        return true;
    }

    public void add(StorableObject object)
    {
        storage.add(object);
    }

    public boolean isItemInStorage(EntityType requiredItemType)
    {
        return storage.stream().anyMatch(item -> item.type.equals(requiredItemType));
    }

    public Optional<StorableObject> getItemOnCurrentSlot()
    {
        try
        {
            return Optional.of(storage.get(getInventoryItemIndex()));
        }
        catch (IndexOutOfBoundsException e)
        {
            return Optional.empty();
        }
    }

    public boolean ifPresentRemoveItemByType(EntityType type)
    {
        Optional<StorableObject> item = getFirstItemByType(type);
        if (item.isPresent())
        {
            storage.remove(item.get());
            return true;
        }
        return false;
    }

    public void removeAllItems()
    {
        storage.clear();
    }

    private Optional<StorableObject> getFirstItemByType(EntityType requiredItemType)
    {
        return storage.stream().filter(item -> item.type.equals(requiredItemType)).findFirst();
    }

    private int getInventoryItemIndex()
    {
        return inventorySlotCursorCol + inventorySlotCursorRow * INVENTORY_COLS;
    }
}
