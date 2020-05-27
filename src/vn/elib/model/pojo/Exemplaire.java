/**
 * 
 */
package vn.elib.model.pojo;

/**
 * @author franel
 *
 */
public class Exemplaire {

	private int id;
	private Rfid rfid;
	
	/**
	 * @param id
	 * @param rfid
	 */
	public Exemplaire(int id, Rfid rfid) {
		this.id = id;
		this.rfid = rfid;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the rfid
	 */
	public Rfid getRfid() {
		return rfid;
	}
	/**
	 * @param rfid the rfid to set
	 */
	public void setRfid(Rfid rfid) {
		this.rfid = rfid;
	}
	
	
}
