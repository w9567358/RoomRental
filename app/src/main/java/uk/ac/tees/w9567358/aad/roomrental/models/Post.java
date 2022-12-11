package uk.ac.tees.w9567358.aad.roomrental.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post implements Parcelable{

    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "attribute")
    private String attribute;
    @ColumnInfo(name = "sq")
    private String sq;
    @ColumnInfo(name = "bedCount")
    private String bedCount;
    @ColumnInfo(name = "rentOrSale")
    private String rentOrSale;
    @ColumnInfo(name = "bathCount")
    private String bathCount;
    @ColumnInfo(name = "mImageUrl")
    private String imageUrl;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "latitude")
    private String latitude;
    @ColumnInfo(name="longitude")
    private String longitude;

    @PrimaryKey(autoGenerate = true)
    private int pid ;

    public Post(){

    }



    public Post(String phone, String description, String attribute, String sq, String bedCount, String rentOrSale, String bathCount, String imageUrl, String price,String location,String latitude, String longitude) {
        this.phone = phone;
        this.description = description;
        this.attribute = attribute;
        this.sq = sq;
        this.bedCount = bedCount;
        this.rentOrSale = rentOrSale;
        this.bathCount = bathCount;
        this.imageUrl = imageUrl;
        this.price = price;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    protected Post(Parcel in) {
        phone = in.readString();
        description = in.readString();
        attribute = in.readString();
        sq = in.readString();
        bedCount = in.readString();
        rentOrSale = in.readString();
        bathCount = in.readString();
        imageUrl = in.readString();
        price = in.readString();
        location=in.readString();
        latitude = in.readString();
        longitude = in.readString();
        pid = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public static Creator<Post> getCREATOR() {
        return CREATOR;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getSq() {
        return sq;
    }

    public void setSq(String sq) {
        this.sq = sq;
    }

    public String getBedCount() {
        return bedCount;
    }

    public void setBedCount(String bedCount) {
        this.bedCount = bedCount;
    }

    public String getRentOrSale() {
        return rentOrSale;
    }

    public void setRentOrSale(String rentOrSale) {
        this.rentOrSale = rentOrSale;
    }

    public String getBathCount() {
        return bathCount;
    }

    public void setBathCount(String bathCount) {
        this.bathCount = bathCount;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phone);
        dest.writeString(description);
        dest.writeString(attribute);
        dest.writeString(sq);
        dest.writeString(bedCount);
        dest.writeString(rentOrSale);
        dest.writeString(bathCount);
        dest.writeString(imageUrl);
        dest.writeString(price);
        dest.writeInt(pid);
    }
}
