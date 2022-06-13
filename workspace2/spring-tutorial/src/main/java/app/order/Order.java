package app.order;

public class Order {
	private Long memberId; // 주문자
	private String itemName; // 상품이름
	private int itemPrice; // 상품가격
	private int itemCount; // 상품갯수
	private int shippngFee; // 배송비
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public int getShippngFee() {
		return shippngFee;
	}
	public void setShippngFee(int shippngFee) {
		this.shippngFee = shippngFee;
	}
	
	
}
