/**
 * 
 */
package flipcache.IO;

/**
 * @author bhavesh
 *
 */
public class Output {
	String prodName;
	String prodDesc;
	boolean success;
	
	public Output() {
		this.success=false;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
}
