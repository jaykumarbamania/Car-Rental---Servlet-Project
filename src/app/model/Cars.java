package app.model;

public class Cars {
	private int carId;
	private String carNo, carName, carMaker, carModel, carColor;
	private int carAvailable;

	
	public Cars() {
		
	}
	
	
	
	public Cars(int carId, String carNo, String carName, String carMaker, String carModel, String carColor,
			int carAvailable) {
		super();
		this.carId = carId;
		this.carNo = carNo;
		this.carName = carName;
		this.carMaker = carMaker;
		this.carModel = carModel;
		this.carColor = carColor;
		this.carAvailable = carAvailable;
	}



	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarMaker() {
		return carMaker;
	}
	public void setCarMaker(String carMaker) {
		this.carMaker = carMaker;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public int getCarAvailable() {
		return carAvailable;
	}
	public void setCarAvailable(int carAvailable) {
		this.carAvailable = carAvailable;
	}
	@Override
	public String toString() {
		return "Cars [carNo=" + carNo + ", carName=" + carName + ", carMaker=" + carMaker + ", carModel=" + carModel
				+ ", carColor=" + carColor + ", carAvailable=" + carAvailable + "]";
	}
	
	
}


