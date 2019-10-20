import java.sql.Date;

import javax.print.attribute.DateTimeSyntax;



/**
 * This class has all of the parts of purchase table.
 *
 * @author jmartin
 */


public class Purchase {



	private int iD;

	private String itemID;

	private int quantity;

	private DateTimeSyntax ps;
	Date p;

	

	

	public Purchase( String itemID, int quantity) {

	

		this.itemID=itemID;

		this.quantity=quantity;

	

		

	}

	public Purchase(String itemID, int quantity,  Date p) {



		this.itemID=itemID;

		this.quantity=quantity;

		this.p=p;

		

	}


	public Purchase(int Id, String itemID, int quantity,  Date p) {

		this.iD= Id;

		this.itemID=itemID;

		this.quantity=quantity;

		this.p=p;

		

	}



	public int getiD() {

		return iD;

	}



	public String getItemCode() {

		return itemID;

	}







	

	public String toString(){

        return String.format("(%s, %s, %s, %s)", iD, itemID, quantity, p);

    }



	public int getQuantity() {

		return quantity;

	}



}



