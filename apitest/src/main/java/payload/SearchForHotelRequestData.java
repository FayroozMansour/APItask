package payload;

import java.sql.Date;
import java.time.LocalDate;

public class SearchForHotelRequestData {



	   LocalDate  fromdate;
	   LocalDate  todate;
	   String   hotel;

	   public LocalDate getfromdate() {
		return fromdate;
	}
	public void setfromdate(LocalDate checkInDate) {
		this.fromdate = checkInDate;
	}

	public String gethotel() {
		return hotel;
	}
	public LocalDate getTodate() {
		return todate;
	}
	public void setTodate(LocalDate todate) {
		this.todate = todate;
	}
	public void sethotel(String hotel) {
		this.hotel = hotel;
	}
	
	}

	