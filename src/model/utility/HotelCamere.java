package model.utility;

import java.util.ArrayList;
import java.util.List;

import model.Camera;
import model.Hotel;

public class HotelCamere {
	
	private Hotel hotel;
	private List<Camera> camere=new ArrayList<>();
	
	public HotelCamere(Hotel h) {
		hotel=h;
	}
	
	public void addCamera(Camera c) {
		camere.add(c);
	}
	
	public void removeCamera(Camera c) {
		camere.remove(c);
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public List<Camera> getCamere() {
		return camere;
	}
	public void setCamere(List<Camera> camere) {
		this.camere = camere;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camere == null) ? 0 : camere.hashCode());
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelCamere other = (HotelCamere) obj;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		return true;
	}
	
}
