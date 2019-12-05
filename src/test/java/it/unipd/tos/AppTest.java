////////////////////////////////////////////////////////////////////
// MARCEL JUNIOR WANDJI 1171044
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private ArrayList<MenuItem> list;
    private App myApp;

    public AppTest() {
        myApp = new App();
    }
    
    @Before
    public void before() {
        list = new ArrayList<MenuItem>();
    }
    /**
     * Dato un elenco di ordinazioni (Panini e Fritti e Bevande) calcolare il totale
     */
    @Test
    public void test_totaleElencoOrdineDiPaniniFrittiBevande() throws TakeAwayBillException {

         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",3.5));
         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",3.5));
         list.add(new MenuItem(MenuItem.ItemType.Fritto,"arancino",2.5));
         list.add(new MenuItem(MenuItem.ItemType.Bevande,"fanta",1.5));

         assertEquals(11,myApp.getOrderPrice(list),0);
    }
    
    /**
     *  Se vengono ordinati pi� di 5 Panini viene fatto uno sconto del 50% sul prezzo 
     *  del panino meno caro
     */
    @Test
    public void test_OrdineCon5PaniniScontoDi50SuPaninoMenoCaro() throws TakeAwayBillException {

        for(int i=0; i<6; i++) {
            list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",3.5));
        }

        assertEquals(21-1.75,myApp.getOrderPrice(list),0);
    }

    /**
     *  Se l�importo totale delle ordinazioni (Panini e Fritti) supera i 50 euro viene fatto 
     *  il 10% disconto
     */
    @Test
    public void test_scontodi10SeLimportoTotaleDiPanini_e_FrittiSuperaI50() throws TakeAwayBillException {

         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",20));
         list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",20));
         list.add(new MenuItem(MenuItem.ItemType.Fritto,"arancino",15));
         list.add(new MenuItem(MenuItem.ItemType.Fritto,"arancino",15));

         assertEquals(70-7,myApp.getOrderPrice(list),0);
    }
    
    /**
     * Non � possibile avere un�ordinazione con pi� di 30 elementi (se accade prevedere un 
     * messaggio d�errore
     */
    @Test(expected = TakeAwayBillException.class)
    public void test_OrdineConPiuDi30Elementi() throws TakeAwayBillException {

        for (int i=0; i<31; i++)
            list.add(new MenuItem(MenuItem.ItemType.Panino,"vegetariano",20));
        myApp.getOrderPrice(list);

       /* try {
            myApp.getOrderPrice(list);
        } catch (TakeAwayBillException expected) {
            expected.printStackTrace();
        }*/
    }
}