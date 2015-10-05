package com.flochaz.noeudnoeud;

import android.app.Application;
import android.content.ContentProvider;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.RenamingDelegatingContext;

import com.flochaz.noeudnoeud.database.DatabaseHandler;
import com.flochaz.noeudnoeud.database.Item;
import com.flochaz.noeudnoeud.database.ItemProvider;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ItemProviderTest extends ProviderTestCase2<ItemProvider> {

    public ItemProviderTest() {
        super(ItemProvider.class, ItemProvider.class.getName());
    }

    protected void setUp() throws Exception {
        super.setUp();
    }





    public void testDatabaseCreated() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        DatabaseHandler dbHandler = new DatabaseHandler(context);
        int expected = 2;
        int actual = dbHandler.getColumns();
        assertEquals(expected, actual);
    }

    public void testSinglePutItem() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        DatabaseHandler dbHandler = new DatabaseHandler(context);
        String expectedName1 = "salt";
        Item item1 = new Item(expectedName1);
        assertTrue(dbHandler.putItem(item1));
        Item actual = dbHandler.getItem(1);
        assertEquals(expectedName1, actual.name);
        assertEquals(1, dbHandler.getNumOfItems());

        String expectedName2 = "pepper";
        Item item2 = new Item(expectedName2);
        assertTrue(dbHandler.putItem(item2));
        actual = dbHandler.getItem(2);
        assertEquals(expectedName2, actual.name);
        assertEquals(2, dbHandler.getNumOfItems());

    }

    public void testDuplicatePutItem() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        DatabaseHandler dbHandler = new DatabaseHandler(context);
        String expectedName1 = "salt";
        Item item1 = new Item(expectedName1);
        assertTrue(dbHandler.putItem(item1));
        assertTrue(dbHandler.putItem(item1));
        Item actual = dbHandler.getItem(1);
        assertEquals(expectedName1, actual.name);
        assertEquals(1, dbHandler.getNumOfItems());
    }

    public void testRemoveItem() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        DatabaseHandler dbHandler = new DatabaseHandler(context);
        String expectedName1 = "salt";
        Item item1 = new Item(expectedName1);
        assertTrue(dbHandler.putItem(item1));

        String expectedName2 = "pepper";
        Item item2 = new Item(expectedName2);
        assertTrue(dbHandler.putItem(item2));

        assertEquals(1, dbHandler.removeItem(item1));
        Item actual = dbHandler.getItem(2);
        assertEquals(expectedName2, actual.name);
        assertEquals(1, dbHandler.getNumOfItems());

        assertEquals(1, dbHandler.removeItem(item2));
        actual = dbHandler.getItem(2);
        assertNull(actual);
        assertEquals(0, dbHandler.getNumOfItems());
    }
}