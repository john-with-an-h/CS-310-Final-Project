import java.sql.Date;
/**
 * This class has all of the parts of shipment table.
 *
 * @author jmartin
 */

public class Shipment {





	private int iD;

	private String itemID;

	private int quantity;

	private Date date;

	

	public Shipment( String itemID, int quantity, Date date) {

		

		this.itemID=itemID;

		this.quantity=quantity;

		this.date=date;

		

	}

	public Shipment(int iD, String itemID, int quantity, Date date) {

		this.iD=iD;

		this.itemID=itemID;

		this.quantity=quantity;

		this.date=date;

		

	}



	public int getiD() {

		return iD;

	}



	public String getItemID() {

		return itemID;

	}





	public Date getDate() {

		return date;

	}









	public String toString(){

        return String.format("(%s, %s, %s, %s)", iD, itemID, quantity, date);

    }



	public int getQuantity() {

		return quantity;

	}



}



