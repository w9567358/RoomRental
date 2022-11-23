package uk.ac.tees.w9567358.aad.roomrental;

public class HouseModel {
    String houseId, noOfRoom, rentPerRoom, houseDescription, houseLocation, houseImage,ownerContact, userId;

    public String getHouseImage() {
        return houseImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHouseImage(String houseImage) {
        this.houseImage = houseImage;
    }

    public String getownerContact() {
        return ownerContact;
    }
    public void setownerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }


    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getNoOfRoom() {
        return noOfRoom;
    }

    public void setNoOfRoom(String noOfRoom) {
        this.noOfRoom = noOfRoom;
    }

    public String getRentPerRoom() {
        return rentPerRoom;
    }

    public void setRentPerRoom(String rentPerRoom) {
        this.rentPerRoom = rentPerRoom;
    }

    public String getHouseDescription() {
        return houseDescription;
    }

    public void setHouseDescription(String houseDescription) {
        this.houseDescription = houseDescription;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }

    public HouseModel() {
    }

    public HouseModel(String houseId, String noOfRoom, String rentPerRoom, String houseDescription, String houseLocation, String houseImage, String userId) {
        this.houseId = houseId;
        this.noOfRoom = noOfRoom;
        this.rentPerRoom = rentPerRoom;
        this.houseDescription = houseDescription;
        this.houseLocation = houseLocation;
        this.houseImage = houseImage;
        this.userId = userId;
    }
}
