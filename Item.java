/**
 * This class has all of the parts of the item table.
 *
 * @author jmartin
 */


public class Item {

	private int iD;

	private String itemCode;

	private String itemDescription;

	private double price;

	

	

	public Item( String itemCode, String itemDescription, double price) {



		this.itemCode=itemCode;

		this.itemDescription=itemDescription;

		this.price=price;

		

	}

	

	public Item(int iD, String itemCode, String itemDescription, double price) {

		this.iD=iD;

		this.itemCode=itemCode;

		this.itemDescription=itemDescription;

		this.price=price;

		

	}



	public int getiD() {

		return iD;

	}



	public String getItemCode() {

		return itemCode;

	}



	public String getItemDescription() {

		return itemDescription;

	}



	public double getPrice() {

		return price;

	}

	public String toString(){

        return String.format("(%s, %s, %s, %s)", iD, itemCode, itemDescription, price);

    }



}